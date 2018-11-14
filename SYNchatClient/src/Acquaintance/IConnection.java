package Acquaintance;

import java.util.Map;

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

    public void commandHandling(String command);
    
    public void connect();

    public boolean updateFriends(IFriends friends);
    
    public boolean updateProfile(IUser user);
    
    public void userMap(Map userMap);
    
    public void publicUser(IUser pUser);
}
