/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btn_login_action(ActionEvent event) {
        if (validateInfo()) {
            loginHandler();
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
    
    private void loginHandler() {
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
                    break;
                default:
                    label_wrongInfo.setText("Something went wrong");
            }
    }
}
