package Business;


import Acquaintance.IFriends;
import Acquaintance.IMessage;
import Acquaintance.IProfile;
import Acquaintance.IUser;
import Acquaintance.Nationality;
import Acquaintance.IPrivateChat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User implements IUser {

    Management management;
    ClientSystem client = ClientSystem.getInstance();

    private int userID;
    private boolean banned; // a flag for if the user is banned
    private int reports;    // the amount of reprts a user have received
    private Map<Integer, String> chats;
    private IFriends friends;
    private IProfile profile;
    private Map<Integer,PrivateChat> activePrivateChats = new HashMap<Integer, PrivateChat>();

    public User(String firstName, String lastName, Nationality nationality, String profileText) {
        profile = new Profile(firstName, lastName, nationality, "");
    }

    public User(int userID, boolean banned, int reports, Map<Integer, String> chats, IFriends friends, IProfile profile) {
        this.userID = userID;
        this.banned = banned;
        this.reports = reports;
        this.chats = chats;
        IFriends finalFriends = new Friends(friends.getFriendlist());
        this.friends = finalFriends;
        IProfile finalProfile = new Profile(profile.getFirstName(), profile.getLastName(), profile.getNationality(), profile.getProfileText());
        this.profile = finalProfile;
    }

    @Override
    public int getUserID() {
        return userID;
    }

    @Override
    public boolean isBanned() {
        return banned;
    }

    @Override
    public int getReports() {
        return reports;
    }

    @Override
    public Map<Integer, String> getChats() {
        return chats;
    }
    
    @Override
    public IProfile getProfile() {
        return profile;
    }

    public int changePw(String oldPw, String newPw) {
        management = new Management(2, client.getCurrentUser().getUserID(), client.hash(oldPw), client.hash(newPw));
        return BusinessFacade.getInstance().sendChangePw(management);
    }

    public int changeMail(String pw, String newMail) {
        management = new Management(1, client.getCurrentUser().getUserID(), client.hash(pw), client.hash(newMail));
        return BusinessFacade.getInstance().sendChangeMail(management);
    }

    /**
     * method for adding a frind, it checks if the frind is alredy in the frindslist. and then ask the server to add the fring as well. At this point it also creates a chat with that user, this may be a contention of change in a later iteration.
     * @param friendID  the user id of the frind i question
     * @param profileName the profile name of the friend
     * @return 
     */
    public boolean addFriend(int friendID, String profileName) {
        if (friends.addFriend(userID, profileName)) {
            createChat(userID, profileName + " " + profile.getFirstName() + " " + profile.getLastName());
            return updateFriends(friends);
        } else {
            return true;
        }
    }

    public boolean removeFriend(int userID) {
        friends.removeFriend(userID);
        return updateFriends(friends);
    }

    boolean updateFriends(IFriends friends) {
        return BusinessFacade.getInstance().updateFriends(friends);
    }
    

    IPrivateChat loadPrivateChat(int chatID){
       throw new UnsupportedOperationException("this method shuld fetch private chat form server with many 10 messages in chathistory");
    }

    @Override
    public IFriends getFriends() {
        return friends;
    }
    /**
     * method for creating a new chat with a user
     * @param frindID   id on the user that the chat is initiated with
     * @param name name of the chat
     */
    void createChat(int frindID,String name){
        IPrivateChat newChat = new PrivateChat(this.userID, frindID, name);
        BusinessFacade.getInstance().sendPrivateMessage(newChat);
        
    }
   /**
    * method for adding a chat to the chatsmap when reciving a new chat privateChat object on the stream
    * @param newChat 
    */ 
    void addPrivateChat(IPrivateChat newChat){
        chats.put(newChat.getChatID(), newChat.getName());
        
    }
    
    void sendPrivateMessage(int chatID, IMessage msg){
        activePrivateChats.get(chatID).sendMessage(msg);
    }

}
