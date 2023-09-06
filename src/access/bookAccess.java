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

    public List<Books> displayBooks() throws SQLException {

        List<Books> bookList =new ArrayList<Books>();

        try {
            Connection conn = DB.Db.getConnection();
            Statement st = conn.createStatement();
            ResultSet s = st.executeQuery("SELECT * FROM books");

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

                Author author = new Author();

                Books book =new Books(title,author,isbn,category,release_date,quantity,available,borrow,lost);
                bookList.add(book);

            }

        } catch (SQLException e){

            e.printStackTrace();
        }
        return bookList;
    }

    public static int deleteBook(int id) throws SQLException {
        int status = 0;
        String deleteSql = "DELETE FROM books WHERE id = ?";

             Connection connection = DB.Db.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteSql);

            // Set the id parameter in the prepared statement
            statement.setInt(1, id);

            // Execute the delete statement
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                return  1;
            } else {
                return 0;
            }

    }




}
