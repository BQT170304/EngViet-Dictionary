package com.example.dictionary;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Dictionary extends Trie {
    protected static Trie trie;
    protected static List<Word> dictionary;
    public Dictionary() {
        dictionary = new ArrayList<>();
        trie = new Trie();
        ConnectJDBC connectJDBC = new ConnectJDBC();
        Connection conn = connectJDBC.connect();

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
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
