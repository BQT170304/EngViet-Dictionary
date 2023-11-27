package com.example.dictionary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(App.class.getResource("Dictionary.fxml"));
            Scene scene = new Scene(root);
            Image logo = new Image(App.class.getResource("image/logo.png").openStream());
            stage.getIcons().add(logo);
            stage.setTitle("Dictionary");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        launch();
    }
}