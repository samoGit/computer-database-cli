package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum ConnectionManager {

	/**
	 * Instance of {@link ConnectionManager} (for Singleton pattern).
	 */
	INSTANCE;
	
	private final static String JDBC_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	private final static String BDD_URL = "jdbc:mysql://127.0.0.1:3306/computer-database-db";
	private final static String BDD_USER = "admincdb";
	private final static String BDD_PASSWORD = "qwerty1234";

	/**
	 * Load JDBC_DRIVER.
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void InitConnection() throws ClassNotFoundException, SQLException {
		System.out.println("Init Driver...");
		Class.forName(JDBC_DRIVER_CLASS_NAME);
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(BDD_URL, BDD_USER, BDD_PASSWORD);
	}
}
