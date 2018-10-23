/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sigurd E. Espersen
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_pw;
    @FXML
    private Button btn_login;
    @FXML
    private Label label_wrongInfo;
    private Stage stage;
    @FXML
    private MediaView mv_background;
    private MediaPlayer mp;
    private Media me;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String path = new File("src/Assets/backgroundAnimation.mp4").getAbsolutePath();
        me = new Media(new File(path).toURI().toString());
        mp = new MediaPlayer(me);
        mv_background.setMediaPlayer(mp);
        mp.setCycleCount(mp.INDEFINITE);
        mp.setAutoPlay(true);
//        DoubleProperty width = mv_background.fitWidthProperty();
//        DoubleProperty height = mv_background.fitHeightProperty();
//        width.bind(Bindings.selectDouble(mv_background.sceneProperty(), "width"));
//        height.bind(Bindings.selectDouble(mv_background.sceneProperty(), "height"));
    }

    @FXML
    private void btn_login_action(ActionEvent event) {
        if (validateInfo()) {
            loginHandler(event);
        }

    }

    private boolean validateInfo() {
        if (txt_email.getText().contains("@") && txt_email.getText().contains(".")) {
            if (txt_pw.getText().length() >= 8) {
                return true;
            } else {
                label_wrongInfo.setText("Password must be atleast 8 characters long");
                return false;
            }
        } else {
            label_wrongInfo.setText("Invalid email");
            return false;
        }
    }

    private void loginHandler(ActionEvent event) {
        int validationInt = PresentationFacade.getInstance().hashLogin(txt_email.getText(), txt_pw.getText());
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
                changeScene(event);
                break;
            default:
                label_wrongInfo.setText("Something went wrong");
        }
    }

    private void changeScene(ActionEvent event) {
            Parent SYNchat;
        try {
            SYNchat = FXMLLoader.load(getClass().getResource("SYNchat.fxml"));
            Scene newScene = new Scene(SYNchat);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(newScene);
            appStage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
