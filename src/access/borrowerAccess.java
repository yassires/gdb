package access;
import DB.Db;

import java.sql.*;

public class borrowerAccess {

public static int checkBorrower(String borrowerName) throws SQLException {
        int borrower_id = 0;
        String searchSql = "SELECT * FROM borrowers WHERE name = ?";
        Connection connection = DB.Db.getConnection();
        PreparedStatement statement = connection.prepareStatement(searchSql);

        // Set parameters for the user's input
        statement.setString(1, borrowerName);

        ResultSet resultSet = statement.executeQuery();
        borrower_id = resultSet.getInt("id");

    // Check if the result set contains any rows
        boolean borrowerExists = resultSet.isBeforeFirst();

        resultSet.close();
        statement.close();
        connection.close();

        return borrower_id;


}

    public static void createBorrower(String borrowerName,String borrowerCin,String borrowerMail) throws SQLException {

        try {
            String insertSql = "INSERT INTO borrowers (name,email,cin) VALUES (?,?,?)";
            Connection connection = Db.getConnection();
            PreparedStatement statement = connection.prepareStatement(insertSql);

            statement.setString(1, borrowerName);
            statement.setString(2, borrowerMail);
            statement.setString(2, borrowerCin);

            // Execute the INSERT statement to create the new borrower
            int rowsInserted = statement.executeUpdate();


            System.out.println("Borrower created successfully.");
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Failed to create borrower.");
            ;
        }



    }
}

