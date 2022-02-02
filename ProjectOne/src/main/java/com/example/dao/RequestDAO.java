package com.example.dao;

import java.util.List;

import com.example.model.Employee;
import com.example.model.Funds;
import com.example.model.Request;
import com.example.model.RequestType;

public interface RequestDAO {
	
	void insertRequest(Employee employee, String title, String description, double amount, int typeid);//tle varchar(30), des varchar(300), num numeric, rec bytea, e_id int
	void updateRequest(int requestid, String title, String description, double amount, int typeid);//r_id int, tle varchar(30), des varchar(300), num numeric, rec bytea
	void updateStatus(Employee employee, int requestid, int statusid, String comment);//a_id int, s_id int, r_id int
//	void setRequestType(Request request, String type);
	
	List<RequestType> listRequestTypes();
	List<Request> getAllRequest();
	List<Request> getAllPending();
	List<Request> getAllRejected();
	List<Request> getRequestOrdered();
	List<Funds> getTotalTypeFunds();
}
