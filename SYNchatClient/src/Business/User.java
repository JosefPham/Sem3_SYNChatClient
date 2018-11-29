package Business;

import Acquaintance.IFriends;
import Acquaintance.IProfile;
import Acquaintance.IUser;
import java.util.List;

/**
 *
 * @author Group 9
 */
public class User implements IUser {

    private int userID;
    private boolean banned; // a flag for if the user is banned
    private int reports;    // the amount of reprts a user have received
    private List<Integer> chats;
    private Friends friends;
    private IProfile profile;

    public User(IProfile profile) {
        this.profile = profile;
    }

    public User(IUser user) {
        this.profile = new Profile(user.getProfile());
        this.userID = user.getUserID();
        this.banned = user.isBanned();
        this.reports = user.getReports();
        this.chats = user.getChats();
        this.friends = new Friends(user.getFriends());
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
    public List<Integer> getChats() {
        return chats;
    }

    @Override
    public IProfile getProfile() {
        return profile;
    }

    public boolean addFriend(int userID) {
        if (friends.addFriend(userID)) {
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

    @Override
    public IFriends getFriends() {
        return friends;
    }

}
