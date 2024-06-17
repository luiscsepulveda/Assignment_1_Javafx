/*
Name> Luis C. Sepulveda
date> 2024/06/16

this will handel the connection with the database.   */

package org.example.assignment_1_applestocks;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/Apple_stocks_price";
    private static final String USER = "root";
    private static final String PASS = "Kine0319..";
    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }
}