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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author CasaRol
 */
public class RegisterNewUserController implements Initializable {

    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_Password1;
    @FXML
    private TextField txt_password2;
    @FXML
    private Button btn_register;
    @FXML
    private ChoiceBox<String> choice_Country;
    @FXML
    private TextField txt_firstName;
    @FXML
    private TextField txt_lastName;
    @FXML
    private Label label_warninginfo;
    @FXML
    private Button btn_cancel;
    @FXML
    private MediaView mv_background;
    private MediaPlayer mp;
    private Media me;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        choice_Country.getItems().addAll("Select Country", "Denmark", "Japan", "USA");
        choice_Country.setValue("Select Country");

        String path = new File("src/Assets/backgroundAnimation.mp4").getAbsolutePath();
        me = new Media(new File(path).toURI().toString());
        mp = new MediaPlayer(me);
        mv_background.setMediaPlayer(mp);
        mp.setCycleCount(mp.INDEFINITE);
        mp.setAutoPlay(true);

        txt_firstName.setStyle("-fx-prompt-text-fill: WHITE;"
                + "-fx-text-inner-color: WHITE;");
        txt_lastName.setStyle("-fx-prompt-text-fill: WHITE;"
                + "-fx-text-inner-color: WHITE;");
        txt_email.setStyle("-fx-prompt-text-fill: WHITE;"
                + "-fx-text-inner-color: WHITE;");
        txt_Password1.setStyle("-fx-prompt-text-fill: WHITE;"
                + "-fx-text-inner-color: WHITE;");
        txt_password2.setStyle("-fx-prompt-text-fill: WHITE;"
                + "-fx-text-inner-color: WHITE;");
    }

    @FXML
    public void registerNewUser(ActionEvent Event) {
        if (validateInfo()) {
//                        PresentationFacade.getInstance().regUser((txt_lastName.getText() + ", " + txt_firstName.getText()), txt_email.getText(), txt_Password1.getText())

            label_warninginfo.setText("Registration Complete");
//                            backToLoginHandler(Event);

        }

        }

        @FXML
        public void backToLoginHandler
        (ActionEvent event
        
            ) {
        try {
                Parent login = FXMLLoader.load(getClass().getResource("Login.fxml"));
                Scene newScene = new Scene(login);
                Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                appStage.setScene(newScene);
                appStage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    

    private boolean validateInfo() {

        boolean check = false;
        if (!txt_firstName.getText().isEmpty() && !txt_lastName.getText().isEmpty() && !txt_email.getText().isEmpty() && !txt_Password1.getText().isEmpty() && !txt_password2.getText().isEmpty()) {
            if (txt_email.getText().contains("@") && txt_email.getText().contains(".")) {
                if (txt_Password1.getText().equals(txt_password2.getText())) {
                    if (txt_Password1.getText().length() >= 8) {
                        if (!choice_Country.getValue().toString().equals("Select Country")) {
                            check = true;
                            if (check) {
//                        PresentationFacade.getInstance().regUser((txt_lastName.getText() + ", " + txt_firstName.getText()), txt_email.getText(), txt_Password1.getText())
                                return true;
                            } else {
                                label_warninginfo.setText("Mail already registred");
                            }
                        } else {
                            label_warninginfo.setText("Please select your country");
                        }
                    } else {
                        label_warninginfo.setText("Password must be atleast 8 characters");
                    }
                } else {
                    label_warninginfo.setText("Passwords does not match");
                }
            } else {
                label_warninginfo.setText("Invalid email");
            }
        } else {
            label_warninginfo.setText("Please fill out the information");
        }
        // delete when done
        return false;
    }

}
