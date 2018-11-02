package Connection;


import Acquaintance.IUser;
import java.util.List;

public class ConUser implements IUser {

    private int userID;
    private String tmpName; //must be removed when profile is implemented
    private boolean banned; // a flag for if the user is banned
    private int reports;    // the amount of reprts a user have recived
    private List<Integer> chats;

    public ConUser(String tmpName) {
        this.tmpName = tmpName;
    }

    public ConUser(int userID, String tmpName, boolean banned, int reports, List<Integer> chats) {
        this.userID = userID;
        this.tmpName = tmpName;
        this.banned = banned;
        this.reports = reports;
        this.chats = chats;
    }

    @Override
    public int getUserID() {
        return userID;
    }

    @Override
    public String getTmpName() {
        return tmpName;
    }

    @Override
    public boolean isBanned() {
        return banned;
    }

    @Override
    public int getReports() {
        return reports;
    }

    @Override
    public List<Integer> getChats() {
        return chats;
    }

    
    //Not to be implemented
    @Override
    public boolean changePw(String oldPw, String newPw) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean changeMail(String pw, String newMail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean verifyPw(String pw) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}