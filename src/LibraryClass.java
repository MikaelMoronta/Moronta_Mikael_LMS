/*
Mikael Moronta CEN 3024C 03/24/24
LibraryClass
The Library class is a very fundamental class that will handle most of the
features needed in order to have a functional library management system.
It in charge of managing and storing the collection of books that are either
inputted by the user or read from the text file. It also keeps the text file
updated once the user quits out of the program.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class LibraryClass {
    private List<BookClass> bookList;

    // LibraryClass method serves as a constructor
    public LibraryClass(){
        this.bookList = new ArrayList<>();
    }

    public boolean loadBooksFromTextFile(String textFileName){
        String textFileLine, title, author;
        String[] text;
        int id;
        boolean fileNotFound = false;
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
           fileNotFound = true;
        }

        return fileNotFound;
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
    public boolean removeBookByID (int id){

        boolean wrongID;

        if (bookList.removeIf(book -> book.getId() == id)){
            wrongID = false;
        } else{
            wrongID = true;
        }

        return wrongID;
    }

    /*
    removeBookByTitle only takes the title as an input and doesn't return
    anything. It then matches that inputted title to one on the internal
    Book list and deletes the book if it does.
     */
    public boolean removeBookByTitle(String title) {
        boolean wrongTitle;

        if (bookList.removeIf(book -> book.getTitle().equalsIgnoreCase(title))){
            wrongTitle = false;
        } else{
            wrongTitle = true;
        }

        return wrongTitle;
    }

    /*
    checkOutBook only takes the title as an input and doesn't return
    anything. It then matches the inputted title to one on the internal
    Book list and checks out the book if it does.
    */
    public int checkOutBook(String title) {
        int wrongTitle = 1;

        for (BookClass book : bookList) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                wrongTitle = book.checkOut();
            }
        }
        return wrongTitle;
    }

    /*
    checkInBook only takes the title as an input and doesn't return
    anything. It then matches the inputted title to one on the internal
    Book list and checks in the book if it does.
    */
    public int checkInBook(String title) {
        int wrongTitle = 1;

        for (BookClass book : bookList) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                wrongTitle = book.checkIn();
            }
        }
        return wrongTitle;

    }

    /*
    outputAllBooks is a simple method that doesn't return or take in
    anything. What it does is that outputs the internal Book List to
    the console.
     */
    public String outputAllBooks() {
        StringBuilder output = new StringBuilder();
        for (BookClass book : bookList) {
            output.append(book.toString()).append("\n");
        }
        return output.toString();
    }

}
