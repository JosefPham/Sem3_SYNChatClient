package Business;

import Acquaintance.IBusiness;
import Acquaintance.IConnection;
import Acquaintance.ILogin;
import Acquaintance.IManagement;
import Acquaintance.IPresentation;
import java.util.List;

/**
 *
 * @author Group 9
 */
public class BusinessFacade implements IBusiness {

    private IConnection Icon;
    private IPresentation Ipres;

    private static BusinessFacade instance = null;
    private Management management;

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
    
    @Override
    public boolean sendVerifyPw(IManagement management) {
        return Icon.sendVerifyPw(management);
    }
    
    @Override
    public boolean sendChangePw(IManagement management) {
        return Icon.sendChangePw(management);
    }

    @Override
    public boolean sendChangeMail(IManagement managemnt) {
        return Icon.sendChangeMail(management);
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
    
    public boolean verifyPw(String pw) {
        return ClientSystem.getInstance().getCurrentUser().verifyPw(pw);
    }
    
    public boolean changePw(String oldPw, String newPw) {
        return ClientSystem.getInstance().getCurrentUser().changePw(oldPw, newPw);
    }
    
    public boolean changeMail(String pw, String mail) {
        return ClientSystem.getInstance().getCurrentUser().changeMail(pw, mail);
    }
    
    
}
