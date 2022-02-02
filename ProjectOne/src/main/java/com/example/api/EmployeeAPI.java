package com.example.api;


import java.util.List;

import com.example.model.Employee;
import com.example.service.EmployeeService;

import io.javalin.http.Handler;

public class EmployeeAPI {
	
	private EmployeeService eServ;
	
	public EmployeeAPI() {
		// TODO Auto-generated constructor stub
	}

	public EmployeeAPI(EmployeeService eServ) {
		super();
		this.eServ = eServ;
	}
	
	//Log into TobyTech
	public final Handler POSTEMPLOYEELOGIN = (ctx) ->{
		
		Employee emp = eServ.verifyEmployeeLogin(ctx.formParam("username"), ctx.formParam("password"));
		if(emp != null) {
			ctx.sessionAttribute("currentemp", emp); 
			
			if(emp.getRoleid()<3) {
				ctx.status(201);
				ctx.redirect("/html/manhome.html");
			}
			else {
				ctx.status(201);
				ctx.redirect("/html/emphome.html");
			}
			
			
		}
		else {
			ctx.status(203);
			ctx.redirect("/html/badlogin.html");
		}
	};
	
	//Manager gets employees request
	public final Handler GETEMPLOYEE = (ctx) ->{
		
		Employee emp = (Employee)ctx.sessionAttribute("currentemp");
		List<Employee> empList = eServ.findAllEmployee(emp);
		
		ctx.status(200);
		ctx.json(empList);
	};
	
	
	//Update user profile
	public final Handler PUTPROFILE = (ctx) ->{
		
		Employee emp = (Employee)ctx.sessionAttribute("currentemp");
		eServ.updateProfile(emp, ctx.formParam("firstname"), ctx.formParam("lastname"), 
				ctx.formParam("username"), ctx.formParam("password"), ctx.formParam("email"));
		
		emp = eServ.findEmployeeProfile(emp);
		ctx.status(200);
		ctx.json(emp);
		
	};
	
	//Get current session employee
	public final Handler GETCURRENTSESS = (ctx) ->{
		
		Employee emp = (Employee)ctx.sessionAttribute("currentemp");
		ctx.json(emp);
	};
	

}
