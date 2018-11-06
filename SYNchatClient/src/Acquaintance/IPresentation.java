package Acquaintance;

/**
 *
 * @author Group 9
 */
public interface IPresentation {

    public void injectBusiness(IBusiness bus);

    public void startApplication(String[] args);

    public void receivePublicMsg(String s);

    public void sendPublicMsg(String s);

    public String getS();
    
    public void logoutHandling(String logout);
    
    public IUser getUser();
    
    public boolean editProfileInfo(String firstName, String lastName, Nationality nationality, String profileText);
    
    public int changeMail(String pw, String newMail);
    
    public int changePw(String oldPw, String newPw);
}
