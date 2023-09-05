import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Scanner;

public class Main {

        Scanner scanner = new Scanner(System.in);



    public static void main(String[] args)
    {
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gdb","root","");
                System.out.println(con);
            }
            catch (Exception e){

            }
        Scanner scanner = new Scanner(System.in);

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
                    staticMethod();
                    break;

                case 2:

                    break;

                case 3:

                    break;

                case 4:

                    break;

                case 5:

                    break;
                case 6:

                    break;
                case 7:

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
    public  void viewBooks()
    {
        System.out.println("-----------------------------------------------");
            //books

        System.out.println("-----------------------------------------------");
        System.out.println("\n");

    }


    public static void staticMethod() {

        // instance of the class
        Main instance = new Main();

        // Call the non-static method on the instance
        instance.addBook();
    }
    public  void addBook()
    {

        System.out.println("------------------------------------------------");
        System.out.println("Enter Book ISBN:");
        System.out.println("------------------------------------------------");
        String bookIsbn = scanner.nextLine();
        System.out.println("------------------------------------------------");
        System.out.println("Enter Book Title:");
        System.out.println("------------------------------------------------");
        String bookTitle = scanner.nextLine();
        System.out.println("------------------------------------------------");
        System.out.println("Enter Category:");
        System.out.println("------------------------------------------------");
        String bookCategory = scanner.nextLine();
        System.out.println("------------------------------------------------");
        System.out.println("Enter Quantity:");
        System.out.println("------------------------------------------------");
        int BookQuantity = Integer.parseInt(scanner.nextLine());
        int status = 1;
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

    public void updateBook()
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
        if(status ==1 )
        {
            System.out.println("Book updated successfully");
        }
        else
        {
            System.out.println("ERROR while updating book");
        }
        System.out.println("\n");

    }

    public  void deleteBook()
    {
        System.out.println("------------------------------------------------");
        System.out.println("Enter Book Isbn:");
        System.out.println("------------------------------------------------");
        String bookIsbn = scanner.nextLine();
        int status = 1;
        if(status == 1 )
        {
            System.out.println("Book deleted successfully");
        }
        else
        {
            System.out.println("ERROR while deleting book");
        }
        System.out.println("\n");
    }






}
