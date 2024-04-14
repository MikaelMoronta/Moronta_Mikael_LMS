import DBHelper.Books;
import javax.swing.*;
import java.util.ArrayList;

/**
 LibraryManagementSystem class --- The LibraryManagementSystem class which contains the initial GUI window for the LMS
 program. It directs the user right to the main panel of the program.
 @author Mikael Moronta CEN 3024C 04/14/24
 */
public class LibraryManagementSystem extends JFrame {
    public static ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
    public static Books db1 = new Books();
    public LibraryManagementSystem() {

        MainPanel mainMenu = new MainPanel();
        mainMenu.setVisible(true);
    }

    public static void main(String[] args) {
        LibraryManagementSystem inputFrame = new LibraryManagementSystem();
    }
}