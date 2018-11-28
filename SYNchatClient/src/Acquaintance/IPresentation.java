package Acquaintance;

import java.util.Map;

/**
 *
 * @author Group 9
 */
public interface IPresentation {

    public void injectBusiness(IBusiness bus);

    public void startApplication(String[] args);

    public void receivePublicMsg(IMessage msg);

    public void sendPublicMsg(String s);

    public void commandHandling(String command);

    public IUser getUser();

    public boolean updateUserInfo(String pw, String mail, String firstName, String lastName, Nationality nationality, String profileText, String picture);

    public void userMap(Map<Integer, IUser> userMap);

    public void publicUser(IUser pUser);

    public boolean checkPW(String pw);

    public boolean checkMail(String mail);
}
