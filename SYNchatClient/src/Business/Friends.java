package Business;

import Acquaintance.IFriends;
import java.util.ArrayList;
import java.util.List;

public class Friends implements IFriends {

    List<Integer> friendlist = new ArrayList<>();

    public Friends(IFriends friends) {
        this.friendlist = friends.getFriendlist();
    }

    public Friends(List<Integer> friendlist) {
        for (Integer key : friendlist) {
            this.friendlist.add(key);
        }
    }

    @Override
    public List<Integer> getFriendlist() {
        return friendlist;
    }

    public boolean addFriend(int userID) {
        if (!friendlist.contains(userID)) {
            friendlist.add(userID);
            return true;
        } else {
            return false;
        }
    }

    public void removeFriend(int userID) {
        friendlist.remove(userID);
    }
}
