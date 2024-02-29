/*
Mikael Moronta CEN 3024C 02/29/24
LibraryManagementSystem class
The LibraryManagementSystem class which contains the main method
provides a simple console menu that the user can interact with.
The main method interacts with other methods of other classes by creating objects
for the purpose of completing the specific task that the user inputs.
Thanks to this the user will be able to interact with the library management
system, which will let him add, remove by barcode, remove by title, check in, check out, and list all books in the collection.
 */

import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        int menuChoice, bookID;
        String title, author;

        LibraryClass bookLibrary = new LibraryClass();
        Scanner scan = new Scanner(System.in);

        System.out.print("Please input file name to load database: ");
        String filename = scan.nextLine();
        bookLibrary.loadBooksFromTextFile(filename);


        do {
            System.out.println("\nWelcome to the Library Management System");
            System.out.println("Please choose from the following options:");
            System.out.println("1. Add new book");
            System.out.println("2. Remove book by barcode number");
            System.out.println("3. Remove book by title");
            System.out.println("4. Check out book");
            System.out.println("5. Check in book");
            System.out.println("6. List all books");
            System.out.println("7. Save and exit");
            System.out.print("Enter your choice: ");

            menuChoice = scan.nextInt();
            scan.nextLine();

            switch (menuChoice){
                case 1:
                    System.out.print("Enter unique book ID: ");
                    bookID = scan.nextInt();
                    scan.nextLine();
                    System.out.print("Enter book title: ");
                    title = scan.nextLine();
                    System.out.print("Enter book author: ");
                    author = scan.nextLine();
                    bookLibrary.addBook(bookID, title, author);
                    break;
                case 2:
                    System.out.print("Enter book ID to remove: ");
                    int idToRemove = scan.nextInt();
                    bookLibrary.removeBookByID(idToRemove);
                    System.out.println("Book deleted.");
                    System.out.println("List of all books:");
                    bookLibrary.outputAllBooks();
                    break;
                case 3:
                    System.out.print("Enter book title to remove: ");
                    String titleToRemove = scan.nextLine();
                    bookLibrary.removeBookByTitle(titleToRemove);
                    System.out.println("Book deleted.");
                    System.out.println("List of all books:");
                    bookLibrary.outputAllBooks();
                    break;
                case 4:
                    System.out.print("Enter book title to check out: ");
                    String titleToCheckOut = scan.nextLine();
                    bookLibrary.checkOutBook(titleToCheckOut);
                    break;
                case 5:
                    System.out.print("Enter book title to check in: ");
                    String titleToCheckIn = scan.nextLine();
                    bookLibrary.checkInBook(titleToCheckIn);
                    break;
                case 6:
                    System.out.println("List of all books:");
                    bookLibrary.outputAllBooks();
                    break;
                case 7:
                    bookLibrary.saveBooksToTextFile(filename);
                    System.out.println("Library data saved. See you soon!");
                    break;
                default:
                    System.out.println("Invalid menu choice, please try again.");
            }
        } while (menuChoice != 7);
    }
}