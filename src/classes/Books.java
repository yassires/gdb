package classes;

import classes.Author;

import java.sql.Date;

public class Books {
    private int id;
    private String title;
    private Author author ;
    private String isbn;
    private String category;
    private Date release_date;
    private int quantity;
    private int available;
    private int borrow;
    private int lost;

    public Books(String title, Author author, String isbn, String category,Date release_date, int quantity, int available,int borrow, int lost){

        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.release_date = release_date;
        this.quantity = quantity;
        this.available = available;
        this.borrow = borrow;
        this.lost = lost;
    };
    public Books(int id) {
        this.id = id;
    }

    public Books(String bookTitle, int authorId, String bookIsbn, String bookCategory, Date bookReleaseDate, int bookQuantity, int bookQuantity1, int borrow, int lost) {


        this.title = bookTitle;
        this.author = new Author();
        this.isbn = bookIsbn;
        this.category = bookCategory;
        this.release_date = bookReleaseDate;
        this.quantity = bookQuantity;
        this.available = bookQuantity1;
        this.borrow = borrow;
        this.lost = lost;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
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

    public Date getReleaseDate(){
        return release_date;
    }

    public int getQuantity(){
        return quantity;
    }
    public int getAvailable(){
        return available;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public int getBorrow(){
        return borrow;
    }
    public int getLost(){
        return lost;
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


}