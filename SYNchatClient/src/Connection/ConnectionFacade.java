package Connection;

import Acquaintance.IBusiness;
import Acquaintance.IClient;
import Acquaintance.IConnection;
import Acquaintance.IFriends;
import Acquaintance.ILogin;
import Acquaintance.IManagement;
import Acquaintance.IMessage;
import Acquaintance.IUser;
import java.util.Map;

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
        client.send(sendLogin);
        //ILogin recieveLogin = new ConLogin
        return client.receiveLogin();
    }

    @Override
    public Boolean regUser(ILogin ilogin) {
        IUser tmpUser = ilogin.getUser();
        ConUser user = new ConUser(tmpUser.getProfile().getFirstName(), tmpUser.getProfile().getLastName(), tmpUser.getProfile().getNationality(), tmpUser.getProfile().getProfileText());
        ilogin.setUser(user);
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
    public void receivePublicMsg(IMessage msg) {
        Ibus.receivePublicMsg(msg);
    }

    @Override
    public void sendPublicMsg(IMessage msg) {
        ConTextMessage conMsg = new ConTextMessage(msg.getSenderID(), msg.getContext());
        client.send(conMsg);
    }

    @Override
    public void commandHandling(String command) {
        client.send(command);
    }

    @Override
    public void connect() {
        client.connectToServer();
    }

    @Override
    public boolean updateFriends(IFriends friends) {
        IFriends sendFriends = new ConFriends(friends.getFriendlist());
        client.send(sendFriends);
        return client.receiveBool();
    }

    @Override
    public void userMap(Map userMap) {
        Ibus.userMap(userMap);
    }

    @Override
    public void publicUser(IUser pUser) {
        Ibus.publicUser(pUser);
    }

    @Override
    public boolean checkPW(IManagement management) {
        ConManagement conMana = new ConManagement(management.getAction(), management.getPw(), management.getMail(), management.getProfile());
        client.send(conMana);
        return client.receiveBool();
    }

    @Override
    public boolean checkMail(IManagement management) {
        ConManagement conMana = new ConManagement(management.getAction(), management.getPw(), management.getMail(), management.getProfile());
        client.send(conMana);
        return client.receiveBool();
    }

    @Override
    public boolean updateUserInfo(IManagement management) {
        ConManagement conMana = new ConManagement(management.getAction(), management.getPw(), management.getMail(), management.getProfile());
        client.send(conMana);
        return client.receiveBool();
    }
}
