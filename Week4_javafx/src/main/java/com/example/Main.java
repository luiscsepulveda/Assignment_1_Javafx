package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main extends Application {

    DatabaseConnector dbConnector = new DatabaseConnector();
    TextArea textArea = new TextArea();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TextField idField = new TextField();
        idField.setPromptText("ID");
        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        TextField ageField = new TextField();
        ageField.setPromptText("Age");

        Button insertButton = new Button("Insert");
        insertButton.setOnAction(e -> insertRecord(idField.getText(), nameField.getText(), ageField.getText()));

        Button updateButton = new Button("Update");
        updateButton.setOnAction(e -> updateRecord(idField.getText(), nameField.getText(), ageField.getText()));

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteRecord(idField.getText()));

        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(e -> showStudents());

        VBox vbox = new VBox(5, textArea, idField, nameField, ageField, insertButton, updateButton, deleteButton, refreshButton);
        vbox.setPadding(new Insets(15));
        Scene scene = new Scene(vbox, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        showStudents();
    }

    private void showStudents() {
        try (Connection connection = dbConnector.connect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Students");
            StringBuilder builder = new StringBuilder();
            while (resultSet.next()) {
                builder.append(resultSet.getInt("id")).append(" - ")
                        .append(resultSet.getString("name")).append(" - ")
                        .append(resultSet.getInt("age")).append("\n");
            }
            textArea.setText(builder.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertRecord(String id, String name, String age) {
        String sql = "INSERT INTO Students (id, name, age) VALUES (?, ?, ?)";
        try (Connection connection = dbConnector.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(id));
            pstmt.setString(2, name);
            pstmt.setInt(3, Integer.parseInt(age));
            pstmt.executeUpdate();
            showStudents();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateRecord(String id, String name, String age) {
        String sql = "UPDATE Students SET name = ?, age = ? WHERE id = ?";
        try (Connection connection = dbConnector.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, Integer.parseInt(age));
            pstmt.setInt(3, Integer.parseInt(id));
            pstmt.executeUpdate();
            showStudents();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteRecord(String id) {
        String sql = "DELETE FROM Students WHERE id = ?";
        try (Connection connection = dbConnector.connect(); PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(id));
            pstmt.executeUpdate();
            showStudents();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}