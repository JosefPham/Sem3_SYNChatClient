package Acquaintance;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IUser extends Serializable {

    int getUserID();

    boolean isBanned();

    int getReports();

    Map<Integer, String> getChats();

    IProfile getProfile();

    IFriends getFriends();

    
}
