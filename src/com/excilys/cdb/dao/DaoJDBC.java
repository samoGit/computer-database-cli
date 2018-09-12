package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
	 * 
	 * @return DaoJDBC
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
	 * 
	 * @return List of {@link Company}
	 */	
	public List<Company> getListCompanies() {
		ArrayList<Company> listCompanies = new ArrayList<>();
		
    	try {
	    	ResultSet results = this.executeQuery("SELECT id, Name FROM company;");
	    	while (results.next()) {
	    		Long id = results.getLong("id");
	    		String name = results.getString("Name");
	    		
	    		Company c = new Company(id, name);
	    		listCompanies.add(c);
	    	}
    	} catch (SQLException e) {
 			e.printStackTrace();
 		}
    	
    	return listCompanies;
	}

	/**
	 * Return the list of computer present in the BDD
	 * 
	 * @return List of {@link Computer}
	 */	
	public List<Computer> getListComputers() {
		ArrayList<Computer> listComputers = new ArrayList<>();

    	try {
	    	ResultSet results = this.executeQuery("SELECT id, Name, introduced, discontinued, company_id FROM computer;");
	    	while (results.next()) {
	    		Long id = results.getLong("id");
	    		String name = results.getString("Name");
	    		Date introduced = results.getDate("introduced");;
	    		Date discontinued = results.getDate("discontinued");
	    		Long company_id = results.getLong("company_id");
	    		
	    		Computer c = new Computer(id, name, introduced, discontinued, company_id);
	    		listComputers.add(c);
	    	}
    	} catch (SQLException e) {
 			e.printStackTrace();
 		}

    	return listComputers;
	}
	
	/**
	 * Find every computers in the BDD with a given name
	 * 
	 * @param name String 
	 * 
	 * @return a list of {@link Computer}
	 */	
	public List<Computer> getListComputersByName(String name) {
		ArrayList<Computer> listComputersFound = new ArrayList<>();

    	try {
	    	ResultSet results = this.executeQuery("SELECT id, introduced, discontinued, company_id FROM computer where name=\"" + name + "\";");
	    	while (results.next()) {// TODO : check only one result !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	    		Long id = results.getLong("id");
	    		Date introduced = results.getDate("introduced");;
	    		Date discontinued = results.getDate("discontinued");
	    		Long company_id = results.getLong("company_id");
	    		
	    		listComputersFound.add( new Computer(id, name, introduced, discontinued, company_id) );
	    	}
    	} catch (SQLException e) {
 			e.printStackTrace();
 		}

    	return listComputersFound;
	}

	/**
	 * Execute a SQL query to read data from BDD
	 * 
	 * @param query String a valid SQL query.
	 * 
	 * @return The resultSet of the query executed
	 * @throws SQLException if a database access error occurs
	 */	
	public ResultSet executeQuery(String query) throws SQLException {
		Statement stmt;
    	System.out.println("\nExecute the following query : " + query);
		stmt = connection.createStatement();
		ResultSet result =  stmt.executeQuery(query);
    	System.out.println("Success !\n");

    	return result;
	}

	/**
	 * Execute a SQL query to update the BDD.
	 * 
	 * @param query String a valid SQL query.
	 * 
	 * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing
	 * @throws SQLException if a database access error occurs
	 */	
	public int executeUpdate(String query) throws SQLException {
		Statement stmt;
    	System.out.println("\nExecute the following query : " + query);
		stmt = connection.createStatement();
		int nbLineModified =  stmt.executeUpdate(query);
    	System.out.println("Success !\n");

    	return nbLineModified;
	}

	/**
	 * Create a new computer in the BDD.
	 * 
	 * @param name String
	 * @param introduced LocalDate
	 * @param discontinued LocalDate
	 * @param idCompany Long
	 */
	public void CreateNewComputer(String name, LocalDate introduced, LocalDate discontinued, Long idCompany) {
    	try {
    		String indices = "name";
    		String values = "\"" + name + "\"";

    		if (introduced != null) {
    			indices += ", introduced";
    			values  += ", \"" + introduced + "\"";
    		}
    		if (discontinued != null) {
    			indices += ", discontinued";
    			values += ", \"" + discontinued + "\"";
    		}
    		if (idCompany != null) {
    			indices += ", company_id";
    			values += ", \"" + idCompany + "\"";
    		}

	    	this.executeUpdate("INSERT INTO computer (" + indices + ") VALUES (" + values + ")");
    	} catch (SQLException e) {
 			e.printStackTrace();
 		}
	}

	/**
	 * Delete the given computer from the BDD
	 * 
	 * @param computer Computer
	 */	
	public void DeleteComputer(Computer computer) {
    	try {
	    	this.executeUpdate("DELETE FROM computer WHERE id=" + computer.getId() + ";");
    	} catch (SQLException e) {
 			e.printStackTrace();
 		}
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
