package com.example.service;

import java.util.ArrayList;
import java.util.List;

import com.example.dao.RequestDAOImpl;
import com.example.model.Employee;
import com.example.model.Funds;
import com.example.model.Request;
import com.example.model.RequestType;

public class RequestService {
	
	public RequestDAOImpl rDao;
	//public EmployeeDAOImpl eDao;
	
	public RequestService() {
		// TODO Auto-generated constructor stub
	}

	public RequestService(RequestDAOImpl rDao) {
		super();
		this.rDao = rDao;
	}
	
	//insert a request
	public void insertRequest(Employee employee, Request request, String title, String description, 
			double amount, int type) {
		rDao.insertRequest(employee, title, description, amount, type);
//		rDao.setRequestType(request, type);
	}
	
	//update a request
	public void updateRequest(int requestid, String title, String description, double amount,
			int type) {
		rDao.updateRequest(requestid, title, description, amount, type);
//		rDao.setRequestType(request, type);
	}
	
	//manager updates the status
	public void updateStatus(Employee employee, int requestid, int statusid, String comment) {
		rDao.updateStatus(employee, requestid, statusid, 
				comment);
	}
	
	//drop down list of request types
	public List<RequestType> findTypesOfRequest(){
		return rDao.listRequestTypes();
	}
	
	//List of ordered employee request
	public List<Request> findOrderedRequest(Employee employee){
		return rDao.getRequestOrdered();
	}
	
	//List of total funds employee request
	public List<Funds> findFunds(){
		return rDao.getTotalTypeFunds();
	}
	
	//List all employee request
	public List<Request> findAllRequest(Employee employee){
		
		List<Request> hisList = rDao.getAllRequest();
		List<Request> historyList = new ArrayList<>();
		
		for(Request temp : hisList) {
			
//			Employee emp = eDao.getEmployeeByid(temp.getEmployeeid());
			
			if(employee.getEmployeeid()==temp.getEmployeeid()) {
				historyList.add(temp);
			}
		}
		System.out.println("This is the sorted history list \n"+historyList);
		return historyList;
	}
	
	//List all current request
	public List<Request> findAllPending(Employee employee){
		
		System.out.println("in find all pending");
		List<Request> pendList = rDao.getAllPending();
		List<Request> pendingList = new ArrayList<>();
		
		for(Request temp : pendList) {
			
//			Employee emp = eDao.getEmployeeByid(temp.getEmployeeid());
			
			if(employee.getEmployeeid()==temp.getEmployeeid()) {
				pendingList.add(temp);
			}
		}
		System.out.println("This is the sorted pending list \n"+pendingList);
		return pendingList;
	}
	
	public List<Request> findAllRejected(Employee employee){
		
		List<Request> rejList = rDao.getAllRejected();
		List<Request> rejectList = new ArrayList<>();
		
		for(Request temp : rejList) {
			
//			Employee emp = eDao.getEmployeeByid(temp.getEmployeeid());
			
			if(employee.getEmployeeid()==temp.getEmployeeid()) {
				rejectList.add(temp);
			}
		}
		System.out.println("This is the sorted reject list \n"+rejectList);
		return rejectList;
	}
	
	public List<Request> findAllCurrentPend(Employee employee){
		return rDao.getAllPending();
	}
	
}
