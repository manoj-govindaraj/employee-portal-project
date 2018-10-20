/**
 * 
 */
package com.employee.dao;

import java.sql.SQLException;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

import com.employee.bean.Employee;

/**
 * Interface for employee dao communication.
 * 
 * @author manoj.g
 *
 */
public interface EmployeeDao {

	/**
	 * Method to add employee details from a csv file.
	 * 
	 * @param reader
	 *            the csv file input.
	 *
	 * @throws SQLException
	 *             if something went wrong with database.
	 **/
	void addEmployeeDetails(CSVReader reader) throws SQLException;

	/**
	 * Method to get the total count of employees entry in the table.
	 * 
	 * @return the total count
	 * @throws SQLException
	 *             if something went wrong with database.
	 */
	int getTotalCount() throws SQLException;

	/**
	 * Method to get paginated response of employee details.
	 * 
	 * @param startindex
	 *            the offset
	 * @param limit
	 *            the limit per page
	 * @return the filtered employee details
	 * @throws SQLException
	 *             if something went wrong with database.
	 */
	List<Employee> retrieveEmployeeList(int startindex, int limit) throws SQLException;

}
