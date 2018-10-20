/**
 * 
 */
package com.employee.bean;

import java.sql.Date;

/**
 * Bean definition for employee details.
 * 
 * @author manoj.g
 *
 */
public class Employee {

	private int id;

	private String firstName;

	private String lastName;

	private Date dob;

	private Date doj;

	private String designation;

	private String contact;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the dob
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * @param dob
	 *            the dob to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * @return the doj
	 */
	public Date getDoj() {
		return doj;
	}

	/**
	 * @param doj
	 *            the doj to set
	 */
	public void setDoj(Date doj) {
		this.doj = doj;
	}

	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation
	 *            the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @param contact
	 *            the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Employee [id=");
		builder.append(id);

		builder.append(", firstName=");
		builder.append(firstName);

		builder.append(", lastName=");
		builder.append(lastName);

		builder.append(", dob=");
		builder.append(dob);

		builder.append(", doj=");
		builder.append(doj);

		builder.append(", designation=");
		builder.append(designation);

		builder.append(", contact=");
		builder.append(contact);

		builder.append("]");

		return builder.toString();
	}
}
