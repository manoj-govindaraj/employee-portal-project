/**
 * 
 */
package com.employee.management;

import java.io.File;
import java.util.Map;

import com.employee.exception.AppException;

/**
 * Interface for employee management methods.
 * 
 * @author manoj.g
 *
 */
public interface EmployeeManagement {

	/**
	 * Method to process uploaded employee csv file before adding in db;
	 * 
	 * @param csvFile
	 *            the csv file
	 * @throws AppException
	 *             if any
	 */
	void addEmployeeDatails(File csvFile) throws AppException;

	/**
	 * Method to process pagination related employee inventory calls.
	 * 
	 * @param startindex
	 *            the offset
	 * @param limit
	 *            the page limit
	 * @return the response required for controller
	 * @throws AppException
	 *             if any
	 */
	Map<String, Object> retrieveEmployeeList(int startindex, int limit) throws AppException;

}
