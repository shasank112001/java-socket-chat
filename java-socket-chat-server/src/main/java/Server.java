import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

public class Server {
    ServerSocket serverSocket;
    Stack<Message> msgBuffer;
    int clientCounter;
    ArrayList<ClientHandler> clientList;
    MessageSender serverMsgSender;
    {
        clientCounter=0;
        clientList=new ArrayList<ClientHandler>();
        msgBuffer=new Stack<>();
    }
    public Server(int port) throws IOException
    {
        this.serverSocket=new ServerSocket(port);
//        new MessageReceiver(this).start();
        serverMsgSender=new MessageSender(this);
        serverMsgSender.start();
    }

    class ClientHandler extends Thread
    {
        BufferedReader in;
        int clientId;
        PrintWriter out;
        Socket clientSocket;
        Server mainServer;

        public ClientHandler(Socket clientSocket, Server server) throws IOException
        {
            this.clientSocket=clientSocket;
            this.mainServer=server;
            this.clientId=mainServer.clientCounter++;
            this.out=new PrintWriter(clientSocket.getOutputStream(), true);
            this.in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.mainServer.clientList.add(this);
            this.mainServer.serverMsgSender.connectionEstablished(this.out);
        }

        public void run()
        {
            try {
                while (true)
                    new MessageReceiverClientBased(this, this.mainServer).startReceivingMessages();
            }
            catch(IOException ex)
            {

            }
        }
        public void close()        {
            this.close();
        }

    }
    public void start() throws IOException
    {
        while(true)
        {
                new ClientHandler(serverSocket.accept(), this).start();
                System.out.println("clientCounter="+clientCounter+" threadCount="+clientList);
        }
    }
    public void stop() throws IOException
    {
        this.serverSocket.close();
    }
    public void pushMessage(String msg)
    {
        System.out.println(msg+"   - was pushed into the stack");
        this.msgBuffer.push(new Message(msg));
    }
    public static void main(String[] args) throws IOException
    {
        new Server(6666).start();
    }

}
