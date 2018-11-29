package Business;

import Acquaintance.IBusiness;
import Acquaintance.IConnection;
import Acquaintance.IFriends;
import Acquaintance.ILogin;
import Acquaintance.IManagement;
import Acquaintance.IMessage;
import Acquaintance.IPresentation;
import Acquaintance.IProfile;
import Acquaintance.IUser;
import Acquaintance.Nationality;
import java.util.HashMap;
import java.util.Map;

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

    @Override
    public int login(String mail, String pw) {
        ILogin ilogin = new Login(ClientSystem.getInstance().hash(mail), ClientSystem.getInstance().hash(pw));
        return ilogin.login(Icon.login(ilogin));
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
        IMessage msg = new TextMessage(ClientSystem.getInstance().getCurrentUser().getUserID(), ClientSystem.getInstance().cipherMsg(s));
        Icon.sendPublicMsg(msg);

    }

    @Override
    public void commandHandling(String command) {
        Icon.commandHandling(command);
    }

    @Override
    public void connect() {
        Icon.connect();
    }

    @Override
    public boolean addFriend(int userID) {
        return ClientSystem.getInstance().getCurrentUser().addFriend(userID);
    }

    @Override
    public boolean removeFriend(int userID) {
        return ClientSystem.getInstance().getCurrentUser().removeFriend(userID);
    }

    /**
     *
     * method for updating the friendsobject in database
     */
    boolean updateFriends(IFriends friends) {
        return Icon.updateFriends(friends);
    }

    @Override
    public IUser getUser() {
        return ClientSystem.getInstance().getCurrentUser();
    }

    @Override
    public void userMap(Map<Integer, IUser> userMap) {
        Map<Integer, IUser> publicUserMap = new HashMap<>();
        if (!userMap.isEmpty()) {
            for (int i : userMap.keySet()) {
                publicUserMap.put(i, new User(userMap.get(i)));
            }
        }
        Ipres.userMap(publicUserMap);
    }

    @Override
    public void publicUser(IUser pUser) {
        IUser user = new User(pUser);
        Ipres.publicUser(user);
    }

    @Override
    public boolean checkPW(String pw) {
        IManagement mana = new Management(0);
        mana.setPw(ClientSystem.getInstance().hash(pw));
        mana.setMail("");
        mana.setProfile(new Profile("", "", Nationality.USA, "", ""));
        return Icon.checkPW(mana);
    }

    @Override
    public boolean checkMail(String mail) {
        IManagement mana = new Management(1);
        mana.setMail(ClientSystem.getInstance().hash(mail));
        mana.setPw("");
        mana.setProfile(new Profile("", "", Nationality.USA, "", ""));
        return Icon.checkMail(mana);
    }

    @Override
    public boolean updateUserInfo(String pw, String mail, String firstName, String lastName, Nationality nationality, String profileText, String picture) {
        IManagement mana = new Management(2);
        if (!pw.equals("")) {
            mana.setPw(ClientSystem.getInstance().hash(pw));
        } else {
            mana.setPw(pw);
        }
        if (!mail.equals("")) {
            mana.setMail(ClientSystem.getInstance().hash(mail));
        } else {
            mana.setMail(mail);
        }
        IProfile profile = new Profile(firstName, lastName, nationality, profileText, picture);
        mana.setProfile(profile);
        ClientSystem.getInstance().updateProfile(profile);
        return Icon.updateUserInfo(mana);
    }
}
