package access;

import classes.Author;
import classes.Books;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class bookAccess {


    public static int addBook(Books book, int author_id) throws SQLException {

        int status = 0;
            Connection conn = DB.Db.getConnection();
            PreparedStatement s = conn.prepareStatement("INSERT INTO `books`( `title`, `author_id`, `isbn`, `category`, `release_date`, `quantity`, `available`, `borrow`, `lost`) VALUES (?,?,?,?,?,?,?,?,?)");

            s.setString(1, book.getTitle());
            s.setInt(2,author_id);
            s.setString(3, book.getIsbn());
            s.setString(4, book.getCategory());
            s.setDate(5, book.getReleaseDate());
            s.setInt(6, book.getQuantity());
            s.setInt(7, book.getAvailable());
            s.setInt(8, book.getBorrow());
            s.setInt(9, book.getLost());
            status = s.executeUpdate();


        return status;
    }

    public static void displayBooks() throws SQLException {

        String sql = "SELECT b.*, a.name AS author_name " + "FROM books b " + "JOIN author a ON b.author_id = a.id";

            Connection conn = DB.Db.getConnection();
            Statement st = conn.createStatement();

            ResultSet s = st.executeQuery(sql);

            while(s.next()){

                int id = s.getInt("id");
                String title =s.getString("title");
                String isbn = s.getString("isbn");
                String category = s.getString("category");
                Date release_date = s.getDate("release_date");
                int quantity = s.getInt("quantity");
                int available = s.getInt("available");
                int borrow = s.getInt("borrow");
                int lost = s.getInt("lost");
                int author_id = s.getInt("author_id");
                String author_name = s.getString("author_name");

                System.out.println("Book ID: " + id);
                System.out.println("Book Title: " + title);
                System.out.println("ISBN: " + isbn);
                System.out.println("Category: " + category);
                System.out.println("Author: " + author_name);
                System.out.println("Release Date: " + release_date);
                System.out.println("Available: " + available);
                System.out.println("Borrow: " + borrow);
                System.out.println("Lost: " + lost);
                System.out.println("---------------");

            }



    }

    public static void deleteBook(int id) {
        try {
            String deleteSql = "DELETE FROM books WHERE id = ?";
            Connection connection = DB.Db.getConnection();
            PreparedStatement statement = connection.prepareStatement(deleteSql);

            // Set the id parameter in the prepared statement
            statement.setInt(1, id);

            // Execute the delete statement
            int rowsDeleted = statement.executeUpdate();

            // Handle the result or error here if needed
            if (rowsDeleted > 0) {
                System.out.println("Book deleted successfully");
            } else {
                System.out.println("Book with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting book: " + e.getMessage());
        }
    }


    public static int updateBook(int bookId,String bookName) throws SQLException{
        int status = 0;
        String deleteSql = "UPDATE `books` SET title= ? WHERE id = ?";

        Connection connection = DB.Db.getConnection();
        PreparedStatement statement = connection.prepareStatement(deleteSql);

        // Set the id parameter in the prepared statement
        statement.setString(1, bookName);
        statement.setInt(2, bookId);

        // Execute the delete statement
        int rowsUpdated = statement.executeUpdate();

        if (rowsUpdated > 0) {
            return  1;
        } else {
            return 0;
        }
    }

    public static int  fetchBookId(int bookid) throws SQLException {
        int book_Id = 0;

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/t", "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM books WHERE id = ?");
            preparedStatement.setInt(1, bookid);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                book_Id = resultSet.getInt("id");
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();

        return book_Id;
    }


    public static void searchBooks(String srch) {
        try {


            String searchSql = "SELECT b.*, a.name " + "FROM books b " + "JOIN author a ON b.author_id = a.id " + "WHERE LOWER(b.title) LIKE  LOWER(?) OR LOWER(a.name) LIKE LOWER(?)";
            Connection connection = DB.Db.getConnection();
            PreparedStatement statement = connection.prepareStatement(searchSql);

            // Set parameters for title and author name search
            String searchParam = "%" + srch + "%";
            statement.setString(1, searchParam);
            statement.setString(2, searchParam);

            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("No matching books found.");
            } else {
                System.out.println("Matching Books:");
                while (resultSet.next()) {

                    int bookId = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String authorName = resultSet.getString("name");
                    System.out.println("Book ID: " + bookId);
                    System.out.println("Title: " + title);
                    System.out.println("Author: " + authorName);
                    System.out.println("---------------");
                }
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Something went wrong"+ e.getMessage());
        }
    }






}
