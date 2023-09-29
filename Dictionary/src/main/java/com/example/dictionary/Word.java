package com.example.dictionary;

public class Word {
    private String engword;
    private String definition;
    public Word(String word) {
        this.definition = "";
        this.engword = word;
    }

    public Word(String word, String definition) {
        this.definition = definition;
        this.engword = word;
    }

    public String getWord() {
        return engword;
    }

    public void setWord(String word) {
        this.engword = word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
