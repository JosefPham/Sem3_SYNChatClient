package Acquaintance;

/**
 *
 * @author Group 9
 */
public interface IPresentation {

    public void injectBusiness(IBusiness bus);

    public void startApplication(String[] args);

    public void receivePublicMsg(IMessage msg);

    public void sendPublicMsg(String s);

    public String getContext();
    
    public int getSenderID();
    
    public void logoutHandling(String logout);
}
