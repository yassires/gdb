package access;

import classes.Author;

import java.sql.*;

public class authorAccess {
    public static int CreateAuthor(String name) throws SQLException {
        int status = 0;
        Connection conn = DB.Db.getConnection();
        PreparedStatement s = conn.prepareStatement("INSERT INTO `author`(`name`) VALUES (?)");
        s.setString(1,name);
        status = s.executeUpdate();
        return status;
    }

    public static int fetchAuthorId(String authorName) throws SQLException {
        int authorId = 0;

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/t", "root", "");
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM author WHERE LOWER(name) = LOWER(?)");
        preparedStatement.setString(1, authorName);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            authorId = resultSet.getInt("id");
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return authorId;
    }
}
