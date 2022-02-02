package com.example.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Employee;
import com.example.model.Funds;
import com.example.model.Request;
import com.example.model.RequestType;

public class RequestDAOImpl implements RequestDAO{
	
	public TobyTechDB tobyCon;
	
	public RequestDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	public RequestDAOImpl(TobyTechDB tobyCon) {
		super();
		this.tobyCon = tobyCon;
	}

	//insert a new request
	@Override
	public void insertRequest(Employee employee, String title, String description, double amount, int typeid) {
		// insert_request(tle varchar(30), des varchar(300), num numeric, e_id int)
		
		List<RequestType> typeList = listRequestTypes();
		
		try(Connection con = tobyCon.getDBConnection()){
			
			String sql = "{? = call insert_request(?,?,?,?,?)}";
			
			CallableStatement callable = con.prepareCall(sql);
			callable.registerOutParameter(1, Types.VARCHAR);
			
			callable.setString(2, title);
			callable.setString(3, description);
			callable.setBigDecimal(4, BigDecimal.valueOf(amount));
			callable.setInt(5, employee.getEmployeeid());
			for(RequestType type : typeList){
				if(type.getTypeid()==typeid) {
					//request.setTypeid(typeid.getTypeid());
					callable.setInt(6, type.getTypeid());
				}
			}
			callable.execute();
			System.out.println(callable.getString(1));
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
	}

	//update a request
	@Override
	public void updateRequest(int requestid, String title, String description, double amount, int typeid) {
		// update_data(r_id int, tle varchar(30), des varchar(300), num numeric, rec bytea)
		
		List<RequestType> typeList = listRequestTypes();
		
		try(Connection con = tobyCon.getDBConnection()){
			
			String sql = "{? = call update_data(?,?,?,?,?)}";
			
			CallableStatement callable = con.prepareCall(sql);
			callable.registerOutParameter(1, Types.VARCHAR);
			
			callable.setInt(2, requestid);
			callable.setString(3, title);
			callable.setString(4, description);
			callable.setBigDecimal(5, BigDecimal.valueOf(amount));
			for(RequestType type : typeList){
				if(type.getTypeid()==typeid) {
					//request.setTypeid(typeid.getTypeid());
					callable.setInt(6, type.getTypeid());
				}
			}
			callable.execute();
			
			
			System.out.println(callable.getString(1));
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	//manager update status
	@Override
	public void updateStatus(Employee employee, int requestid, int statusid, String comment) {
		// update_request(a_id int, s_id int, r_id int)
		try(Connection con = tobyCon.getDBConnection()){
			
			String sql = "{? = call update_request(?,?,?,?)}";
			
			CallableStatement callable = con.prepareCall(sql);
			callable.registerOutParameter(1, Types.VARCHAR);
			
			callable.setInt(2, employee.getEmployeeid());
			callable.setInt(3, statusid);
			callable.setInt(4, requestid);
			callable.setString(5, comment);
			callable.execute();
			
			System.out.println(callable.getString(1));
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	//list all request types
	@Override
	public List<RequestType> listRequestTypes() {
		List<RequestType> typeList = new ArrayList<>();
		
		try(Connection con = tobyCon.getDBConnection()){
			
			String sql = "select * from requesttype";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(); //this will return the set of data from the database
			
			while(rs.next()) {
				typeList.add(new RequestType(rs.getInt(1), rs.getString(2)));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("type:"+typeList);
		return typeList;
	}

	//history of requests
	@Override
	public List<Request> getAllRequest() {
		
		List<Request> requestList = new ArrayList<>();
		
		try(Connection con = tobyCon.getDBConnection()){
			
			String sql = "select * from requests where statusid > ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, 2);
			ResultSet rs = ps.executeQuery(); //this will return the set of data from the database
			
			while(rs.next()) {
				requestList.add(new Request(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), 
						rs.getObject(5, LocalDate.class), rs.getObject(6, LocalDate.class), 
						rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getString(11)));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("history:"+requestList);
		return requestList;
	}

	//sorted history for manager
	@Override
	public List<Request> getRequestOrdered() {
		
		List<Request> requestList = new ArrayList<>();

		
		try(Connection con = tobyCon.getDBConnection()){
			
			String sql = "select * from requests where statusid > ? order by amount desc";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, 2);
			ResultSet rs = ps.executeQuery(); //this will return the set of data from the database
			
			while(rs.next()) {
				
				requestList.add(new Request(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), 
						rs.getObject(5, LocalDate.class), rs.getObject(6, LocalDate.class), 
						rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getString(11)));
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("ordered:"+requestList);
		return requestList;
	}

	//total amount spent by type
	@Override
	public List<Funds> getTotalTypeFunds() {

		List<Funds> fundsList = new ArrayList<>();
		double fund;
		String name;
		
		try(Connection con = tobyCon.getDBConnection()){
			
			String sql = "select sum(amount) from requests r join requesttype rt on r.typeid = rt.typeid group by r.typeid";//group by
			String sql2 = "select typename from requesttype";
			
			PreparedStatement ps = con.prepareStatement(sql);
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ResultSet rs = ps.executeQuery(); //this will return the set of data from the database
			ResultSet rs2 = ps2.executeQuery();
			


			while(rs.next()) {
				fund = rs.getDouble(1);
				rs2.next();
				name = rs2.getString(1);
				fundsList.add(new Funds(fund, name));
				
			}

			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("funds:"+fundsList);
		return fundsList;
	}

	//updates the request type on submition
//	@Override
//	public void setRequestType(Request request, String type) {
//		
//		List<RequestType> typeList = listRequestTypes();
//		
//		try(Connection con = tobyCon.getDBConnection()){
//			
//			for(RequestType typeid : typeList){
//				if(typeid.getTypename().equals(type)) {
//					//request.setTypeid(typeid.getTypeid());
//					String sql = "{? = call update_type(?,?)}";
//			
//					CallableStatement callable = con.prepareCall(sql);
//					callable.registerOutParameter(1, Types.VARCHAR);
//					
//					callable.setInt(2, typeid.getTypeid());
//					callable.setInt(3, request.getRequestid());
//					callable.execute();
//					
//					System.out.println(callable.getString(1));
//				}
//			}
//			
//			
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}
//		
//	}

	//Check current request
	@Override
	public List<Request> getAllPending() {

		List<Request> requestList = new ArrayList<>();
		
		try(Connection con = tobyCon.getDBConnection()){
			
			String sql = "select * from requests where statusid = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, 1);
			ResultSet rs = ps.executeQuery(); //this will return the set of data from the database
			
			while(rs.next()) {
				requestList.add(new Request(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), 
						rs.getObject(5, LocalDate.class), rs.getObject(6, LocalDate.class), rs.getInt(7), 
						rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getString(11)));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("pending:"+requestList);
		return requestList;
	}

	@Override
	public List<Request> getAllRejected() {

		List<Request> requestList = new ArrayList<>();
		
		try(Connection con = tobyCon.getDBConnection()){
			
			String sql = "select * from requests where statusid = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, 2);
			ResultSet rs = ps.executeQuery(); //this will return the set of data from the database
			
			while(rs.next()) {
				requestList.add(new Request(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), 
						rs.getObject(5, LocalDate.class), rs.getObject(6, LocalDate.class), 
						rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getString(11)));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("rejected:"+requestList);
		return requestList;
	}

}
