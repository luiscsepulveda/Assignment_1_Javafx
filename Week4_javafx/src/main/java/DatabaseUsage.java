import com.example.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
public class DatabaseUsage {
    public static void main(String[] args) {
        DatabaseConnector dbConnector = new DatabaseConnector();
        Connection connection = dbConnector.connect();
        try {
            Statement statement = connection.createStatement();
            int rowsAffected = statement.executeUpdate("INSERT INTO Students (id, name, age) VALUES (1, 'John Doe', 20)");
            System.out.println("Inserted " + rowsAffected + " row(s) into the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
// Don't forget to close your connections when you're done
            try {
                if(connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}