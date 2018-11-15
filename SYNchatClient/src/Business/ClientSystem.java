package Business;

import Acquaintance.ILogin;
import Acquaintance.IProfile;
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
    
    private User currentUser;
    
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
        return ilogin.login(BusinessFacade.getInstance().login(ilogin));
    }
    
    protected boolean regUser(String firstName, String lastName, String mail, String pw, Nationality nationality) {
        IProfile profile = new Profile(firstName, lastName, nationality, "", "");
        IUser iuser = new User(profile);
        ILogin ilogin = new Login(hash(mail), hash(pw));
        ilogin.setUser(iuser);
        return BusinessFacade.getInstance().regBool(ilogin);
    }
    
    protected String cipherMsg(String msg) {
        Cipher cipher = new Cipher();
        msg = cipher.cipher(msg);
        
        return msg;
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
    
    public void setUser(User user) {
        this.currentUser = user;
    }
    
    protected void updateProfile(IProfile profile) {
        currentUser.getProfile().setFirstName(profile.getFirstName());
        currentUser.getProfile().setLastName(profile.getLastName());
        currentUser.getProfile().setNationality(profile.getNationality());
        currentUser.getProfile().setPicture(profile.getPicture());
        currentUser.getProfile().setProfileText(profile.getProfileText());
    }
}
