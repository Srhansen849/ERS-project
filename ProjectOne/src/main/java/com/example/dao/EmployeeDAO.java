package com.example.dao;

import java.util.List;

import com.example.model.Employee;

public interface EmployeeDAO {
	
	void updateProfile(Employee employee, String firstname, String lastname, String username, String password, String email);//e_id int, f_name varchar(30), l_name varchar(30), u_name varchar(30), p_word varchar(30), e_mail varchar(50)
	
	Employee getEmployeeByUsername(String name);
	Employee getEmployeeByid(int id);
	Employee getEmployeeProfile(Employee employee);
	
	List<Employee> getAllEmployee(Employee employee);
	
	
}
