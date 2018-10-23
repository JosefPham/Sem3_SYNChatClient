/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Group 9
 */
public class SYNchat extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage.setTitle("SYNchat");
        
        javafx.scene.image.Image image = new javafx.scene.image.Image("/Assets/SYNlogo_mini.png");
        
        stage.getIcons().add(image);
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        //stage.setResizable(false);
    }
    
    //Starts FXML with start()
    public void startApplication (String[] args) {
        launch(args);
    }
    
}
