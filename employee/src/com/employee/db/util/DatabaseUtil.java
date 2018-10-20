/**
 * 
 */
package com.employee.db.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.util.Base64Utils;

import com.employee.constants.DBConstants;

/**
 * Singleton class to get the database connection.
 * 
 * @author manoj.g
 *
 */
public final class DatabaseUtil {

	private static Connection connection = null;

	static {
		init();
	}

	/**
	 * Utility class should have private constructor so that other classes
	 * wouldn't be able to extend it.
	 */
	private DatabaseUtil() {
	}

	private static void createDatabaseConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}

		try {
			connection = DriverManager.getConnection(DBConstants.JDBC_URL, DBConstants.USERNAME,
					new String(Base64Utils.decodeFromString(DBConstants.PASSWORD)));
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	}

	/**
	 * Initializes the database connection.
	 */
	private static void init() {
		synchronized (DatabaseUtil.class) {
			createDatabaseConnection();
		}
	}

	public static Connection getDBConnection() {
		try {
			if (connection == null || connection.isClosed()) {
				createDatabaseConnection();
			}
		} catch (SQLException exception) {
			createDatabaseConnection();
		}
		return connection;
	}
}
