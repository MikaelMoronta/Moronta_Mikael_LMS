/*
Mikael Moronta CEN 3024C 04/07/24
MainPanel class
The MainPanel class represents the main menu GUI of the Library Management System program.
Its function is to provide users with a centralized interface for performing various operations
related to managing the library's book collection. This includes functionalities such as
removing books by barcode or title, checking out and checking in books, displaying the database
of books, and exiting the program. Each operation is associated with a specific text field for
user input and a button to trigger the corresponding action. The class enhances user interaction
by providing intuitive controls and feedback messages, ensuring efficient management of the library
operations through automation and user-friendly interfaces. It also contains the queries that update
the linked database.
*/

import DBHelper.Books;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainPanel extends JFrame {

    private JPanel MainPanel;
    private JTextField tfRemoveBookByBarcode;
    private JButton btnRemoveBookByBarcode;
    private JTextField tfRemoveBookByTitle;
    private JButton btnRemoveBookByTitle;
    private JTextField tfCheckOutBook;
    private JButton btnCheckOutBook;
    private JTextField tfCheckInBook;
    private JButton btnCheckInBook;
    private JButton btnShowDatabase;
    private JButton btnExit;

    public MainPanel() {

        Books db1 = new Books();

        setContentPane(MainPanel);
        setTitle("Main Menu");
        setSize(600, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        btnShowDatabase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                DatabasePanel newPanel = new DatabasePanel();
                newPanel.setVisible(true);
            }
        });
        btnRemoveBookByBarcode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int idToRemove = Integer.parseInt(tfRemoveBookByBarcode.getText());
                boolean wrongID = removeBookByID(idToRemove);

                if (wrongID){
                    JOptionPane.showMessageDialog(MainPanel.this, "Barcode not found, please try again.");
                } else{
                    JOptionPane.showMessageDialog(MainPanel.this, "Book successfully removed");
                }
            }
        });
        btnRemoveBookByTitle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                 String titleToRemove = tfRemoveBookByTitle.getText();
                 boolean wrongTitle = removeBookByTitle(titleToRemove);

                if (wrongTitle){
                    JOptionPane.showMessageDialog(MainPanel.this, "Title not found, please try again.");
                } else{
                    JOptionPane.showMessageDialog(MainPanel.this, "Book successfully removed");
                }
            }
        });
        btnCheckOutBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String titleToCheckOut = tfCheckOutBook.getText();
                int wrongTitle = checkOutBook(titleToCheckOut);
                switch (wrongTitle) {
                    case 0:
                        JOptionPane.showMessageDialog(MainPanel.this, "Book successfully Checked out");
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(MainPanel.this, "Title not found, please try again.");
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(MainPanel.this, "Book is already checked out");
                        break;
                }
            }
        });
        btnCheckInBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String titleToCheckIn = tfCheckInBook.getText();
                int wrongTitle = checkInBook(titleToCheckIn);
                switch (wrongTitle) {
                    case 0:
                        JOptionPane.showMessageDialog(MainPanel.this, "Book successfully Checked in");
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(MainPanel.this, "Title not found, please try again.");
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(MainPanel.this, "Book is already checked in");
                        break;
                }
            }
        });
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });
    }

    /*
    BookExistQuery only takes a String as an argument. This string contains
    the query that checks if a book exists in the database. Based on the result
    the function returns either a 1 or 0;
    */
    public int bookExistQuery (String query){
        int bookExist = 0;

        LibraryManagementSystem.data = LibraryManagementSystem.db1.getExecuteResult(query);

        ArrayList<Object> innerList = LibraryManagementSystem.data.get(0);
        Object objectValue = innerList.get(0);
        String stringValue = objectValue.toString().replaceAll("\\[|\\]", ""); // Removing square brackets
        bookExist = Integer.parseInt(stringValue);

        return bookExist;
    }

    /*
    removeBookByID only takes the id as an input and returns a boolean result.
    The boolean result serves to communicate if the ID supplied was valid. This
    function deletes a record of the linked database if the ID matches one of the
    books.
    */
    public boolean removeBookByID (int id){

        boolean wrongID;

        String query = "SELECT COUNT(*) AS record_count " + "FROM Books " + "WHERE barcode = " + id;

        int bookExists = bookExistQuery(query);

        String idToRemove = String.valueOf(id);

        if (bookExists == 1){
            LibraryManagementSystem.db1.delete("barcode", idToRemove);
            wrongID = false;
        } else{
            wrongID = true;
        }

        return wrongID;
    }

    /*
    removeBookByTitle only takes a String as an input and returns a boolean result.
    The boolean result serves to communicate if the title supplied was valid. This
    function deletes a record of the linked database if the title matches one of the
    books.
    */
    public boolean removeBookByTitle(String title) {
        boolean wrongTitle;

        String query = "SELECT COUNT(*) AS record_count FROM Books WHERE LOWER(title) = LOWER('" + title + "')";

        int bookExists = bookExistQuery(query);

        if (bookExists == 1){
            LibraryManagementSystem.db1.delete("LOWER(title)", "LOWER('" + title.toLowerCase() + "')");
            wrongTitle = false;
        } else{
            wrongTitle = true;
        }

        return wrongTitle;
    }

    /*
    checkOutBook only takes the title as an input and returns an integer.
    The integer is then used to communicate if the title is not found (1), if
    the books is checked out already (2) or if the checking out was successfully (0).
    It then updates the database by changing the status and update of the book.
    */
    public int checkOutBook(String title) {
        int wrongTitle = 1;

        String query = "SELECT COUNT(*) AS record_count FROM Books WHERE LOWER(title) = LOWER('" + title + "')";

        int bookExists = bookExistQuery(query);

        if (bookExists == 1){

            query = "SELECT COUNT(*) AS checked_in_count FROM Books WHERE LOWER(title) = LOWER('" + title + "') " +
                    "AND status = 'checked in'";

            int isBookCheckedIn = bookExistQuery(query);

            if (isBookCheckedIn == 1){
                // Get today's date
                LocalDate currentDate = LocalDate.now();

                // Calculate the due date (4 weeks from today)
                LocalDate dueDate = currentDate.plusWeeks(4);

                // Format the due date as a string in 'yyyy-MM-dd' format
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String formattedDueDate = dueDate.format(formatter);

                query = "UPDATE Books SET status = 'checked out', [due_date] = '" + formattedDueDate + "' " +
                        "WHERE LOWER(title) = LOWER('" + title + "')";

                LibraryManagementSystem.db1.execute(query);

                wrongTitle = 0;
            } else{
                wrongTitle = 2;
            }
        }
        return wrongTitle;
    }

    /*
    checkInBook only takes the title as an input and returns an integer.
    The integer is then used to communicate if the title is not found (1), if
    the books is checked in already (2) or if the checking in was successfully (0).
    It then updates the database by changing the status and update of the book.
    */
    public int checkInBook(String title) {
        int wrongTitle = 1;

        String query = "SELECT COUNT(*) AS record_count FROM Books WHERE LOWER(title) = LOWER('" + title + "')";

        int bookExists = bookExistQuery(query);

        if (bookExists == 1){

            query = "SELECT COUNT(*) AS checked_out_count FROM Books WHERE LOWER(title) = LOWER('" + title + "') " +
                    "AND status = 'checked out'";

            int isBookCheckedOut = bookExistQuery(query);

            if (isBookCheckedOut == 1){
                query = "UPDATE Books SET status = 'checked in', [due_date] = NULL WHERE LOWER(title) = " +
                        "LOWER('" + title + "')";

                LibraryManagementSystem.db1.execute(query);

                wrongTitle = 0;
            } else{
                wrongTitle = 2;
            }
        }
        return wrongTitle;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainPanel().setVisible(true));
    }
}