import java.util.*;
public class Message {
    private int fromClient;
    private int toClient;
    private String message;
    private static final int msgToAll=-100;

    public Message()
    {

    }
    public Message(String input)//Used to parse a single string into the fromClient, toClient and the message.
    {
        String[] brokenMsg=input.split("#");
        initMessage(Integer.parseInt(brokenMsg[0]),checkMsgToAll(brokenMsg[1]),brokenMsg[2]);
    }
    public int checkMsgToAll(String toClient)
    {
        if(toClient.equalsIgnoreCase("all"))
            return Message.msgToAll;
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
        return fromClient;
    }
    public int getReciever()
    {
        return toClient;
    }
}
