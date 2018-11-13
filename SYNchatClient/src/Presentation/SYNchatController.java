package Presentation;

import Acquaintance.IController;
import Acquaintance.IMessage;
import Acquaintance.Nationality;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    }

    @FXML
    public void startPublicChat(ActionEvent event) {
        pane_chat.setDisable(false);
        pane_Welcome.toBack();
        PresentationFacade.Ibus.publicThreads();
        btn_privatChat.setStyle(btn_publicChat.getStyle());
        btn_publicChat.setStyle(btn_publicChat.getStyle() + "-fx-background-color: #162ab7");

        this.t = startrun();
    }

    private synchronized Thread startrun() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String comparisonString = "";
                int comparisonInt = 0;
                IMessage Imsg;
                while (true) {
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SYNchatController.class.getName()).log(Level.SEVERE, null, ex);
                    }
//                    System.out.println("");
//                    System.out.println(" .----------------.  .----------------.  .-----------------. .----------------.  .----------------.  .----------------.  .----------------. ");
//                    System.out.println("| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |");
//                    System.out.println("| |    _______   | || |  ____  ____  | || | ____  _____  | || |     ______   | || |  ____  ____  | || |      __      | || |  _________   | |");
//                    System.out.println("| |   /  ___  |  | || | |_  _||_  _| | || ||_   \\|_   _| | || |   .' ___  |  | || | |_   ||   _| | || |     /  \\     | || | |  _   _  |  | |");
//                    System.out.println("| |  |  (__ \\_|  | || |   \\ \\  / /   | || |  |   \\ | |   | || |  / .'   \\_|  | || |   | |__| |   | || |    / /\\ \\    | || | |_/ | | \\_|  | |");
//                    System.out.println("| |   '.___`-.   | || |    \\ \\/ /    | || |  | |\\ \\| |   | || |  | |         | || |   |  __  |   | || |   / ____ \\   | || |     | |      | |");
//                    System.out.println("| |  |`\\____) |  | || |    _|  |_    | || | _| |_\\   |_  | || |  \\ `.___.'\\  | || |  _| |  | |_  | || | _/ /    \\ \\_ | || |    _| |_     | |");
//                    System.out.println("| |  |_______.'  | || |   |______|   | || ||_____|\\____| | || |   `._____.'  | || | |____||____| | || ||____|  |____|| || |   |_____|    | |");
//                    System.out.println("| |              | || |              | || |              | || |              | || |              | || |              | || |              | |");
//                    System.out.println("| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |");
//                    System.out.println(" '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'");
//                    System.out.println("");
                    String context = PresentationFacade.getInstance().getContext();
                    int senderID = PresentationFacade.getInstance().getSenderID();
                    if (!context.equals(comparisonString) && (senderID != comparisonInt)) {
                        comparisonString = context;
                        comparisonInt = senderID;
                        Imsg = PresentationFacade.getInstance().getImsg();
                        receivePublicMsg(Imsg);
                    }
                }
            }
        };
        Thread t = new Thread(runnable);
        t.setDaemon(true);
        t.start();
        return t;
    }

    @FXML
    private void sendMsg(ActionEvent event) {
        if (!txtArea_YourChat.getText().trim().isEmpty()) {
            PresentationFacade.getInstance().sendPublicMsg(txtArea_YourChat.getText());
            String nameMsg = "";
            String yourMsg = "";
            String dateMsg = "";
            nameMsg = "Default user:\n";
            txtArea_Chat.appendText(nameMsg);
            yourMsg = txtArea_YourChat.getText() + "\n";
            txtArea_Chat.appendText(yourMsg);
            dateMsg = new SimpleDateFormat("HH.mm").format(new Date()) + "\n";
            txtArea_Chat.appendText(dateMsg);
            txtArea_YourChat.clear();
        }
    }

    @FXML
    private void startPrivatChat(ActionEvent event) {
        pane_chat.setDisable(false);
        pane_Welcome.toBack();
        PresentationFacade.Ibus.privateThreads();
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

    public void receivePublicMsg(IMessage msg) {
        System.out.println("En eller anden faggots besked: " + msg.getContext());
        System.out.println("timestamp " + msg.getTimestamp().toString());
        System.out.println("...");
        System.out.println("navn:" + PresentationFacade.getInstance().getUser().getProfile().getFirstName());
//        String nameMsg = "";
//        String yourMsg = "";
//        String dateMsg = "";
//        nameMsg = "Default user:\n";
//        txtArea_Chat.appendText(nameMsg);
//        yourMsg = msg.getContext() + "\n";
//        txtArea_Chat.appendText(yourMsg);
//        dateMsg = msg.getTimestamp().toString() + "\n";
//        txtArea_Chat.appendText(dateMsg);
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
        PresentationFacade.getInstance().logoutHandling("!SYN!-logout-!SYN!");
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
    }

    @FXML
    private void AvatarChooser2(MouseEvent event) {
        pic_profile.setImage(new Image(new File("src/Assets/Avatar_2.png").toURI().toString()));
        ScrollPane_AvatarChooser.setVisible(false);
        scrollPane = true;
        pane_cogView.setVisible(false);
        cog = true;
    }

    @FXML
    private void AvatarChooser3(MouseEvent event) {
        pic_profile.setImage(new Image(new File("src/Assets/Avatar_3.png").toURI().toString()));
        ScrollPane_AvatarChooser.setVisible(false);
        scrollPane = true;
        pane_cogView.setVisible(false);
        cog = true;
    }

    @FXML
    private void AvatarChooser4(MouseEvent event) {
        pic_profile.setImage(new Image(new File("src/Assets/Avatar_4.png").toURI().toString()));
        ScrollPane_AvatarChooser.setVisible(false);
        scrollPane = true;
        pane_cogView.setVisible(false);
        cog = true;
    }

    @FXML
    private void AvatarChooser5(MouseEvent event) {
        pic_profile.setImage(new Image(new File("src/Assets/Avatar_5.png").toURI().toString()));
        ScrollPane_AvatarChooser.setVisible(false);
        scrollPane = true;
        pane_cogView.setVisible(false);
        cog = true;
    }

    @FXML
    private void AvatarChooser6(MouseEvent event) {
        pic_profile.setImage(new Image(new File("src/Assets/Avatar_6.png").toURI().toString()));
        ScrollPane_AvatarChooser.setVisible(false);
        scrollPane = true;
        pane_cogView.setVisible(false);
        cog = true;
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

}
