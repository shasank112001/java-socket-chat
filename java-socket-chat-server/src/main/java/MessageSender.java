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

    }

}
