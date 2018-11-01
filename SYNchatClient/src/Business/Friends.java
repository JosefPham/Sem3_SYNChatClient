package Business;

import java.util.HashMap;
import java.util.Map;


public class Friends {
    
    Map<Integer,String> friendlist = new HashMap<Integer, String>();

    
    
    
    boolean addFriend(int userID, String profileName){
        if (!friendlist.containsKey(userID)){
            friendlist.put(userID, profileName);
            return true;}
        else{
            System.out.println("user is already in friendlist");
            return false;
        }
    }

    void removeFriend(int userID) {
        friendlist.remove(userID);
    }
}
