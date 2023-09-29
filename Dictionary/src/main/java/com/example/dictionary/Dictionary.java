package com.example.dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Dictionary {
    protected HashMap<String,String> dictionary;
    protected final String dict_path = "F:\\GitClone\\Baitaplon\\Dictionary\\src\\main\\resources\\com\\example\\dictionary\\dictionary.txt";
    public Dictionary() throws IOException {
        dictionary = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(dict_path));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            String word = parts[0].trim();
            String definition = parts[1].trim();
            dictionary.put(word, definition);
        }
        reader.close();
    }
}
