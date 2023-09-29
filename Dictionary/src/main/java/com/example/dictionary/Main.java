package com.example.dictionary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("interface.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        Dictionary dictionary = new Dictionary();
        DictionaryManagement dictManagement = new DictionaryManagement();
        Word word = new Word("abstract");
        dictManagement.dictionaryLookup(word);
        //System.out.format("%s: %s", word.getWord(), word.getDefinition());
//        String search = "dis";
//        System.out.println(search);
//        ArrayList<String> wordList = dictManagement.dictionarySearcher(search);
//        for (String s : wordList) {
//            System.out.println(s);
//        }
        dictManagement.addWord(new Word("developer","lập trình viên"));
        // launch();
    }
}