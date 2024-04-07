/*
Mikael Moronta CEN 3024C 04/07/24
DatabasePanel class
The DatabasePanel class represents a GUI panel that displays the database of books
in the Library Management System program. Its primary function is to provide users
with a visual representation of the library's book collection.
*/

import javax.swing.*;
import java.util.List;

public class DatabasePanel extends JFrame {
    private JPanel DatabasePanel;
    private JTextPane textPane;

    public DatabasePanel() {
        LibraryManagementSystem.data = LibraryManagementSystem.db1.getExecuteResult("select * from Books");

        StringBuilder output = new StringBuilder();
        for (List<Object> record : LibraryManagementSystem.data){
            output.append(record.toString()).append("\n");
        }

        textPane.setText(output.toString());

        setContentPane(DatabasePanel);
        setTitle("Database");
        setSize(800, 500);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainPanel().setVisible(true));
    }
}
