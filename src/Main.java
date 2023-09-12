import access.AuthorAccess;
import access.BookAccess;
import access.BorrowRdAccess;
import access.BorrowerAccess;
import classes.Author;
import classes.Book;
import classes.BorrowingRecords;


import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) throws SQLException, ParseException {

        int option ;

        do
        {
            System.out.println("1. Ajouter un livre à la bibliothéque");
            System.out.println("2. Rechercher un livre par titre ou auteur");
            System.out.println("3. Afficher la liste des livres");
            System.out.println("4. Emprunter un livre");
            System.out.println("5. Retourner un livre emprunté");
            System.out.println("6. Supprimer un livre de la bibliothèque");
            System.out.println("7. Modifier les informations d'un livre");
            System.out.println("8. Statistiques");
            System.out.println("0. Exit");
            System.out.println("===========================================");
            System.out.println("Enter an option");
            System.out.println("===========================================");
            option = scanner.nextInt();
            System.out.println("\n");

            switch(option)
            {
                case 1:
                    addBook();
                    break;

                case 2:
                    searchBooks();
                    break;

                case 3:
                    displayBooks();
                    break;

                case 4:
                    borrowBook();
                    break;

                case 5:
                    returnBook();
                    break;
                case 6:
                    deleteBook();
                    break;
                case 7:
                    updateBook();
                    break;
                case 8:
                    statistics();
                    break;
                case 0:
                    System.out.println("******************************THANK YOU******************************");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid Option! Please enter again");
                    break;
            }
        }while(option != 0);
    }
    public static void displayBooks() throws SQLException {
        System.out.println("-----------------------ALL BOOKS------------------------");
            //books
           BookAccess.displayBooks();
        System.out.println("-----------------------------------------------");
        System.out.println("\n");

    }



    public static void addBook() throws ParseException, SQLException {

        System.out.println("------------------------------------------------");
        System.out.println("Enter Book ISBN:");
        System.out.println("------------------------------------------------");
        String bookIsbn = scanner.next();
        scanner.nextLine();
        System.out.println("------------------------------------------------");
        System.out.println("Enter Author Name:");
        System.out.println("------------------------------------------------");
        String authorName = scanner.nextLine();
        Author author = new Author();

        int authorId = AuthorAccess.fetchAuthorId(authorName);

        if (authorId != 0) {
            System.out.println("Author ID: " + authorId);
        } else {
                System.out.println("Something went wrong");
        }
        System.out.println("------------------------------------------------");
        System.out.println("Enter Book Title:");
        System.out.println("------------------------------------------------");
        String bookTitle = scanner.nextLine();
        System.out.println("------------------------------------------------");
        System.out.println("Enter Category:");
        System.out.println("------------------------------------------------");
        String bookCategory = scanner.nextLine();
        System.out.println("------------------------------------------------");
        System.out.println("Enter Release Date [yyyy-mm-dd]:");
        System.out.println("------------------------------------------------");
        String dateString = scanner.next();
        Date bookReleaseDate = Date.valueOf(dateString);

        System.out.println("------------------------------------------------");
        System.out.println("Enter Quantity:");
        System.out.println("------------------------------------------------");
        int BookQuantity = Integer.valueOf(scanner.nextInt());
        while (true) {
            if (BookQuantity <= 0) {
                System.out.println("La quantité doit être supérieure à 0. Veuillez entrer une quantité valide :");
                BookQuantity = scanner.nextInt();
                scanner.nextLine();
            } else {
                break;
            }
        }


        Book book = new Book(bookTitle,authorId,bookIsbn,bookCategory,bookReleaseDate,BookQuantity,BookQuantity,0,0);
        int status = BookAccess.addBook(book,authorId);
        if(status ==1 )
        {
            System.out.println("Book added successfully");
        }
        else
        {
            System.out.println("something went wrong");
        }
        System.out.println("\n");
    }

    public static void updateBook() throws SQLException {
        int opt;
        int choice;
        int book_id = 0;
        System.out.println("------------------------------------------------");
        System.out.println("Select what do you want to update:");
        System.out.println("------------------------------------------------");
        System.out.println("1. Update title");
        System.out.println("2. Update author name");
        System.out.println("3. Update quantity");
        opt = scanner.nextInt();
        System.out.println("\n");
        scanner.nextLine();

        System.out.println("------------------------------------------------");
        System.out.println("Enter Book Isbn:");
        System.out.println("------------------------------------------------");
        String bookIsbn = scanner.nextLine();

        int status = 0;
        switch (opt)
        {
            case 1:
                System.out.println("------------------------------------------------");
                System.out.println("Enter New Book Name:");
                System.out.println("------------------------------------------------");
                String bookName = scanner.nextLine();
                System.out.println("------------------------------------------------");

                Book book = new Book(book_id);
                book_id = BookAccess.fetchBookIsbn(bookIsbn);
                status = BookAccess.updateBook(book_id,bookName);
                break;
            case 2:
                System.out.println("------------------------------------------------");
                System.out.println("Enter New Author Name:");
                System.out.println("------------------------------------------------");
                String authorName = scanner.nextLine();
                System.out.println("------------------------------------------------");
                int authorId = AuthorAccess.fetchAuthorId(authorName);
                book_id = BookAccess.fetchBookIsbn(bookIsbn);
                Book book1 = new Book(book_id);
                Author author = new Author();
                author.setId(authorId);
                book1.setAuthor(author);
                status = BookAccess.updateBook(book_id, book1,opt);
                break;
            case 3:
                System.out.println("------------------------------------------------");
                System.out.println("Enter New  Quantity:");
                System.out.println("------------------------------------------------");
                int quantity = scanner.nextInt();
                System.out.println("------------------------------------------------");

                Book books = new Book();
                books.setQuantity(quantity);
                book_id = BookAccess.fetchBookIsbn(bookIsbn);
                status = BookAccess.updateBook(book_id,books,opt);
                break;
            default:
                System.out.println("Invalid Option! Please enter again");
                break;
        }



        if(status == 1 )
        {
            System.out.println("Book updated successfully");
        }
        else
        {
            System.out.println("Something went wrong");
        }
        System.out.println("\n");

    }

    public static void deleteBook() {
        try {
            System.out.println("------------------------------------------------");
            System.out.println("Enter Book Id:");
            System.out.println("------------------------------------------------");
            int bookId = scanner.nextInt();
            BookAccess.deleteBook(bookId);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer for Book Id.");
            scanner.nextLine(); // Clear the invalid input from the scanner
        }
        System.out.println("\n");
    }


    public static void searchBooks() {
        System.out.println("--------------------Search Book-----------------------");
        System.out.println("------------------------------------------------");
        System.out.println("Search for books by title or author: ");
        System.out.println("------------------------------------------------");
        String srch = scanner.nextLine();
        BookAccess.searchBooks(srch);
        System.out.println("Press Enter to return to the main menu...");
        scanner.nextLine();

    }

    public static void borrowBook() throws SQLException {
        String borrowerCin = "";
        String borrowerMail = "";
        System.out.println("--------------------Borrow a Book-----------------------");
        System.out.println("------------------------------------------------");
        System.out.println("Enter Book Isbn: ");
        System.out.println("------------------------------------------------");
        String ibn = scanner.next();
        scanner.nextLine();
        System.out.println("------------------------------------------------");
        System.out.println("Enter Your name:");
        System.out.println("------------------------------------------------");
        String borrowerName = scanner.nextLine();
        int borrowerId = BorrowerAccess.checkBorrower(borrowerName);

        if (borrowerId !=0){
            System.out.println("Borrower ID: " + borrowerId);
        }else {

        System.out.println("------------------------------------------------");
        System.out.println("Enter Your CIN:");
        System.out.println("------------------------------------------------");
        borrowerCin = scanner.nextLine();
        System.out.println("------------------------------------------------");
        System.out.println("Enter Your Mail:");
        System.out.println("------------------------------------------------");
        borrowerMail = scanner.nextLine();
            BorrowerAccess.createBorrower(borrowerName,borrowerCin,borrowerMail);

            borrowerId = BorrowerAccess.checkBorrower(borrowerName);
        }




        Date borrowDate = Date.valueOf(LocalDate.now());
        System.out.println("------------------------------------------------");
        System.out.println("Enter Return date:");
        System.out.println("------------------------------------------------");
        String dateStringR = scanner.nextLine();
        Date borrowReturn = Date.valueOf(dateStringR);


        BorrowingRecords borrowingRecords = new BorrowingRecords(ibn,borrowerId,borrowDate,borrowReturn);
        String returnResult = BorrowRdAccess.borrowBook(borrowingRecords,borrowerId);

        System.out.println(returnResult);
        System.out.println("\n");

    }

    public static void returnBook() throws SQLException {
        System.out.println("------------------------------------------------");
        System.out.print("Enter Your ID: ");
        System.out.println("------------------------------------------------");
        int borrower_Id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("------------------------------------------------");
        System.out.print("Enter the ISBN of the book: ");
        String isbn = scanner.nextLine();
        System.out.println("------------------------------------------------");

        String returnResult = BorrowRdAccess.returnBook(borrower_Id,isbn);

        System.out.println(returnResult);
    }


    public static void statistics(){
        int totalBooksStats = BookAccess.getTotalBooks();
        int availableBooksStats = BookAccess.getAvailableBooks();
        int reservedBooksStats = BookAccess.getReservedBooks();
        int lostBooksStats = BookAccess.getLostBooks();
        int totalUsersStats = BookAccess.getTotalUsers();

        System.out.println("----------------Library Statistics:----------------");
        System.out.println("Total Books: " + totalBooksStats);
        System.out.println("Available Books: " + availableBooksStats);
        System.out.println("Reserved Books: " + reservedBooksStats);
        System.out.println("Lost Books: " + lostBooksStats);
        System.out.println("Total Users: " + totalUsersStats);
        scanner.nextLine();
        System.out.println("Press Enter to return to the main menu...");
        scanner.nextLine();

    }



}
