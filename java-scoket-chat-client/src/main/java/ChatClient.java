import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

    private PrintWriter out;
    private BufferedReader in;
    private Socket clientSocket;
    private final String serverIp = "localhost";
    private final int port = 6666;
    private boolean isRunning = true;
    private Scanner scanner;
    private MessageSender messageSender;

    public static void main(String[] args) throws IOException{
        ChatClient chatClient = new ChatClient();
        chatClient.start();
    }

    public void start() throws IOException {
        clientSocket = new Socket(serverIp, port);
        Scanner scan = new Scanner(System.in);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        new MessageReceiver(this.clientSocket).start();
        this.messageSender = new MessageSender(this.clientSocket);

        while(isRunning) {
            String message = scan.nextLine();

            if(message.equalsIgnoreCase("exit chat")) {
                this.isRunning = false;
                stop();
                break;
            }
            this.messageSender.sendMessage(message);
        }
    }

    public void stop() throws IOException {
        this.clientSocket.close();
        this.out.close();
        this.in.close();
    }
}
