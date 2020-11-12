import java.io.BufferedReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;

public class MessageReceiver extends Thread {
    ArrayList<BufferedReader> readers;
    Server mainServer;

    public MessageReceiver()
    {

    }
    public MessageReceiver(Server mainServer, ArrayList<BufferedReader> readers)
    {
        this.readers=readers;
        this.mainServer=mainServer;
    }

    public void run() {
        String message;
        try {
            while(true) {
                for(BufferedReader in : readers)
                {
                    message=in.readLine();
                    mainServer.pushMessage(message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

