package com.example.dictionary;

import java.util.*;

public class Trie {
    protected final Map<Character, Trie> children;
    protected String content;
    protected boolean isEnd = false;

    public Trie() {
        this(null);
    }

    private Trie(String content) {
        this.content = content;
        children = new HashMap<>();
    }

    // Them ki tu vao trie
    protected void add(char character) {
        String s;
        if (this.content == null) {
            s = Character.toString(character);
        } else {
            s = this.content + character;
        }
        children.put(character, new Trie(s));
    }


    public void insert(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Null word are not valid.");
        }
        Trie node = this;
        for (char c : word.toCharArray()) {
            if (!node.children.containsKey(c)) {
                node.add(c);
            }
            node = node.children.get(c);
        }
        node.isEnd = true;
    }

    public List<String> findWords(String prefix) {
        Trie TrieNode = this;
        for (char c : prefix.toCharArray()) {
            if (!TrieNode.children.containsKey(c)) {
                return null;
            }
            TrieNode = TrieNode.children.get(c);
        }
        return TrieNode.allPrefixes();
    }

    protected List<String> allPrefixes() {
        List<String> results = new ArrayList<>();
        if (this.isEnd) {
            results.add(this.content);
        }
        for (Map.Entry<Character, Trie> entry : children.entrySet()) {
            Trie child = entry.getValue();
            Collection<String> childPrefixes = child.allPrefixes();
            results.addAll(childPrefixes);
        }
        return results;
    }
}
