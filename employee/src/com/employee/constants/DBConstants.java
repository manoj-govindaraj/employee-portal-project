/**
 * 
 */
package com.employee.constants;

/**
 * Class to store constants related to database.
 * 
 * @author manoj.g
 *
 */
public final class DBConstants {

	/**
	 * The url of mysql instance along with the db name to which connection has
	 * to be made.
	 */
	public static final String JDBC_URL = "jdbc:mysql://mydbinstance.ceyudr3siqgj.us-east-2.rds.amazonaws.com:3306/employee";

	/**
	 * Database username.
	 */
	public static final String USERNAME = "manoj";

	/**
	 * Encoded database password.
	 */
	public static final String PASSWORD = "ZGJwYXNzd2QxMjM=";

	/**
	 * Utility class should have private constructor so that other classes
	 * wouldn't be able to extend it.
	 */
	private DBConstants() {
	}

}
