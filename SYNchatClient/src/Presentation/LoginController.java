
package Presentation;

import Acquaintance.IController;
import static Presentation.PresentationFacade.stage;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Group 9
 */
public class LoginController implements IController, Initializable {

    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_pw;
    @FXML
    private Button btn_login;
    @FXML
    private Label label_wrongInfo;
    @FXML
    private MediaView mv_background;
    private MediaPlayer mp;
    private Media me;
    @FXML
    private Button btn_register;
    @FXML
    private JFXButton btn_forgotPW;
    private Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("pfacade " + PresentationFacade.getInstance());
        btn_forgotPW.setVisible(false);
        txt_email.setStyle("-fx-prompt-text-fill: #1d1f21;"
                + "-fx-text-inner-color: #1d1f21;");
        txt_pw.setStyle("-fx-prompt-text-fill: #1d1f21;"
                + "-fx-text-inner-color: #1d1f21;");

        String path = new File("src/Assets/backgroundAnimation.mp4").getAbsolutePath();
        me = new Media(new File(path).toURI().toString());
        mp = new MediaPlayer(me);
        mv_background.setMediaPlayer(mp);
        mp.setCycleCount(mp.INDEFINITE);
        mp.setAutoPlay(true);
    }

    @FXML
    private void btn_login_action(ActionEvent event) {
        label_wrongInfo.setText("");
        if (validateInfo()) {
            loginHandler(event);
        }

    }

    @FXML
    private void btn_register_action(ActionEvent event) {
        PresentationFacade.getInstance().changeScene("RegisterNewUser.fxml");
    }
    

    private boolean validateInfo() {
        if (txt_email.getText().contains("@") && txt_email.getText().contains(".")) {
            if (txt_pw.getText().length() >= 8) {
                return true;
            } else {
                label_wrongInfo.setText("Incorrect password");
                btn_forgotPW.setVisible(true);
                return false;
            }
        } else {
            label_wrongInfo.setText("Invalid email");
            return false;
        }
    }

    private void loginHandler(ActionEvent event) {
        int validationInt = PresentationFacade.getInstance().login(txt_email.getText(), txt_pw.getText());
        //System.out.println(validationInt);
        switch (validationInt) {
            case 0:
                label_wrongInfo.setText("Email doesn't exist");
                //display register option?
                break;
            case 1:
                label_wrongInfo.setText("Email or password is incorrect");
                //display forgot password?
                break;
            case 2:
                //*login*
                PresentationFacade.getInstance().changeScene("SYNchat.fxml");
                break;
            default:
                label_wrongInfo.setText("Something went wrong");
        }
    }

    @FXML
    private void btn_register_out(MouseEvent event) {
        btn_register.setUnderline(false);
    }

    @FXML
    private void btn_register_in(MouseEvent event) {
        btn_register.setUnderline(true);
    }

    @FXML
    private void btn_forgotPW_out(MouseEvent event) {
        btn_forgotPW.setUnderline(false);
    }

    @FXML
    private void btn_forgotPW_in(MouseEvent event) {
        btn_forgotPW.setUnderline(true);
    }

    @Override
    public void injectStage(Stage stage) {
        PresentationFacade.stage = stage;
    }
}
