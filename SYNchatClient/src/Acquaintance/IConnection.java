package Acquaintance;

import Business.Friends;

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

    public void sendPublicMsg(Object msg);

    public void injectBusiness(IBusiness bus);
    
    public void logoutHandling(String logout);
    
    public void connect();

    public boolean updateFriends(Friends friends);
}
