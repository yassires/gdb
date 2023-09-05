public class Books {
    private int id;
    private String title;
    private Author author ;
    private String isbn;
    private String category;
    private int quantity;
    private int available;
    private int borrow;
    private int lost;

    public Books(int id, String title, Author author, String isbn, String category, int quantity, int available,int borrow, int lost) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.quantity = quantity;
        this.available = available;
        this.borrow = borrow;
        this.lost = lost;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }


    public String getCategory() {
        return category;
    }

    public int setQuantity() {
        return quantity;
    }
    public int setAvailable() {
        return available;
    }

    public int setBorrow() {
        return borrow;
    }

    public int setLost() {
        return lost;
    }

    public int addBook()
    {
        int status = 0;
        return status;
    }

    public String getAllBooks()
    {
        return "Books";
    }

    public int updateBook()
    {
        int status = 0;
        return status;
    }

    public int deleteBook()
    {
        int status = 0;
        return status;
    }
}