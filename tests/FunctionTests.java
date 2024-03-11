import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class FunctionTests {
    int id = 1;
    String title = "Title for Test";
    String author = "Carl Newport";
    public List<BookClass> bookList;

    @Test
    public void addBook() {
        this.bookList = new ArrayList<>();
        assertTrue(bookList.add(new BookClass(id, title, author)));
    }

    @Test
    public void removeBookByID() {
        this.bookList = new ArrayList<>();
        bookList.add(new BookClass(id, title, author));

        assertTrue(bookList.removeIf(book -> book.getId() == id));
    }

    @Test
    public void removeBookByTitle() {
        this.bookList = new ArrayList<>();
        bookList.add(new BookClass(id, title, author));

        assertTrue(bookList.removeIf(book -> book.getTitle().equalsIgnoreCase(title)));
    }

    @Test
    public void checkOutBook() {
        this.bookList = new ArrayList<>();
        bookList.add(new BookClass(id, title, author));

        for (BookClass book : bookList) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.checkOut();
                assertTrue(book.isCheckedOut());
                assertNotEquals(null, book.getDueDate());
            }
        }
    }

    @Test
    public void checkInBook() {
        this.bookList = new ArrayList<>();
        bookList.add(new BookClass(id, title, author));

        for (BookClass book : bookList) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.checkOut();
                book.checkIn();
                assertFalse(book.isCheckedOut());
                assertEquals(null, book.getDueDate());
            }
        }
    }
}