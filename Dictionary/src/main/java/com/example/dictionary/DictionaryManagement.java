package com.example.dictionary;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class DictionaryManagement {
    public HashMap<String,String> dictionary;
    public DictionaryManagement() throws IOException {
        dictionary = new HashMap<>();
        String dict_path = "F:\\GitClone\\Baitaplon\\Dictionary\\src\\main\\resources\\com\\example\\dictionary\\dictionary.txt";
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

    public String dictionaryLookup(Word word) {
        String definition = dictionary.get(word.getWord());
        word.setDefinition(definition);
        return definition;
    }
}
