/*

Name> Luis C. Sepulveda
        date> 2024/06/16

        basically this class initializes and manage barscene and tableviewscene and also
        will handle the switching methods btw these scenes.
        */


package org.example.assignment_1_applestocks;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class StockApp extends Application {
    private Scene barChartScene;
    private Scene tableViewScene;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Apple Stock Prices");

        // Set the favicon
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/bear-market.png"))));

        // Initialize the scenes
        barChartScene = ViewChart.createBarChartScene(this);
        tableViewScene = TableViewScene.createTableViewScene(this);

        // Start with the bar chart scene
        primaryStage.setScene(barChartScene);
        primaryStage.show();
    }

    //switching methods in order to jump from barchartscene to table view scene and viceversa
    public void switchToBarChartScene() {
        primaryStage.setScene(barChartScene);
    }

    public void switchToTableViewScene() {
        primaryStage.setScene(tableViewScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
