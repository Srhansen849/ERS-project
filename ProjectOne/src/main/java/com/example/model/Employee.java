package com.example.model;

import java.util.List;

public class Employee {

	private int employeeid;
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private String email;
	private int roleid;
	private int managerid;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(int employeeid, String firstname, String lastname, String username, String password, String email,
			 int roleid, int managerid) {
		super();
		this.employeeid = employeeid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.roleid = roleid;
		this.managerid = managerid;
	}

	public Employee(String firstname, String lastname, String username, String password, String email,
			 int roleid, int managerid) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.roleid = roleid;
		this.managerid = managerid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public int getEmployeeid() {
		return employeeid;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public int getManagerid() {
		return managerid;
	}

	public void setManagerid(int managerid) {
		this.managerid = managerid;
	}

	@Override
	public String toString() {
		return "Employee [employeeid=" + employeeid + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", username=" + username + ", password=" + password + ", email=" + email + ", roleid=" + roleid
				+ ", managerid=" + managerid + "] \n";
	}

	

	
}
