import java.io.PrintWriter;

public class MessageSender {
    Server mainServer;
    public MessageSender()
    {

    }
    public MessageSender(Server mainServer)
    {
        this.mainServer=mainServer;
    }

    public void connectionEstablished(PrintWriter out)
    {
        out.println("Connection Established. Welcome to the server");
    }
    public void sendMessage(int fromClientId,int toClientId, String message )
    {
        Server.ClientHandler receiver=getClient(toClientId);
        if(receiver!=null)
            receiver.out.println("Sender= Client number-"+fromClientId+" message=" +message);
        else
        {
            receiver=getClient(fromClientId);
            receiver.out.println("ERROR. Client cannot available. Message Cannot be sent.");
        }
    }
    public Server.ClientHandler getClient(int toClientId)
    {
        for (Server.ClientHandler client : mainServer.clientList)
        {
            if(client.clientId==toClientId)
                return client;
        }
        return null;
    }

}
