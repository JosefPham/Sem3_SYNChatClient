package Presentation;

import Acquaintance.IController;
import Acquaintance.IMessage;
import Acquaintance.IUser;
import Acquaintance.Nationality;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Group 9
 */
public class SYNchatController implements IController, Initializable {

    private Thread t = null;
    private IMessage iMsg;
    private Map<Integer, IUser> comparisonMap = new HashMap<>();
    private boolean popUp = true;
    private boolean cog = true;
    private boolean settings = true;
    private boolean scrollPane = true;
    private boolean isPublicChatting = false;
    private boolean loadPublicChat = true;
    private String btnStyle;
    private Text listName;
    private ImageView listPic;
    private ImageView listCountry;

    @FXML
    private JFXButton btn_publicChat;
    @FXML
    private JFXButton btn_send;
    @FXML
    private ImageView pic_profile;
    @FXML
    private JFXButton btn_privatChat;
    @FXML
    private AnchorPane Popup_pane;
    @FXML
    private TextField txtArea_YourChat;
    @FXML
    private MediaView mv_background;
    private MediaPlayer mp;
    private Media me;
    @FXML
    private AnchorPane pane_Welcome;
    @FXML
    private AnchorPane pane_cogView;
    @FXML
    private AnchorPane pane_chat;
    @FXML
    private JFXButton btn_logout;
    @FXML
    private AnchorPane pane_settings;
    @FXML
    private ImageView hamburger_settings;
    @FXML
    private Label label_viewProfile;
    @FXML
    private Label label_changeProfilePic;
    @FXML
    private ScrollPane ScrollPane_AvatarChooser;
    @FXML
    private Label label_userName;
    @FXML
    private Label label_userCountry;
    @FXML
    private JFXTextArea SYN_news;
    @FXML
    private JFXTextArea SYN_features;
    @FXML
    private JFXTextArea SYN_welcome;
    @FXML
    private Label label_logo;
    @FXML
    private Label label_features;
    @FXML
    private Label label_welcome;
    @FXML
    private Label label_news;
    @FXML
    private ImageView image_yourCountry;
    @FXML
    private TextFlow txtFlow_publicChat;
    @FXML
    private TextFlow txtFlow_publicMsg;
    @FXML
    private ScrollPane scrollpane_chat;
    @FXML
    private ScrollPane scrollpane_list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtFlow_publicMsg.heightProperty().addListener((observable) -> {
            scrollpane_chat.setVvalue(1D);
        });
        txtFlow_publicChat.heightProperty().addListener((observable) -> {
            scrollpane_list.setVvalue(1D);
        });
        btnStyle = btn_publicChat.getStyle();
        btn_send.setStyle(btn_send.getStyle() + "-fx-text-fill: white");
        txtArea_YourChat.setStyle("-fx-text-fill: white");
        String path = new File("src/Assets/Graphics/backgroundAnimation.mp4").getAbsolutePath();
        me = new Media(new File(path).toURI().toString());
        mp = new MediaPlayer(me);
        mv_background.setMediaPlayer(mp);
        mp.setCycleCount(mp.INDEFINITE);
        mp.setAutoPlay(true);
        pane_cogView.setVisible(false);
        Popup_pane.setVisible(false);
        pane_chat.setDisable(true);
        pane_settings.setVisible(false);
        ScrollPane_AvatarChooser.setVisible(false);
        label_userName.setText(PresentationFacade.getInstance().getUser().getProfile().getFirstName());
        label_userCountry.setText(PresentationFacade.getInstance().getUser().getProfile().getNationality().toString());
        NationalityInterface(PresentationFacade.getInstance().getUser().getProfile().getNationality());
        switch (PresentationFacade.getInstance().getUser().getProfile().getNationality()) {
            case Denmark:
                image_yourCountry.setImage(new Image(new File("src/Assets/Flags/Flag_DK_Color.png").toURI().toString()));
                break;
            case USA:
                image_yourCountry.setImage(new Image(new File("src/Assets/Flags/Flag_USA_Color.png").toURI().toString()));
                break;
            case Japan:
                image_yourCountry.setImage(new Image(new File("src/Assets/Flags/Flag_Japan_Color.png").toURI().toString()));
                break;
        }
        pic_profile.setImage(new Image(new File(PresentationFacade.getInstance().getUser().getProfile().getPicture()).toURI().toString()));
    }

    @Override
    public void injectStage(Stage stage) {
        PresentationFacade.stage = stage;
    }

    @FXML
    public void startPublicChat(ActionEvent event) {
        if (!isPublicChatting) {
            PresentationFacade.getInstance().commandHandling("!SYN!-PublicChat-!SYN!");
            isPublicChatting = !isPublicChatting;
            pane_chat.setDisable(false);
            pane_Welcome.toBack();
            PresentationFacade.Ibus.publicThreads();
            btn_privatChat.setStyle(btnStyle);
            btn_publicChat.setStyle(btnStyle + "-fx-background-color: #162ab7");
            this.t = startRun();
        }
    }

    @FXML
    private void startPrivatChat(ActionEvent event) {
        txtFlow_publicMsg.getChildren().removeAll(txtFlow_publicMsg.getChildren());
        if (isPublicChatting) {
            commandHandling("!SYN!-PublicChat-!SYN!");
            isPublicChatting = !isPublicChatting;
        }
        pane_chat.setDisable(false);
        pane_Welcome.toBack();
        btn_publicChat.setStyle(btnStyle);
        btn_privatChat.setStyle(btnStyle + "-fx-background-color: #162ab7");
    }

    /**
     * Thread that runs while user is in public chat. Checks for incomming
     * messages and new users logging into public chat.
     *
     * @return
     */
    private synchronized Thread startRun() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                do {
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SYNchatController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } while (PresentationFacade.getInstance().getpUserMap() == null);
                if (loadPublicChat) {
                    for (int i : PresentationFacade.getInstance().getpUserMap().keySet()) {
                        updatepUserMap(PresentationFacade.getInstance().getpUserMap().get(i));
                        comparisonMap.put(i, PresentationFacade.getInstance().getpUserMap().get(i));
                    }
                }
                loadPublicChat = false;
                String comparisonString = "";
                int comparisonInt = -1;
                while (isPublicChatting) {
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SYNchatController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (PresentationFacade.getInstance().getIMsg() != null) {
                        iMsg = PresentationFacade.getInstance().getIMsg();
                        int senderID = iMsg.getSenderID();
                        String context = iMsg.getContext();
                        if (!context.equals(comparisonString) || (senderID != comparisonInt)) {
                            comparisonString = context;
                            comparisonInt = senderID;
                            receivePublicMsg();
                        }
                    }
                    if (PresentationFacade.getInstance().getpUserMap() != null) {
                        for (int key : PresentationFacade.getInstance().getpUserMap().keySet()) {
                            if (!comparisonMap.containsKey(key)) { //add
                                updatepUserMap(PresentationFacade.getInstance().getpUserMap().get(key));
                            }
                        }
                        for (int i : comparisonMap.keySet()) { //remove
                            if (!PresentationFacade.getInstance().getpUserMap().containsKey(i)) {
                                updatepUserMap(comparisonMap.get(i));
                            }
                        }
                    }
                }
            }
        };
        Thread t = new Thread(runnable);

        t.setDaemon(
                true);
        t.start();
        return t;
    }

    @FXML
    private void sendMsg(ActionEvent event) {
        if (!txtArea_YourChat.getText().trim().isEmpty()) {
            PresentationFacade.getInstance().sendPublicMsg(txtArea_YourChat.getText());
            txtArea_YourChat.clear();
        }
    }

    /**
     * Appends the incoming message to the GUI.
     */
    public void receivePublicMsg() {
        Date date = new Date();
        date.setTime(iMsg.getTimestamp().toEpochMilli());
        String timeStamp = new SimpleDateFormat("HH.mm").format(date);
        Text senderName = new Text(PresentationFacade.getInstance().getpUserMap().get(iMsg.getSenderID()).getProfile().getFirstName() + ":\n");
        Text senderContent = new Text(iMsg.getContext() + "\n");
        Text senderTimeStamp = new Text(timeStamp + "\n\n");
        senderName.setFont(Font.font("Gill Sans MT", 10));
        senderContent.setFont(Font.font("Gill Sans MT", 16));
        senderTimeStamp.setFont(Font.font("Gill Sans MT", 10));
        senderName.setTranslateX(50);
        senderName.setTranslateY(43);
        senderContent.setTranslateX(50);
        senderContent.setTranslateY(40);
        senderTimeStamp.setTranslateX(50);
        senderTimeStamp.setTranslateY(40);
        senderName.setFill(Paint.valueOf("#A9A9A9"));
        senderContent.setFill(Paint.valueOf("WHITE"));
        senderTimeStamp.setFill(Paint.valueOf("#A9A9A9"));

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                txtFlow_publicMsg.getChildren().add(senderName);
                txtFlow_publicMsg.getChildren().add(senderContent);
                txtFlow_publicMsg.getChildren().add(senderTimeStamp);
            }
        });
    }

    /**
     * Updates the "online list" in public chat with new users.
     *
     * @param IUser
     */
    private void updatepUserMap(IUser user) {
        if (comparisonMap.containsKey(user.getUserID()) && loadPublicChat == false) {
            comparisonMap.remove(user.getUserID());
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    txtFlow_publicChat.getChildren().remove(listName);
                    txtFlow_publicChat.getChildren().remove(listPic);
                    txtFlow_publicChat.getChildren().remove(listCountry);
                }
            });
        } else {
            String imgCountry;
            switch (user.getProfile().getNationality()) {
                case Denmark:
                    imgCountry = "DK";
                    break;
                case USA:
                    imgCountry = "USA";
                    break;
                case Japan:
                    imgCountry = "Japan";
                    break;
                default:
                    imgCountry = "USA";
                    break;
            }
            ImageView imgC = new ImageView(new Image(new File("src/Assets/Flags/Flag_" + imgCountry + "_Color.png").toURI().toString()));
            imgC.fitHeightProperty().set(15);
            imgC.fitWidthProperty().set(15);
            imgC.setTranslateX(50);
            Text txt = new Text(user.getProfile().getFirstName() + " " + user.getProfile().getLastName() + "\n");
            txt.setFont(Font.font("Gill Sans MT", 20));
            txt.setTranslateX(50);
            txt.setTranslateY(20);
            txt.setOnMouseEntered((event) -> {
                txt.setUnderline(true);
            });
            txt.setOnMouseExited((event) -> {
                txt.setUnderline(false);
            });
            txt.setOnMousePressed((event) -> {
                PresentationFacade.getInstance().setSelectedUser(user);
                commandHandling("!SYN!-PublicChat-!SYN!");
                isPublicChatting = !isPublicChatting;
                PresentationFacade.getInstance().changeScene("ViewProfile.fxml");
            });
            ImageView imgP = new ImageView(new Image(new File(user.getProfile().getPicture()).toURI().toString()));
            imgP.fitHeightProperty().set(35);
            imgP.fitWidthProperty().set(35);
            listName = txt;
            listPic = imgP;
            listCountry = imgC;
            comparisonMap.put(user.getUserID(), user);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    txtFlow_publicChat.getChildren().add(listName);
                    txtFlow_publicChat.getChildren().add(listCountry);
                    txtFlow_publicChat.getChildren().add(listPic);
                }
            });
        }
    }

    private void commandHandling(String command) {
        PresentationFacade.getInstance().commandHandling(command);
        if (command.equals("!SYN!-PublicChat-!SYN!")) {
            t.stop(); //We know this is wrong, but since calling stop worked and interrupt didn't we went with it for now.
            PresentationFacade.getInstance().getpUserMap().remove(PresentationFacade.getInstance().getUser().getUserID());
            txtFlow_publicChat.getChildren().clear();
            loadPublicChat = true;
        }
    }

    @FXML
    private void logoutAction(ActionEvent event) {
        if (isPublicChatting) {
            commandHandling("!SYN!-PublicChat-!SYN!");
            isPublicChatting = !isPublicChatting;
        }
        commandHandling("!SYN!-logout-!SYN!");
        PresentationFacade.getInstance().changeScene("Login.fxml");
    }

    @FXML
    private void returnToWelcome(ActionEvent event) {
        if (isPublicChatting) {
            commandHandling("!SYN!-PublicChat-!SYN!");
            isPublicChatting = !isPublicChatting;
        }
        pane_chat.setDisable(true);
        pane_Welcome.toFront();
        btn_privatChat.setStyle(btnStyle);
        btn_publicChat.setStyle(btnStyle);
    }

    @FXML
    private void viewProfile(MouseEvent event) {
        if (isPublicChatting) {
            commandHandling("!SYN!-PublicChat-!SYN!");
            isPublicChatting = !isPublicChatting;
        }
        PresentationFacade.getInstance().changeScene("ChangeInfo.fxml");
    }

    @FXML
    private void changeProfilePicture(MouseEvent event) {
        if (scrollPane) {
            ScrollPane_AvatarChooser.setVisible(true);
            scrollPane = false;
        } else {
            ScrollPane_AvatarChooser.setVisible(false);
            scrollPane = true;
        }
    }

    @FXML
    private void cogHandler(MouseEvent event) {
        if (cog) {
            pane_cogView.setVisible(true);
            pane_cogView.toFront();
            cog = false;
        } else {
            pane_cogView.setVisible(false);
            ScrollPane_AvatarChooser.setVisible(false);
            pane_cogView.toBack();
            cog = true;
            scrollPane = true;
        }
    }

    @FXML
    private void hamburger_handler(MouseEvent event) {
        if (settings) {
            pane_settings.setVisible(true);
            pane_settings.toFront();
            btn_logout.toFront();
            hamburger_settings.toFront();
            settings = false;
        } else {
            pane_settings.setVisible(false);
            settings = true;
        }
    }

    @FXML
    private void popOpHandler(MouseEvent event) {
        if (popUp) {
            Popup_pane.toFront();
            Popup_pane.setVisible(true);
            popUp = false;
        } else {
            Popup_pane.toBack();
            Popup_pane.setVisible(false);
            popUp = true;
        }
    }

    @FXML
    private void viewProfile_out(MouseEvent event) {
        label_viewProfile.setUnderline(false);
    }

    @FXML
    private void viewProfile_in(MouseEvent event) {
        label_viewProfile.setUnderline(true);
    }

    @FXML
    private void changeProfilePicture_out(MouseEvent event) {
        label_changeProfilePic.setUnderline(false);
    }

    @FXML
    private void changeProfilePicture_in(MouseEvent event) {
        label_changeProfilePic.setUnderline(true);
    }

    @FXML
    private void AvatarChooser1(MouseEvent event) {
        pic_profile.setImage(new Image(new File("src/Assets/Avatars/Avatar_1.png").toURI().toString()));
        ScrollPane_AvatarChooser.setVisible(false);
        scrollPane = true;
        pane_cogView.setVisible(false);
        cog = true;
        PresentationFacade.getInstance().getUser().getProfile().setPicture("src/Assets/Avatars/Avatar_1.png");
        PresentationFacade.getInstance().updateUserInfo("", "", PresentationFacade.getInstance().getUser().getProfile().getFirstName(), PresentationFacade.getInstance().getUser().getProfile().getLastName(), PresentationFacade.getInstance().getUser().getProfile().getNationality(), PresentationFacade.getInstance().getUser().getProfile().getProfileText(), "src/Assets/Avatars/Avatar_1.png");
    }

    @FXML
    private void AvatarChooser2(MouseEvent event) {
        pic_profile.setImage(new Image(new File("src/Assets/Avatars/Avatar_2.png").toURI().toString()));
        ScrollPane_AvatarChooser.setVisible(false);
        scrollPane = true;
        pane_cogView.setVisible(false);
        cog = true;
        PresentationFacade.getInstance().getUser().getProfile().setPicture("src/Assets/Avatars/Avatar_2.png");
        PresentationFacade.getInstance().updateUserInfo("", "", PresentationFacade.getInstance().getUser().getProfile().getFirstName(), PresentationFacade.getInstance().getUser().getProfile().getLastName(), PresentationFacade.getInstance().getUser().getProfile().getNationality(), PresentationFacade.getInstance().getUser().getProfile().getProfileText(), "src/Assets/Avatars/Avatar_2.png");
    }

    @FXML
    private void AvatarChooser3(MouseEvent event) {
        pic_profile.setImage(new Image(new File("src/Assets/Avatars/Avatar_3.png").toURI().toString()));
        ScrollPane_AvatarChooser.setVisible(false);
        scrollPane = true;
        pane_cogView.setVisible(false);
        cog = true;
        PresentationFacade.getInstance().getUser().getProfile().setPicture("src/Assets/Avatars/Avatar_3.png");
        PresentationFacade.getInstance().updateUserInfo("", "", PresentationFacade.getInstance().getUser().getProfile().getFirstName(), PresentationFacade.getInstance().getUser().getProfile().getLastName(), PresentationFacade.getInstance().getUser().getProfile().getNationality(), PresentationFacade.getInstance().getUser().getProfile().getProfileText(), "src/Assets/Avatars/Avatar_3.png");
    }

    @FXML
    private void AvatarChooser4(MouseEvent event) {
        pic_profile.setImage(new Image(new File("src/Assets/Avatars/Avatar_4.png").toURI().toString()));
        ScrollPane_AvatarChooser.setVisible(false);
        scrollPane = true;
        pane_cogView.setVisible(false);
        cog = true;
        PresentationFacade.getInstance().getUser().getProfile().setPicture("src/Assets/Avatars/Avatar_4.png");
        PresentationFacade.getInstance().updateUserInfo("", "", PresentationFacade.getInstance().getUser().getProfile().getFirstName(), PresentationFacade.getInstance().getUser().getProfile().getLastName(), PresentationFacade.getInstance().getUser().getProfile().getNationality(), PresentationFacade.getInstance().getUser().getProfile().getProfileText(), "src/Assets/Avatars/Avatar_4.png");
    }

    @FXML
    private void AvatarChooser5(MouseEvent event) {
        pic_profile.setImage(new Image(new File("src/Assets/Avatars/Avatar_5.png").toURI().toString()));
        ScrollPane_AvatarChooser.setVisible(false);
        scrollPane = true;
        pane_cogView.setVisible(false);
        cog = true;
        PresentationFacade.getInstance().getUser().getProfile().setPicture("src/Assets/Avatars/Avatar_5.png");
        PresentationFacade.getInstance().updateUserInfo("", "", PresentationFacade.getInstance().getUser().getProfile().getFirstName(), PresentationFacade.getInstance().getUser().getProfile().getLastName(), PresentationFacade.getInstance().getUser().getProfile().getNationality(), PresentationFacade.getInstance().getUser().getProfile().getProfileText(), "src/Assets/Avatars/Avatar_5.png");
    }

    @FXML
    private void AvatarChooser6(MouseEvent event) {
        pic_profile.setImage(new Image(new File("src/Assets/Avatars/Avatar_6.png").toURI().toString()));
        ScrollPane_AvatarChooser.setVisible(false);
        scrollPane = true;
        pane_cogView.setVisible(false);
        cog = true;
        PresentationFacade.getInstance().getUser().getProfile().setPicture("src/Assets/Avatars/Avatar_6.png");
        PresentationFacade.getInstance().updateUserInfo("", "", PresentationFacade.getInstance().getUser().getProfile().getFirstName(), PresentationFacade.getInstance().getUser().getProfile().getLastName(), PresentationFacade.getInstance().getUser().getProfile().getNationality(), PresentationFacade.getInstance().getUser().getProfile().getProfileText(), "src/Assets/Avatars/Avatar_6.png");
    }

    @FXML
    private void AvatarChooser7(MouseEvent event) {
        pic_profile.setImage(new Image(new File("src/Assets/Avatars/Avatar_7.png").toURI().toString()));
        ScrollPane_AvatarChooser.setVisible(false);
        scrollPane = true;
        pane_cogView.setVisible(false);
        cog = true;
        PresentationFacade.getInstance().getUser().getProfile().setPicture("src/Assets/Avatars/Avatar_7.png");
        PresentationFacade.getInstance().updateUserInfo("", "", PresentationFacade.getInstance().getUser().getProfile().getFirstName(), PresentationFacade.getInstance().getUser().getProfile().getLastName(), PresentationFacade.getInstance().getUser().getProfile().getNationality(), PresentationFacade.getInstance().getUser().getProfile().getProfileText(), "src/Assets/Avatars/Avatar_7.png");
    }

    @FXML
    private void AvatarChooser8(MouseEvent event) {
        pic_profile.setImage(new Image(new File("src/Assets/Avatars/Avatar_8.png").toURI().toString()));
        ScrollPane_AvatarChooser.setVisible(false);
        scrollPane = true;
        pane_cogView.setVisible(false);
        cog = true;
        PresentationFacade.getInstance().getUser().getProfile().setPicture("src/Assets/Avatars/Avatar_8.png");
        PresentationFacade.getInstance().updateUserInfo("", "", PresentationFacade.getInstance().getUser().getProfile().getFirstName(), PresentationFacade.getInstance().getUser().getProfile().getLastName(), PresentationFacade.getInstance().getUser().getProfile().getNationality(), PresentationFacade.getInstance().getUser().getProfile().getProfileText(), "src/Assets/Avatars/Avatar_8.png");
    }

    @FXML
    private void AvatarChooser9(MouseEvent event) {
        pic_profile.setImage(new Image(new File("src/Assets/Avatars/Avatar_9.png").toURI().toString()));
        ScrollPane_AvatarChooser.setVisible(false);
        scrollPane = true;
        pane_cogView.setVisible(false);
        cog = true;
        PresentationFacade.getInstance().getUser().getProfile().setPicture("src/Assets/Avatars/Avatar_9.png");
        PresentationFacade.getInstance().updateUserInfo("", "", PresentationFacade.getInstance().getUser().getProfile().getFirstName(), PresentationFacade.getInstance().getUser().getProfile().getLastName(), PresentationFacade.getInstance().getUser().getProfile().getNationality(), PresentationFacade.getInstance().getUser().getProfile().getProfileText(), "src/Assets/Avatars/Avatar_9.png");
    }

    @FXML
    private void AvatarChooser10(MouseEvent event) {
        pic_profile.setImage(new Image(new File("src/Assets/Avatars/Avatar_10.png").toURI().toString()));
        ScrollPane_AvatarChooser.setVisible(false);
        scrollPane = true;
        pane_cogView.setVisible(false);
        cog = true;
        PresentationFacade.getInstance().getUser().getProfile().setPicture("src/Assets/Avatars/Avatar_10.png");
        PresentationFacade.getInstance().updateUserInfo("", "", PresentationFacade.getInstance().getUser().getProfile().getFirstName(), PresentationFacade.getInstance().getUser().getProfile().getLastName(), PresentationFacade.getInstance().getUser().getProfile().getNationality(), PresentationFacade.getInstance().getUser().getProfile().getProfileText(), "src/Assets/Avatars/Avatar_10.png");
    }

    @FXML
    private void AvatarChooser11(MouseEvent event) {
        pic_profile.setImage(new Image(new File("src/Assets/Avatars/Avatar_11.png").toURI().toString()));
        ScrollPane_AvatarChooser.setVisible(false);
        scrollPane = true;
        pane_cogView.setVisible(false);
        cog = true;
        PresentationFacade.getInstance().getUser().getProfile().setPicture("src/Assets/Avatars/Avatar_11.png");
        PresentationFacade.getInstance().updateUserInfo("", "", PresentationFacade.getInstance().getUser().getProfile().getFirstName(), PresentationFacade.getInstance().getUser().getProfile().getLastName(), PresentationFacade.getInstance().getUser().getProfile().getNationality(), PresentationFacade.getInstance().getUser().getProfile().getProfileText(), "src/Assets/Avatars/Avatar_11.png");
    }

    @FXML
    private void AvatarChooser12(MouseEvent event) {
        pic_profile.setImage(new Image(new File("src/Assets/Avatars/Avatar_12.png").toURI().toString()));
        ScrollPane_AvatarChooser.setVisible(false);
        scrollPane = true;
        pane_cogView.setVisible(false);
        cog = true;
        PresentationFacade.getInstance().getUser().getProfile().setPicture("src/Assets/Avatars/Avatar_12.png");
        PresentationFacade.getInstance().updateUserInfo("", "", PresentationFacade.getInstance().getUser().getProfile().getFirstName(), PresentationFacade.getInstance().getUser().getProfile().getLastName(), PresentationFacade.getInstance().getUser().getProfile().getNationality(), PresentationFacade.getInstance().getUser().getProfile().getProfileText(), "src/Assets/Avatars/Avatar_12.png");
    }

    /**
     * Defines the language in the system depending on the user's nationality.
     *
     * @param Nationality
     */
    private void NationalityInterface(Nationality nat) {
        switch (nat) {
            case Denmark:
                label_logo.setText("Velkommen");
                label_news.setText("SYNchat Nyheder");
                label_welcome.setText("Velkommen " + PresentationFacade.getInstance().getUser().getProfile().getFirstName());
                label_features.setText("SYNchat funktioner");
                SYN_news.setText("- Vi arbejder hårdt på at fikse kendte fejl. Tak for din tålmodighed!\n"
                        + "\n"
                        + "- Er der nogle ændringer du mener der mangler? Skriv endelig en mail til os med dine forslag på mail: post@synchat.com\n"
                        + "\n"
                        + "- Software ingeniører på SDU i Odense programmerer systemer på professionelt niveau allerede på 3. semester!\n"
                        + "\n"
                        + "- >>UDVIKLINGS OPDATERING<< Tilføjet ”Cross Cultural chatting”, tilpasser systemmeddelelser til diverse kulturelle forskelle.");
                SYN_welcome.setText("Nuværende tidspunkt: <User time>\n"
                        + "\n"
                        + "Temperatur: \n"
                        + "\n"
                        + "Antal online brugere:");
                SYN_features.setText("- Tillslut enten privat eller offentlig chat ved brug af de to knapper til højre.\n"
                        + "\n"
                        + "- Du kan ændre dit profilbillede ved at trykke på tandhjulet i øverste højre hjørne.\n"
                        + "\n"
                        + "- Har du brug for at sende en GIF, en fil, eller et lydklip? Tryk på SYNchat logoet ved siden af send-knappen (Vi elsker ferskner!).");
                break;
            case USA:
                label_logo.setText("Welcome");
                label_news.setText("SYNchat News");
                label_welcome.setText("Welcome " + PresentationFacade.getInstance().getUser().getProfile().getFirstName());
                label_features.setText("SYNchat features");
                SYN_news.setText("- We're working hard to fix known bugs. Thank you for your patience!\n"
                        + "\n"
                        + "- Have any features you think we're missing? Feel free to send us a mail: post@synchat.com\n"
                        + "\n"
                        + "- Software engineers at SDU Odense is programming professionel level software already at 3rd semester!\n"
                        + "\n"
                        + "- >>DEVELOPER UPDATE<< Added cross cultural chatting, addressing multiple culture adaptation");
                SYN_welcome.setText("Current time: <User time>\n"
                        + "\n"
                        + "Current temperature: \n"
                        + "\n"
                        + "Total users online:");
                SYN_features.setText("- Join either public or privat chat with the buttons to the right\n"
                        + "\n"
                        + "- You can change your profile picture by pressing the cog in the upper right corner\n"
                        + "\n"
                        + "- Want to send a gif, attach a file or even send a sound clip? Press the SYNchat Peach right next to the send button. (We love peaches)");
                break;
            case Japan:
                label_logo.setText("ようこそ");
                label_news.setText("SYNchat ニュース");
                label_welcome.setText("ようこそ " + PresentationFacade.getInstance().getUser().getProfile().getFirstName());
                label_features.setText("SYNchat 機能");
                SYN_news.setText("- 私たちは既知のバグを修正するために懸命に努めています. お待ちいただいてありがとうございます！\n"
                        + "\n"
                        + "- あなたが欠けていると思っている機能はありますか？ 私たちにメールをお送りください: post@synchat.com\n"
                        + "\n"
                        + "- SDUオーデンセのソフトウェアエンジニアは、すでに第3学期にプロフェッショナルレベルのソフトウェアをプログラミングしています！\n"
                        + "\n"
                        + "- >>開発者の更新<< クロスカルチャーチャットの追加, 複数の文化適応に取り組む.");
                SYN_welcome.setText("現在の時刻： <User time>\n"
                        + "\n"
                        + "現在の温度： <Temperature>\n"
                        + "\n"
                        + "オンラインユーザー総数：<Users online>");
                SYN_features.setText("- 右側のボタンで公開チャットまたはプライベートチャットに参加する.\n"
                        + "\n"
                        + "- 右上のコグを押すとプロフィール画像を変更できます.\n"
                        + "\n"
                        + "- あなたはgifを送ったり、ファイルを添付したり、サウンドクリップを送ったりしたいですか？送信ボタンのすぐ隣にあるSYNchatの桃を押してください。. （私たちは桃を愛する)");
                break;
            default:
                break;
        }
    }
}
