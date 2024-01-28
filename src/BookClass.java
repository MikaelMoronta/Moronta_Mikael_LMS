/*
Mikael Moronta CEN 3024C 1/28/24
BookClass
The BookClass class is responsible for representing the concept of a book containing a unique
id, title and author. Thanks to this class it provides a blueprint for creating and working with
book objects which it's needed in order to create a library full of them.
 */
public class BookClass {
    private int id;
    private String title;
    private String author;

    // BookClass is the constructor for creating books (objects) using the BookClass class.
    public BookClass(int id, String title, String author){
        this.id = id;
        this.title = title;
        this.author = author;
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

    // toString method returns the book as a string organized for the text file using commas.
    public String toString(){
        return id + "," + title + "," + author;
    }
}
