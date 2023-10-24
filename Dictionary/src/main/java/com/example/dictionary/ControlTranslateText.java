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

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

    public void switchToMinigame(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Minigame.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void clickSoundBtn1(MouseEvent event) {
        System.setProperty("freetts.voices" , "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        if (voice != null) {
            voice.allocate();
            String s = Text.getText();
            voice.speak(s);
        } else {
            throw new IllegalStateException("Cannot find voice: kevin16");
        }
    }

    public void clickSoundBtn2(MouseEvent event) {
        System.setProperty("freetts.voices" , "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        if (voice != null) {
            voice.allocate();
            String s = TextTrans.getText();
            voice.speak(s);
        } else {
            throw new IllegalStateException("Cannot find voice: kevin16");
        }
    }
}