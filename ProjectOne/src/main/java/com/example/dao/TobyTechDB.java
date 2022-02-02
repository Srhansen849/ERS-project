package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TobyTechDB {
	
	private static final String URL = "jdbc:postgresql://jwa-2112-dec.csa6lart3qd4.us-east-2.rds.amazonaws.com:5432/tobydb";
	private static final String USERNAME = "tobyuser";
	private static final String PASSWORD = "Password";
	
	public Connection getDBConnection() throws SQLException {
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}

}
