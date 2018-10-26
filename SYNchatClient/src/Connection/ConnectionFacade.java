
package Connection;

import Acquaintance.IBusiness;
import Acquaintance.IClient;
import Acquaintance.IConnection;
import Acquaintance.ILogin;

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

    public ILogin login(ILogin ilogin) {
        client.send(ilogin);

        return client.recieveLogin();
    }

    public Boolean regBool(ILogin ilogin) {
        client.send(ilogin);
        return client.recieveBool();
    }

    public IClient getClient() {
        return client;
    }

    public void startPublicThreads() {
        client.startPublicThreads();
    }

    public void startPrivateThreads() {
        client.startPrivateThreads();
    }

    @Override
    public void recievePublicMsg(String s) {
        System.out.println("connectionfacade");
        Ibus.recievePublicMsg(s);
    }

    @Override
    public void sendPublicMsg(String s) {
        client.send(s);
    }

}
