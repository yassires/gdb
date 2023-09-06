import classes.Author;
import classes.Books;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.text.SimpleDateFormat;


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

                    break;

                case 3:
                    displayBooks();
                    break;

                case 4:

                    break;

                case 5:

                    break;
                case 6:
                    deleteBook();
                    break;
                case 7:
                    updateBook();
                    break;
                case 8:

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
    public static void displayBooks()
    {
        System.out.println("-----------------------------------------------");
            //books

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

        int authorId = author.fetchAuthorId(authorName);

        if (authorId != 0) {
            System.out.println("Author ID: " + authorId);
        } else {
            System.out.println("Author not found.");
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
        // yyyy-mm-dd
        Date bookReleaseDate = Date.valueOf(dateString);

        System.out.println("------------------------------------------------");
        System.out.println("Enter Quantity:");
        System.out.println("------------------------------------------------");
        int BookQuantity = Integer.valueOf(scanner.nextInt());




        Books book = new Books(bookTitle,authorId,bookIsbn,bookCategory,bookReleaseDate,BookQuantity,BookQuantity,0,0);
        int status = access.bookAccess.addBook(book,authorId);
        if(status ==1 )
        {
            System.out.println("Book added successfully");
        }
        else
        {
            System.out.println("ERROR while adding book");
        }
        System.out.println("\n");
    }

    public static void updateBook()
    {
        System.out.println("------------------------------------------------");
        System.out.println("Enter Book Isbn:");
        System.out.println("------------------------------------------------");
        String bookIsbn = scanner.nextLine();
        System.out.println("------------------------------------------------");
        System.out.println("Enter New Book Name:");
        System.out.println("------------------------------------------------");
        String bookName = scanner.nextLine();
        System.out.println("------------------------------------------------");

        int status = 1;
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

    public  static void deleteBook() throws SQLException {
        System.out.println("------------------------------------------------");
        System.out.println("Enter Book Id:");
        System.out.println("------------------------------------------------");
        int bookId = scanner.nextInt();
        Books book = new Books(bookId);
        int status =access.bookAccess.deleteBook(bookId);
        if(status == 1 )
        {
            System.out.println("Book deleted successfully");
        }
        else
        {
            System.out.println("Something went wrong");
        }
        System.out.println("\n");
    }


}
