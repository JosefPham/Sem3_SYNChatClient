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
}
