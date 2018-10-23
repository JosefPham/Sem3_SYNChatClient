/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        choice_Country.getItems().addAll("Select Country", "Afghanistan", "Albania", "Angola", "Argentina", "Armenia", "Australia", "Azerbaijan", "Bahamas", "Bangladesh", "Barbados", "Belgium",
                "Belize", "Bolivia", "Bosnia", "Botswana", "Brazil", "Bulgaria", "Cambodia", "Cameroon", "Canada", "Chile", "China", "Colombia", "Comoros", "Costa Rica", "Croatia", "Cuba",
                "Cypres", "Czech Republic", "Denmark", "Dominican Republic", "Ecuador", "Egypt", "El Salvador", "Estonia", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Georgia", "Ghana",
                "Greece", "Grenada", "Guetemala", "Guyana", "Haiti", "Hounduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan",
                "Jordan", "Kazakhstan", "Kenya", "North Korea", "South Korea", "Kosovo", "Kuwait", "Kyrgyztan", "Latva", "Lebanon", "Liberia", "Libya", "Lithuania", "Luxembourg", "Macedonia",
                "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Mexico", "Monaco", "Mongolia", "Montenegro", "Mozambique", "Nepal", "Netherlands", "New Zealand", "Nicaragua",
                "Nigeria", "Norway", "Oman", "pakistan", "Panama", "Papua New Guinea", "Paraguay", "Peru", "philippines", "Poland", "Portugal", "Qatar", "Romania", "Russia", "Saint Lucia",
                "Samoa", "San Marino", "Saudi Arabia", "Senegal", "Serbia", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "Spain",
                "Sri Lanka", "Sudan", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tanzania", "Thailand", "Tunisia", "Turkey", "Uganda", "Ukraine", "United Arab Emirates",
                "United Kingdom", "United states", "Uruguay", "Uzbekistan", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe");
        choice_Country.setValue("Select Country");
    }

    public void registerNewUser(ActionEvent Event) {
        if (txt_Password1 == txt_password2) {
            if (txt_Password1.getText().length() >= 8) {

                if (PresentationFacade.getInstance().regUser((txt_lastName.getText() + ", " + txt_firstName.getText()), txt_email.getText(), txt_Password1.getText())) {
                    //success change scene to login with confirmation message
                } else {
                    label_warninginfo.setText("Mail already registred");
                }
            } else {
                label_warninginfo.setText("Password must be atleast 8 characters");
            }
        } else {
            label_warninginfo.setText("Passwords does not match");
        }
    }

    @FXML
    public void cancelRegistration(ActionEvent event) {
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
}
