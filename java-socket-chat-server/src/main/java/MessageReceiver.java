import java.io.BufferedReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;

public class MessageReceiver extends Thread {
    Server mainServer;

    public MessageReceiver()
    {

    }
    public MessageReceiver(Server mainServer)
    {
        this.mainServer=mainServer;
    }

    public void run() {
        String message;
        try {
            while(true) {
                if(this.mainServer.clientList.isEmpty()) {
                    System.out.println("The array is empty as of now");
                    continue;
                }
                for(Server.ClientHandler client : this.mainServer.clientList)
                {
                    message=client.in.readLine();
                    mainServer.pushMessage(client.clientId+"#"+message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

