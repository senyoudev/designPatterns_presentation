package org.example.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {
    private static DatabaseConnectionManager instance = new DatabaseConnectionManager();

    private DatabaseConnectionManager() {}

    public static DatabaseConnectionManager getInstance() {
        return instance;
    }

    private static final String url = "jdbc:postgresql://localhost:5432/dp";
    private static final String username = "postgres";
    private static final String password = "password";

    public static String getUrl() {
        return url;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to establish a database connection.", e);
        }
    }
}