/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Acquaintance.IController;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sigurd E. Espersen
 */
public class ViewProfileController implements IController, Initializable {

    @FXML
    private JFXTextField textField_fname;
    @FXML
    private JFXTextField textField_lname;
    @FXML
    private JFXTextField textField_nationality;
    @FXML
    private JFXTextArea textArea_profileInfo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textField_fname.setText(PresentationFacade.getInstance().getSelectedUser().getProfile().getFirstName());
        textField_lname.setText(PresentationFacade.getInstance().getSelectedUser().getProfile().getLastName());
        textField_nationality.setText(PresentationFacade.getInstance().getSelectedUser().getProfile().getNationality().toString());
        textArea_profileInfo.setText(PresentationFacade.getInstance().getSelectedUser().getProfile().getProfileText());
    }

    @FXML
    private void btn_home_action(ActionEvent event) {
        PresentationFacade.getInstance().changeScene("SYNchat.fxml");
    }

    @Override
    public void injectStage(Stage stage) {
        PresentationFacade.stage = stage;
    }

}
