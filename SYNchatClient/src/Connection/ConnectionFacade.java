/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import Acquaintance.IConnection;
import Acquaintance.ILogin;

/**
 *
 * @author Sigurd E. Espersen
 */
public class ConnectionFacade implements IConnection {

    private static ConnectionFacade instance = null;

    private ConnectionFacade() {
    }

    public static ConnectionFacade getInstance() {
        if (instance == null) {
            instance = new ConnectionFacade();
        }
        return instance;
    }
    
    public int login(ILogin ilogin) {
        //server connection
        return 1;
    }
}
