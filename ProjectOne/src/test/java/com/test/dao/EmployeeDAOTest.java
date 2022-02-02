package com.test.dao;

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

public class EmployeeDAOTest {
	
	@Mock
	private TobyTechDB tdb;
	
	@Mock
	private Connection con;
	
	@Mock
	private PreparedStatement ps;
	
	@Mock
	private CallableStatement cs;
	
	@Mock
	private ResultSet rs;
	
	private EmployeeDAOImpl eDao;
	private Employee employee;
	private List<Employee> eList;
	
	
	@BeforeEach
	public void setUp() throws Exception{
		
		MockitoAnnotations.initMocks(this);
		eDao = new EmployeeDAOImpl(tdb);
		when(tdb.getDBConnection()).thenReturn(con);
		when(con.prepareStatement(isA(String.class))).thenReturn(ps);
		when(con.prepareCall(isA(String.class))).thenReturn(cs);
		when(ps.executeQuery()).thenReturn(rs);
		when(ps.execute()).thenReturn(true);
		when(cs.execute()).thenReturn(true);
		when(cs.getString(isA(Integer.class))).thenReturn("Test Succesful");
		employee = new Employee(1, "Bobby", "Boy", "username", "password", "email", 1, 1);
		eList = Arrays.asList(employee);
		when(rs.next()).thenReturn(true).thenReturn(false);
		when(rs.getInt(1)).thenReturn(employee.getEmployeeid());
		when(rs.getString(2)).thenReturn(employee.getFirstname());
		when(rs.getString(3)).thenReturn(employee.getLastname());
		when(rs.getString(4)).thenReturn(employee.getUsername());
		when(rs.getString(5)).thenReturn(employee.getPassword());
		when(rs.getString(6)).thenReturn(employee.getEmail());
		when(rs.getInt(7)).thenReturn(employee.getRoleid());
		when(rs.getInt(8)).thenReturn(employee.getManagerid());
	
	}
	
	//List All employess
	@Test
	public void findEmployeeList() {
		List<Employee> testEmpList = eDao.getAllEmployee(employee);
		assertEquals(eList.size(), testEmpList.size());
	}
	
	//find user by username
	@Test
	public void findByUsernameSuccess() {
		Employee testEmployee = eDao.getEmployeeByUsername(employee.getUsername());
		assertEquals(employee.getEmployeeid(), testEmployee.getEmployeeid());
		assertEquals(employee.getFirstname(), testEmployee.getFirstname());
		assertEquals(employee.getLastname(), testEmployee.getLastname());
		assertEquals(employee.getUsername(), testEmployee.getUsername());
		assertEquals(employee.getPassword(), testEmployee.getPassword());
		assertEquals(employee.getEmail(), testEmployee.getEmail());
		assertEquals(employee.getRoleid(), testEmployee.getRoleid());
		assertEquals(employee.getManagerid(), testEmployee.getManagerid());
	}
	
	//find user by id
	@Test
	public void findByidSuccess() {
		Employee testEmployee = eDao.getEmployeeByid(employee.getEmployeeid());
		assertEquals(employee.getEmployeeid(), testEmployee.getEmployeeid());
		assertEquals(employee.getFirstname(), testEmployee.getFirstname());
		assertEquals(employee.getLastname(), testEmployee.getLastname());
		assertEquals(employee.getUsername(), testEmployee.getUsername());
		assertEquals(employee.getPassword(), testEmployee.getPassword());
		assertEquals(employee.getEmail(), testEmployee.getEmail());
		assertEquals(employee.getRoleid(), testEmployee.getRoleid());
		assertEquals(employee.getManagerid(), testEmployee.getManagerid());
	}
	
	//find profile
	@Test
	public void findProfileSuccess() {
		Employee testEmployee = eDao.getEmployeeProfile(employee);
		assertEquals(employee.getEmployeeid(), testEmployee.getEmployeeid());
		assertEquals(employee.getFirstname(), testEmployee.getFirstname());
		assertEquals(employee.getLastname(), testEmployee.getLastname());
		assertEquals(employee.getUsername(), testEmployee.getUsername());
		assertEquals(employee.getPassword(), testEmployee.getPassword());
		assertEquals(employee.getEmail(), testEmployee.getEmail());
		assertEquals(employee.getRoleid(), testEmployee.getRoleid());
		assertEquals(employee.getManagerid(), testEmployee.getManagerid());
	}
	
	//update profile
	@Test
	public void updateProfileSuccess() throws SQLException {
		eDao.updateProfile(employee, employee.getFirstname(), employee.getLastname(), employee.getUsername(),
				employee.getPassword(), employee.getEmail());
		verify(cs, times(1)).execute();
	}

}
