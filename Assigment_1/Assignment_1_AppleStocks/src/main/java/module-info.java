module org.example.assignment_1_applestocks {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens org.example.assignment_1_applestocks to javafx.fxml;
    exports org.example.assignment_1_applestocks;
}