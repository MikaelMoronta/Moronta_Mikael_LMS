package DBHelper;

import java.util.ArrayList;

/**
 * Books class --- it's an auto-generated class that communicates with the table Books from the database. It contains
 * methods that run queries that modify and communicate with the table from the database.
 * @author Mikael Moronta CEN 3024C 04/14/24
 */
public class Books extends DBHelper {
	private final String TABLE_NAME = "Books";

	/**
	 * Executes a query that deletes a record from the database based of the arguments.
	 * @param whatField a string that specifies the desired field on the database.
	 * @param whatValue a string that specifies which value to delete from the specified field of the database
	 */
	public void delete(String whatField, String whatValue) {
		super.execute("DELETE from " + TABLE_NAME + " where " + whatField + " = " + whatValue + ";");
	}

	/**
	 * Executes a specified query and returns the result.
	 * @param query a string that contains the specified query.
	 * @return an ArrayList that contains the entire result of the executed query.
	 */
	public ArrayList<ArrayList<Object>> getExecuteResult(String query) {
		return super.executeQuery(query);
	}

	/**
	 * Executes a query to the linked database.
	 * @param query a string that contains the specified query.
	 */
	public void execute(String query) {
		super.execute(query);
	}

}