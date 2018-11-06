package Business;

import Acquaintance.IBusiness;
import Acquaintance.IConnection;
import Acquaintance.ILogin;
import Acquaintance.IManagement;
import Acquaintance.IPresentation;
import Acquaintance.IProfile;
import Acquaintance.IUser;
import Acquaintance.Nationality;

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
    public Boolean regUser(String firstName, String lastName, String mail, String pw, Nationality nationality) {
        return ClientSystem.getInstance().regUser(firstName, lastName, mail, pw, nationality);
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
        Object msg = new TextMessage(0, ClientSystem.getInstance().cipherMsg(s));
        Icon.sendPublicMsg(msg);

    }

    @Override
    public void logoutHandling(String logout) {
        Icon.logoutHandling(logout);
    }

    @Override
    public void connect() {
        Icon.connect();
    }
    
    @Override
    public int sendVerifyPw(IManagement management) {
        return Icon.sendVerifyPw(management);
    }
    
    @Override
    public int sendChangePw(IManagement management) {
        return Icon.sendChangePw(management);
    }

    @Override
    public int sendChangeMail(IManagement managemnt) {
        return Icon.sendChangeMail(management);
    }

    @Override
    public boolean addFriend(int userID, String profileName){
        return ClientSystem.getInstance().getCurrentUser().addFriend(userID, profileName);
    }
    
    @Override
    public boolean removeFriend(int userID){
        return ClientSystem.getInstance().getCurrentUser().removeFriend(userID);
    }
    /*
    method for updating the friendsobject in database
    */
    boolean updateFriends(Friends friends) {
        return Icon.updateFriends(friends);
    }
    
    public int verifyPw(String pw) {
        return ClientSystem.getInstance().getCurrentUser().verifyPw(pw);
    }
    
    public int changePw(String oldPw, String newPw) {
        return ClientSystem.getInstance().getCurrentUser().changePw(oldPw, newPw);
    }
    
    public int changeMail(String pw, String mail) {
        return ClientSystem.getInstance().getCurrentUser().changeMail(pw, mail);
    }
    
    @Override
    public IUser getUser() {
        return ClientSystem.getInstance().getCurrentUser();
    }
    
    public boolean editProfileInfo(String firstName, String lastName, Nationality nationality, String profileText) {
        return ClientSystem.getInstance().updateProfile(firstName, lastName, nationality, profileText);
    }
    
    public boolean updateProfile(IProfile profile) {
        return Icon.updateProfile(profile);
    }
    
    
}
