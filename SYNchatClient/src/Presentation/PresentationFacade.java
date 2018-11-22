package Presentation;

import Acquaintance.IBusiness;
import Acquaintance.IController;
import Acquaintance.IMessage;
import Acquaintance.IPresentation;
import Acquaintance.IUser;
import Acquaintance.Nationality;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Group 9
 */
public class PresentationFacade implements IPresentation, IController {

    protected static IBusiness Ibus;
    private SYNchat synchat;
    private IMessage iMsg;
    private Map<Integer, IUser> pUserMap;
    public static Stage stage;

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

    public Boolean regUser(String firstName, String lastName, String mail, String pw, Nationality nationality) {
        return Ibus.regUser(firstName, lastName, mail, pw, nationality);
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
    public void receivePublicMsg(IMessage msg) {
        this.iMsg = msg;
    }

    @Override
    public void sendPublicMsg(String s) {
        Ibus.sendPublicMsg(s);
    }

    public void changeScene(String resource) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
            Parent root = loader.load();
            IController controller = loader.getController();
            controller.injectStage(stage);

            Scene newScene = new Scene(root);
            stage.setScene(newScene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void injectStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void commandHandling(String command) {
        Ibus.commandHandling(command);
    }

    public void connect() {
        Ibus.connect();
    }

    public IMessage getIMsg() {
        return iMsg;
    }

    @Override
    public IUser getUser() {
        return Ibus.getUser();
    }

    @Override
    public void userMap(Map<Integer, IUser> userMap) {
        pUserMap = userMap;
    }

    public Map<Integer, IUser> getpUserMap() {
        return pUserMap;
    }

    @Override
    public void publicUser(IUser pUser) {

    }

    @Override
    public boolean checkPW(String pw) {
        return Ibus.checkPW(pw);
    }

    @Override
    public boolean checkMail(String mail) {
        return Ibus.checkMail(mail);
    }

    @Override
    public boolean updateUserInfo(String pw, String mail, String firstName, String lastName, Nationality nationality, String profileText, String picture) {
        return Ibus.updateUserInfo(pw, mail, firstName, lastName, nationality, profileText, picture);
    }
}
