package Presentation;

import Acquaintance.IController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Group 9
 */
public class SYNchatController implements IController, Initializable {

    private Thread t = null;
    private static SYNchatController instance = null;

    public static SYNchatController getInstance() {
        if (instance == null) {
            instance = new SYNchatController();
        }
        return instance;
    }

    @FXML
    private JFXButton btn_publicChat;
    @FXML
    private JFXButton btn_send;
    @FXML
    private ImageView pic_profile;
    @FXML
    private Label label_userInfo;
    @FXML
    private JFXButton btn_privatChat;
    @FXML
    private AnchorPane Popup_pane;
    @FXML
    private JFXTextField txtArea_YourChat;
    @FXML
    private JFXTextArea txtArea_rightChat;
    @FXML
    private JFXTextArea txtArea_leftChat;
    @FXML
    private AnchorPane AnchorPane_List;
    @FXML
    private MediaView mv_background;
    private MediaPlayer mp;
    private Media me;
    @FXML
    private AnchorPane pane_Welcome;
    boolean popUp = true;
    boolean cog = true;
    boolean settings = true;
    @FXML
    private AnchorPane pane_cogView;
    @FXML
    private AnchorPane pane_chat;
    @FXML
    private JFXButton btn_logout;
    @FXML
    private AnchorPane pane_settings;
    @FXML
    private ImageView hamburger_settings;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String path = new File("src/Assets/backgroundAnimation.mp4").getAbsolutePath();
        me = new Media(new File(path).toURI().toString());
        mp = new MediaPlayer(me);
        mv_background.setMediaPlayer(mp);
        mp.setCycleCount(mp.INDEFINITE);
        mp.setAutoPlay(true);
        pane_cogView.setVisible(false);
        Popup_pane.setVisible(false);
        pane_chat.setDisable(true);
        pane_settings.setVisible(false);
    }

    @FXML
    public void startPublicChat(ActionEvent event) {
        pane_chat.setDisable(false);
        pane_Welcome.toBack();
        PresentationFacade.Ibus.publicThreads();
        btn_privatChat.setStyle(btn_publicChat.getStyle());
        btn_publicChat.setStyle(btn_publicChat.getStyle() + "-fx-background-color: #162ab7");

        this.t = startrun();
    }

    private synchronized Thread startrun() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String s = "";
                while (true) {
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SYNchatController.class.getName()).log(Level.SEVERE, null, ex);
                    }
//                    System.out.println("");
//                    System.out.println(" .----------------.  .----------------.  .-----------------. .----------------.  .----------------.  .----------------.  .----------------. ");
//                    System.out.println("| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |");
//                    System.out.println("| |    _______   | || |  ____  ____  | || | ____  _____  | || |     ______   | || |  ____  ____  | || |      __      | || |  _________   | |");
//                    System.out.println("| |   /  ___  |  | || | |_  _||_  _| | || ||_   \\|_   _| | || |   .' ___  |  | || | |_   ||   _| | || |     /  \\     | || | |  _   _  |  | |");
//                    System.out.println("| |  |  (__ \\_|  | || |   \\ \\  / /   | || |  |   \\ | |   | || |  / .'   \\_|  | || |   | |__| |   | || |    / /\\ \\    | || | |_/ | | \\_|  | |");
//                    System.out.println("| |   '.___`-.   | || |    \\ \\/ /    | || |  | |\\ \\| |   | || |  | |         | || |   |  __  |   | || |   / ____ \\   | || |     | |      | |");
//                    System.out.println("| |  |`\\____) |  | || |    _|  |_    | || | _| |_\\   |_  | || |  \\ `.___.'\\  | || |  _| |  | |_  | || | _/ /    \\ \\_ | || |    _| |_     | |");
//                    System.out.println("| |  |_______.'  | || |   |______|   | || ||_____|\\____| | || |   `._____.'  | || | |____||____| | || ||____|  |____|| || |   |_____|    | |");
//                    System.out.println("| |              | || |              | || |              | || |              | || |              | || |              | || |              | |");
//                    System.out.println("| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |");
//                    System.out.println(" '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'");
//                    System.out.println("");
                    String tmp = PresentationFacade.getInstance().getS();
                    if (!tmp.equals(s)) {
                        s = tmp;
                        receivePublicMsg(s);
                    }
                }
            }
        };
        Thread t = new Thread(runnable);
        t.setDaemon(true);
        t.start();
        return t;
    }

    @FXML
    private void sendMsg(ActionEvent event) {
        if (!txtArea_YourChat.getText().trim().isEmpty()) {
            PresentationFacade.getInstance().sendPublicMsg(txtArea_YourChat.getText());
            String nameMsg = "";
            String yourMsg = "";
            String dateMsg = "";
            nameMsg = "Default user:\n";
            txtArea_rightChat.appendText(nameMsg);
            yourMsg = txtArea_YourChat.getText() + "\n";
            txtArea_rightChat.appendText(yourMsg);
            dateMsg = new SimpleDateFormat("HH.mm").format(new Date()) + "\n";
            txtArea_rightChat.appendText(dateMsg);

            txtArea_leftChat.appendText("\n\n");

            txtArea_YourChat.clear();
        }
    }

    @FXML
    private void startPrivatChat(ActionEvent event) {
        pane_chat.setDisable(false);
        pane_Welcome.toBack();
        PresentationFacade.Ibus.privateThreads();
        btn_publicChat.setStyle(btn_privatChat.getStyle());
        btn_privatChat.setStyle(btn_privatChat.getStyle() + "-fx-background-color: #162ab7");
    }

    @FXML
    private void popOpHandler(MouseEvent event) {
        if (popUp) {
            Popup_pane.toFront();
            Popup_pane.setVisible(true);
            popUp = false;
        } else {
            Popup_pane.toBack();
            Popup_pane.setVisible(false);
            popUp = true;
        }
    }

    public void receivePublicMsg(String s) {
        System.out.println("En eller anden faggots besked: " + s);
        String nameMsg = "";
        String yourMsg = "";
        String dateMsg = "";
        nameMsg = "Default user:\n";
        txtArea_leftChat.appendText(nameMsg);
        yourMsg = s + "\n";
        txtArea_leftChat.appendText(yourMsg);
        dateMsg = new SimpleDateFormat("HH.mm").format(new Date()) + "\n";
        txtArea_leftChat.appendText(dateMsg);
        txtArea_rightChat.appendText("\n\n");
    }

    @FXML
    private void cogHandler(MouseEvent event) {
        if (cog) {
            pane_cogView.setVisible(true);
            pane_cogView.toFront();
            cog = false;
        } else {
            pane_cogView.setVisible(false);
            pane_cogView.toBack();
            cog = true;
        }
    }

    @FXML
    private void changeProfilePicture(MouseEvent event) {
        
    }

    @FXML
    private void logoutAction(ActionEvent event) {
        PresentationFacade.getInstance().logoutHandling("!SYN!-logout-!SYN!");
        PresentationFacade.getInstance().changeScene("Login.fxml");
    }

    @FXML
    private void returnToWelcome(ActionEvent event) {
        pane_chat.setDisable(true);
        pane_Welcome.toFront();
        btn_privatChat.setStyle(btn_privatChat.getStyle() + "-fx-background-color: #1f96e0");
        btn_publicChat.setStyle(btn_publicChat.getStyle() + "-fx-background-color: #1f96e0");
    }

    @FXML
    private void hamburger_handler(MouseEvent event) {
        if (settings) {
            pane_settings.setVisible(true);
            settings = false;
        } else {
            pane_settings.setVisible(false);
            settings = true;
        }
    }

    @Override
    public void injectStage(Stage stage) {
        PresentationFacade.stage = stage;
    }

}
