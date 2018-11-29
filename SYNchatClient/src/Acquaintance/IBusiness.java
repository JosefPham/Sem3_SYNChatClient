package Acquaintance;

import java.util.Map;

/**
 *
 * @author Group 9
 */
public interface IBusiness {

    void injectConnection(IConnection con);

    void injectPresentation(IPresentation pres);

    /**
     * Receives unhashed mail and password.
     * Hashes mail and password.
     * Creates new login object.
     * Sends object to server.
     * Returns int based on success.
     * @param String
     * @param String
     * @return int
     */
    int login(String mail, String pw);

    Boolean regUser(String firstName, String lastName, String mail, String pw, Nationality nationality);

    /**
     * Starts thread for user
     * connected to public chat.
     * While loop checks for object types
     * and call methods corresponding to
     * the correct object types.
     */
    void publicThreads();

    void privateThreads();

    void receivePublicMsg(IMessage msg);

    /**
     * hashes message and sends it to the server
     * @param String
     */
    void sendPublicMsg(String s);

    /**
     * Sends a string to server to handle commands
     * such as logout, entering chats etc.
     * @param String 
     */
    void commandHandling(String command);

    void connect();

    boolean addFriend(int userID);

    boolean removeFriend(int userID);

    IUser getUser();

    boolean updateUserInfo(String pw, String mail, String firstName, String lastName, Nationality nationality, String profileText, String picture);

    /**
     * Map with all public chat users
     * @param Map 
     */
    void userMap(Map<Integer, IUser> userMap);

    void publicUser(IUser pUser);

    boolean checkPW(String pw);

    boolean checkMail(String mail);
}
