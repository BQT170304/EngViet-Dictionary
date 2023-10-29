package com.example.dictionary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectJDBC {
    public Connection connect() {
        String localhost = "localhost:3306";
        String database = "dictionary";
        String connectionURL = "jdbc:mysql://" + localhost + "/" + database;
        Connection conn = null;
        try {
            String user = "root";
            String password = "1703";
            conn = DriverManager.getConnection(connectionURL, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
