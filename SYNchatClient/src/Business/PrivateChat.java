package Business;

import Acquaintance.IChatHistory;
import Acquaintance.IMessage;
import Acquaintance.IPrivateChat;
import java.util.ArrayList;
import java.util.List;

public class PrivateChat implements IPrivateChat {

    private List<Integer> userIDs = new ArrayList<>();
    private int chatID = -1;
    private String name;
    private ChatHistory ch;

    /**
     * For when creating a new private chat between 2 users
     *
     * @param userID
     * @param userIDagain
     * @param name
     */
    PrivateChat(int userID, int userIDagain, String name) {
        this.userIDs.add(userID);
        this.userIDs.add(userIDagain);
        this.name = name;
        this.ch = new ChatHistory(0, new Message(0) {
            @Override
            public String getContext() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }

    /**
     * for when loading an existing private chat object
     *
     * @param userIDs
     * @param chatID
     * @param name
     * @param ch
     */
    PrivateChat(List<Integer> userIDs, int chatID, String name, IChatHistory ch) {
        this.userIDs = userIDs;
        this.chatID = chatID;
        this.name = name;
        this.ch = new ChatHistory(ch.getMsgLoadedCount(), ch.getMsgList());
    }

    /**
     * constructor for sending a message, Change user id's to a list of id's
     * when adding functionality for multiple users in a chat
     *
     * @param userID
     * @param userIDagain
     * @param name
     * @param sendMsg
     */
    PrivateChat(int userID, int userIDagain, String name, IMessage sendMsg) {
        this.userIDs.add(userID);
        this.userIDs.add(userIDagain);
        this.name = name;
        this.ch = new ChatHistory(1, sendMsg);
    }

    void sendMessage(IMessage msg) {
        ch.addMessage(msg);
        IChatHistory sendAMsg = new ChatHistory(1, msg);
        IPrivateChat newMegPrivateChat = new PrivateChat(this.userIDs, this.chatID, this.name, sendAMsg);
        BusinessFacade.getInstance().sendPrivateMessage(newMegPrivateChat);
    }

    void receiveMessage(IMessage msg) {

    }

    boolean addUser(int userID) {
        throw new UnsupportedOperationException("Not implemented in this version.");
    }

    boolean removeUser(int userID) {
        throw new UnsupportedOperationException("Not implemented in this version.");
    }

    @Override
    public List<Integer> getUserIDs() {
        return userIDs;
    }

    @Override
    public int getChatID() {
        return chatID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public IChatHistory getCh() {
        return ch;
    }

}
