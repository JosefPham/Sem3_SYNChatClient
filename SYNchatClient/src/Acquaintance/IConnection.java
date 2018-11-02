package Acquaintance;

/**
 *
 * @author Group 9
 */
public interface IConnection {

    public ILogin login(ILogin ilogin);

    public Boolean regUser(ILogin ilogin);

    public IClient getClient();

    public void startPublicThreads();

    public void startPrivateThreads();

    public void receivePublicMsg(String s);

    public void sendPublicMsg(String s);

    public void injectBusiness(IBusiness bus);
    
    public void logoutHandling(String logout);
    
    public void connect();
}
