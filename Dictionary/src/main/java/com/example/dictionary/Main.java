package com.example.dictionary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.Parent;
import java.util.Objects;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Dictionary.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Image logo = new Image(Main.class.getResource("image/dictionary_icon.png").openStream());
        stage.getIcons().add(logo);
        stage.setTitle("Dictionary");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        Dictionary dictionary = new Dictionary();
        //DictionaryManagement.showAllEngWord();
        launch();
    }
}