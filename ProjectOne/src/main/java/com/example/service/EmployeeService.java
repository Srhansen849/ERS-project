package com.example.service;

import java.util.List;

import com.example.dao.EmployeeDAOImpl;
import com.example.model.Employee;

public class EmployeeService {
	
	public EmployeeDAOImpl eDao;
	
	public EmployeeService() {
		// TODO Auto-generated constructor stub
	}

	public EmployeeService(EmployeeDAOImpl eDao) {
		super();
		this.eDao = eDao;
	}
	
	//update an employee profile
	public void updateProfile(Employee employee, String firstname, String lastname, String username,
			String password, String email) {
		eDao.updateProfile(employee, firstname, lastname, 
				username, password, email);
	}
	
	//get employee by username
	public Employee findEmployeeByUsername(String username) {
		Employee emp = eDao.getEmployeeByUsername(username);
		if(emp==null) {
			throw new NullPointerException();
		}
		return emp;
		
	}

	//get current employee profile
	public Employee findEmployeeProfile(Employee employee) {
		Employee emp = eDao.getEmployeeProfile(employee);
		return emp;
	}
	
	//verify login
	public Employee verifyEmployeeLogin(String username, String password) {
		
		Employee emp = findEmployeeByUsername(username);
		if(emp.getPassword().equals(password)) {
			return emp;
		}
		return null;
	}
	
	//Manager gets a list of employee request
	public List<Employee> findAllEmployee(Employee employee){
		return eDao.getAllEmployee(employee);
	}
	
	
	

}
