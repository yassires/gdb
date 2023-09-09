package access;
import DB.Db;

import java.sql.*;

public class borrowerAccess {

public static int checkBorrower(String borrowerName) throws SQLException {
        int borrower_id = 0;
    Connection connection = Db.getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM borrowers WHERE LOWER(name) = LOWER(?)");
    preparedStatement.setString(1, borrowerName);
    ResultSet resultSet = preparedStatement.executeQuery();
    if (resultSet.next()) {
        borrower_id = resultSet.getInt("id");
    }
    else {
        borrower_id = 0;
    }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return borrower_id;


}

    public static String createBorrower(String borrowerName,String borrowerCin,String borrowerMail) throws SQLException {

        try {
            String insertSql = "INSERT INTO borrowers (name,email,cin) VALUES (?,?,?)";
            Connection connection = Db.getConnection();
            PreparedStatement statement = connection.prepareStatement(insertSql);

            statement.setString(1, borrowerName);
            statement.setString(2, borrowerMail);
            statement.setString(3, borrowerCin);

            statement.executeUpdate();


            String msg ="Borrower created successfully.";
            return msg;

        } catch (SQLException e) {
            String msg = "Failed to create borrower.";
            return msg;
        }

    }
}

