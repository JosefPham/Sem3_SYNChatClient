package Business;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Group 9
 */
public class ClientSystem {
    
    private static ClientSystem instance = null;
    
    private ClientSystem () {
    }
    
    public static ClientSystem getInstance() {
        if (instance == null) {
            instance = new ClientSystem();
        }
        return instance;
    }
    
    protected void hashLogin(String mail, String pw) {
        try {
            MessageDigest hash = MessageDigest.getInstance("MD5");
            hash.update(mail.getBytes(), 0, mail.length());
            String hashedMail = new BigInteger(1, hash.digest()).toString(16);           
            hash.update(pw.getBytes(), 0, pw.length());
            String hashedPW = new BigInteger(1, hash.digest()).toString(16);
            
            Login login = new Login(hashedMail, hashedPW);
            
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ClientSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
