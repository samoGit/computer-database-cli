package com.excilys.cdb.persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
	
	public final static ConnectionManager INSTANCE = new ConnectionManager();

	private final String JDBC_DRIVER_CLASS_NAME;
	private final String BDD_URL;
	private final String BDD_USER;
	private final String BDD_PASSWORD;	

	public ConnectionManager() {
		Properties properties = new Properties();
		try {
			InputStream input = new FileInputStream("config.properties");
			properties.load(input);
		} catch (FileNotFoundException e) {
			System.err.println("The configuration file ('config.properties') is not found.");
		}
		catch (IOException e) {
			System.err.println("Impossible to load the properties from the file 'config.properties'");
		}
		
		JDBC_DRIVER_CLASS_NAME = properties.getProperty("JDBC_DRIVER_CLASS_NAME");
		BDD_URL = properties.getProperty("BDD_URL");
		BDD_USER = properties.getProperty("BDD_USER");
		BDD_PASSWORD = properties.getProperty("BDD_PASSWORD");		
	}

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
