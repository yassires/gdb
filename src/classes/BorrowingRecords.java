package classes;

import classes.Borrower;

import java.util.Date;

public class BorrowingRecords {
    private int id;
    private String br_id;

    private Date borrowingDate;
    private Date returnborrowingDate;

    private Books book;
    private Borrower borrower;

    public BorrowingRecords(int id, String bookIsbn, String br_id, Date borrowingDate, Date returnborrowingDate, Books book, Borrower borrower) {
        this.id = id;
        this.book = book;
        this.br_id = br_id;
        this.borrowingDate = borrowingDate;
        this.returnborrowingDate = returnborrowingDate;
        this.borrower = borrower;
    }

    public int getId() {
        return id;
    }

    public Books getBookIsbn() {
        return book;
    }
    public Borrower getBorrower() {
        return borrower;
    }

    public String getUid() {
        return br_id;
    }

    public Date getBorrowingDate() {
        return borrowingDate;
    }

    public Date getReturnborrowingDate() {
        return returnborrowingDate;
    }
}
