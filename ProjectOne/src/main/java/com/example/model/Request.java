package com.example.model;

//import java.awt.Image;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class Request {
	
	
	private int requestid;
	private String title;
	private String description;
	private double amount;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate submition;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate resolution;
	//private Image receipt;
	private int approverid;
	private int employeeid;
	private int statusid;
	private int typeid;
	private String comment;
	
	public Request() {
		// TODO Auto-generated constructor stub
	}

	public Request(int requestid, String title, String description, double amount, LocalDate submition, 
			LocalDate resolution, int approverid, int employeeid, int statusid, int typeid, String comment) {
		super();
		this.requestid = requestid;
		this.title = title;
		this.description = description;
		this.amount = amount;
		this.submition = submition;
		this.resolution = resolution;
		//this.receipt = receipt;
		this.approverid = approverid;
		this.employeeid = employeeid;
		this.statusid = statusid;
		this.typeid = typeid;
		this.comment = comment;
	}

	public Request(String title, String description, double amount, LocalDate submition, LocalDate resolution, 
			int approverid, int employeeid, int statusid, int typeid, String comment) {
		super();
		this.title = title;
		this.description = description;
		this.amount = amount;
		this.submition = submition;
		this.resolution = resolution;
		//this.receipt = receipt;
		this.approverid = approverid;
		this.employeeid = employeeid;
		this.statusid = statusid;
		this.typeid = typeid;
		this.comment = comment;
	}
	

	public Request(int requestid, String title, String description, double amount, LocalDate submition, int employeeid,
			int statusid, int typeid) {
		super();
		this.requestid = requestid;
		this.title = title;
		this.description = description;
		this.amount = amount;
		this.submition = submition;
		this.employeeid = employeeid;
		this.statusid = statusid;
		this.typeid = typeid;
	}
	
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDate getSubmition() {
		return submition;
	}

	public void setSubmition(LocalDate submition) {
		this.submition = submition;
	}

	public LocalDate getResolution() {
		return resolution;
	}

	public void setResolution(LocalDate resolution) {
		this.resolution = resolution;
	}

//	public Image getReceipt() {
//		return receipt;
//	}
//
//	public void setReceipt(Image receipt) {
//		this.receipt = receipt;
//	}

	public int getApproverid() {
		return approverid;
	}

	public void setApproverid(int approverid) {
		this.approverid = approverid;
	}

	public int getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}

	public int getStatusid() {
		return statusid;
	}

	public void setStatusid(int statusid) {
		this.statusid = statusid;
	}

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public int getRequestid() {
		return requestid;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}



	@Override
	public String toString() {
		return "Request [requestid=" + requestid + ", title=" + title + ", description=" + description + ", amount="
				+ amount + ", submition=" + submition + ", resolution=" + resolution + ", approverid=" + approverid
				+ ", employeeid=" + employeeid + ", statusid=" + statusid + ", typeid=" + typeid + ", comment="
				+ comment + "] \n";
	}

	

	

		

}
