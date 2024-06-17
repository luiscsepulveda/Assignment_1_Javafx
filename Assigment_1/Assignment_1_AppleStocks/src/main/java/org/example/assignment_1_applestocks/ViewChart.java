/*
Name> Luis C. Sepulveda
date> 2024/06/16

Thes class display will create  a scene that display the barchart view with the
* apple stock pear year for the last 10 years.   */


package org.example.assignment_1_applestocks;
//javaFx components
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//sql connection
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewChart {
    public static Scene createBarChartScene(StockApp app) {
        // Bar Chart configuration
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Years");
        yAxis.setLabel("Average Closing Stock Price ($USD)");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Apple Stock Prices by Year");

        XYChart.Series<String, Number> barSeries = new XYChart.Series<>();
        barSeries.setName("Average Stock Prices");

        // Fetching data from db
        try (Connection connection = DatabaseConnector.connect()) {
            //this line will fetch the avg stock price pear Year
            String query = "SELECT YEAR(Date) AS Year, AVG(Close) AS AverageClose FROM stock_prices GROUP BY YEAR(Date)";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String year = resultSet.getString("Year");
                double averageClose = resultSet.getDouble("AverageClose");

                // Add data to bar chart series
                barSeries.getData().add(new XYChart.Data<>(year, averageClose));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        barChart.getData().add(barSeries);

        // Button to switch to Table View
        Button switchButton = new Button("Table View");
        switchButton.getStyleClass().add("switch-button");
        switchButton.setOnAction(e -> app.switchToTableViewScene());

        // Layout
         // 10 px is the space btw elements
        VBox vbox = new VBox(10);
        // set alignment
        vbox.setAlignment(Pos.CENTER);
        //Padding arround the vbox in this case will be 20
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(barChart, switchButton);

        Scene scene = new Scene(vbox, 800, 600);
        scene.getStylesheets().add("appStyle.css");

        return scene;
    }
}
