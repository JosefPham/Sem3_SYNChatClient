package Presentation;

import Acquaintance.IBusiness;
import Acquaintance.IController;
import Acquaintance.IPresentation;
import java.io.IOException;
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
    private String s = "";
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
    public void receivePublicMsg(String s) {
        this.s = s;
        System.out.println("presentationfacade");
        //SYNchatController.getInstance().receivePublicMsg(s);
    }

    @Override
    public String getS() {
        return s;
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
    public void logoutHandling(String logout) {
        Ibus.logoutHandling(logout);
    }
    
    public void connect() {
        Ibus.connect();
    }
}
