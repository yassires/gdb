package classes;

import java.sql.Date;

public class BorrowingRecords {
    private int id;
    private String isbn;
    private int br_id;

    private Date borrowingDate;
    private Date returnborrowingDate;

    private Books book;
    private Borrower borrower;

    public BorrowingRecords(String isbn, int br_id, Date borrowingDate, Date returnborrowingDate) {
        this.isbn = isbn;
        this.br_id = br_id;
        this.borrowingDate = borrowingDate;
        this.returnborrowingDate = returnborrowingDate;

    }

    public int getId() {
        return id;
    }

    public String getBookIsbn() {

        return isbn;
    }
    public Borrower getBorrower() {
        return borrower;
    }

    public int getborrowerId() {
        return br_id;
    }


    public java.sql.Date getBorrowingDate() {
        return borrowingDate;
    }

    public java.sql.Date getReturnborrowingDate() {
        return  returnborrowingDate;
    }
}
