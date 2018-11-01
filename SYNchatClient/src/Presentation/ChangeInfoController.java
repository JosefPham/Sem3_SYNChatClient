/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
