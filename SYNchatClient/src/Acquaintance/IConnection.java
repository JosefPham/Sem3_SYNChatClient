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

    public void receivePublicMsg(IMessage msg);

    public void sendPublicMsg(IMessage msg);

    public void injectBusiness(IBusiness bus);
    
    public int sendChangePw(IManagement management);
    
    public int sendChangeMail(IManagement management);

    public void logoutHandling(String logout);
    
    public void connect();

    public boolean updateFriends(IFriends friends);
    
    public boolean updateProfile(IUser user);

    public void sendPrivateMessage(IPrivateChat newMegPrivateChat);

}
