package Business;

import Acquaintance.ILogin;
import Acquaintance.IUser;
import Acquaintance.Nationality;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Group 9
 */
public class ClientSystem {

    User currentUser;

    private static ClientSystem instance = null;

    private ClientSystem() {

    }

    public static ClientSystem getInstance() {
        if (instance == null) {
            instance = new ClientSystem();
        }
        return instance;
    }

    protected String hash(String hashString) {
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

    protected boolean regUser(String firstName, String lastName, String mail, String pw, Nationality nationality) {
        IUser iuser = new User(firstName, lastName, nationality);
        ILogin ilogin = new Login(hash(mail), hash(pw));
        ilogin.setUser(iuser);
        return BusinessFacade.getInstance().regBool(ilogin);
    }

    protected String cipherMsg(String msg) {
        Cipher cipher = new Cipher();
        System.out.println("Origin : " + msg);
        msg = cipher.cipher(msg);
        System.out.println("cipher : " + msg);

        return msg;
    }

    User getCurrentUser() {
        return currentUser;
    }

    void setUser(User user) {
        this.currentUser = user;
    }

    protected boolean updateProfile(String firstName, String lastName, Nationality nationality, String profileText) {
        currentUser.getProfile().setFirstName(firstName);
        currentUser.getProfile().setLastName(lastName);
        currentUser.getProfile().setNationality(nationality);
        currentUser.getProfile().setProfileText(profileText);
        return BusinessFacade.getInstance().updateProfile(currentUser);
    }
}
