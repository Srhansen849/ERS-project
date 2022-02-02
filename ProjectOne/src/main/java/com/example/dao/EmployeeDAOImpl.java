package com.example.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Employee;
import com.example.model.Request;


public class EmployeeDAOImpl implements EmployeeDAO{
	
	public TobyTechDB tobyCon = new TobyTechDB();
	public RequestDAOImpl rDao = new RequestDAOImpl(tobyCon);

	public EmployeeDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
	
	public EmployeeDAOImpl(TobyTechDB tobyCon) {
		super();
		this.tobyCon = tobyCon;
	}

	//update your profile
	@Override
	public void updateProfile(Employee employee, String firstname, String lastname, String username,
			String password, String email) {
		
		try(Connection con = tobyCon.getDBConnection()){
			
			String sql = "{? = call update_profile(?,?,?,?,?,?)}";
			
			CallableStatement callable = con.prepareCall(sql);
			callable.registerOutParameter(1, Types.VARCHAR);
			
			callable.setInt(2, employee.getEmployeeid());
			callable.setString(3, firstname);
			callable.setString(4, lastname);
			callable.setString(5, username);
			callable.setString(6, password);
			callable.setString(7, email);
			callable.execute();
			
			System.out.println(callable.getString(1));
			
			//getClient(clientID);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	//manager lists all employee
	@Override
	public List<Employee> getAllEmployee(Employee employee) { 
		
		List<Employee> empList = new ArrayList<>();
		try(Connection con = tobyCon.getDBConnection()){
			
			
			if(employee.getRoleid()==1) {
				
				String sql = "select * from tobyemp";
				
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery(); //this will return the set of data from the database
				
				while(rs.next()) {
					empList.add(new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
							rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8)));
				}
				System.out.println("All: "+empList);
				return empList;
			}
			else if(employee.getRoleid()>1) {
				
				String sql = "select * from tobyemp where managerid = ?";
				
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, employee.getEmployeeid());
				ResultSet rs = ps.executeQuery(); //this will return the set of data from the database
				
				while(rs.next()) {
					empList.add(new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
							rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8)));
				}
				System.out.println("with manager: "+empList);
				return empList;
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println(empList);
		return null;
		
	}

	//gets an employee by their username
	@Override
	public Employee getEmployeeByUsername(String name) {

		try(Connection con = tobyCon.getDBConnection()){
			
			Employee employee = new Employee();
			String sql = "select * from tobyemp where username = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			
			
			
			while(rs.next()) {
				employee = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
			}
			System.out.println("By username: "+employee);
			return employee;
			
			
			//System.out.println(cs.getString(1));
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	//gets all employees ordered
	

	//gets current users profile
	@Override
	public Employee getEmployeeProfile(Employee employee) {

		try(Connection con = tobyCon.getDBConnection()){
			
			String sql = "select * from tobyemp where employeeid = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, employee.getEmployeeid());
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next()) {
				employee = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
			}
			System.out.println("get profile: "+employee);
			return employee;
			
			
			//System.out.println(cs.getString(1));
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public Employee getEmployeeByid(int id) {

		try(Connection con = tobyCon.getDBConnection()){
			
			Employee employee = new Employee();
			String sql = "select * from tobyemp where employeeid = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			
			
			while(rs.next()) {
				employee = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
			}
			System.out.println("By id: "+employee);
			return employee;
			
			
			//System.out.println(cs.getString(1));
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	
	
	

}
