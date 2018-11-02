package Acquaintance;

/**
 *
 * @author Group 9
 */
public interface IBusiness {

    public void injectConnection(IConnection con);

    public void injectPresentation(IPresentation pres);

    public int login(String mail, String pw);

    public Boolean regUser(String tmpName, String mail, String pw);

    public void publicThreads();

    public void privateThreads();

    public void receivePublicMsg(String s);

    public void sendPublicMsg(String s);

    public boolean addFriend(int userID, String profileName);

    public boolean removeFriend(int userID);

    public boolean sendVerifyPw(int userID, String pw);

    public boolean sendChangePw(int userID, String newPw);

    public boolean sendChangeMail(int userID, String mail);
}
