import java.util.*;
public class Message {
    private int fromClient;
    private int toClient;
    private String message;
    public static final int msgToAll=-100;
    public static final int msgToServer=-200;
    public Message()
    {

    }
    public Message(String input)//Used to parse a single string into the fromClient, toClient and the message.
    {
        String[] brokenMsg=input.split("#");
        System.out.println(brokenMsg[2]);
        initMessage(Integer.parseInt(brokenMsg[0]),checkMsgToAllOrServer(brokenMsg[1]),brokenMsg[2]);
    }
    public int checkMsgToAllOrServer(String toClient)
    {
        if(toClient.equalsIgnoreCase("all"))
            return Message.msgToAll;
        else if(toClient.equalsIgnoreCase("server"))
            return Message.msgToServer;
        else
            return Integer.parseInt(toClient);
    }
    public void initMessage(int fromClient, int toClient, String message)
    {
        this.fromClient=fromClient;
        this.toClient=toClient;
        this.message=message;
    }
    public int getSender()
    {
        return this.fromClient;
    }
    public int getReceiver()
    {
        return this.toClient;
    }
    public String getMessage()
    {
        return this.message;
    }
}
