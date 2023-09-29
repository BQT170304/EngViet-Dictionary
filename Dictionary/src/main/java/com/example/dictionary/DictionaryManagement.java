package com.example.dictionary;

import java.io.*;
import java.util.ArrayList;

public class DictionaryManagement extends Dictionary {
    public DictionaryManagement() throws IOException {

    }

    public boolean dictionaryLookup(Word word) {
        if (dictionary.containsKey(word.getWord())) {
            String definition = dictionary.get(word.getWord());
            word.setDefinition(definition);
            return true;
        }
        return false;
    }

    public void showAllWords() {
        for (String key : dictionary.keySet()) {
            System.out.format("%s:\t%s\n", key, dictionary.get(key));
        }
    }

    public ArrayList<String> dictionarySearcher(String word) {
        ArrayList<String> wordList = new ArrayList<>();
        for (String key : dictionary.keySet()) {
            if (key.length() < word.length()) continue;
            if (key.startsWith(word)) {
                wordList.add(key);
            }
        }
        return wordList;
    }

    // Them tu moi vao cuoi file
    public void addWord(Word word) throws IOException {
        if (!dictionaryLookup(word)) {
            FileWriter writer = new FileWriter(dict_path, true);
            writer.write(String.format("\n%s,%s", word.getWord(),word.getDefinition()));
            writer.close();
        } else {
            System.out.println("Đã có trong từ điển!");
        }
    }

    public void removeWord(Word word) throws IOException {
        File file = new File(dict_path);

        // Đọc tất cả dữ liệu từ file
        BufferedReader reader = new BufferedReader(new FileReader(file));
        ArrayList<String> data = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            data.add(line);
        }
        reader.close();
        // Tìm dòng cần xóa
        int index = -1;
        String target = String.format("%s,%s", word.getWord(),word.getDefinition());
        System.out.println(target);
        System.out.println(data.get(441));
        System.out.println(target.equals(data.get(441)));
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).length() < word.getWord().length()) continue;
            if (word.getWord().equals(data.get(i).substring(0,word.getWord().length()))
                || data.get(i).equals(target)) {
                System.out.println("Tim thay");
                index = i;
                break;
            }
        }

        if (index != -1) {
            // Xóa dòng khỏi danh sách
            data.remove(index);
            file.delete();
            File newFile = new File(dict_path);
            // Ghi danh sách đã sửa đổi vào file
            BufferedWriter writer = new BufferedWriter(new FileWriter(newFile, true));
            for (String line1 : data) {
                writer.write(line1 + "\n");
            }
            System.out.println("Đã xóa từ khỏi từ điển!");
            writer.close();
        } else System.out.println("Khong tim thay");
    }
}
