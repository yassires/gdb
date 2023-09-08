package access;

import DB.Db;
import classes.Author;

import java.sql.*;

public class authorAccess {


    public static int fetchAuthorId(String authorName) throws SQLException {
        int authorId = 0;

        Connection connection = Db.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM authors WHERE LOWER(name) = LOWER(?)");
        preparedStatement.setString(1, authorName);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            authorId = resultSet.getInt("id");
        }
        else {
            PreparedStatement s = connection.prepareStatement("INSERT INTO `authors`(`name`) VALUES (?)");
            s.setString(1,authorName);
            s.executeUpdate();
            preparedStatement = connection.prepareStatement("SELECT id FROM authors WHERE LOWER(name) = LOWER(?)");
            preparedStatement.setString(1, authorName);
            resultSet = preparedStatement.executeQuery();
            authorId = resultSet.getInt("id");
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return authorId;
    }
}
