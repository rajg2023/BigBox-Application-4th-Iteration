package bigbox.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * The Class DBUtil.
 */
public class DBUtil {
	
	/** The connection. */
	private static Connection connection;

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public static synchronized Connection getConnection() {
		if (connection != null) {
			return connection;
		} else {
			try {
				// set the db url, username, and password
				String url = "jdbc:mysql://localhost:3306/bigboxdb";
				String username = "bigboxdb_user";
				String password = "sesame";

				// get and return connection
				connection = DriverManager.getConnection(url, username, password);
				// return connection;
			} catch (SQLException e) {
				System.out.println("Error with connection" + e);
			}
		}
		return connection;

	}

	/**
	 * Close connection.
	 */
	public static synchronized void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				System.err.println(e);
				;
			} finally {
				connection = null;
			}
		}
	}

	/**
	 * Fix DB string.
	 *
	 * @param s the s
	 * @return the string
	 */
	// handle strings that contain one or more single quotes (')
	public static String fixDBString(String s) {
		// if the string is null, return it
		if (s == null)
			return s;

		// add a single quote immediately before it
		StringBuilder sb = new StringBuilder(s);
		for (int i = 0; i < sb.length(); i++) {
			char ch = sb.charAt(i);
			if (ch == 39) // 39 is the ASCII code for a single quote
				sb.insert(i++, "'");
		}
		return sb.toString();
	}
}