package Presentation;

import Acquaintance.IController;
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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        IController controller = loader.getController();
        controller.injectStage(stage);
        stage.setTitle("SYNchat");

        javafx.scene.image.Image image = new javafx.scene.image.Image("/Assets/SYNlogo_mini.png");

        stage.getIcons().add(image);

        stage.setScene(newScene);
        stage.show();
//        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
        //stage.setResizable(false);
    }

    //Starts FXML with start()
    public void startApplication(String[] args) {
        launch(args);
    }

}
