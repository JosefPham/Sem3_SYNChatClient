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
    private JFXTextArea txtArea_yourChat;
    @FXML
    private JFXButton btn_send;
    @FXML
    private ImageView pic_profile;
    @FXML
    private Label label_userInfo;
    @FXML
    private JFXButton btn_privatChat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void startPublicChat(ActionEvent event) {
        System.out.println("Chat starting...");
        facade.getClient().startPublicThreads();
        System.out.println("Ready to chat");
    }

    @FXML
    private void sendMsg(ActionEvent event) {
    }

    @FXML
    private void startPrivatChat(ActionEvent event) {
    }
    
}
