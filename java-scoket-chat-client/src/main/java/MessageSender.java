import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class MessageSender {

    private Socket clientSocket;
    private PrintWriter out;

    public MessageSender(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        out = new PrintWriter(this.clientSocket.getOutputStream(), true);
    }

    public void sendMessage(String msg) throws IOException {
        out.println(msg);
    }
}
