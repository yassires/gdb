package classes;

import java.sql.*;

public class Author {
    private int id;
    private String name;


    public Author() {

    }


    public int getId() {

        return id;
    }

    public String getName() {

        return name;
    }

    public void CreateAuthor(){

        System.out.println("Autour created successfully");
    }

    public  int fetchAuthorId(String authorName) {
        int authorId = 0;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/t", "root", "");

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM author WHERE name = ?");
            preparedStatement.setString(1, authorName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                authorId = resultSet.getInt("id");
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authorId;
    }

}
