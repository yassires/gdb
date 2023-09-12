package access;

import DB.Db;
import classes.BorrowingRecords;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BorrowRdAccess {
    public static String borrowBook(BorrowingRecords borrowingRecords, int borrower_id) throws SQLException {

            try (Connection connection = Db.getConnection()) {
                    String selectBookSql = "SELECT id, available, borrow FROM books WHERE isbn = ?";
                    try (PreparedStatement selectBookStatement = connection.prepareStatement(selectBookSql)) {
                            selectBookStatement.setString(1, borrowingRecords.getBookIsbn());
                            ResultSet bookResultSet = selectBookStatement.executeQuery();

                            if (bookResultSet.next()) {
                                    int bookId = bookResultSet.getInt("id");
                                    int available = bookResultSet.getInt("available");
                                    int borrow = bookResultSet.getInt("borrow");

                                            if (available > 0) {
                                                    String insertRecordSql = "INSERT INTO `borrowingrecords`( `bookIsbn`, `br_id`, `borrowingDate`, `returnBorrowingDate`) VALUES (?,?,?,?)";

                                                    try (PreparedStatement insertRecordStatement = connection.prepareStatement(insertRecordSql)) {
                                                            insertRecordStatement.setString(1, borrowingRecords.getBookIsbn());
                                                            insertRecordStatement.setInt(2, borrower_id);
                                                            insertRecordStatement.setDate(3, borrowingRecords.getBorrowingDate());
                                                            insertRecordStatement.setDate(4, borrowingRecords.getReturnborrowingDate());
                                                            insertRecordStatement.executeUpdate();

                                                            String updateBookSql = "UPDATE books SET available = available - 1 , borrow = borrow + 1 WHERE id = ?";
                                                            try (PreparedStatement updateBookStatement = connection.prepareStatement(updateBookSql)) {
                                                                    updateBookStatement.setInt(1, bookId);
                                                                    updateBookStatement.executeUpdate();
                                                            }
                                                            String successMessage = "Book with ISBN '" + borrowingRecords.getBookIsbn() + "' borrowed successfully";
                                                            return successMessage;
                                                    }
                                            } else {
                                                    String errorMessage = "Book with ISBN '" + borrowingRecords.getBookIsbn() + "' is not available for borrowing";
                                                    return errorMessage;
                                            }

                            }
                    }
            } catch (SQLException e) {
                    e.printStackTrace();
                    String errorMessage = "Error: " + e.getMessage();
                    return errorMessage;
            }

            String errorMessage = "Book with ISBN '" + borrowingRecords.getBookIsbn() + "' not found";
            return errorMessage;

    }

        public static String returnBook(int borrowerId, String bookIsbn) {
                try (Connection connection = Db.getConnection()) {
                        String selectSql = "SELECT br.id ,b.available,b.borrow FROM borrowingRecords br INNER JOIN books b ON br.bookIsbn = ? AND br.br_id = ?";
                        try (PreparedStatement selectRecordStatement = connection.prepareStatement(selectSql)) {
                                selectRecordStatement.setString(1, bookIsbn);
                                selectRecordStatement.setInt(2, borrowerId);
                                ResultSet recordResultSet = selectRecordStatement.executeQuery();

                                if (recordResultSet.next()) {
                                        int recordId = recordResultSet.getInt("id");
                                        String updateBookSql = "UPDATE books SET available = available + 1, borrow = borrow - 1 WHERE isbn = ?";
                                        try (PreparedStatement updateBookStatement = connection.prepareStatement(updateBookSql)) {

                                                updateBookStatement.setString(1, bookIsbn);
                                                updateBookStatement.executeUpdate();
                                        }

                                        String deleteRecordSql = "DELETE FROM borrowingrecords WHERE id = ?";
                                        try (PreparedStatement deleteRecordStatement = connection.prepareStatement(deleteRecordSql)) {
                                                deleteRecordStatement.setInt(1, recordId);
                                                deleteRecordStatement.executeUpdate();
                                                String successMessage = "Book with ISBN '" + bookIsbn + "' returned successfully";
                                                return successMessage;
                                        }
                                } else {
                                        String errorMessage = "Borrowing record  is not found or the book is already returned.";
                                        return errorMessage;
                                }
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                        String errorMessage = "Error: " + e.getMessage();
                        return errorMessage;
                }

        }

}
