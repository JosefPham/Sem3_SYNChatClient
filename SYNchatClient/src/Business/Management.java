package Business;

import Acquaintance.IManagement;
import Acquaintance.IProfile;

public class Management implements IManagement {

    private final int action;
    private String pw, mail;
    private IProfile profile;

    public Management(int action) {
        this.action = action;
    }

    @Override
    public int getAction() {
        return action;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public IProfile getProfile() {
        return profile;
    }

    public void setProfile(IProfile profile) {
        this.profile = profile;
    }
}
