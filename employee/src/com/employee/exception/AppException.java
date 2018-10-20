/**
 * 
 */
package com.employee.exception;

/**
 * Application specific exceptions.
 * 
 * @author manoj.g
 *
 */
public class AppException extends Exception {

	private static final long serialVersionUID = -8400409442176474249L;

	public AppException(String errorMessage) {
		super(errorMessage);
	}

}
