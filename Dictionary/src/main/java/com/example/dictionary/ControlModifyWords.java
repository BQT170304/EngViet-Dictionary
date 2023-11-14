package com.example.dictionary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class ControlModifyWords {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField textField;
    private TextField addTextField;

    public ControlModifyWords() {
        addTextField = new TextField();
        addTextField.setPromptText("Nhập nghĩa của từ");
    }
    public void switchToDictionary(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Dictionary.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToTranslate(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("TranslateText.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
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

    private Node setAddContent() {
        GridPane gridPane = new GridPane();
        gridPane.add(new Label("Nghĩa:\t"), 0, 0);
        addTextField.setPrefWidth(240);
        gridPane.add(addTextField, 1, 0);
        return gridPane;
    }

    public void alertAdd(MouseEvent event) {
        String word = textField.getText();
        if (DictionaryManagement.checkWord(word) != -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("Từ này đã có trong từ điển!");
            alert.setResizable(false);
            alert.showAndWait();
        } else {
            Dialog dialog = new Dialog();
            dialog.setHeaderText("Thêm từ vào từ điển");
            dialog.getDialogPane().setContent(setAddContent());
            ButtonType addBtn = new ButtonType("Add");
            dialog.getDialogPane().setPrefSize(315,120);
            dialog.getDialogPane().getButtonTypes().add(addBtn);
            Optional<ButtonType> choice = dialog.showAndWait();
            if (choice.isEmpty()) {
                dialog.close();
            } else if (choice.get() == addBtn) {
                String definition = addTextField.getText();
                if (definition.equals("") || definition.startsWith(" ")) {
                    Alert alertAdd = new Alert(Alert.AlertType.ERROR);
                    alertAdd.setTitle("Error!");
                    alertAdd.setContentText("Vui lòng nhập nghĩa của từ!");
                    alertAdd.setResizable(false);
                    alertAdd.showAndWait();
                } else {
                    DictionaryManagement.addWord(word, definition);
                    Dialog success = new Dialog();
                    success.setHeaderText("Đã thêm từ vào từ điển!");
                    success.getDialogPane().getButtonTypes().add(ButtonType.OK);
                    Optional<ButtonType> action = success.showAndWait();
                    if (action.isEmpty()) {
                        System.out.println("Exit from dialog");
                    } else {
                        success.close();
                    }
                }
            }
        }
    }


    public void alertDelete(MouseEvent event) {
        String word = textField.getText();
        if (DictionaryManagement.checkWord(word) != -1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setContentText("Bạn có chắc muốn xóa từ này?");
            alert.setResizable(false);
            Optional<ButtonType> choice = alert.showAndWait();
            if (choice.isEmpty()) {
                alert.close();
            } else if (choice.get() == ButtonType.OK) {
                DictionaryManagement.removeWord(word);
                Dialog success = new Dialog();
                success.setHeaderText("Đã xóa từ khỏi từ điển!");
                success.getDialogPane().getButtonTypes().add(ButtonType.OK);
                Optional<ButtonType> action = success.showAndWait();
                if (action.isEmpty()) {
                    System.out.println("Exit from dialog");
                } else {
                    success.close();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setContentText("Từ này không có trong từ điển!");
            alert.setResizable(false);
            Optional<ButtonType> choice = alert.showAndWait();
            if (choice.isEmpty()) {
                alert.close();
            } else if (choice.get() == ButtonType.OK) {
                System.out.println("Từ không tồn tại trong từ điển");
            }
        }

    }
}
