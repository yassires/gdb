package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Db {

        private static final String DB_URL = "jdbc:mysql://localhost:3306/t";
        private static final String USER = "root";
        private static final String PASSWORD = "";

        public static Connection getConnection() {
            try {
                return DriverManager.getConnection(DB_URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to connect to the database.");
            }
        }


}
