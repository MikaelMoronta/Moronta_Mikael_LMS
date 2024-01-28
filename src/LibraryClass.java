/*
Mikael Moronta CEN 3024C 1/28/24
LibraryClass
The Library class is a very fundamental class that will handle most of the
features needed in order to have a functional library management system.
It in charge of managing and storing the collection of books that are either
inputted by the user or read from the text file. It also keeps the text file
updated once the user quits out of the program.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class LibraryClass {
    private List<BookClass> bookList;

    // LibraryClass method serves as a constructor
    public LibraryClass(){
        this.bookList = new ArrayList<>();
    }

    public void loadBooksFromTextFile(String textFileName){
        String textFileLine, title, author;
        String[] text;
        int id;
        /*
        The try and catch was implemented in order to catch any exceptions in the case
        the there is something wrong with the text file.

        The BufferedReader is wrapped around FileReader in order to be more efficient.
         */
        try (BufferedReader reader = new BufferedReader(new FileReader(textFileName))) {
            while ((textFileLine = reader.readLine()) != null) {
                /*
                Each line on the text file is separated with a comma and then fed into
                it's respective variables. .parseInt was needed in order to turn the number
                String into an integer.
                 */
                text = textFileLine.split(",");
                id = Integer.parseInt(text[0]);
                title = text[1];
                author = text[2];
                bookList.add(new BookClass(id, title, author));
            }
        } catch (IOException e) {
           System.out.println("There is an error with the file, please try again.");
        }
    }

    /*
    addBook takes the id, title and author as an argument and
    uses that that information to add a new book to the internal
    Book List.
     */
    public void addBook(int id, String title, String author){
        if (checkID(id)){
            System.out.println("Error: Book with ID " + id + " already exists. Each new book must have an Unique ID.");
        }
        else {
            bookList.add(new BookClass(id, title, author));
            System.out.println("Book added to the collection.");
        }
    }

    /*
    checkID takes an id as an argument and returns a true or false result.
    It then proceeds to compare the requested new book id to all the other book
    ids in the collection in order to see if that new book id is not being used
    by another book. Each ID must be unique to each book.
     */
    private boolean checkID(int id) {
        boolean result = false;

        for (BookClass book : bookList) {
            if (book.getId() == id) {
                result = true;
            }
        }
        return result;
    }

    /*
    removeBook only takes the id as an input and doesn't return
    anything. It then matches that inputted id to one on the internal
    Book list and deletes the book if it does.
     */
    public void removeBook (int id){
        bookList.removeIf(book -> book.getId() == id);
    }

    /*
    outputAllBooks is a simple method that doesn't return or take in
    anything. What it does is that outputs the internal Book List to
    the console.
     */
    public void outputAllBooks() {
        for (BookClass book : bookList) {
            System.out.println(book);
        }
    }

    /*
    saveBooksToTextFile method is in charge of taking the internal
    Book List and outputting it to the designated text file. It doesn't
    return anything, but it does take in the text file name.
     */
    public void saveBooksToTextFile(String textFileName) {
        try (FileWriter writer = new FileWriter(textFileName)) {
            for (BookClass book : bookList) {
                writer.write(book.toString() + "\n");
            }
        }catch (IOException e) {
            System.out.println("There is an error with the file, please try again.");
        }
    }
}
