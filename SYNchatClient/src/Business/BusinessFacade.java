/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Acquaintance.IBusiness;
import Acquaintance.IConnection;
import Acquaintance.ILogin;
import Connection.ConnectionFacade;

/**
 *
 * @author Sigurd E. Espersen
 */
public class BusinessFacade implements IBusiness {
    
    private IConnection Icon;
    
    private static BusinessFacade instance = null;
    
    private BusinessFacade () {
    }
    
    public static BusinessFacade getInstance() {
        if (instance == null) {
            instance = new BusinessFacade();
        }
        return instance;
    }
    
    @Override
    public void injectConnection(IConnection con) {
        this.Icon = con;
    }
    
    protected int login(ILogin ilogin) {
        return ConnectionFacade.getInstance().login(ilogin);
    }

    @Override
    public int hashLogin(String mail, String pw) {
        return ClientSystem.getInstance().hashLogin(mail, pw);  
    }
    
    @Override
    public boolean registerNewUser(String firstName, String lastName, String email, String password) {
        return instance.registerNewUser(firstName, lastName, email, password);
    }
    
    
}
