package Presentation;

import Acquaintance.IController;
import Acquaintance.IMessage;
import Acquaintance.IUser;
import Acquaintance.Nationality;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
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
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
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
    private static SYNchatController instance = null;

    public static SYNchatController getInstance() {
        if (instance == null) {
            instance = new SYNchatController();
        }
        return instance;
    }

    private IMessage iMsg;
    Map<Integer, IUser> comparisonMap = new HashMap<>();

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
    private JFXTextField txtArea_YourChat;
    @FXML
    private AnchorPane AnchorPane_List;
    @FXML
    private MediaView mv_background;
    private MediaPlayer mp;
    private Media me;
    @FXML
    private AnchorPane pane_Welcome;
    boolean popUp = true;
    boolean cog = true;
    boolean settings = true;
    boolean scrollPane = true;
    int colorIndex = 0;
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
    private JFXTextArea txtArea_Chat;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        txtArea_Chat.setStyle("-fx-text-fill: white");
        btn_send.setStyle(btn_send.getStyle() + "-fx-text-fill: white");
        txtArea_YourChat.setStyle("-fx-text-fill: white");
        String path = new File("src/Assets/backgroundAnimation.mp4").getAbsolutePath();
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
                image_yourCountry.setImage(new Image(new File("src/Assets/Flag_DK_Color.png").toURI().toString()));
                break;
            case USA:
                image_yourCountry.setImage(new Image(new File("src/Assets/Flag_USA_Color.png").toURI().toString()));
                break;
            case Japan:
                image_yourCountry.setImage(new Image(new File("src/Assets/Flag_Japan_Color.png").toURI().toString()));
                break;
        }
        pic_profile.setImage(new Image(new File(PresentationFacade.getInstance().getUser().getProfile().getPicture()).toURI().toString()));
    }

    @FXML
    public void startPublicChat(ActionEvent event) {
        PresentationFacade.getInstance().commandHandling("!SYN!-PublicChat-!SYN!");
        pane_chat.setDisable(false);
        pane_Welcome.toBack();
        PresentationFacade.Ibus.publicThreads();
        btn_privatChat.setStyle(btn_publicChat.getStyle());
        btn_publicChat.setStyle(btn_publicChat.getStyle() + "-fx-background-color: #162ab7");
        txtArea_Chat.appendText("** You have entered the chat **\n\n");
        this.t = startRun();
    }

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
                for (int i : PresentationFacade.getInstance().getpUserMap().keySet()) {
                    comparisonMap.put(i, PresentationFacade.getInstance().getpUserMap().get(i));
                }
                String comparisonString = "";
                int comparisonInt = -1;
                while (true) {
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
                    if ((comparisonMap != PresentationFacade.getInstance().getpUserMap()) && (comparisonMap != null)) {
                        for (int i : PresentationFacade.getInstance().getpUserMap().keySet()) {
                            if (!comparisonMap.containsKey(i)) {
                                updatepUserMap(PresentationFacade.getInstance().getpUserMap().get(i));
                            }
                        }
                        for (int i : comparisonMap.keySet()) {
                            if (!PresentationFacade.getInstance().getpUserMap().containsKey(i)) {
                                updatepUserMap(comparisonMap.get(i));
                            }
                        }
                    }
                }
            }
        };
        Thread t = new Thread(runnable);
        t.setDaemon(true);
        t.start();
        return t;
    }

    private void updatepUserMap(IUser user) {
        String imgCountry;
        switch(user.getProfile().getNationality()) {
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
        ImageView imgC = new ImageView(new Image(new File("src/Assets/Flag_" + imgCountry + "_Color.png").toURI().toString()));
        imgC.fitHeightProperty().set(15);
        imgC.fitWidthProperty().set(15);
        imgC.setTranslateX(50);
        imgC.setTranslateY(1);
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
            PresentationFacade.getInstance().changeScene("ChangeInfo.fxml");
        });
        ImageView imgP = new ImageView(new Image(new File(user.getProfile().getPicture()).toURI().toString()));
        imgP.fitHeightProperty().set(35);
        imgP.fitWidthProperty().set(35);
        if (comparisonMap.containsKey(user.getUserID())) {
            txtArea_Chat.appendText("** " + user.getProfile().getFirstName() + " has left the chat **\n\n");
            comparisonMap.remove(user.getUserID());
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    txtFlow_publicChat.getChildren().remove(txt);
                }
            });
        } else {
            txtArea_Chat.appendText("** " + user.getProfile().getFirstName() + " has entered the chat **\n\n");
            comparisonMap.put(user.getUserID(), user);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    txtFlow_publicChat.getChildren().add(txt);
                    txtFlow_publicChat.getChildren().add(imgC);
                    txtFlow_publicChat.getChildren().add(imgP);
                }
            });

        }
    }

    @FXML
    private void sendMsg(ActionEvent event) {
        if (!txtArea_YourChat.getText().trim().isEmpty()) {
            PresentationFacade.getInstance().sendPublicMsg(txtArea_YourChat.getText());
            txtArea_YourChat.clear();
        }
    }

    @FXML
    private void startPrivatChat(ActionEvent event) {
        PresentationFacade.getInstance().commandHandling("!SYN!-PublicChat-!SYN!");
        pane_chat.setDisable(false);
        pane_Welcome.toBack();
        //PresentationFacade.Ibus.privateThreads();
        btn_publicChat.setStyle(btn_privatChat.getStyle());
        btn_privatChat.setStyle(btn_privatChat.getStyle() + "-fx-background-color: #162ab7");
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

    public void receivePublicMsg() {
        txtArea_Chat.appendText(PresentationFacade.getInstance().getpUserMap().get(iMsg.getSenderID()).getProfile().getFirstName() + ": ");
        txtArea_Chat.appendText(iMsg.getContext() + "\n");
        Date date = new Date();
        date.setTime(iMsg.getTimestamp().toEpochMilli());
        String timeStamp = new SimpleDateFormat("HH.mm").format(date);
        txtArea_Chat.appendText(timeStamp + "\n\n");
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
    private void logoutAction(ActionEvent event) {
        PresentationFacade.getInstance().commandHandling("!SYN!-PublicChat-!SYN!");
        PresentationFacade.getInstance().commandHandling("!SYN!-logout-!SYN!");
        PresentationFacade.getInstance().changeScene("Login.fxml");
    }

    private void returnToWelcome(ActionEvent event) {
        pane_chat.setDisable(true);
        pane_Welcome.toFront();
        btn_privatChat.setStyle(btn_privatChat.getStyle() + "-fx-background-color: #1f96e0");
        btn_publicChat.setStyle(btn_publicChat.getStyle() + "-fx-background-color: #1f96e0");
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

    @Override
    public void injectStage(Stage stage) {
        PresentationFacade.stage = stage;
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
    private void viewProfile(MouseEvent event) {
        PresentationFacade.getInstance().commandHandling("!SYN!-PublicChat-!SYN!");
        PresentationFacade.getInstance().changeScene("ChangeInfo.fxml");
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
        pic_profile.setImage(new Image(new File("src/Assets/Avatar_1.png").toURI().toString()));
        ScrollPane_AvatarChooser.setVisible(false);
        scrollPane = true;
        pane_cogView.setVisible(false);
        cog = true;
        PresentationFacade.getInstance().getUser().getProfile().setPicture("src/Assets/Avatar_1.png");
        PresentationFacade.getInstance().updateUserInfo("", "", PresentationFacade.getInstance().getUser().getProfile().getFirstName(), PresentationFacade.getInstance().getUser().getProfile().getLastName(), PresentationFacade.getInstance().getUser().getProfile().getNationality(), PresentationFacade.getInstance().getUser().getProfile().getProfileText(), "src/Assets/Avatar_1.png");
    }

    @FXML
    private void AvatarChooser2(MouseEvent event) {
        pic_profile.setImage(new Image(new File("src/Assets/Avatar_2.png").toURI().toString()));
        ScrollPane_AvatarChooser.setVisible(false);
        scrollPane = true;
        pane_cogView.setVisible(false);
        cog = true;
        PresentationFacade.getInstance().getUser().getProfile().setPicture("src/Assets/Avatar_2.png");
        PresentationFacade.getInstance().updateUserInfo("", "", PresentationFacade.getInstance().getUser().getProfile().getFirstName(), PresentationFacade.getInstance().getUser().getProfile().getLastName(), PresentationFacade.getInstance().getUser().getProfile().getNationality(), PresentationFacade.getInstance().getUser().getProfile().getProfileText(), "src/Assets/Avatar_2.png");
    }

    @FXML
    private void AvatarChooser3(MouseEvent event) {
        pic_profile.setImage(new Image(new File("src/Assets/Avatar_3.png").toURI().toString()));
        ScrollPane_AvatarChooser.setVisible(false);
        scrollPane = true;
        pane_cogView.setVisible(false);
        cog = true;
        PresentationFacade.getInstance().getUser().getProfile().setPicture("src/Assets/Avatar_3.png");
        PresentationFacade.getInstance().updateUserInfo("", "", PresentationFacade.getInstance().getUser().getProfile().getFirstName(), PresentationFacade.getInstance().getUser().getProfile().getLastName(), PresentationFacade.getInstance().getUser().getProfile().getNationality(), PresentationFacade.getInstance().getUser().getProfile().getProfileText(), "src/Assets/Avatar_3.png");
    }

    @FXML
    private void AvatarChooser4(MouseEvent event) {
        pic_profile.setImage(new Image(new File("src/Assets/Avatar_4.png").toURI().toString()));
        ScrollPane_AvatarChooser.setVisible(false);
        scrollPane = true;
        pane_cogView.setVisible(false);
        cog = true;
        PresentationFacade.getInstance().getUser().getProfile().setPicture("src/Assets/Avatar_4.png");
        PresentationFacade.getInstance().updateUserInfo("", "", PresentationFacade.getInstance().getUser().getProfile().getFirstName(), PresentationFacade.getInstance().getUser().getProfile().getLastName(), PresentationFacade.getInstance().getUser().getProfile().getNationality(), PresentationFacade.getInstance().getUser().getProfile().getProfileText(), "src/Assets/Avatar_4.png");
    }

    @FXML
    private void AvatarChooser5(MouseEvent event) {
        pic_profile.setImage(new Image(new File("src/Assets/Avatar_5.png").toURI().toString()));
        ScrollPane_AvatarChooser.setVisible(false);
        scrollPane = true;
        pane_cogView.setVisible(false);
        cog = true;
        PresentationFacade.getInstance().getUser().getProfile().setPicture("src/Assets/Avatar_5.png");
        PresentationFacade.getInstance().updateUserInfo("", "", PresentationFacade.getInstance().getUser().getProfile().getFirstName(), PresentationFacade.getInstance().getUser().getProfile().getLastName(), PresentationFacade.getInstance().getUser().getProfile().getNationality(), PresentationFacade.getInstance().getUser().getProfile().getProfileText(), "src/Assets/Avatar_5.png");
    }

    @FXML
    private void AvatarChooser6(MouseEvent event) {
        pic_profile.setImage(new Image(new File("src/Assets/Avatar_6.png").toURI().toString()));
        ScrollPane_AvatarChooser.setVisible(false);
        scrollPane = true;
        pane_cogView.setVisible(false);
        cog = true;
        PresentationFacade.getInstance().getUser().getProfile().setPicture("src/Assets/Avatar_6.png");
        PresentationFacade.getInstance().updateUserInfo("", "", PresentationFacade.getInstance().getUser().getProfile().getFirstName(), PresentationFacade.getInstance().getUser().getProfile().getLastName(), PresentationFacade.getInstance().getUser().getProfile().getNationality(), PresentationFacade.getInstance().getUser().getProfile().getProfileText(), "src/Assets/Avatar_6.png");
    }

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

    @FXML
    private void AvatarChooser7(MouseEvent event) {
        pic_profile.setImage(new Image(new File("src/Assets/Avatar_7.png").toURI().toString()));
        ScrollPane_AvatarChooser.setVisible(false);
        scrollPane = true;
        pane_cogView.setVisible(false);
        cog = true;
        PresentationFacade.getInstance().getUser().getProfile().setPicture("src/Assets/Avatar_7.png");
        PresentationFacade.getInstance().updateUserInfo("", "", PresentationFacade.getInstance().getUser().getProfile().getFirstName(), PresentationFacade.getInstance().getUser().getProfile().getLastName(), PresentationFacade.getInstance().getUser().getProfile().getNationality(), PresentationFacade.getInstance().getUser().getProfile().getProfileText(), "src/Assets/Avatar_7.png");
    }

    @FXML
    private void AvatarChooser8(MouseEvent event) {
        pic_profile.setImage(new Image(new File("src/Assets/Avatar_8.png").toURI().toString()));
        ScrollPane_AvatarChooser.setVisible(false);
        scrollPane = true;
        pane_cogView.setVisible(false);
        cog = true;
        PresentationFacade.getInstance().getUser().getProfile().setPicture("src/Assets/Avatar_8.png");
        PresentationFacade.getInstance().updateUserInfo("", "", PresentationFacade.getInstance().getUser().getProfile().getFirstName(), PresentationFacade.getInstance().getUser().getProfile().getLastName(), PresentationFacade.getInstance().getUser().getProfile().getNationality(), PresentationFacade.getInstance().getUser().getProfile().getProfileText(), "src/Assets/Avatar_8.png");
    }

    @FXML
    private void AvatarChooser9(MouseEvent event) {
        pic_profile.setImage(new Image(new File("src/Assets/Avatar_9.png").toURI().toString()));
        ScrollPane_AvatarChooser.setVisible(false);
        scrollPane = true;
        pane_cogView.setVisible(false);
        cog = true;
        PresentationFacade.getInstance().getUser().getProfile().setPicture("src/Assets/Avatar_9.png");
        PresentationFacade.getInstance().updateUserInfo("", "", PresentationFacade.getInstance().getUser().getProfile().getFirstName(), PresentationFacade.getInstance().getUser().getProfile().getLastName(), PresentationFacade.getInstance().getUser().getProfile().getNationality(), PresentationFacade.getInstance().getUser().getProfile().getProfileText(), "src/Assets/Avatar_9.png");
    }

    @FXML
    private void AvatarChooser10(MouseEvent event) {
        pic_profile.setImage(new Image(new File("src/Assets/Avatar_10.png").toURI().toString()));
        ScrollPane_AvatarChooser.setVisible(false);
        scrollPane = true;
        pane_cogView.setVisible(false);
        cog = true;
        PresentationFacade.getInstance().getUser().getProfile().setPicture("src/Assets/Avatar_10.png");
        PresentationFacade.getInstance().updateUserInfo("", "", PresentationFacade.getInstance().getUser().getProfile().getFirstName(), PresentationFacade.getInstance().getUser().getProfile().getLastName(), PresentationFacade.getInstance().getUser().getProfile().getNationality(), PresentationFacade.getInstance().getUser().getProfile().getProfileText(), "src/Assets/Avatar_10.png");
    }

    @FXML
    private void AvatarChooser11(MouseEvent event) {
        pic_profile.setImage(new Image(new File("src/Assets/Avatar_11.png").toURI().toString()));
        ScrollPane_AvatarChooser.setVisible(false);
        scrollPane = true;
        pane_cogView.setVisible(false);
        cog = true;
        PresentationFacade.getInstance().getUser().getProfile().setPicture("src/Assets/Avatar_11.png");
        PresentationFacade.getInstance().updateUserInfo("", "", PresentationFacade.getInstance().getUser().getProfile().getFirstName(), PresentationFacade.getInstance().getUser().getProfile().getLastName(), PresentationFacade.getInstance().getUser().getProfile().getNationality(), PresentationFacade.getInstance().getUser().getProfile().getProfileText(), "src/Assets/Avatar_11.png");
    }

    @FXML
    private void AvatarChooser12(MouseEvent event) {
        pic_profile.setImage(new Image(new File("src/Assets/Avatar_12.png").toURI().toString()));
        ScrollPane_AvatarChooser.setVisible(false);
        scrollPane = true;
        pane_cogView.setVisible(false);
        cog = true;
        PresentationFacade.getInstance().getUser().getProfile().setPicture("src/Assets/Avatar_12.png");
        PresentationFacade.getInstance().updateUserInfo("", "", PresentationFacade.getInstance().getUser().getProfile().getFirstName(), PresentationFacade.getInstance().getUser().getProfile().getLastName(), PresentationFacade.getInstance().getUser().getProfile().getNationality(), PresentationFacade.getInstance().getUser().getProfile().getProfileText(), "src/Assets/Avatar_12.png");
    }

    @FXML
    private void colorPicker(MouseEvent event) {
        switch (colorIndex) {
            case 0:
                txtArea_Chat.setStyle("-fx-text-fill: #98fb98");
                txtArea_YourChat.setStyle("-fx-text-fill: #98fb98");
                colorIndex++;
                break;
            case 1:
                txtArea_Chat.setStyle("-fx-text-fill: blue");
                txtArea_YourChat.setStyle("-fx-text-fill: blue");
                colorIndex++;
                break;
            case 2:
                txtArea_Chat.setStyle("-fx-text-fill: red");
                txtArea_YourChat.setStyle("-fx-text-fill: red");
                colorIndex++;
                break;
            case 3:
                txtArea_Chat.setStyle("-fx-text-fill: orange");
                txtArea_YourChat.setStyle("-fx-text-fill: orange");
                colorIndex++;
                break;
            case 4:
                txtArea_Chat.setStyle("-fx-text-fill: purple");
                txtArea_YourChat.setStyle("-fx-text-fill: purple");
                colorIndex++;
                break;
            case 5:
                txtArea_Chat.setStyle("-fx-text-fill: pink");
                txtArea_YourChat.setStyle("-fx-text-fill: pink");
                colorIndex++;
                break;
            default:
                txtArea_Chat.setStyle("-fx-text-fill: white");
                txtArea_YourChat.setStyle("-fx-text-fill: white");
                colorIndex = 0;
                break;
        }
    }
}
