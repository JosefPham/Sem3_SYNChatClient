/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import java.net.URL;
import java.util.ArrayList;
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
    private ChoiceBox<String> choice_Country;
    @FXML
    private TextField txt_firstName;
    @FXML
    private TextField txt_lastName;
    @FXML
    private Label label_warninginfo;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        choice_Country.getItems().addAll("Select Country", "Afghanistan", "Albania", "Angola", "Argentina", "Armenia", "Australia", "Azerbaijan", "Bahamas", "Bangladesh", "Barbados", "Belgium",
                "Belize", "Bolivia", "Bosnia", "Botswana", "Brazil", "Bulgaria", "Cambodia", "Cameroon", "Canada", "Chile", "China", "Colombia", "Comoros", "Costa Rica", "Croatia", "Cuba", "Cypres",
                "Czech Republic", "Denmark", "Dominican Republic", "Ecuador", "Egypt", "El Salvador", "Estonia", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Georgia", "Ghana", "Greece", "Grenada",
                "Guetemala", "Guyana", "Haiti", "Hounduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya",
                "North Korea", "South Korea", "Kosovo", "Kuwait", "Kyrgyztan", "Latva", "Lebanon", "Liberia", "Libya", "Lithuania", "Luxembourg", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives",
                "Mali", "Malta", "Mexico", "Monaco", "Mongolia", "Montenegro", "Mozambique", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Nigeria", "Norway", "Oman", "pakistan", "Panama", "Papua New Guinea",
                "Paraguay", "Peru", "philippines", "Poland", "Portugal", "Qatar", "Romania", "Russia", "Saint Lucia", "Samoa", "San Marino", "Saudi Arabia", "Senegal", "Serbia", "Sierra Leone", "Singapore",
                "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "Spain", "Sri Lanka", "Sudan", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tanzania", "Thailand", "Tunisia",
                "Turkey", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United states", "Uruguay", "Uzbekistan", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe");
        choice_Country.setValue("Select Country");
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
