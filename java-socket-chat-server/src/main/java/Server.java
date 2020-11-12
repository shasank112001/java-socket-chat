import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

public class Server {
    ServerSocket serverSocket;
    Stack<Message> msgBuffer;
    int clientCounter;
    ArrayList<BufferedReader> readerArray;
    ArrayList<ClientHandler> clientList;
    MessageSender serverMsgSender;
    {
        clientCounter=0;
        clientList=new ArrayList<ClientHandler>();
        readerArray=new ArrayList<BufferedReader>();
        msgBuffer=new Stack<>();
        serverMsgSender=new MessageSender(this);
    }
    public Server(int port) throws IOException
    {
        this.serverSocket=new ServerSocket(port);
        new MessageReceiver(this, this.readerArray).start();
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
            this.mainServer.addReader(this.in);
            this.mainServer.serverMsgSender.connectionEstablished(this.out);
        }

        public void run()
        {
            while(true)
            {
                if(this.mainServer.msgBuffer.isEmpty())
                    continue;
                else
                    out.println(msgBuffer.pop().toString());
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
                if(!msgBuffer.isEmpty())
                    System.out.println("MessageBuffer="+msgBuffer.peek());
        }
    }
    public void stop() throws IOException
    {
        this.serverSocket.close();
    }
    public void pushMessage(String msg)
    {
        this.msgBuffer.push(new Message(msg));
    }
    public void addReader(BufferedReader in)
    {
        readerArray.add(in);
    }
    public static void main(String[] args) throws IOException
    {
        new Server(6666).start();
    }

}
