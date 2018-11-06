
package Connection;

import Acquaintance.IBusiness;
import Acquaintance.IClient;
import Acquaintance.IConnection;
import Acquaintance.IFriends;
import Acquaintance.ILogin;
import Acquaintance.IManagement;
import Acquaintance.IProfile;
import Acquaintance.IUser;
import Business.Friends;

/**
 *
 * @author Group 9
 */
public class ConnectionFacade implements IConnection {

    private static ConnectionFacade instance = null;
    private IClient client;
    private IBusiness Ibus;

    private ConnectionFacade() {
        client = new Client();
    }

    @Override
    public void injectBusiness(IBusiness bus) {
        this.Ibus = bus;
    }

    public static ConnectionFacade getInstance() {
        if (instance == null) {
            instance = new ConnectionFacade();
        }
        return instance;
    }

    @Override
    public ILogin login(ILogin ilogin) {
        // the login to send to server
        ILogin sendLogin = new ConLogin(ilogin.gethMail(), ilogin.gethPW(), ilogin.getLoginvalue(), ilogin.getUser());
        client.send(ilogin);
        //ILogin recieveLogin = new ConLogin
        return client.receiveLogin();
    }

    @Override
    public Boolean regUser(ILogin ilogin) {
        client.send(ilogin);
        return client.receiveBool();
    }

    @Override
    public IClient getClient() {
        return client;
    }

    @Override
    public void startPublicThreads() {
        client.startPublicThreads();
    }

    @Override
    public void startPrivateThreads() {
        client.startPrivateThreads();
    }

    @Override
    public void receivePublicMsg(String s) {
        System.out.println("connectionfacade");
        Ibus.receivePublicMsg(s);
    }

    @Override
    public void sendPublicMsg(Object msg) {
        client.send(msg);
    }
    
    @Override
    public int sendChangePw(IManagement management) {
        client.send(management);
        return client.receiveInt();
    }
    
    @Override
    public int sendChangeMail(IManagement management) {
        client.send(management);
        return client.receiveInt();
    }

    @Override
    public void logoutHandling(String logout) {
        client.send(logout);
    }

    @Override
    public void connect() {
        client.connectToServer();
    }
    
    @Override
    public boolean updateFriends(Friends friends) {
       IFriends sendFriends = new ConFriends(friends.getFriendlist());
       client.send(sendFriends);
       return client.receiveBool();
    }
    
    @Override
    public boolean updateProfile(IProfile profile) {
        client.send(profile);
        return client.receiveBool();
    }
}
