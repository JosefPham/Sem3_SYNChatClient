/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Connection.ConnectionFacade;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Sigurd E. Espersen
 */
public class SYNchatController implements Initializable {

    ConnectionFacade facade = ConnectionFacade.getInstance();

    @FXML
    private JFXButton btn_publicChat;
    @FXML
    private JFXTextArea txtArea_totalChat;
    @FXML
    private JFXButton btn_send;
    @FXML
    private ImageView pic_profile;
    @FXML
    private Label label_userInfo;
    @FXML
    private JFXButton btn_privatChat;
    @FXML
    private AnchorPane Popup_pane;
    @FXML
    private JFXButton btn_popup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Popup_pane.setDisable(true);
    }

    @FXML
    public void startPublicChat(ActionEvent event) {
        PresentationFacade.Ibus.publicThreads();
    }

    @FXML
    private void sendMsg(ActionEvent event) {
    }

    @FXML
    private void startPrivatChat(ActionEvent event) {
        PresentationFacade.Ibus.privateThreads();
    }

    @FXML
    private void popOpHandler(ActionEvent event) {
        if(!Popup_pane.isDisabled()) {
            Popup_pane.setDisable(true);
            Popup_pane.toBack();
        } else if(Popup_pane.isDisabled()) {
            Popup_pane.setDisable(false);
            Popup_pane.toFront();
        }
//        Popup_pane.toBack();
    }

}
