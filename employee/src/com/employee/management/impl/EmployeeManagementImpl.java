/**
 * 
 */
package com.employee.management.impl;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import au.com.bytecode.opencsv.CSVReader;

import com.employee.constants.Constants;
import com.employee.dao.EmployeeDao;
import com.employee.exception.AppException;
import com.employee.management.EmployeeManagement;

/**
 * Implementation for management methods that holds the business logics related
 * to employees.
 * 
 * @author manoj.g
 *
 */
@ComponentScan("com.employee.dao")
@Service
public class EmployeeManagementImpl implements EmployeeManagement {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeManagementImpl.class);

	@Autowired
	EmployeeDao employeeDao;

	@Override
	public void addEmployeeDatails(File csvFile) throws AppException {
		final String fileName = csvFile.getName();
		LOGGER.info("Processing csv file: {} to add entries to database", fileName);
		try (CSVReader reader = new CSVReader(new FileReader(csvFile), Constants.DELIMITER_COMMA);) {
			final String[] headers = reader.readNext();
			// Validate headers before adding
			validateHeaders(headers);
			employeeDao.addEmployeeDetails(reader);
		} catch (SQLException e) {
			LOGGER.error("Database error while adding details of csv file:{}", fileName, e);
			throw new AppException(Constants.DATABASE_CONNECTION_ISSUES);
		} catch (IOException e) {
			LOGGER.error("Error while adding details of csv file:{}", fileName, e);
		}
	}

	private void validateHeaders(String[] headers) throws AppException {
		boolean isValidHeaders = true;
		final List<String> validHeadersList = Constants.getEmployeeColumnHeaders();
		// Since id is an auto increment field, need not be a part of uploaded
		// sheet
		if (headers != null && (validHeadersList.size() - 1) == headers.length) {
			for (int index = 0; index < headers.length; index++) {
				// Validates the value and order of headers
				if (!validHeadersList.get(index + 1).equalsIgnoreCase(headers[index])) {
					isValidHeaders = false;
					// Even if one header is found invalid, break the loop and
					// throw the exception
					break;
				}
			}
		} else {
			isValidHeaders = false;
		}
		if (!isValidHeaders) {
			throw new AppException("Invalid headers. Please validate the headers with provided sample sheet.");
		}
	}

	@Override
	public Map<String, Object> retrieveEmployeeList(int startindex, int limit) throws AppException {
		LOGGER.info("Retrieving employee details with start index: {} and limit: {}", startindex, limit);
		try {
			final Map<String, Object> response = new HashMap<>();
			response.put(Constants.KEY_TOTAL_RECORDS, employeeDao.getTotalCount());
			response.put(Constants.KEY_EMPLOYEE_LIST, employeeDao.retrieveEmployeeList(startindex, limit));
			return response;
		} catch (SQLException e) {
			LOGGER.error("Database error while retrieving employee list with start index: {} and limit: {}",
					startindex, limit, e);
			throw new AppException(Constants.DATABASE_CONNECTION_ISSUES);
		}

	}
}
