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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Pottemuld
 */
public class ChangeInfoController implements Initializable {

    @FXML
    private PasswordField pwField_oldPW;
    @FXML
    private PasswordField pwField_newPW;
    @FXML
    private Tab textField_oldEmail;
    @FXML
    private TextField textField_newEmail;
    @FXML
    private TextField textField_confirmEmail;
    @FXML
    private PasswordField pwField_changeEmailPassword;
    @FXML
    private PasswordField pwField_confirmPW;
    @FXML
    private Label label_warning;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void changeMail(ActionEvent event) {
        if (textField_newEmail.getText().equals(textField_confirmEmail.getText())) {
            if (PresentationFacade.getInstance().changeMail(pwField_changeEmailPassword.getText(), textField_newEmail.getText())) {
                label_warning.setText("Mail was successfully changed");
            } else {
                label_warning.setText("Something went wrong");
            }
        } else if (!textField_newEmail.getText().equals(textField_confirmEmail.getText())) {
            label_warning.setText("Mails does not match!");
        }
    }
    
    public void changepw(ActionEvent event) {
        if(pwField_newPW.getText().equals(pwField_confirmPW.getText())) {
            if(PresentationFacade.getInstance().changePw(pwField_oldPW.getText(), pwField_newPW.getText())) {
                label_warning.setText("Password has been successfully changed");
            } else {
                label_warning.setText("Something went wrong!");
            }
        } else if(!pwField_newPW.getText().equals(pwField_confirmPW.getText())) {
            label_warning.setText("Passwords does not match!");
        }
    }

}
