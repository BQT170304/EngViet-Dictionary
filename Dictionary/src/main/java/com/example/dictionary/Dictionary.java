package com.example.dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Dictionary {
    protected static ArrayList<Word> dictionary;
    protected static final String dict_path = "D:\\duma\\Baitaplon\\Dictionary\\src\\main\\resources\\com\\example\\dictionary\\dictionary.txt";
    public Dictionary() throws IOException {
        dictionary = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(dict_path));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (parts.length == 3)
                dictionary.add(new Word(parts[0],parts[1],parts[2]));
            else if (parts.length == 2)
                dictionary.add(new Word(parts[0],parts[1]));
        }
        reader.close();
    }
}
