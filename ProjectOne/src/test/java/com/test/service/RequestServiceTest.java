package com.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.dao.RequestDAOImpl;
import com.example.model.Employee;
import com.example.model.Funds;
import com.example.model.Request;
import com.example.model.RequestType;
import com.example.service.RequestService;

public class RequestServiceTest {
	
	@Mock
	private RequestDAOImpl rDao;
	
	private RequestService rServ;
	private Employee employee;
	private Request request;
	private RequestType type;
	private Funds funds;
	private List<Request> rList;
	private List<Funds> fList;
	private List<RequestType> typeList;
	
	@BeforeEach
	public void setUp() throws Exception{
		
		MockitoAnnotations.initMocks(this);
		rServ = new RequestService(rDao);
		employee = new Employee(1, "Bobby", "Boy", "username", "password", "email", 1, 1);
		request = new Request(1, "Title", "Description", 1, LocalDate.of(2022, 1, 30), LocalDate.of(2022, 1, 31),
				1, 1, 2, 1, "comment");
		rList = Arrays.asList(request);
		type = new RequestType(1, "Test");
		typeList = Arrays.asList(type);
		funds = new Funds(1, "tests");
		fList = Arrays.asList(funds);
		
		when(rDao.getAllRequest()).thenReturn(rList);
		when(rDao.getAllPending()).thenReturn(rList);
		when(rDao.getAllRejected()).thenReturn(rList);
		when(rDao.getRequestOrdered()).thenReturn(rList);
		when(rDao.getTotalTypeFunds()).thenReturn(fList);
		when(rDao.listRequestTypes()).thenReturn(typeList);
		
	}
	
	//This will test the get all pending list 
	@Test
	public void findPendingRequest() {
		List<Request> testReqList = rServ.findAllPending(employee);
		assertEquals(rList.size(), testReqList.size());
	}
	
	//This will test the get all rejected list 
	@Test
	public void findRejectedRequest() {
		List<Request> testReqList = rServ.findAllRejected(employee);
		assertEquals(rList.size(), testReqList.size());
	}
		
	//This will test the get all past list 
	@Test
	public void findHistoryRequest() {
		List<Request> testReqList = rServ.findAllRequest(employee);
		assertEquals(rList.size(), testReqList.size());
	}
		
	//This will test the get all ordered list 
	@Test
	public void findOrderedRequest() {
		List<Request> testReqList = rServ.findOrderedRequest(employee);
		assertEquals(rList.size(), testReqList.size());
	}
		
	//This will test the get all managerpend list 
	@Test
	public void findManagerPending() {
		List<Request> testReqList = rServ.findAllCurrentPend(employee);
		assertEquals(rList.size(), testReqList.size());
	}
		
	//This will test the get all type list 
	@Test
	public void findTypeList() {
		List<RequestType> testReqList = rServ.findTypesOfRequest();
		assertEquals(typeList.size(), testReqList.size());
	}
		
	//This will test the get all funds list 
	@Test
	public void findTotalFund() {
		List<Funds> testFundList = rServ.findFunds();
		assertEquals(fList.size(), testFundList.size());
	}
	
	//insert a new request
	@Test
	public void insertRequestSuccess() {
		rServ.insertRequest(employee, request, request.getTitle(), request.getDescription(),
				request.getAmount(), request.getTypeid());
		verify(rDao, times(1)).insertRequest(employee, request.getTitle(), request.getDescription(),
				request.getAmount(), request.getTypeid());
	}
	
	//update request info
	@Test
	public void updateRequestSuccess() {
		rServ.updateRequest(request.getRequestid(), request.getTitle(), request.getDescription(),
				request.getAmount(), request.getTypeid());
		verify(rDao, times(1)).updateRequest(request.getRequestid(), request.getTitle(), request.getDescription(),
				request.getAmount(), request.getTypeid());
	}
	
	//update status of request
	@Test
	public void updateStatusSuccess() {
		rServ.updateStatus(employee, request.getRequestid(), request.getStatusid(), request.getComment());
		verify(rDao, times(1)).updateStatus(employee, request.getRequestid(), request.getStatusid(), request.getComment());
	}

}
