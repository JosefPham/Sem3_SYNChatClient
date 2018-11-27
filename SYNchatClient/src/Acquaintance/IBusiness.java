package Acquaintance;

import java.util.Map;

/**
 *
 * @author Group 9
 */
public interface IBusiness {

    public void injectConnection(IConnection con);

    public void injectPresentation(IPresentation pres);

    public int login(String mail, String pw);

    public Boolean regUser(String firstName, String lastName, String mail, String pw, Nationality nationality);

    public void publicThreads();

    public void privateThreads();

    public void receivePublicMsg(IMessage msg);

    public void sendPublicMsg(String s);

    public void commandHandling(String command);

    public void connect();

    public boolean addFriend(int userID);

    public boolean removeFriend(int userID);

    public IUser getUser();

    public boolean updateUserInfo(String pw, String mail, String firstName, String lastName, Nationality nationality, String profileText, String picture);

    public void userMap(Map<Integer, IUser> userMap);

    public void publicUser(IUser pUser);

    public boolean checkPW(String pw);

    public boolean checkMail(String mail);
}
