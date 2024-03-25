/*
Mikael Moronta CEN 3024C 03/24/24
LibraryManagementSystem class
The LibraryManagementSystem class which contains the initial GUI window for the LMS program. It allows users to input a
file name, load a database of books from a text file, and proceed to the main panel of the application.

This program aims to provide a digital solution for managing a library's book collection.
Users can load book data from a text file, remove existing ones by ID or title,
check out and check in books. The program offers a user-friendly graphical interface where users can perform
these operations efficiently. It enhances library management by automating tasks and ensuring accurate record-keeping.
*/

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryManagementSystem extends JFrame {
    private JLabel Title;
    private JTextField tfFileName;
    private JButton btnSubmit;
    private JPanel FileNamePanel;
    public static LibraryClass bookLibrary = new LibraryClass();
    public LibraryManagementSystem() {

        setContentPane(FileNamePanel);
        setTitle("Database Input");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String filename = tfFileName.getText();
                boolean fileNotFound = bookLibrary.loadBooksFromTextFile(filename);

                if (fileNotFound){
                    JOptionPane.showMessageDialog(LibraryManagementSystem.this, "File is not found, please try again");
                } else{
                    dispose();

                    MainPanel newPanel = new MainPanel();
                    newPanel.setVisible(true);
                }
            }
        });
    }

    public static void main(String[] args) {

        LibraryManagementSystem inputFrame = new LibraryManagementSystem();
    }
}