package com.JPAMaven.JEE;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "customer") //the name of the table in DB
public class Customer implements Serializable {

	@Id // define that is the primary key of the table
	@Column(name = "cust_ID", unique = true) // defines the column name of the table
	private int cust_ID;	
	
	@Column(name = "firstName", nullable = false) // defines the column name of the table
	private String firstName;
	
	@Column(name = "lastName", nullable = false) // defines the column name of the table
	private String lastName;
	
	
	public int getCust_ID() {
		return cust_ID;
	}

	public void setCust_ID(int cust_ID) {
		this.cust_ID = cust_ID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
