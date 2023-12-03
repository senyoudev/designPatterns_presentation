package org.example;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.example.model.DatabaseConnectionManager;

public class TableCreator {

    public static void createTable() {
        String createTableQuery = "CREATE TABLE products ("
                + "product_id SERIAL PRIMARY KEY,"
                + "name VARCHAR(255),"
                + "price DOUBLE PRECISION)";

        try (Connection connection = DatabaseConnectionManager.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(createTableQuery);

            System.out.println("Table created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create the table.", e);
        }
    }

    public static void main(String[] args) {
        createTable();
    }
}
