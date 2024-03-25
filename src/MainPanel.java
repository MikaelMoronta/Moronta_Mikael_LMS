/*
Mikael Moronta CEN 3024C 03/24/24
MainPanel class
The MainPanel class represents the main menu GUI of the Library Management System program.
Its function is to provide users with a centralized interface for performing various operations
related to managing the library's book collection. This includes functionalities such as
removing books by barcode or title, checking out and checking in books, displaying the database
of books, and exiting the program. Each operation is associated with a specific text field for
user input and a button to trigger the corresponding action. The class enhances user interaction
by providing intuitive controls and feedback messages, ensuring efficient management of the library
operations through automation and user-friendly interfaces.
*/

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        setContentPane(MainPanel);
        setTitle("Main Menu");
        setSize(600, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        LibraryManagementSystem.bookLibrary.outputAllBooks();
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
                boolean wrongID = LibraryManagementSystem.bookLibrary.removeBookByID(idToRemove);

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
                 boolean wrongTitle = LibraryManagementSystem.bookLibrary.removeBookByTitle(titleToRemove);

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
                int wrongTitle = LibraryManagementSystem.bookLibrary.checkOutBook(titleToCheckOut);
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
                int wrongTitle = LibraryManagementSystem.bookLibrary.checkInBook(titleToCheckIn);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainPanel().setVisible(true));
    }
}