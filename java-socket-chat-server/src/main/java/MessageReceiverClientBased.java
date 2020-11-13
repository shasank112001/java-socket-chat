import java.io.BufferedReader;
import java.io.IOException;

public class MessageReceiverClientBased {
    Server.ClientHandler client;
    Server mainServer;
    public MessageReceiverClientBased()
    {

    }
    public MessageReceiverClientBased(Server.ClientHandler client,Server mainServer)
    {
        this.client=client;
        this.mainServer=mainServer;
    }
    public void startReceivingMessages() throws IOException
    {
        while(true)
        {
            String message=client.in.readLine();
            this.mainServer.pushMessage(client.clientId+"#"+message);

        }
    }
}
