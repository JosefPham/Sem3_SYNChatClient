/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Acquaintance.IController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

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
    @FXML
    private Label label_profile;
    @FXML
    private Label label_about;
    @FXML
    private Label label_changeStatus;
    @FXML
    private JFXButton btn_addFriend;
    @FXML
    private JFXButton btn_removeFriend;
    @FXML
    private ScrollPane pane_friends;
    @FXML
    private JFXTextArea txtArea_friends;
    private List fList = PresentationFacade.getInstance().getUser().getFriends().getFriendlist();
    private List pList = PresentationFacade.getInstance().getSelectedUser().getFriends().getFriendlist();
    boolean t = true;
    boolean b = true;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pane_friends.setVisible(false);
        label_profile.setText(PresentationFacade.getInstance().getSelectedUser().getProfile().getFirstName() + "'s profile");
        label_about.setText("About " + PresentationFacade.getInstance().getSelectedUser().getProfile().getFirstName() + ":");
        textField_fname.setText(PresentationFacade.getInstance().getSelectedUser().getProfile().getFirstName());
        textField_lname.setText(PresentationFacade.getInstance().getSelectedUser().getProfile().getLastName());
        textField_nationality.setText(PresentationFacade.getInstance().getSelectedUser().getProfile().getNationality().toString());
        textArea_profileInfo.setText(PresentationFacade.getInstance().getSelectedUser().getProfile().getProfileText());
        for (int i = 0; i < fList.size(); i++) {
            if (fList.get(i).equals(PresentationFacade.getInstance().getSelectedUser().getUserID())) {
                btn_addFriend.setVisible(false);
            }
        }
        if(btn_addFriend.isVisible()) {
            btn_removeFriend.setVisible(false);
        }
    }

    @FXML
    private void btn_home_action(ActionEvent event) {
        PresentationFacade.getInstance().changeScene("SYNchat.fxml");
    }

    @Override
    public void injectStage(Stage stage) {
        PresentationFacade.stage = stage;
    }

    @FXML
    private void on_addFriend(ActionEvent event) {
        if (PresentationFacade.getInstance().addFriend(PresentationFacade.getInstance().getSelectedUser().getUserID())) {
            label_changeStatus.setText("Friend added");
            btn_removeFriend.setVisible(true);
            btn_addFriend.setVisible(false);
        } else {
            label_changeStatus.setText("Something went wrong");
        }
    }

    @FXML
    private void on_viewFriends(ActionEvent event) {
        if (t) {
            for (int i = 0; i < pList.size(); i++) {
                txtArea_friends.appendText(pList.get(i).toString() + "\n");
            }
            t = false;
        }
        if (b) {
            pane_friends.setVisible(true);
            b = false;
        } else {
            pane_friends.setVisible(false);
            b = true;
        }
    }

    @FXML
    private void on_removeFriend(ActionEvent event) {
        if (PresentationFacade.getInstance().removeFriend(PresentationFacade.getInstance().getSelectedUser().getUserID())) {
            label_changeStatus.setText("Friend removed");
            btn_addFriend.setVisible(true);
            btn_removeFriend.setVisible(false);
        } else {
            label_changeStatus.setText("Something went wrong");
        }
    }

}
