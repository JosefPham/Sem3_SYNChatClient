package Business;

import Acquaintance.IChatHistory;
import Acquaintance.IMessage;
import Acquaintance.ITextMessage;
import java.util.ArrayList;
import java.util.List;

public class ChatHistory implements IChatHistory {
    
    private int msgLoadedCount;
    List<IMessage> msgList;

    /**
     * used for when reciving an existing chatHistory object
     * @param msgLoadedCount
     * @param msgList 
     */
    public ChatHistory(int msgLoadedCount, List<IMessage> msgList) {
        this.msgLoadedCount = msgLoadedCount;
        this.msgList = new ArrayList<>();
        for(IMessage ms: msgList){
            this.msgList.add(ms);
        }
    }
    
    /**
     * For sending a messege in a private chat to the server
     * @param msgLoadedCount must be 1!
     * @param msg 
     */
    public ChatHistory(int msgLoadedCount, IMessage msg){
        this.msgLoadedCount = msgLoadedCount;
        msgList = new ArrayList<>();
        msgList.add(msg);
    }
    
    boolean addMessage(IMessage msg){
        if(msg instanceof ITextMessage){    // Remember to add an if for additional message types when theay are added to the system
            msgList.add(new TextMessage(msg.getSenderID(), msg.getContext()));
            msgLoadedCount++;
            return true;
        }else{
            return false;
            
        }
    }

    public int getMsgLoadedCount() {
        return msgLoadedCount;
    }

    public List<IMessage> getMsgList() {
        return msgList;
    }
    
    @Override
    public int loadMoreMessages() {
        throw new UnsupportedOperationException("For implementation in a later version");
    }
}