package Acquaintance;

import java.io.Serializable;
import java.util.Map;

public interface IFriends extends Serializable {

    Map<Integer, String> getFriendlist();

}
