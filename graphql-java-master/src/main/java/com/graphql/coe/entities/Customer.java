package com.graphql.coe.entities;

public class Customer {

	private final String id;
	private final String dob;
	private final String emailId;
	private final String firstName;
	private final String lastName;
	private final String mobile;

	public Customer(String dob, String emailId, String firstName,
			String lastName, String mobile) {
		this(null, dob, emailId, firstName, lastName, mobile);
	}

	public Customer(String id, String dob, String emailId, String firstName,
			String lastName, String mobile) {
		this.id = id;
		this.dob = dob;
		this.emailId = emailId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobile = mobile;
	}

	public String getId() {
		return id;
	}

	public String getDob() {
		return dob;
	}

	public String getEmailId() {
		return emailId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMobile() {
		return mobile;
	}
    

}
