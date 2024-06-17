/*
Name> Luis C. Sepulveda
date> 2024/06/16

Thes class display a stock data in a table view that was created on my workbench sql.
* so basically will connect to the database and fetche all the data */


package org.example.assignment_1_applestocks;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class TableViewScene {
    //this method create a Scene with the table view
    public static Scene createTableViewScene(StockApp app) {

        TableView<StockInfo> tableView = new TableView<>();

        //collumns tables for the data

        TableColumn<StockInfo, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<StockInfo, Double> openColumn = new TableColumn<>("Open");
        openColumn.setCellValueFactory(new PropertyValueFactory<>("open"));

        TableColumn<StockInfo, Double> highColumn = new TableColumn<>("High");
        highColumn.setCellValueFactory(new PropertyValueFactory<>("high"));

        TableColumn<StockInfo, Double> lowColumn = new TableColumn<>("Low");
        lowColumn.setCellValueFactory(new PropertyValueFactory<>("low"));

        TableColumn<StockInfo, Double> closeColumn = new TableColumn<>("Close");
        closeColumn.setCellValueFactory(new PropertyValueFactory<>("close"));

        TableColumn<StockInfo, Long> volumeColumn = new TableColumn<>("Volume");
        volumeColumn.setCellValueFactory(new PropertyValueFactory<>("volume"));

        tableView.getColumns().addAll(dateColumn, openColumn, highColumn, lowColumn, closeColumn, volumeColumn);

        // Fetching the data from the database
        ObservableList<StockInfo> stockDataList = FXCollections.observableArrayList();
        try (Connection connection = DatabaseConnector.connect()) {
            //select all the data from stock prices table
            String query = "SELECT * FROM stock_prices";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                stockDataList.add(new StockInfo(
                        resultSet.getString("Date"),
                        resultSet.getDouble("Open"),
                        resultSet.getDouble("High"),
                        resultSet.getDouble("Low"),
                        resultSet.getDouble("Close"),
                        resultSet.getLong("Volume")
                ));
            }

            //the list will be set as items of the table view
            tableView.setItems(stockDataList);

            // Button to switch to Bar Chart View this call the switchtobarchartscene method.
            Button switchButton = new Button("Bar Chart View");
            switchButton.getStyleClass().add("switch-button");
            switchButton.setOnAction(e -> app.switchToBarChartScene());

            // Layout for the table view
            VBox vbox = new VBox(10);
            //allignment
            vbox.setAlignment(Pos.CENTER);
            //padding
            vbox.setPadding(new Insets(20));
            vbox.getChildren().addAll(tableView, switchButton);
            //columns fill the entire display view https://jenkov.com/tutorials/javafx/vbox.html
            VBox.setVgrow(tableView, Priority.ALWAYS);

            //size of the scene to show tableview
            //I use 430 width bcz is the total width of the columns
            Scene scene = new Scene(vbox, 430, 600);
            scene.getStylesheets().add("appStyle.css");

            return scene;
//this will catch any sqlException during the connection with the database
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
