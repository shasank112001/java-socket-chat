import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MessageReceiver extends Thread {

    private Socket clientSocket;
    private BufferedReader in;

    public MessageReceiver(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {

            while (true) {
                readMessages();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void readMessages() throws IOException {
        in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        System.out.println(in.readLine());
    }
}
