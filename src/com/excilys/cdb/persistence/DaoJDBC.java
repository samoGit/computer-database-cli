package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import com.excilys.cdb.model.Computer;

/**
 * Singleton that manage interactions with the BDD.
 * 
 * @author samy
 */
public class DaoJDBC {

	final static String JDBC_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	final static String BDD_URL = "jdbc:mysql://127.0.0.1:3306/computer-database-db";
	final static String BDD_USER = "admincdb";
	final static String BDD_PASSWORD = "qwerty1234";

	final static String SQL_SELECT_ALL_COMPANY = "SELECT id, name FROM company;";
	final static String SQL_SELECT_ALL_COMPUTERS = "SELECT id, name, introduced, discontinued, company_id FROM computer;";
	final static String SQL_SELECT_COMPUTERS_FROM_NAME = "SELECT id, name, introduced, discontinued, company_id FROM computer where name = \"%s\";";

	final static String SQL_INSERT_COMPUTER = "INSERT INTO computer (%s) VALUES (%s);";
	final static String SQL_DELETE_COMPUTER = "DELETE FROM computer WHERE id=\"%s\";";
	final static String SQL_UPDATE_COMPUTER = "UPDATE computer SET %s = %s  WHERE id = %s;";

	/**
	 * Instance of {@link DaoJDBC} (for Singleton pattern).
	 */
	static private DaoJDBC instanceDaoJDBC;

	/**
	 * Create the instance of DaoJDBC (if not already created) then return it.
	 * 
	 * @return {@link DaoJDBC}
	 * @throws ClassNotFoundException .
	 * @throws SQLException .
	 */
	static public DaoJDBC GetInstance() throws ClassNotFoundException, SQLException {
		if (instanceDaoJDBC == null)
			instanceDaoJDBC = new DaoJDBC();
		return instanceDaoJDBC;
	}
	
	private Connection connection;

	/**
	 * Constructor call (only once) by GetInstance.
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException 
	 */
	private DaoJDBC() throws ClassNotFoundException, SQLException {
		System.out.println("\nInit Driver...");
		Class.forName(JDBC_DRIVER_CLASS_NAME);
		System.out.println("Success !");
		
		System.out.println("\nGet connection...");
		connection = DriverManager.getConnection(BDD_URL, BDD_USER, BDD_PASSWORD);
		System.out.println("Success !");
	}

	/**
	 * Return the list of companies present in the BDD
	 * 
	 * @return {@link ResultSet}
	 * @throws SQLException .
	 */
	public ResultSet getListCompanies() throws SQLException {
		return this.executeQuery(SQL_SELECT_ALL_COMPANY);
	}

	/**
	 * Return all the computers present in the BDD
	 * 
	 * @return The {@link ResultSet} of the query executed
	 * @throws SQLException .
	 */
	public ResultSet getListComputers() throws SQLException {
		return this.executeQuery(SQL_SELECT_ALL_COMPUTERS);
	}

	/**
	 * Find every computers in the BDD with a given name
	 * 
	 * @param name String
	 * @return The {@link ResultSet} of the query executed
	 * @throws SQLException .
	 */
	public ResultSet getListComputersByName(String name) throws SQLException {
		String query = String.format(SQL_SELECT_COMPUTERS_FROM_NAME, name);
		return this.executeQuery(query);
	}

	/**
	 * Execute a SQL query to read data from BDD.
	 * 
	 * @param query String a valid SQL query.
	 * @return The {@link ResultSet} of the query executed
	 * @throws SQLException if a database access error occurs
	 */
	private ResultSet executeQuery(String query) throws SQLException {
		Statement stmt;
		System.out.println("\nExecute the following query : " + query);
		stmt = connection.createStatement();
		ResultSet result = stmt.executeQuery(query);
		System.out.println("Success !\n");

		return result;
	}

	/**
	 * Execute a SQL query to update the BDD.
	 * 
	 * @param query String a valid SQL query.
	 * 
	 * @return either (1) the row count for SQL Data Manipulation Language (DML)
	 *         statements or (2) 0 for SQL statements that return nothing
	 * @throws SQLException if a database access error occurs
	 */
	public int executeUpdate(String query) throws SQLException {
		Statement stmt;
		System.out.println("\nExecute the following query : " + query);
		stmt = connection.createStatement();
		int nbLineModified = stmt.executeUpdate(query);
		System.out.println("Success !\n");

		return nbLineModified;
	}

	/**
	 * Create a new computer in the BDD.
	 * 
	 * @param name         String
	 * @param introduced   LocalDate
	 * @param discontinued LocalDate
	 * @param idCompany    Long
	 * @throws SQLException .
	 */
	public void CreateNewComputer(String name, LocalDate introduced, LocalDate discontinued, Long idCompany)
			throws SQLException {
		String indices = "name";
		String values = "\"" + name + "\"";

		if (introduced != null) {
			indices += ", introduced";
			values += ", \"" + introduced + "\"";
		}
		if (discontinued != null) {
			indices += ", discontinued";
			values += ", \"" + discontinued + "\"";
		}
		if (idCompany != null) {
			indices += ", company_id";
			values += ", \"" + idCompany + "\"";
		}

		String query = String.format(SQL_INSERT_COMPUTER, indices, values);
		this.executeUpdate(query);
	}

	/**
	 * Delete the given computer from the BDD
	 * 
	 * @param computer {@link Computer}
	 * @throws SQLException .
	 */
	public void DeleteComputer(Computer computer) throws SQLException {
		String query = String.format(SQL_DELETE_COMPUTER, computer.getId());
		this.executeUpdate(query);
	}

	/**
	 * Delete the given computer from the BDD
	 * 
	 * @param computer {@link Computer}
	 * @param field String field of the Table to be updated
	 * @throws SQLException .
	 */
	public void UpdateComputer(Computer computer, String field) throws SQLException {
		String valueWithQuoteIfNeeded = computer.getStringValue(field);
		if (valueWithQuoteIfNeeded != null)
			valueWithQuoteIfNeeded = "\"" + valueWithQuoteIfNeeded + "\"";

		String query = String.format(SQL_UPDATE_COMPUTER, field, valueWithQuoteIfNeeded, computer.getId());
		this.executeUpdate(query);
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
