package com.example.dictionary;

import javafx.collections.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DictionaryManagement extends Dictionary {
    private static final String url =
            "https://script.google.com/macros/s/AKfycbz_g0cKMWhvQsyk4n83kwywXZRVauZ-Pjor6LHy9ZbsGM_Szia83P4DMySl34HevphM9w/exec";

    public DictionaryManagement() throws IOException {

    }

    public static void showAllWords() {
        for (Word w : dictionary) {
            System.out.println(w.getWord());
        }
    }

    public static int checkWord(String word) {
        for (int i = 0; i < dictionary.size(); i++) {
            String w = dictionary.get(i).getWord();
            if (w.equals(word)) {
                return i;
            }
        }
        return -1;
    }

    public static String dictionaryLookup(String word) {
        ConnectJDBC connectJDBC = new ConnectJDBC();
        Connection conn = connectJDBC.connect();

        String query = "SELECT * FROM tbl_edict WHERE word = '" + word + "';";

        Statement stm = null;
        try {
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            String result = "";
            while (rs.next()) {
                String detail = rs.getString("detail");
                detail = detail.replaceAll("<C><F><I><N><Q>", "");
                detail = detail.replaceAll("</Q></N></I></F></C>", "");
                String[] parts = detail.split("<br />");
                parts[0] = parts[0].replaceAll("@", "");
                for (String part : parts) {
                    if (part.contains("@")) {
                        result += part.replace("@", "") + '\n';
                    } else if (part.contains("*")) {
                        result += part.substring(1).trim() + '\n';
                    } else if (part.contains("=")) {
                        result += part.replace("=", "").replace("+", ":");
                        result += '\n';
                    } else result += part + '\n';
                }
                break;
            }
            conn.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static ObservableList<String> trieLookup(String key) {
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            List<String> results = trie.findWords(key);
            if (results != null) {
                int length = Math.min(results.size(), 15);
                for (int i = 0; i < length; i++) {
                    list.add(results.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
        return list;
    }

    public static void addWord(String word) throws IOException {
        if (checkWord(word) != -1) {
            dictionary.add(new Word(word));
            trie.insert(word);
        } else {
            System.out.println("Đã có trong từ điển!");
        }
    }

    public static void removeWord(String word) throws IOException {
        int index = checkWord(word);
        if (index != -1) {
            dictionary.remove(index);
            trie = new Trie();
            for (Word w : dictionary) {
                trie.insert(w.getWord());
            }
        }
    }

    public static String translate(String langFrom, String langTo, String text) throws IOException {
        String urlStr = url + "?q=" + URLEncoder.encode(text, "UTF-8") + "&target=" + langTo + "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}
