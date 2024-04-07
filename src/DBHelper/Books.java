/*
Mikael Moronta CEN 3024C 04/07/24
Books class
This class contains the necessary functions that interact with the linked database.
*/
package DBHelper;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Books extends DBHelper {
	private final String TABLE_NAME = "Books";
	public static final String barcode = "barcode";
	public static final String title = "title";
	public static final String author = "author";
	public static final String genre = "genre";
	public static final String status = "status";
	public static final String due_date = "due_date";

	private String prepareSQL(String fields, String whatField, String whatValue, String sortField, String sort) {
		String query = "SELECT ";
		query += fields == null ? " * FROM " + TABLE_NAME : fields + " FROM " + TABLE_NAME;
		query += whatField != null && whatValue != null ? " WHERE " + whatField + " = \"" + whatValue + "\"" : "";
		query += sort != null && sortField != null ? " order by " + sortField + " " + sort : "";
		return query;
	}

	public void insert(Integer barcode, String title, String author, String genre, String status, String due_date) {
		title = title != null ? "\"" + title + "\"" : null;
		author = author != null ? "\"" + author + "\"" : null;
		genre = genre != null ? "\"" + genre + "\"" : null;
		status = status != null ? "\"" + status + "\"" : null;
		due_date = due_date != null ? "\"" + due_date + "\"" : null;
		
		Object[] values_ar = {barcode, title, author, genre, status, due_date};
		String[] fields_ar = {Books.barcode, Books.title, Books.author, Books.genre, Books.status, Books.due_date};
		String values = "", fields = "";
		for (int i = 0; i < values_ar.length; i++) {
			if (values_ar[i] != null) {
				values += values_ar[i] + ", ";
				fields += fields_ar[i] + ", ";
			}
		}
		if (!values.isEmpty()) {
			values = values.substring(0, values.length() - 2);
			fields = fields.substring(0, fields.length() - 2);
			super.execute("INSERT INTO " + TABLE_NAME + "(" + fields + ") values(" + values + ");");
		}
	}

	public void delete(String whatField, String whatValue) {
		super.execute("DELETE from " + TABLE_NAME + " where " + whatField + " = " + whatValue + ";");
	}

	public void update(String whatField, String whatValue, String whereField, String whereValue) {
		super.execute("UPDATE " + TABLE_NAME + " set " + whatField + " = \"" + whatValue + "\" where " +
				whereField + " = \"" + whereValue + "\";");
	}

	public ArrayList<ArrayList<Object>> select(String fields, String whatField, String whatValue, String sortField, String sort) {
		return super.executeQuery(prepareSQL(fields, whatField, whatValue, sortField, sort));
	}

	public ArrayList<ArrayList<Object>> getExecuteResult(String query) {
		return super.executeQuery(query);
	}

	public void execute(String query) {
		super.execute(query);
	}

	public DefaultTableModel selectToTable(String fields, String whatField, String whatValue, String sortField, String sort) {
		return super.executeQueryToTable(prepareSQL(fields, whatField, whatValue, sortField, sort));
	}

}