/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import Acquaintance.IConnection;
import Acquaintance.ILogin;
import java.util.List;

/**
 *
 * @author Sigurd E. Espersen
 */
public class ConnectionFacade implements IConnection {

    private static ConnectionFacade instance = null;
    private Client client;

    private ConnectionFacade() {
        client = new Client();
    }

    public static ConnectionFacade getInstance() {
        if (instance == null) {
            instance = new ConnectionFacade();
        }
        return instance;
    }

    public ILogin login(ILogin ilogin) {
        //Sendts login information to server and calls to get user and accesslevel back as return
        client.sendLogin(ilogin);
        return client.recieveLogin();
    }

    public Boolean regBool(ILogin ilogin) {
        //server connection
        return true;
    }

    public Client getClient() {
        return client;
    } 

    public void startPublicThreads() {
        client.startPublicThreads();
    }
    
    public void startPrivateThreads() {
        client.startPrivateThreads();
    }
}
