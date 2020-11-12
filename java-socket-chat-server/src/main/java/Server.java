import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

public class Server {
    ServerSocket serverSocket;
    Stack<Message> msgBuffer;
    int clientCounter;
    ArrayList<ClientHandler> clientList;

    {
        clientCounter=0;
        clientList=new ArrayList<ClientHandler>();
        msgBuffer=new Stack<>();
    }
    public Server(int port) throws IOException
    {
        serverSocket=new ServerSocket(port);
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
            server.clientList.add(this);
        }

        public void run()
        {

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

}
