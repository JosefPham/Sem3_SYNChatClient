package Acquaintance;

import java.util.Map;

/**
 *
 * @author Group 9
 */
public interface IConnection {

    ILogin login(ILogin ilogin);

    Boolean regUser(ILogin ilogin);

    IClient getClient();

    void startPublicThreads();

    void startPrivateThreads();

    void receivePublicMsg(IMessage msg);

    void sendPublicMsg(IMessage msg);

    void injectBusiness(IBusiness bus);

    void commandHandling(String command);

    void connect();

    boolean updateFriends(IFriends friends);

    void userMap(Map userMap);

    void publicUser(IUser pUser);

    boolean checkPW(IManagement management);

    boolean checkMail(IManagement management);

    boolean updateUserInfo(IManagement management);
}
