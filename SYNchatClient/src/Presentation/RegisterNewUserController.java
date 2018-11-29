package Presentation;

import Acquaintance.IController;
import Acquaintance.Nationality;
import com.jfoenix.controls.JFXPasswordField;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Group 9
 */
public class RegisterNewUserController implements IController, Initializable {

    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_Password1;
    @FXML
    private TextField txt_firstName;
    @FXML
    private TextField txt_lastName;
    @FXML
    private Label label_warninginfo;
    @FXML
    private MediaView mv_background;
    private MediaPlayer mp;
    private Media me;
    private Boolean countryB = true;
    @FXML
    private JFXPasswordField txt_Password2;
    @FXML
    private AnchorPane pane_countries;
    @FXML
    private Label country_DK;
    @FXML
    private Label country_USA;
    @FXML
    private Label country_Japan;
    @FXML
    private Label label_country;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pane_countries.setVisible(false);
        String path = new File("src/Assets/backgroundAnimation.mp4").getAbsolutePath();
        me = new Media(new File(path).toURI().toString());
        mp = new MediaPlayer(me);
        mv_background.setMediaPlayer(mp);
        mp.setCycleCount(mp.INDEFINITE);
        mp.setAutoPlay(true);
        txt_firstName.setStyle("-fx-prompt-text-fill: #1d1f21;"
                + "-fx-text-inner-color: #1d1f21;");
        txt_lastName.setStyle("-fx-prompt-text-fill: #1d1f21;"
                + "-fx-text-inner-color: #1d1f21;");
        txt_email.setStyle("-fx-prompt-text-fill: #1d1f21;"
                + "-fx-text-inner-color: #1d1f21;");
        txt_Password1.setStyle("-fx-prompt-text-fill: #1d1f21;"
                + "-fx-text-inner-color: #1d1f21;");
        txt_Password2.setStyle("-fx-prompt-text-fill: #1d1f21;"
                + "-fx-text-inner-color: #1d1f21;");
    }

    @FXML
    public void registerNewUser(ActionEvent Event) {
        if (validateInfo()) {
            if (PresentationFacade.getInstance().regUser(txt_firstName.getText(), txt_lastName.getText(), txt_email.getText().toLowerCase(), txt_Password2.getText(), Nationality.valueOf(label_country.getText()))) {
                label_warninginfo.setText("Registration Complete");
                PresentationFacade.getInstance().changeScene("Login.fxml");
            } else {
                label_warninginfo.setText("Mail already registred");
            }

        }
    }

    @FXML
    public void backToLoginHandler(ActionEvent event) {
        PresentationFacade.getInstance().changeScene("Login.fxml");
    }

    private boolean validateInfo() {
        if (!txt_firstName.getText().isEmpty() && !txt_lastName.getText().isEmpty() && !txt_email.getText().isEmpty() && !txt_Password1.getText().isEmpty() && !txt_Password2.getText().isEmpty()) {
            if (txt_email.getText().contains("@") && txt_email.getText().contains(".")) {
                if (txt_Password1.getText().equals(txt_Password2.getText())) {
                    if (txt_Password1.getText().length() >= 8) {
                            return true;                                                
                    } else {
                        label_warninginfo.setText("Password must be atleast 8 characters");
                    }
                } else {
                    label_warninginfo.setText("Passwords does not match");
                }
            } else {
                label_warninginfo.setText("Invalid email");
            }
        } else {
            label_warninginfo.setText("Please fill out the information");
        }
        return false;
    }

    @FXML
    private void selectCountry_handler(MouseEvent event) {
        if(countryB) {
        pane_countries.setVisible(true);
        countryB = false;
        } else {
            pane_countries.setVisible(false);
            countryB = true;
        }
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
        pane_countries.setVisible(false);
        countryB = true;
        label_country.setText("Denmark");
    }

    @FXML
    private void countryUSA_handle(MouseEvent event) {        
        pane_countries.setVisible(false);
        countryB = true;
        label_country.setText("USA");
    }

    @FXML
    private void countryJapan_handle(MouseEvent event) {
        pane_countries.setVisible(false);
        countryB = true;
        label_country.setText("Japan");
    }

    @Override
    public void injectStage(Stage stage) {
        PresentationFacade.stage = stage;
    }
}
