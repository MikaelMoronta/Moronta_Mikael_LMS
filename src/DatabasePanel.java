/*
Mikael Moronta CEN 3024C 03/24/24
DatabasePanel class
The DatabasePanel class represents a GUI panel that displays the database of books
in the Library Management System program. Its primary function is to provide users
with a visual representation of the library's book collection.
*/

import javax.swing.*;

public class DatabasePanel extends JFrame {
    private JPanel DatabasePanel;
    private JTextPane textPane;

    public DatabasePanel() {

        textPane.setText(LibraryManagementSystem.bookLibrary.outputAllBooks());

        setContentPane(DatabasePanel);
        setTitle("Database");
        setSize(600, 300);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainPanel().setVisible(true));
    }
}
