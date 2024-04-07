/*
Mikael Moronta CEN 3024C 04/07/24
LibraryManagementSystem class
The LibraryManagementSystem class which contains the initial GUI window for the LMS program. It directs the user
right to the main panel of the program.

This program aims to provide a digital solution for managing a library's book collection.
Users can interact with the GUI to remove books from the linked database existing by providing barcode or title,
check out and check in books. The program offers a user-friendly graphical interface where users can perform
these operations efficiently. It enhances library management by automating tasks and ensuring accurate record-keeping.
*/

import DBHelper.Books;
import javax.swing.*;
import java.util.ArrayList;

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