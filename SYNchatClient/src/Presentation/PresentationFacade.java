package Presentation;

import Acquaintance.IBusiness;
import Acquaintance.IPresentation;

/**
 *
 * @author Group 9
 */
public class PresentationFacade implements IPresentation {

    protected static IBusiness Ibus;
    private SYNchat synchat;
    private String s = "";

    private static PresentationFacade instance = null;

    private PresentationFacade() {
    }

    public static PresentationFacade getInstance() {
        if (instance == null) {
            instance = new PresentationFacade();
        }
        return instance;
    }

    @Override
    public void injectBusiness(IBusiness bus) {
        this.Ibus = bus;
    }

    public int login(String mail, String pw) {
        return Ibus.login(mail, pw);
    }

    public Boolean regUser(String tmpName, String mail, String pw) {
        return Ibus.regUser(tmpName, mail, pw);
    }

    //Call from Starter to SYNchat with System Startup command
    @Override
    public void startApplication(String[] args) {
        if (synchat == null) {
            synchat = new SYNchat();
        }
        synchat.startApplication(args);
    }

    @Override
    public void recievePublicMsg(String s) {
        this.s = s;
        System.out.println("presentationfacade");
        //SYNchatController.getInstance().recievePublicMsg(s);
    }

    @Override
    public String getS() {
        return s;
    }

    @Override
    public void sendPublicMsg(String s) {
        Ibus.sendPublicMsg(s);
    }

}
