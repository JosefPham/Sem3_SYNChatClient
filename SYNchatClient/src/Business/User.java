package Business;

import Acquaintance.IUser;
import java.util.List;

public class User implements IUser {

    private int userID;
    private String tmpName; //must be removed when profile is implemented
    private boolean banned; // a flag for if the user is banned
    private int reports;    // the amount of reprts a user have received
    private List<Integer> chats;

    public User(String tmpName) {
        this.tmpName = tmpName;
    }

    public User(int userID, String tmpName, boolean banned, int reports, List<Integer> chats) {
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

    @Override
    public boolean changePw(String oldPw, String newPw) {
        if (verifyPw(oldPw)) {
            if (BusinessFacade.getInstance().changePw(this.userID, newPw)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean changeMail(String pw, String newMail) {
        if(verifyPw(pw)) {
            if(BusinessFacade.getInstance().changeMail(this.userID, pw)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean verifyPw(String pw) {
        if (BusinessFacade.getInstance().verifyPw(this.userID, pw)) {
            return true;
        } else {
            return false;
        }
    }

}
