package com.example.dictionary;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javazoom.jl.player.Player;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Controller implements Initializable {
    public MediaPlayer mediaPlayer;
    @FXML
    private TextField typeArea;
    @FXML
    private TextArea meaningArea;
    @FXML
    private ListView<String> ListWord;
    @FXML
    private Button Search;

    public Controller() {
    }

    // Hien danh sach cac tu
    public void showWords(KeyEvent event) {
        try {
            String look = typeArea.getText();
            if (look.equals("")) {
                ListWord.setItems(null);
                return;
            }
            ObservableList<String> input = DictionaryManagement.trieLookup(look);
            ListWord.setItems(input);
        } catch (Exception e) {
            System.out.println("Nhap khong hop le");
        }
    }

    // click chuột vào từ tiếng anh để hiện ra nghĩa
    public void clicked(MouseEvent event){
        try {
            typeArea.setText(ListWord.getSelectionModel().getSelectedItem());
            meaningArea.setText(DictionaryManagement.dictionaryLookup(ListWord.getSelectionModel().getSelectedItem()));
        } catch (NullPointerException exception) {
            System.out.println("There is nothing");
        }
    }

    @FXML
    public void clickSearchBtn(MouseEvent event) {
        String input = typeArea.getText();
        List<String> wordList = ListWord.getItems().stream().toList();
        if (wordList.size()==0) return;
        if (input.equals(wordList.get(0))) {
            meaningArea.setText(DictionaryManagement.dictionaryLookup(wordList.get(0)));
        }
    }

    @FXML
    public void pressEnterToSearch(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String input = typeArea.getText();
            List<String> wordList = ListWord.getItems().stream().toList();
            if (wordList.size()==0) return;
            if (input.equals(wordList.get(0))) {
                meaningArea.setText(DictionaryManagement.dictionaryLookup(wordList.get(0)));
            }
        }
    }

    @FXML
    public void modifyWord(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            Dialog dialog = new Dialog();
            dialog.setHeaderText("Bạn có chắc muốn lưu thay đổi này?");
            dialog.getDialogPane().getButtonTypes().add(ButtonType.YES);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.NO);
            Optional<ButtonType> action = dialog.showAndWait();
            if (action.isEmpty() || action.get() == ButtonType.NO) {
                String previous = DictionaryManagement.dictionaryLookup(typeArea.getText());
                meaningArea.setText(previous);
                dialog.close();
            } else if (action.get() == ButtonType.YES) {
                // Thay doi detail trong sql
                String query = "UPDATE tbl_edict SET detail = '" + meaningArea.getText() + "'" +
                            "WHERE word = '" + typeArea.getText() +  "';";
                try {
                    Statement stm = Dictionary.conn.createStatement();
                    int rowAffected = stm.executeUpdate(query);
                    System.out.println("Số dòng bị ảnh hưởng: " + rowAffected);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    public void clickSoundBtn(MouseEvent event) {
        try {
            String urlStr = "https://translate.google.com/translate_tts?ie=UTF-8&tl="
                    + "en"
                    + "&client=tw-ob&q="
                    + URLEncoder.encode(meaningArea.getText().split(" ")[0], StandardCharsets.UTF_8);
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream audio = connection.getInputStream();
            new Player(audio).play();
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void switchToAddDelete(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ModifyWords.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToTranslate(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TranslateText.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMinigame(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Minigame.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
