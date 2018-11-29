package Acquaintance;

import java.util.Map;

/**
 *
 * @author Group 9
 */
public interface IPresentation {

    void injectBusiness(IBusiness bus);

    void startApplication(String[] args);

    void receivePublicMsg(IMessage msg);

    void sendPublicMsg(String s);

    void commandHandling(String command);

    IUser getUser();

    boolean updateUserInfo(String pw, String mail, String firstName, String lastName, Nationality nationality, String profileText, String picture);

    void userMap(Map<Integer, IUser> userMap);

    void publicUser(IUser pUser);

    boolean checkPW(String pw);

    boolean checkMail(String mail);
}
