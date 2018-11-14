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

    public boolean addFriend(int userID, String profileName);

    public boolean removeFriend(int userID);

    public int changePw(String oldPw, String newPw);

    public int changeMail(String pw, String mail);

    public int sendChangePw(IManagement management);

    public int sendChangeMail(IManagement management);

    public IUser getUser();

    public boolean editProfileInfo(String firstName, String lastName, Nationality nationality, String profileText);

    public boolean updateProfile(IUser user);

    public void userMap(Map userMap);
    
    public void publicUser(IUser pUser);
}
