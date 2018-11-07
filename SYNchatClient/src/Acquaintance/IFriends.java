package Acquaintance;

import java.util.Map;

public interface IFriends {

    Map<Integer, String> getFriendlist();

    boolean addFriend(int userID, String profileName);
    
    void removeFriend(int userID);
}
