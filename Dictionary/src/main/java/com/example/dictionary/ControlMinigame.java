package com.example.dictionary;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javax.speech.EngineException;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class ControlMinigame {
    @FXML
    private Button Search;
    @FXML
    private Button TranslateText;
    @FXML
    private Text text;
    @FXML
    private Pane buttons;
    @FXML
    private Text winStatus;
    @FXML
    private Text realWord;
    @FXML
    public ImageView imageView;

    Image i0 = new Image(getClass().getResourceAsStream("image/0.png"));
    Image i1 = new Image(getClass().getResourceAsStream("image/1.png"));
    Image i2 = new Image(getClass().getResourceAsStream("image/2.png"));
    Image i3 = new Image(getClass().getResourceAsStream("image/3.png"));
    Image i4 = new Image(getClass().getResourceAsStream("image/4.png"));
    Image i5 = new Image(getClass().getResourceAsStream("image/5.png"));
    Image i6 = new Image(getClass().getResourceAsStream("image/6.png"));
    Image i7 = new Image(getClass().getResourceAsStream("image/7.png"));
    Image i8 = new Image(getClass().getResourceAsStream("image/8.png"));

    private int mistakes;
    private int correct;
    private Words word = new Words();
    private String myWord;
    private List<String> myLetters;
    private List<String> answer;
    private int score = 0;
    private Stage stage;
    private Scene scene;
    private Parent root;

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

    public void switchToTranslate(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("TranslateText.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    public ControlMinigame() throws FileNotFoundException {
    }

    public void initialize() {
        imageView.setImage(i0);
        mistakes=0;
        correct=0;
        myWord = word.getRandomWord();
        myLetters = Arrays.asList(myWord.split(""));
        answer = Arrays.asList(new String[myLetters.size()*2]);
        for(int i=0; i<myLetters.size()*2; i++){
            if(i%2==0){
                answer.set(i, "_");
            }else{
                answer.set(i, " ");
            }
        }
        String res = String.join("", answer);
        text.setText(res);
        winStatus.setText("");
        realWord.setText("");
        buttons.setDisable(false);
    }


    public void onClick(ActionEvent event){
        String letter = ((Button)event.getSource()).getText();
        ((Button) event.getSource()).setDisable(true);
        if(myLetters.contains(letter)){
            int index = 0;
            for(int i=0; i<myWord.length(); i++) {
                if (letter.charAt(0) == myWord.charAt(i)) {
                    correct++;
                    int inde = index*2;
                    answer.set(inde, letter);
                    String res = String.join("", answer);
                    text.setText(res);

                }
                index++;
            }
            if(correct==myWord.length()){
                score += 10;
                winStatus.setText(" You Win!\n Score: " + score );
                buttons.setDisable(true);
            }
        }else{
            mistakes++;
            if(mistakes ==1) imageView.setImage(i1);
            else if (mistakes ==2) imageView.setImage(i2);
            else if (mistakes ==3) imageView.setImage(i3);
            else if (mistakes ==4) imageView.setImage(i4);
            else if (mistakes ==5) imageView.setImage(i5);
            else if (mistakes ==6) imageView.setImage(i6);
            else if (mistakes ==7) imageView.setImage(i7);
            else if (mistakes ==8){
                score -= 10;
                if (score < 0){
                    score = 0;
                }
                imageView.setImage(i8);
                winStatus.setText(" You Lose!\n Score: " + score);
                realWord.setText("The actual word was\n" + myWord);
                buttons.setDisable(true);
            }
        }
    }

    public void newGame(){
        for(int i=0; i<26; i++){
            buttons.getChildren().get(i).setDisable(false);
        }
        initialize();
    }



}
