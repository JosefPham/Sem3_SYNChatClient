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
    
    public boolean editProfileInfo(String firstName, String lastName, Nationality nationality, String profileText);
    
    public int changeMail(String pw, String newMail);
    
    public int changePw(String oldPw, String newPw);
    
    public void userMap(Map userMap);
    
    public void publicUser(IUser pUser);
}
