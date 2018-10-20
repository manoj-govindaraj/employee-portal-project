/**
 * 
 */
package com.employee.db.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.employee.constants.Constants;

/**
 * Utility class to build employee table related queries.
 * 
 * @author manoj.g
 *
 */
public final class EmployeeQueryBuilder {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeQueryBuilder.class);

	private static String TABLE_NAME = "employee";

	/**
	 * Utility class should have private constructor so that other classes
	 * wouldn't be able to extend it.
	 */
	private EmployeeQueryBuilder() {
	}

	public static String frameInsertQuery() {
		final StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE_NAME);
		builder.append(" (");
		// Append columns
		for (String columnHeader : Constants.getEmployeeColumnHeaders()) {
			// Ignore id field since it is an auto increment field
			if (!Constants.EMPLOYEE_COLUMN_ID.equals(columnHeader)) {
				builder.append(columnHeader);
				builder.append(Constants.DELIMITER_COMMA);
			}
		}
		// To remove the last comma
		builder.deleteCharAt(builder.lastIndexOf(Character.toString(Constants.DELIMITER_COMMA)));
		builder.append(")");
		// Append values
		builder.append(" VALUES (");
		for (int index = 1; index < Constants.getEmployeeColumnHeaders().size(); index++) {
			builder.append(Constants.DELIMITER_QUESTION_MARK + Character.toString(Constants.DELIMITER_COMMA));
		}
		builder.deleteCharAt(builder.lastIndexOf(Character.toString(Constants.DELIMITER_COMMA)));
		builder.append(")");
		final String query = builder.toString();
		LOGGER.debug("Framed insert query is: {}", query);
		return query;
	}

	public static String frameTotalCountQuery() {
		final StringBuilder builder = new StringBuilder();
		builder.append("SELECT COUNT(*) FROM ");
		builder.append(TABLE_NAME);
		final String query = builder.toString();
		LOGGER.debug("Framed count query is: {}", query);
		return query;
	}

	public static String frameRetrieveWithPaginationQuery(int startindex, int limit) {
		final StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(TABLE_NAME);
		builder.append(" ORDER BY id ASC LIMIT ");
		builder.append(startindex);
		builder.append(Constants.DELIMITER_COMMA);
		builder.append(limit);
		final String query = builder.toString();
		LOGGER.debug("Framed pagination query is: {}", query);
		return query;
	}

}
