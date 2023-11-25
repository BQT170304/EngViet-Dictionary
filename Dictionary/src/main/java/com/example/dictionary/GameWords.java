package com.example.dictionary;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameWords {

    private ArrayList<String> words;


    public GameWords() {
        words = new ArrayList<>();
        Scanner sc = new Scanner(getClass().getResourceAsStream("data/dictionary.txt"));
        while(sc.hasNextLine()) words.add(sc.nextLine());
    }

    public String getRandomWord(){
        return words.get(new Random().nextInt(words.size())).toUpperCase();
    }

}