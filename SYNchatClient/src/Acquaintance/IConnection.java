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

    public void commandHandling(String command);

    public void connect();

    public boolean updateFriends(IFriends friends);

    public void userMap(Map userMap);

    public void publicUser(IUser pUser);

    public boolean checkPW(IManagement management);

    public boolean checkMail(IManagement management);

    public boolean updateUserInfo(IManagement management);
}
