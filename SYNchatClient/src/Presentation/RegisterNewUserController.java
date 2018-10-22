/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    private ChoiceBox<?> choice_Country;
    @FXML
    private TextField txt_firstName;
    @FXML
    private TextField txt_lastName;
    @FXML
    private Label label_warninginfo;
    
    ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList("something", "something else"));

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void registerNewUser(ActionEvent Event) {
        if (txt_Password1 == txt_password2) {
            if (!(txt_Password1.getText().length() >= 8)) {

                if (PresentationFacade.getInstance().registerNewUser(txt_firstName.getText(), txt_lastName.getText(), txt_email.getText(), txt_Password1.getText())) {
                    //success change scene to login with confirmation message
                } else {
                    label_warninginfo.setText("Something went wrong...");
                }
            } else {
                label_warninginfo.setText("Password must be atleast 8 characters");
            }
        } else {
            label_warninginfo.setText("Passwords does ont match");
        }
    }
}
