import java.io.BufferedReader;
import java.io.IOException;
import java.nio.Buffer;

public class MessageReciever extends Thread {
    BufferedReader in;
    Server mainServer;

    public MessageReciever()
    {

    }
    public MessageReciever(Server mainServer, BufferedReader in)
    {
        this.in=in;
        this.mainServer=mainServer;
    }
    public void run() {
        try {
            String mssg=in.readLine();
            mainServer.pushMessage(mssg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

