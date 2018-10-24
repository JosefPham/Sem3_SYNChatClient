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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void popOutHandler(MouseEvent event) {
        Popup_pane.toBack();
    }

    @FXML
    private void popInHandler(MouseEvent event) {
        Popup_pane.toFront();
    }

}
