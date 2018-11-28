package Presentation;

import Acquaintance.IController;
import Acquaintance.Nationality;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ChangeInfoController implements IController, Initializable {

    @FXML
    private PasswordField pwField_oldPW;
    @FXML
    private PasswordField pwField_newPW;
    @FXML
    private TextField textField_newEmail;
    @FXML
    private TextField textField_confirmEmail;
    @FXML
    private PasswordField pwField_confirmPW;
    @FXML
    private JFXTextField textField_fname;
    @FXML
    private JFXTextField textField_nationality;
    @FXML
    private JFXTextField textField_lname;
    @FXML
    private AnchorPane pane_countries;
    @FXML
    private Label country_DK;
    @FXML
    private Label country_USA;
    @FXML
    private Label country_Japan;
    private Boolean countryB = true;
    @FXML
    private JFXTextArea textArea_profileInfo;
    @FXML
    private Label label_changeStatus;

    private Nationality nat;
    @FXML
    private JFXTextField textField_oldMail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pane_countries.setVisible(false);
        //Inserting data into textFields if there is a value to the specific field
        textField_fname.setText(PresentationFacade.getInstance().getUser().getProfile().getFirstName());
        textField_lname.setText(PresentationFacade.getInstance().getUser().getProfile().getLastName());
        textField_nationality.setText(PresentationFacade.getInstance().getUser().getProfile().getNationality().toString());
        textArea_profileInfo.setText(PresentationFacade.getInstance().getUser().getProfile().getProfileText());
    }

    @FXML
    private void changeFname(MouseEvent event) {
        if (!textField_fname.isEditable()) {
            textField_fname.setEditable(true);
        } else if (textField_fname.isEditable()) {
            //TODO - Opdater SQL med nyt fornavn
        }
    }

    @FXML
    private void changeLname(MouseEvent event) {
        if (!textField_lname.isEditable()) {
            textField_lname.setEditable(true);
        } else if (textField_lname.isEditable()) {
            //TODO - Opdater SQL med nyt efternavn
        }
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
        PresentationFacade.getInstance().changeScene("SYNchat.fxml");
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
        //TODO - Opdater SQL med land
        countryB = true;
    }

    @FXML
    private void countryUSA_handle(MouseEvent event) {
        textField_nationality.setText("USA");
        pane_countries.setVisible(false);
        //TODO - Opdater SQL med land
        countryB = true;
    }

    @FXML
    private void countryJapan_handle(MouseEvent event) {
        textField_nationality.setText("Japan");
        pane_countries.setVisible(false);
        //TODO - Opdater SQL med land
        countryB = true;
    }

    @Override
    public void injectStage(Stage stage) {
        PresentationFacade.stage = stage;
    }

    @FXML
    private void changeProfile(ActionEvent event) {
        String fname = PresentationFacade.getInstance().getUser().getProfile().getFirstName();
        String lname = PresentationFacade.getInstance().getUser().getProfile().getLastName();
        nat = PresentationFacade.getInstance().getUser().getProfile().getNationality();
        String ptext = PresentationFacade.getInstance().getUser().getProfile().getProfileText();
        String pic = PresentationFacade.getInstance().getUser().getProfile().getPicture();
        if (!textField_fname.getText().equals("")) {
            fname = textField_fname.getText();
        }
        if (!textField_lname.getText().equals("")) {
            lname = textField_lname.getText();
        }
        if (!textArea_profileInfo.getText().equals("")) {
            ptext = textArea_profileInfo.getText();
        }
        if (!textField_nationality.getText().equals("")) {
            switch (textField_nationality.getText()) {
                case "Denmark":
                    nat = Nationality.Denmark;
                    break;
                case "USA":
                    nat = Nationality.USA;
                    break;
                case "Japan":
                    nat = Nationality.Japan;
                    break;
            }
        }
        if (pwField_oldPW.getText().equals("") && pwField_newPW.getText().equals("") && pwField_confirmPW.getText().equals("")) {
            if (textField_newEmail.getText().equals("") && textField_confirmEmail.getText().equals("") && textField_oldMail.getText().equals("")) {
                PresentationFacade.getInstance().updateUserInfo("", "", fname, lname, nat, ptext, pic);
                label_changeStatus.setText("Profile updated!");
            } else {
                if (textField_newEmail.getText().contains("@") && textField_newEmail.getText().contains(".")) {
                    if (textField_newEmail.getText().equals(textField_confirmEmail.getText())) {
                        if (PresentationFacade.getInstance().checkMail(textField_oldMail.getText())) {
                            if (PresentationFacade.getInstance().updateUserInfo("", textField_newEmail.getText().toLowerCase(), fname, lname, nat, ptext, pic)) {
                                label_changeStatus.setText("Profile updated!");
                            } else {
                                label_changeStatus.setText("Something went wrong");
                            }
                        } else {
                            label_changeStatus.setText("Incorrect password or email");
                        }
                    } else {
                        label_changeStatus.setText("Emails does not match");
                    }
                } else {
                    label_changeStatus.setText("Invalid email");
                }
            }
        } else {
            if (pwField_newPW.getText().length() > 7) {
                if (pwField_newPW.getText().equals(pwField_confirmPW.getText())) {
                    if (PresentationFacade.getInstance().checkPW(pwField_oldPW.getText())) {
                        if (PresentationFacade.getInstance().updateUserInfo(pwField_newPW.getText(), "", fname, lname, nat, ptext, pic)) {
                            label_changeStatus.setText("Profile updated!");
                        } else {
                            label_changeStatus.setText("Something went wrong");
                        }
                    } else {
                        label_changeStatus.setText("Incorrect password");
                    }
                } else {
                    label_changeStatus.setText("Passwords does not match");
                }
            } else {
                label_changeStatus.setText("Password must be atleast 8 characters");
            }
        }
    }
}
