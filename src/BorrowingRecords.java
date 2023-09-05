import java.util.Date;

public class BorrowingRecords {
    private int id;
    private String uid;

    private Date borrowingDate;
    private Date returnborrowingDate;

    private Books book;
    private Borrower borrower;

    public BorrowingRecords(int id, String bookIsbn, String uid, Date borrowingDate, Date returnborrowingDate, Books book, Borrower borrower) {
        this.id = id;
        this.book = book;
        this.uid = uid;
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
        return uid;
    }

    public Date getBorrowingDate() {
        return borrowingDate;
    }

    public Date getReturnborrowingDate() {
        return returnborrowingDate;
    }
}
