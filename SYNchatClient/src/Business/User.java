package Business;

import Acquaintance.IUser;
import java.util.List;

public class User implements IUser {

    Management management;
    ClientSystem client = ClientSystem.getInstance();

    private int userID;
    private String tmpName; //must be removed when profile is implemented
    private boolean banned; // a flag for if the user is banned
    private int reports;    // the amount of reprts a user have received
    private List<Integer> chats;
    private Friends friends;

    public User(String tmpName) {
        this.tmpName = tmpName;
    }

    public User(int userID, String tmpName, boolean banned, int reports, List<Integer> chats, Friends friends) {
        this.userID = userID;
        this.tmpName = tmpName;
        this.banned = banned;
        this.reports = reports;
        this.chats = chats;
        this.friends = friends;
    }

    @Override
    public int getUserID() {
        return userID;
    }

    @Override
    public String getTmpName() {
        return tmpName;
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
    public List<Integer> getChats() {
        return chats;
    }

    public int verifyPw(String pw) {
        management = new Management(1, client.hash(pw));
        return BusinessFacade.getInstance().sendVerifyPw(management);
    }

    public int changePw(String oldPw, String newPw) {
        management = new Management(2, client.hash(oldPw), client.hash(newPw));
        return BusinessFacade.getInstance().sendChangePw(management);
    }

    public int changeMail(String pw, String newMail) {
        management = new Management(3, client.hash(pw), client.hash(newMail));
        return BusinessFacade.getInstance().sendChangeMail(management);
    }

    public boolean addFriend(int userID, String profileName) {
        if (friends.addFriend(userID, profileName)) {
            return updateFriends(friends);
        } else {
            return true;
        }
    }

    public boolean removeFriend(int userID) {
        friends.removeFriend(userID);
        return updateFriends(friends);
    }

    boolean updateFriends(Friends friends) {
        return BusinessFacade.getInstance().updateFriends(friends);
    }

}
