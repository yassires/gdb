package access;

import DB.Db;
import classes.Book;

import java.sql.*;

public class BookAccess {


    public static int addBook(Book book, int author_id) throws SQLException {
        System.out.println(book.getIsbn());
        System.exit(0);
        int status = 0;
            Connection conn = Db.getConnection();
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

        String sql = "SELECT b.*, a.name AS author_name " + "FROM books b " + "JOIN authors a ON b.author_id = a.id";

            Connection conn = Db.getConnection();
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
            Connection connection = Db.getConnection();
            PreparedStatement statement = connection.prepareStatement(deleteSql);

            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();

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
        String updateSql = "UPDATE `books` SET title= ? WHERE id = ?";

        Connection connection = Db.getConnection();
        PreparedStatement statement = connection.prepareStatement(updateSql);

        statement.setString(1, bookName);
        statement.setInt(2, bookId);

        int rowsUpdated = statement.executeUpdate();
        System.out.println(bookName);

        if (rowsUpdated > 0) {
            return 1;
        }else {
            return 0;
        }

    }

    public static int updateBook(int bookId, Book book, int option) throws SQLException {
        int status = 0;
        String deleteSql = "";


        if (option == 2) {
            deleteSql = "UPDATE `books` SET author_id = ? WHERE id = ?";
        } else {
            deleteSql = "UPDATE `books` SET quantity = ? , available = (?) - borrow - lost WHERE id = ?";
        }


    //try()   try with resource
        try(
                Connection connection =Db.getConnection();
                PreparedStatement statement = connection.prepareStatement(deleteSql);) {


            if (option == 2) {
                statement.setInt(1, book.getAuthor().getId());
                System.out.println(book.getAuthor().getId());
                statement.setInt(2, bookId);
            }else {
                statement.setInt(1, book.getQuantity());
                statement.setInt(2, book.getQuantity());
                statement.setInt(3, bookId);
            }

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                return status = 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }


    public static int  fetchBookIsbn(String bookisbn) throws SQLException {
        int book_Id = 0;

            Connection connection = Db.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM books WHERE isbn = ?");
            preparedStatement.setString(1, bookisbn);
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

            String searchSql = "SELECT b.*, a.name " + "FROM books b " + "JOIN authors a ON b.author_id = a.id " + "WHERE LOWER(b.title) LIKE  LOWER(?) OR LOWER(a.name) LIKE LOWER(?)";
            Connection connection = Db.getConnection();
            PreparedStatement statement = connection.prepareStatement(searchSql);

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


    public static int getTotalBooks() {
        int totalBooks = 0;
        try (Connection connection = Db.getConnection();
             Statement statement = connection.createStatement()) {

            String sqlQuery = "SELECT COUNT(*) AS total_books FROM books";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            if (resultSet.next()) {
                totalBooks = resultSet.getInt("total_books");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalBooks;
    }

    public static int getAvailableBooks() {
        int availableBooks = 0;
        try (Connection connection = Db.getConnection();
             Statement statement = connection.createStatement()) {

            String sqlQuery = "SELECT SUM(available) AS available_books FROM books";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            if (resultSet.next()) {
                availableBooks = resultSet.getInt("available_books");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return availableBooks;
    }

    public static int getReservedBooks() {
        int borrowedBooks = 0;
        try (Connection connection = Db.getConnection();
             Statement statement = connection.createStatement()) {

            String sqlQuery = "SELECT SUM(borrow) AS borrowed_books FROM books";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            if (resultSet.next()) {
                borrowedBooks = resultSet.getInt("borrowed_books");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return borrowedBooks;
    }

    public static int getLostBooks() {
        int lostBooks = 0;
        try (Connection connection = Db.getConnection();
             Statement statement = connection.createStatement()) {

            String sqlQuery = "SELECT SUM(lost) AS lost_books FROM books";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            if (resultSet.next()) {
                lostBooks = resultSet.getInt("lost_books");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lostBooks;
    }

    public static int getTotalUsers() {
        int totalBorrowers = 0;
        try (Connection connection = Db.getConnection();
             Statement statement = connection.createStatement()) {

            String sqlQuery = "SELECT COUNT(*) AS total_borrowers FROM borrowers";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            if (resultSet.next()) {
                totalBorrowers = resultSet.getInt("total_borrowers");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalBorrowers;
    }


}
