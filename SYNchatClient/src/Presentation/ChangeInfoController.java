/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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
    private TextField textField_newEmail;
    @FXML
    private TextField textField_confirmEmail;
    @FXML
    private PasswordField pwField_changeEmailPassword;
    @FXML
    private PasswordField pwField_confirmPW;
    @FXML
    private Label label_warning;
    @FXML
    private Button btn_password;
    @FXML
    private Button btn_mail;
    @FXML
    private JFXTextField textField_fname;
    @FXML
    private JFXTextField textField_nationality;
    @FXML
    private JFXTextField textField_email;
    @FXML
    private JFXTextField textField_lname;
    @FXML
    private Label label_warning1;
    @FXML
    private AnchorPane pane_countries;
    @FXML
    private Label country_DK;
    @FXML
    private Label country_USA;
    @FXML
    private Label country_Japan;
    private Boolean countryB = true;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pane_countries.setVisible(false);
    }

    @FXML
    public void changeMail(ActionEvent event) {
        label_warning.setText("");
        if (textField_newEmail.getText().equals(textField_confirmEmail.getText())) {
            if (PresentationFacade.getInstance().changeMail(pwField_changeEmailPassword.getText(), textField_newEmail.getText()) == 1) {
                label_warning.setText("Mail was successfully changed");
            } else {
                label_warning.setText("Something went wrong");
            }
        } else if (!textField_newEmail.getText().equals(textField_confirmEmail.getText())) {
            label_warning.setText("Mails does not match!");
        }
    }

    @FXML
    public void changepw(ActionEvent event) {
        label_warning.setText("");
        if (pwField_newPW.getText().equals(pwField_confirmPW.getText())) {
            if (PresentationFacade.getInstance().changePw(pwField_oldPW.getText(), pwField_newPW.getText()) == 1) {
                label_warning.setText("Password has been successfully changed");
            } else if (PresentationFacade.getInstance().changePw(pwField_oldPW.getText(), pwField_newPW.getText()) == 1) {
                label_warning.setText("Something went wrong!");
            }
        } else if (!pwField_newPW.getText().equals(pwField_confirmPW.getText())) {
            label_warning.setText("Passwords does not match!");
        }
    }

    @FXML
    private void changeFname(MouseEvent event) {
    }

    @FXML
    private void changeLname(MouseEvent event) {
    }

    @FXML
    private void changeNat(MouseEvent event) {
        if (countryB) {
            pane_countries.setVisible(true);
            countryB = false;
        } else {
            pane_countries.setVisible(false);
            countryB = true;
        }
    }

    @FXML
    private void btn_home_action(ActionEvent event) {
    }

    @FXML
    private void countryDK_out(MouseEvent event) {
        country_DK.setUnderline(false);
    }

    @FXML
    private void countryDK_in(MouseEvent event) {
        country_DK.setUnderline(true);
    }

    @FXML
    private void countryUSA_out(MouseEvent event) {
        country_USA.setUnderline(false);
    }

    @FXML
    private void countryUSA_in(MouseEvent event) {
        country_USA.setUnderline(true);
    }

    @FXML
    private void countryJapan_out(MouseEvent event) {
        country_Japan.setUnderline(false);
    }

    @FXML
    private void countryJapan_in(MouseEvent event) {
        country_Japan.setUnderline(true);
    }

    @FXML
    private void countryDK_handle(MouseEvent event) {
        textField_nationality.setText("Denmark");
        pane_countries.setVisible(false);
        countryB = true;
    }

    @FXML
    private void countryUSA_handle(MouseEvent event) {
        textField_nationality.setText("USA");
        pane_countries.setVisible(false);
        countryB = true;
    }

    @FXML
    private void countryJapan_handle(MouseEvent event) {
        textField_nationality.setText("Japan");
        pane_countries.setVisible(false);
        countryB = true;
    }

}
