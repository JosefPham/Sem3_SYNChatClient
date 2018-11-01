package Business;

import Acquaintance.IBusiness;
import Acquaintance.IConnection;
import Acquaintance.ILogin;
import Acquaintance.IPresentation;

/**
 *
 * @author Group 9
 */
public class BusinessFacade implements IBusiness {

    private IConnection Icon;
    private IPresentation Ipres;

    private static BusinessFacade instance = null;

    private BusinessFacade() {
    }

    public static BusinessFacade getInstance() {
        if (instance == null) {
            instance = new BusinessFacade();
        }
        return instance;
    }

    @Override
    public void injectPresentation(IPresentation pres) {
        this.Ipres = pres;
    }

    @Override
    public void injectConnection(IConnection con) {
        this.Icon = con;
    }

    protected ILogin login(ILogin ilogin) {
        return Icon.login(ilogin);
    }

    @Override
    public int login(String mail, String pw) {
        return ClientSystem.getInstance().Login(mail, pw);
    }

    @Override
    public Boolean regUser(String tmpName, String mail, String pw) {
        return ClientSystem.getInstance().regUser(tmpName, mail, pw);
    }

    protected Boolean regBool(ILogin ilogin) {
        return Icon.regUser(ilogin);
    }

    @Override
    public void publicThreads() {
        Icon.startPublicThreads();
    }

    @Override
    public void privateThreads() {
        Icon.startPrivateThreads();
    }

    @Override
    public void receivePublicMsg(String s) {
        Ipres.receivePublicMsg(ClientSystem.getInstance().cipherMsg(s));
    }

    @Override
    public void sendPublicMsg(String s) {
//        ClientSystem.getInstance().cipherMsg(s);
        Icon.sendPublicMsg(ClientSystem.getInstance().cipherMsg(s));
        
    }
    
    public boolean verifyPw(int userID, String pw) {
        return Icon.verifyPw(userID, pw);
    }
    
    public boolean changePw(int userID, String newPw) {
        return Icon.changePw(userID, newPw);
    }
    
    public boolean changeMail(int userID, String mail) {
        return Icon.changeMail(userID, mail);
    }

    public boolean addFriend(int userID, String profileName){
        return ClientSystem.getInstance().getCurrentUser().addFriend(userID, profileName);
    }
    
    public boolean removeFriend(int userID){
        return ClientSystem.getInstance().getCurrentUser().removeFriend(userID);
    }
    
    boolean updateFriends(Friends friends) {
        return Icon.updateFriends(friends);
    }
    
    public boolean changeMail(String pw, String mail) {
        return ClientSystem.getInstance().getCurrentUser().changeMail(pw, mail);
    }
    
    @Override
    public boolean changePw(String oldPw, String newPw) {
        return ClientSystem.getInstance().getCurrentUser().changePw(oldPw, newPw);
    }
}
