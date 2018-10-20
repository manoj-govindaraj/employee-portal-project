package com.employee.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to store all the constants used across application.
 * 
 * @author manoj.g
 *
 */
public final class Constants {

	public static char DELIMITER_COMMA = ',';

	public static char DELIMITER_QUESTION_MARK = '?';

	public static int BATCH_SIZE = 20;

	public static String KEY_TOTAL_RECORDS = "totalRecords";

	public static String KEY_EMPLOYEE_LIST = "employeeList";

	public static String EMPLOYEE_COLUMN_ID = "id";

	public static String EMPLOYEE_COLUMN_FIRSTNAME = "first_name";

	public static String EMPLOYEE_COLUMN_LASTNAME = "last_name";

	public static String EMPLOYEE_COLUMN_DOB = "date_of_birth";

	public static String EMPLOYEE_COLUMN_DOJ = "date_of_joining";

	public static String EMPLOYEE_COLUMN_DESIGNATION = "designation";

	public static String EMPLOYEE_COLUMN_CONTACT = "contact";

	public static final String DATABASE_CONNECTION_ISSUES = "Issue with connecting database. Please try again after sometime or contact admin if problem persists.";

	private static List<String> EMPLOYEE_COLUMNS = new ArrayList<>();

	static {
		EMPLOYEE_COLUMNS.add(EMPLOYEE_COLUMN_ID);
		EMPLOYEE_COLUMNS.add(EMPLOYEE_COLUMN_FIRSTNAME);
		EMPLOYEE_COLUMNS.add(EMPLOYEE_COLUMN_LASTNAME);
		EMPLOYEE_COLUMNS.add(EMPLOYEE_COLUMN_DOB);
		EMPLOYEE_COLUMNS.add(EMPLOYEE_COLUMN_DOJ);
		EMPLOYEE_COLUMNS.add(EMPLOYEE_COLUMN_DESIGNATION);
		EMPLOYEE_COLUMNS.add(EMPLOYEE_COLUMN_CONTACT);
	}

	/**
	 * Utility class should have private constructor so that other classes
	 * wouldn't be able to extend it.
	 */
	private Constants() {
	}

	public static List<String> getEmployeeColumnHeaders() {
		return EMPLOYEE_COLUMNS;
	}
}
