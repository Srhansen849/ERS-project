package com.example.api;

import java.util.List;


import com.example.model.Employee;
import com.example.model.Funds;
import com.example.model.Request;
import com.example.model.RequestType;
import com.example.service.RequestService;

import io.javalin.http.Handler;

public class RequestAPI {
	
	private RequestService rServ;
	
	public RequestAPI() {
		// TODO Auto-generated constructor stub
	}
	
	public RequestAPI(RequestService rServ) {
		super();
		this.rServ = rServ;
	}
	
	//posts a new request
	public final Handler POSTNEWREQUEST = (ctx) ->{
		
		Employee emp = (Employee)ctx.sessionAttribute("currentemp");
		Request req = new Request();
		rServ.insertRequest(emp, req, ctx.formParam("title"), ctx.formParam("description"), 
				Double.parseDouble(ctx.formParam("amount")), Integer.parseInt(ctx.formParam("type")));
		ctx.status(201);
		ctx.sessionAttribute("currentreq", req);
		ctx.redirect("/html/currentrequests.html");
		
	};
	
	//update a request
	public final Handler UPDATEAREQUEST = (ctx) ->{
		
		Request req = (Request)ctx.sessionAttribute("currentreq");
		rServ.updateRequest(Integer.parseInt(ctx.formParam("request")), ctx.formParam("title"), ctx.formParam("description"), 
				Double.parseDouble(ctx.formParam("amount")), Integer.parseInt(ctx.formParam("type")));
		ctx.status(201);
		ctx.redirect("/html/currentrequests.html");
		
	};
	
	//manager updates status
	public final Handler UPDATESTATUS = (ctx) ->{
		
		//Request req = (Request)ctx.sessionAttribute("currentreq");
		Employee emp = (Employee)ctx.sessionAttribute("currentemp");
		System.out.println("session user: "+emp);
		rServ.updateStatus(emp, Integer.parseInt(ctx.formParam("request")), Integer.parseInt(ctx.formParam("status")), ctx.formParam("comment"));
		ctx.status(200);
		ctx.redirect("/html/currentemprequest.html");
		
	};
	
	//drop down of type list
	public final Handler LISTTYPES = (ctx) ->{
		
		List<RequestType> typeList = rServ.findTypesOfRequest();
		
		ctx.status(200);
		ctx.json(typeList);
			
	};
	
	//List of employee stats
	public final Handler LISTEMPLOYEESTATS = (ctx) ->{
		
		Employee emp = (Employee)ctx.sessionAttribute("currentemp");
		List<Request> reqList = rServ.findOrderedRequest(emp);
		
		ctx.status(200);
		ctx.json(reqList);
			
	};
	
	//List all past request
	public final Handler LISTPASTREQUESTS = (ctx) ->{
			
		Employee emp = (Employee)ctx.sessionAttribute("currentemp");
		List<Request> reqList = rServ.findAllRequest(emp);
		
		ctx.status(200);
		ctx.json(reqList);
		
	};
	
	//List of pending request
	public final Handler LISTPENDINGREQUEST = (ctx) ->{
			
		Employee emp = (Employee)ctx.sessionAttribute("currentemp");
		List<Request> reqList = rServ.findAllPending(emp);
		
		ctx.status(200);
		ctx.json(reqList);
		
	};
	
	//List of rejected request
	public final Handler LISTREJECTEDREQUEST = (ctx) ->{
				
		Employee emp = (Employee)ctx.sessionAttribute("currentemp");
		List<Request> reqList = rServ.findAllRejected(emp);
			
		ctx.status(200);
		ctx.json(reqList);
			
	};
	
	//List of ordered requests
	public final Handler LISTORDEREDREQUEST = (ctx) ->{
					
		Employee emp = (Employee)ctx.sessionAttribute("currentemp");
		List<Request> reqList = rServ.findOrderedRequest(emp);
				
		ctx.status(200);
		ctx.json(reqList);
			
	};
	
	//List of ordered requests
	public final Handler LISTTOTALFUNDS = (ctx) ->{
						
		List<Funds> fundList = rServ.findFunds();
					
		ctx.status(200);
		ctx.json(fundList);
				
	};
	
	public final Handler LISTEMPPENDING = (ctx) ->{
		
		Employee emp = (Employee)ctx.sessionAttribute("currentemp");
		List<Request> reqList = rServ.findAllCurrentPend(emp);
		
		ctx.status(200);
		ctx.json(reqList);
	};
	
	//get current request
	public final Handler GETCURRENTREQUEST = (ctx) ->{
		
		Request req = (Request)ctx.sessionAttribute("currentreq");
		ctx.json(req);
		
	};

}
