package Business;

import Acquaintance.ILogin;
import Acquaintance.IUser;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Group 9
 */
public class ClientSystem {

    private static ClientSystem instance = null;

    private ClientSystem() {
    }

    public static ClientSystem getInstance() {
        if (instance == null) {
            instance = new ClientSystem();
        }
        return instance;
    }

    private String hash(String hashString) {
        try {
            MessageDigest hash = MessageDigest.getInstance("MD5");
            hash.update(hashString.getBytes(), 0, hashString.length());
            return new BigInteger(1, hash.digest()).toString(16);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ClientSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    protected int Login(String mail, String pw) {
        ILogin ilogin = new Login(hash(mail), hash(pw));
        return ilogin.login(BusinessFacade.getInstance().login(ilogin).getLoginvalue());
    }
    
    protected boolean regUser(String tmpName, String mail, String pw) {
        IUser iuser = new User(tmpName);
        ILogin ilogin = new Login(hash(mail), hash(pw));
        ilogin.setUser(iuser);
        
        return BusinessFacade.getInstance().regBool(ilogin);
    }
}
