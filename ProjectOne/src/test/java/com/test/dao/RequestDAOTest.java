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
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.dao.RequestDAOImpl;
import com.example.dao.TobyTechDB;
import com.example.model.Employee;
import com.example.model.Funds;
import com.example.model.Request;
import com.example.model.RequestType;

public class RequestDAOTest {
	
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
	
	private RequestDAOImpl rDao;
	private Request request;
	private Employee employee;
	private RequestType type;
	private Funds funds;
	private List<Funds> fList;
	private List<RequestType> typeList;
	private List<Request> rList;
	
	
	@BeforeEach
	public void setUp() throws Exception{
		
		MockitoAnnotations.initMocks(this);
		rDao = new RequestDAOImpl(tdb);
		when(tdb.getDBConnection()).thenReturn(con);
		when(con.prepareStatement(isA(String.class))).thenReturn(ps);
		when(con.prepareCall(isA(String.class))).thenReturn(cs);
		when(ps.executeQuery()).thenReturn(rs);
		when(ps.execute()).thenReturn(true);
		when(cs.execute()).thenReturn(true);
		when(cs.getString(isA(Integer.class))).thenReturn("Test Succesful");
		type = new RequestType(1, "Test");
		typeList = Arrays.asList(type);
		funds = new Funds(1, "tests");
		fList = Arrays.asList(funds);
		employee = new Employee(1, "Bobby", "Boy", "username", "password", "email", 1, 1);
		request = new Request(1, "Title", "Description", 1, LocalDate.of(2022, 1, 30), LocalDate.of(2022, 1, 31),
				1, 1, 2, 1, "comment");
		rList = Arrays.asList(request);
		when(rs.next()).thenReturn(true).thenReturn(false);
		when(rs.getInt(1)).thenReturn(request.getRequestid());
		when(rs.getString(2)).thenReturn(request.getTitle());
		when(rs.getString(3)).thenReturn(request.getDescription());
		when(rs.getDouble(4)).thenReturn(request.getAmount());
		when(rs.getObject(5, LocalDate.class)).thenReturn(request.getSubmition());
		when(rs.getObject(6, LocalDate.class)).thenReturn(request.getResolution());
		when(rs.getInt(7)).thenReturn(request.getApproverid());
		when(rs.getInt(8)).thenReturn(request.getEmployeeid());
		when(rs.getInt(9)).thenReturn(request.getStatusid());
		when(rs.getInt(10)).thenReturn(request.getTypeid());
		when(rs.getString(11)).thenReturn(request.getComment());
	
	}
	
	//This tests the type list
	@Test
	public void findTypeList() {
		List<RequestType> testTypeList = rDao.listRequestTypes();
		assertEquals(typeList.size(), testTypeList.size());
	}
	
	//This tests the history request list
	@Test
	public void findPastRequestList() {
		List<Request> testReqList = rDao.getAllRequest();
		assertEquals(rList.size(), testReqList.size());
	}
	
	//This tests the order request list
	@Test
	public void findOrderList() {
		List<Request> testReqList = rDao.getRequestOrdered();
		assertEquals(rList.size(), testReqList.size());
	}
	
	//This tests the pending request list
	@Test
	public void findPendingList() {
		List<Request> testReqList = rDao.getAllPending();
		assertEquals(rList.size(), testReqList.size());
	}
	
	
	//This tests the rejected request list
	@Test
	public void findRejectList() {
		List<Request> testReqList = rDao.getAllRejected();
		assertEquals(rList.size(), testReqList.size());
	}
	
	//This tests the funds list
	@Test
	public void findFundsList() {
		List<Funds> testFundsList = rDao.getTotalTypeFunds();
		assertEquals(fList.size(), testFundsList.size());
	}
	
	//insert new request
	@Test
	public void insertRequestSuccess() throws SQLException {
		rDao.insertRequest(employee, request.getTitle(), request.getDescription(), request.getAmount(),
				request.getTypeid());
		verify(cs, times(1)).execute();
	}
	
	//update a request
	@Test
	public void updateRequestSuccess() throws SQLException {
		rDao.updateRequest(request.getRequestid(), request.getTitle(), request.getDescription(), request.getAmount(),
				request.getTypeid());
		verify(cs, times(1)).execute();
	}
	
	//update the status request
	@Test
	public void updateStatusSuccess() throws SQLException {
		rDao.updateStatus(employee, request.getRequestid(), request.getStatusid(), request.getComment());
		verify(cs, times(1)).execute();
	}

}
