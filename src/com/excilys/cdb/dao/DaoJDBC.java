package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

/**
 * Singleton that manage interactions with the BDD.
 * 
 * @author samy
 */
public class DaoJDBC {
	/**
	 * Instance of DaoJDBC (for Singleton patern).
	 */
	static private DaoJDBC instanceDaoJDBC;

	/**
	 * Create the instance of DaoJDBC (if not already created) then return it.
	 */
	static public DaoJDBC GetInstance() {
		if (instanceDaoJDBC == null) {
			instanceDaoJDBC = new DaoJDBC();
		}
		return instanceDaoJDBC;
	}

	// Attributes : 
	Connection connection;

	/**
	 * Constructor call (only once) by GetInstance.
	 */
	private DaoJDBC() {
    	try {

        	System.out.println("\nInit Driver...");
        	Class.forName("com.mysql.jdbc.Driver");
        	System.out.println("Success !");

        	System.out.println("\nGet connection...");
			String url = "jdbc:mysql://127.0.0.1:3306/computer-database-db";
			connection = DriverManager.getConnection(url, "admincdb", "qwerty1234");
        	System.out.println("Success !");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Return the list of companies present in the BDD
	 */	
	public List<Company> getListCompanies() {
		ArrayList<Company> listCompanies = new ArrayList<>();
		
    	try {

	    	System.out.println("\nFill the list of companies : ");
	    	String query = "SELECT * FROM company;";
	    	ResultSet results;
	    	Statement stmt = connection.createStatement();
	    	results = stmt.executeQuery(query);

	    	while (results.next()) {
	    		Long id = results.getLong("id");
	    		String name = results.getString("Name");
	    		
	    		Company c = new Company(id, name);
	    		listCompanies.add(c);
	    	}
	    	System.out.println("Success !");	
    	
    	} catch (SQLException e) {
 			e.printStackTrace();
 		}
    	
    	return listCompanies;
	}

	/**
	 * Return the list of computer present in the BDD
	 */	
	public List<Computer> getListComputers() {
		ArrayList<Computer> listComputers = new ArrayList<>();
		
    	try {

	    	System.out.println("\nFill the list of Computers : ");
	    	String query = "SELECT * FROM computer;";
	    	ResultSet results;
	    	Statement stmt = connection.createStatement();
	    	results = stmt.executeQuery(query);

	    	while (results.next()) {
	    		Long id = results.getLong("id");
	    		String name = results.getString("Name");
	    		Date introduced = results.getDate("introduced");;
	    		Date discontinued = results.getDate("discontinued");
	    		Long company_id = results.getLong("company_id");
	    		
	    		Computer c = new Computer(id, name, introduced, discontinued, company_id);
	    		listComputers.add(c);
	    	}
	    	System.out.println("Success !");	
    	
    	} catch (SQLException e) {
 			e.printStackTrace();
 		}
    	
    	return listComputers;
	}
	
	/**
	 * Close the connection at the end
	 */		
	@Override
	public void finalize() {
    	try {
        	System.out.println("\nClose connection...");
			connection.close();
	    	System.out.println("Success !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
