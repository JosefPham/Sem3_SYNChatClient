
package Connection;

import Acquaintance.IBusiness;
import Acquaintance.IClient;
import Acquaintance.IConnection;
import Acquaintance.ILogin;
import Acquaintance.IManagement;
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
    public void sendPublicMsg(String s) {
        client.send(s);
    }
    
    @Override
    public int sendVerifyPw(IManagement management) {
        client.send(management);
        return client.receiveInt();
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
    public boolean updateFriends(Friends friends) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
