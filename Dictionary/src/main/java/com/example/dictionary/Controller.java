package com.example.dictionary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import java.io.IOException;
import java.net.URL;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Controller extends Dictionary implements Initializable {
    public MediaPlayer mediaPlayer;
    @FXML
    private TextField TypeArea;
    @FXML
    private TextArea meaningArea;
    @FXML
    private ListView<String> ListWord;
    @FXML
    private Button Search;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public Controller() throws IOException {
    }

    // Hien danh sach cac tu
    public void showWords(KeyEvent event) {
        try {
            String look = TypeArea.getText();
            if (look.equals("")) {
                ListWord.setItems(null);
                return;
            }
            ArrayList<String> wordList = DictionaryManagement.dictionarySearcher(look);
            ObservableList<String> input = FXCollections.observableArrayList(wordList);
            ListWord.setItems(input);
        } catch (Exception e) {
            System.out.println("Nhap khong hop le");
        }
    }

    // click chuột vào từ tiếng anh để hiện ra nghĩa
    public void clicked(MouseEvent event){
        try {
            TypeArea.setText(ListWord.getSelectionModel().getSelectedItem());
            meaningArea.setText(DictionaryManagement.dictionaryLookup(ListWord.getSelectionModel().getSelectedItem()));
        } catch (NullPointerException exception) {
            System.out.println("There is nothing");
        }
    }

    @FXML
    public void clickSearchBtn(MouseEvent event) {
        String input = TypeArea.getText();
        List<String> wordList = ListWord.getItems().stream().toList();
        if (wordList.size()==0) return;
        if (input.equals(wordList.get(0))) {
            meaningArea.setText(DictionaryManagement.dictionaryLookup(wordList.get(0)));
        }
    }

    @FXML
    public void pressEnterToSearch(KeyEvent event) {
        if (event.getCode()== KeyCode.ENTER) {
            String input = TypeArea.getText();
            List<String> wordList = ListWord.getItems().stream().toList();
            if (wordList.size()==0) return;
            if (input.equals(wordList.get(0))) {
                meaningArea.setText(DictionaryManagement.dictionaryLookup(wordList.get(0)));
            }
        }
    }

    @FXML
    public void clickSoundBtn(MouseEvent event) {
        System.setProperty("freetts.voices" , "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        if (voice != null) {
            voice.allocate();
            String[] parts = meaningArea.getText().split(" ", 3);
            voice.speak(parts[0]);
        } else {
            throw new IllegalStateException("Cannot find voice: kevin16");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void switchToTranslate(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("TranslateText.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
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

}
