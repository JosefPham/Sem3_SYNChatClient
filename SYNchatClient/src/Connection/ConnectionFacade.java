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
        return client.sendLogin(ilogin);
//        return ilogin;
    }

    public Boolean regBool(List regList) {
        //server connection
        return true;
    }

    public Client getClient() {
        return client;
    } 

}
