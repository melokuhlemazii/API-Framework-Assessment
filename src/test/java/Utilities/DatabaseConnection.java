package Utilities;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {

    public static String getEmail;
    public static String getPassword;

    public static void dbConnection() throws SQLException {
        String dbURL = System.getenv("DB_URL");
        String dbUsername = System.getenv("DB_USERNAME");
        String dbPassword = System.getenv("DB_PASSWORD");

        // Fall back to default if not set (for local development)
        if (dbURL == null){
            dbURL = "jdbc:mysql://102.222.124.22:3306/ndosian6b8b7_teaching";
        }
        if (dbUsername == null){
            dbUsername = "ndosian6b8b7_teaching";
        }
        if (dbPassword == null){
            dbPassword = "^{SF0a=#~[~p)@l1";
        }

        try (Connection connection = java.sql.DriverManager.getConnection(dbURL, dbUsername, dbPassword)) {
            try (java.sql.Statement statement = connection.createStatement();
                 java.sql.ResultSet resultSet = statement.executeQuery("SELECT * FROM loginUser WHERE id = 6")) {
                while (resultSet.next()) {
                    getEmail = resultSet.getString("email");
                    getPassword = resultSet.getString("password");
                    System.out.println("Email: " + getEmail + ", Password: " + getPassword);
                }
            } catch (SQLException e) {
                System.out.println("Error executing query: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }


    }
}








