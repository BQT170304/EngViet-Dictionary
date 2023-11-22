package com.example.dictionary;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Dictionary extends Trie {
    public static Trie trie;
    public static List<Word> dictionary;
    public static Connection conn;
    public Dictionary() {
        dictionary = new ArrayList<>();
        trie = new Trie();
        ConnectJDBC connectJDBC = new ConnectJDBC();
        conn = connectJDBC.connect();

        String query = "SELECT word FROM tbl_edict;";

        Statement stm = null;
        try {
            stm = conn.createStatement();

            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                String word = rs.getString("word");
                dictionary.add(new Word(word));
                trie.insert(word);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
