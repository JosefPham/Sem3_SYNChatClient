package Business;

import Acquaintance.IChatHistory;
import Acquaintance.IMessage;
import java.util.ArrayList;
import java.util.List;

public class PrivateChat {

    private List<Integer> userIDs = new ArrayList<>();
    private int chatID = -1;
    private String name;
    private IChatHistory ch;
    
    
    PrivateChat(int userID, int userIDagain, String name) {
        this.userIDs.add(userID);
        this.userIDs.add(userIDagain);
        this.name = name;
    }
    
    
    PrivateChat(List<Integer> userIDs, int chatID, String name, IChatHistory ch) {
        this.userIDs = userIDs;
        this.chatID = chatID;
        this.name = name;
        this.ch = ch;
    }
    
    
    boolean sendMessage(IMessage msg) {
        
    }
    
    boolean addUser(int userID) {
        throw new UnsupportedOperationException("Not implemented in this version.");
    }
    
    boolean removeUser(int userID) {
        throw new UnsupportedOperationException("Not implemented in this version.");
    }
    
}