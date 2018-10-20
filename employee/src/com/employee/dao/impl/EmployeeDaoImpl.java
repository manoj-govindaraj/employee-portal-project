/**
 * 
 */
package com.employee.dao.impl;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import au.com.bytecode.opencsv.CSVReader;

import com.employee.bean.Employee;
import com.employee.constants.Constants;
import com.employee.dao.EmployeeDao;
import com.employee.db.util.DatabaseUtil;
import com.employee.db.util.EmployeeQueryBuilder;

/**
 * Implementation class of employee dao access layer.
 * 
 * @author manoj.g
 *
 */
@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDaoImpl.class);

	@Override
	public void addEmployeeDetails(CSVReader reader) throws SQLException {
		LOGGER.debug("Entering the method to add employee details.");
		final String query = EmployeeQueryBuilder.frameInsertQuery();
		try (PreparedStatement preparedStatement = DatabaseUtil.getDBConnection().prepareStatement(query);) {
			String[] rowData = null;
			int batchCount = 0;
			while ((rowData = reader.readNext()) != null) {
				int index = 1;
				for (String data : rowData) {
					preparedStatement.setString(index++, data);
				}
				preparedStatement.addBatch();
				batchCount++;
				if (batchCount == Constants.BATCH_SIZE) {
					preparedStatement.executeBatch();
					LOGGER.info("Executing the batch with count: {}", batchCount);
					batchCount = 0;
				}
			}
			// To add the last batch of records
			if (batchCount != 0) {
				preparedStatement.executeBatch();
				LOGGER.info("Executing the batch with count: {}", batchCount);
			}
		} catch (IOException e) {
			LOGGER.error("Error while processing the employee details for addition", e);
		}
	}

	@Override
	public int getTotalCount() throws SQLException {
		LOGGER.debug("Retrieving total employee count from database.");
		final String query = EmployeeQueryBuilder.frameTotalCountQuery();
		try (PreparedStatement preparedStatement = DatabaseUtil.getDBConnection().prepareStatement(query);) {
			final ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				return resultSet.getInt(1);
			}
		}
		return 0;
	}

	@Override
	public List<Employee> retrieveEmployeeList(int startindex, int limit) throws SQLException {
		LOGGER.debug("Retrieving employee list with start index: {}, and limit: {}", startindex, limit);
		final List<Employee> employeeList = new ArrayList<>();
		final String query = EmployeeQueryBuilder.frameRetrieveWithPaginationQuery(startindex, limit);
		try (PreparedStatement preparedStatement = DatabaseUtil.getDBConnection().prepareStatement(query);) {
			final ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				final Employee employee = getEmployee(resultSet);
				employeeList.add(employee);
			}
			LOGGER.debug("Returing employee list of size: {} for start index: {}, and limit: {}", new Object[] {
					startindex, employeeList.size(), limit, });
			return employeeList;
		}
	}

	private Employee getEmployee(ResultSet resultSet) throws SQLException {
		final Employee employee = new Employee();
		employee.setId(resultSet.getInt(Constants.EMPLOYEE_COLUMN_ID));
		employee.setFirstName(resultSet.getString(Constants.EMPLOYEE_COLUMN_FIRSTNAME));
		employee.setLastName(resultSet.getString(Constants.EMPLOYEE_COLUMN_LASTNAME));
		employee.setDob(Date.valueOf(resultSet.getString(Constants.EMPLOYEE_COLUMN_DOB)));
		employee.setDoj(Date.valueOf(resultSet.getString(Constants.EMPLOYEE_COLUMN_DOJ)));
		employee.setDesignation(resultSet.getString(Constants.EMPLOYEE_COLUMN_DESIGNATION));
		employee.setContact(resultSet.getString(Constants.EMPLOYEE_COLUMN_CONTACT));
		return employee;
	}

}
