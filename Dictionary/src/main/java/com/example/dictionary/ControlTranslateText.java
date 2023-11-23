package com.example.dictionary;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javax.speech.EngineException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javazoom.jl.player.Player;

public class ControlTranslateText {
    private String from = "vi";
    private String to = "en";
    @FXML
    private Button Search;
    @FXML
    private TextArea Text;
    @FXML
    private TextArea TextTrans;
    @FXML
    private Button VTE;
    @FXML
    private Button ETV;
    @FXML
    private Label translating;

    private Stage stage;
    private Scene scene;
    private Parent root;
    public ControlTranslateText() throws EngineException, IOException {
    }

    public void setOffInTrans() {
        this.Text.setText("");
        this.TextTrans.setText("");
    }
    public void EnToVi(MouseEvent event) throws IOException {
        from = "en";
        to = "vi";
        String TextType = Text.getText();
        this.translating.toFront();
        String translatedText = DictionaryManagement.translate(from, to, TextType);
        this.TextTrans.setText(translatedText);
        this.translating.toBack();
    }
    public void ViToEn(MouseEvent event) throws IOException {
        from = "vi";
        to = "en";
        String TextType = Text.getText();
        this.translating.toFront();
        String translatedText = DictionaryManagement.translate(from, to, TextType);
        this.TextTrans.setText(translatedText);
        this.translating.toBack();
    }

    public void switchToDictionary(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Dictionary.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAddDelete(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ModifyWords.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMinigame(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Minigame.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void clickSoundBtn1(MouseEvent event) {
        try {
            String urlStr = "https://translate.google.com/translate_tts?ie=UTF-8&tl="
                    + "en"
                    + "&client=tw-ob&q="
                    + URLEncoder.encode(Text.getText(), StandardCharsets.UTF_8);
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream audio = connection.getInputStream();
            new Player(audio).play();
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickSoundBtn2(MouseEvent event) {
        try {
            String urlStr = "https://translate.google.com/translate_tts?ie=UTF-8&tl="
                    + "en"
                    + "&client=tw-ob&q="
                    + URLEncoder.encode(TextTrans.getText(), StandardCharsets.UTF_8);
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream audio = connection.getInputStream();
            new Player(audio).play();
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}