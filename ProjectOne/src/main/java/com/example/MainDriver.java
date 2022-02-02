package com.example;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.example.api.EmployeeAPI;
import com.example.api.RequestAPI;
import com.example.dao.EmployeeDAOImpl;
import com.example.dao.RequestDAOImpl;
import com.example.dao.TobyTechDB;
import com.example.model.Employee;
import com.example.service.EmployeeService;
import com.example.service.RequestService;

import io.javalin.Javalin;

public class MainDriver {

	public static void main(String[] args) {
		
		EmployeeAPI eAPI = new EmployeeAPI(new EmployeeService(new EmployeeDAOImpl(new TobyTechDB())));
		EmployeeDAOImpl eDao = new EmployeeDAOImpl(new TobyTechDB());
		RequestAPI rAPI = new RequestAPI(new RequestService(new RequestDAOImpl(new TobyTechDB())));
		RequestDAOImpl rDao = new RequestDAOImpl(new TobyTechDB());
		RequestService rServ = new RequestService(new RequestDAOImpl(new TobyTechDB()));
		
//		Employee employee = new Employee(3, "Stuart", "Noel", "noelstua1", "Password123", "noel.stuart1@tobytech.com", 2, 1);
//		
////		rServ.findAllRejected(employee);
////		rServ.findAllRequest(employee);
//		rServ.findAllCurrentPend(employee);
//		rServ.findOrderedRequest(employee);
		//rDao.getTotalTypeFunds();
		//rDao.getRequestOrdered(1);
		
		//eDao.updateProfile(null, "Christopher", "Cline", "clinechri1", "Password123", "cline.christopher1@tobytech.com");
		//eDao.getAllEmployee(eDao.getEmployeeByUsername("clinechri1"));
		
		Javalin app = Javalin.create(config ->{
			config.addStaticFiles("/frontend");
			config.enableCorsForAllOrigins();
		});
		
		app.start(9065);
		
		//login
		app.post("/employee/login", eAPI.POSTEMPLOYEELOGIN);
		
		//get current sess
		app.get("/employee/sessemp", eAPI.GETCURRENTSESS);
		
		//manager gets employee 
		app.get("/manager/employee", eAPI.GETEMPLOYEE);
		
		//employee updates profile
		app.put("/employee/profile", eAPI.PUTPROFILE);
		
		//post a new request
		app.post("/request/new", rAPI.POSTNEWREQUEST);
		
		//get current request
		app.get("/request/current", rAPI.GETCURRENTREQUEST);
		
		//update a request
		app.post("/request/update", rAPI.UPDATEAREQUEST);
		
		//manager updates status
		app.post("/request/status", rAPI.UPDATESTATUS);
		
		//drop down of types
		app.get("/request/types", rAPI.LISTTYPES);
		
		//List past request
		app.get("/request/history", rAPI.LISTPASTREQUESTS);
		
		//List pending request
		app.get("/request/pending", rAPI.LISTPENDINGREQUEST);
		
		//List rejected request
		app.get("/request/rejected", rAPI.LISTREJECTEDREQUEST);
		
		//List current manager employee pending
		app.get("/request/manpend", rAPI.LISTEMPPENDING);
		
		//List ordered list
		app.get("/request/order", rAPI.LISTORDEREDREQUEST);
		
		//List funds list
		app.get("/request/funds", rAPI.LISTTOTALFUNDS);
		
		app.exception(NullPointerException.class, (e, ctx)->{
			ctx.status(404);
			ctx.redirect("/html/badlogin.html");
		});
		
		File file = new File("src/main/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://localhost:9065/html/index.html");

	}

}
