package Business;

import Acquaintance.IBusiness;
import Acquaintance.IChatHistory;
import Acquaintance.IConnection;
import Acquaintance.IFriends;
import Acquaintance.ILogin;
import Acquaintance.IManagement;
import Acquaintance.IMessage;
import Acquaintance.IPresentation;
import Acquaintance.IUser;
import Acquaintance.Nationality;
import Acquaintance.IPrivateChat;

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

    //Hashed login
    protected ILogin login(ILogin ilogin) {
        return Icon.login(ilogin);
    }

    //Unhashed login
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
    public void receivePublicMsg(IMessage msg) {        
        Message finalMsg = new TextMessage(msg.getSenderID(), ClientSystem.getInstance().cipherMsg(msg.getContext()));
        finalMsg.setTimestamp(msg.getTimestamp());
        Ipres.receivePublicMsg(finalMsg);
    }

    @Override
    public void sendPublicMsg(String s) {
//        ClientSystem.getInstance().cipherMsg(s);
        IMessage msg = new TextMessage(0, ClientSystem.getInstance().cipherMsg(s));
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
    boolean updateFriends(IFriends friends) {
        return Icon.updateFriends(friends);
    }
    
    @Override
    public int changePw(String oldPw, String newPw) {
        return ClientSystem.getInstance().getCurrentUser().changePw(oldPw, newPw);
    }
    
    @Override
    public int changeMail(String pw, String mail) {
        return ClientSystem.getInstance().getCurrentUser().changeMail(pw, mail);
    }

    boolean sendPrivateMessage(IPrivateChat newMegPrivateChat) {
        return Icon.sendPrivateMessage(newMegPrivateChat);
    }
    
    @Override
    public IUser getUser() {
        return ClientSystem.getInstance().getCurrentUser();
    }
    
    @Override
    public boolean editProfileInfo(String firstName, String lastName, Nationality nationality, String profileText) {
        return ClientSystem.getInstance().updateProfile(firstName, lastName, nationality, profileText);
    }
    
    @Override
    public boolean updateProfile(IUser user) {
        return Icon.updateProfile(user);
    }
    
    
}
