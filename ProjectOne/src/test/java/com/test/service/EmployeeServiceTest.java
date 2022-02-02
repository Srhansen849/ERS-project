package com.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.dao.EmployeeDAOImpl;
import com.example.dao.TobyTechDB;
import com.example.model.Employee;
import com.example.service.EmployeeService;

public class EmployeeServiceTest {

	@Mock
	private EmployeeDAOImpl eDao;

	private EmployeeService eServ;
	private Employee employee;
	private List<Employee> eList;
	
	@BeforeEach
	public void setUp() throws Exception{
		
		MockitoAnnotations.initMocks(this);
		eServ = new EmployeeService(eDao);
		employee = new Employee(1, "Bobby", "boy", "username", "password", "email", 1, 1);
		eList = Arrays.asList(employee);
		
//		when(eDao.updateProfile(employee, employee.getFirstname(),
//		 employee.getLastname(), employee.getUsername(), employee.getPassword(),
//		 employee.getEmail())).thenReturn(null);
		 
		when(eDao.getEmployeeByUsername(employee.getUsername())).thenReturn(employee);
		when(eDao.getEmployeeProfile(employee)).thenReturn(employee);
		when(eDao.getAllEmployee(employee)).thenReturn(eList);
	
	}
	
	//This gets employee by username
	@Test
	public void findByUsernameSuccess() {
		Employee testEmployee = eServ.findEmployeeByUsername(employee.getUsername());
		assertEquals(employee.getEmployeeid(), testEmployee.getEmployeeid());
		assertEquals(employee.getFirstname(), testEmployee.getFirstname());
		assertEquals(employee.getLastname(), testEmployee.getLastname());
		assertEquals(employee.getUsername(), testEmployee.getUsername());
		assertEquals(employee.getPassword(), testEmployee.getPassword());
		assertEquals(employee.getEmail(), testEmployee.getEmail());
		assertEquals(employee.getRoleid(), testEmployee.getRoleid());
		assertEquals(employee.getManagerid(), testEmployee.getManagerid());
	}
	
	//This gets the profile
	@Test
	public void findProfileSuccess() {
		Employee testEmployee = eServ.findEmployeeProfile(employee);
		assertEquals(employee.getEmployeeid(), testEmployee.getEmployeeid());
		assertEquals(employee.getFirstname(), testEmployee.getFirstname());
		assertEquals(employee.getLastname(), testEmployee.getLastname());
		assertEquals(employee.getUsername(), testEmployee.getUsername());
		assertEquals(employee.getPassword(), testEmployee.getPassword());
		assertEquals(employee.getEmail(), testEmployee.getEmail());
		assertEquals(employee.getRoleid(), testEmployee.getRoleid());
		assertEquals(employee.getManagerid(), testEmployee.getManagerid());
	}
	
	//This will update a profile
	@Test
	public void updateProfileSuccess() throws SQLException {
		eServ.updateProfile(employee, employee.getFirstname(), employee.getLastname(), employee.getUsername(),
				employee.getPassword(), employee.getEmail());
		verify(eDao, times(1)).updateProfile(employee, employee.getFirstname(), employee.getLastname(), employee.getUsername(),
				employee.getPassword(), employee.getEmail());
	}
	
	//This will get a list of employees
	@Test
	public void findEmployeeList() {
		List<Employee> testEmpList = eServ.findAllEmployee(employee);
		assertEquals(eList.size(), testEmpList.size());
	}
	
	//This will verify the login
	@Test
	public void LoginSuccess() {
		Employee testEmployee = eServ.verifyEmployeeLogin(employee.getUsername(), employee.getPassword());
		assertEquals(employee.getEmployeeid(), testEmployee.getEmployeeid());
		assertEquals(employee.getFirstname(), testEmployee.getFirstname());
		assertEquals(employee.getLastname(), testEmployee.getLastname());
		assertEquals(employee.getUsername(), testEmployee.getUsername());
		assertEquals(employee.getPassword(), testEmployee.getPassword());
		assertEquals(employee.getEmail(), testEmployee.getEmail());
		assertEquals(employee.getRoleid(), testEmployee.getRoleid());
		assertEquals(employee.getManagerid(), testEmployee.getManagerid());
	}

}
