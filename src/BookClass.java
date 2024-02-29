import java.util.Calendar;
import java.util.Date;

/*
Mikael Moronta CEN 3024C 02/29/24
BookClass
The BookClass class is responsible for representing the concept of a book containing a unique
id, title, author and due date. Thanks to this class it provides a blueprint for creating and working with
book objects which it's needed in order to create a library full of them.
 */
public class BookClass {
    private int id;
    private String title;
    private String author;
    private boolean checkedOut;
    private Date dueDate;

    // BookClass is the constructor for creating books (objects) using the BookClass class.
    public BookClass(int id, String title, String author){
        this.id = id;
        this.title = title;
        this.author = author;
        this.dueDate = null;
    }

    // Getter methods for books.
    public int getId(){
        return id;
    }
    public String getTitle() {
            return title;
        }
    public String getAuthor(){
        return author;
    }
    public boolean isCheckedOut() {
        return checkedOut;
    }
    public Date getDueDate() {
        return dueDate;
    }

    /*
    checkOut is the method that handles the due dates of each book, if a book is wished
    to be checked out by the user then this function will change its due date to 4 weeks
    from the current date. It also checks if the book is already checked out.
    */
    public void  checkOut() {
        if (!checkedOut) {
            checkedOut = true;
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.WEEK_OF_YEAR, 4);
            dueDate = calendar.getTime();
            System.out.println("Book \"" + title + "\" checked out. Due date: " + dueDate);
        } else {
            System.out.println("Error: This book is already checked out.");
        }
    }

    /*
    checkIn is the method that handles the checking in of each book. If a user desires to check
    in a book it will first check if the book is checked out, and then it will nullify the due date
    and adjust the check-out status to false.
    */
    public void checkIn() {
        if (checkedOut){
            checkedOut = false;
            dueDate = null;
            System.out.println("Book \"" + title + "\" checked in. Due date: " + dueDate);
        } else {
            System.out.println("Error: This book is not checked out.");
        }
    }

    // toString method returns the book as a string organized for the text file using commas.
    public String toString(){
        return id + "," + title + "," + author;
    }
}
