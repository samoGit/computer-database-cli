package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.mapper.ComputerMapper;
import com.excilys.cdb.model.Computer;

/**
 * Singleton that manage interactions with the BDD (for Computer).
 * 
 * @author samy
 */
public enum ComputerDao {
	/**
	 * Instance of {@link ComputerDao} (for Singleton pattern).
	 */
	INSTANCE;

	final static String SQL_SELECT_ALL_COMPUTERS = "SELECT id, name, introduced, discontinued, company_id FROM computer;";
	final static String SQL_SELECT_COMPUTERS_FROM_NAME = "SELECT id, name, introduced, discontinued, company_id FROM computer where name = \"%s\";";

	final static String SQL_INSERT_COMPUTER = "INSERT INTO computer (%s) VALUES (%s);";
	final static String SQL_DELETE_COMPUTER = "DELETE FROM computer WHERE id=\"%s\";";
	final static String SQL_UPDATE_COMPUTER = "UPDATE computer SET %s = %s  WHERE id = %s;";

	private final ConnectionManager connectionManager = ConnectionManager.INSTANCE;

	/**
	 * Return all the computers present in the BDD
	 * 
	 * @return List of {@link Computer}
	 */
	public List<Computer> getListComputers() {
		ArrayList<Computer> listComputers = new ArrayList<>();
		try {
			Connection connection = connectionManager.getConnection();
			Statement stmt;
			stmt = connection.createStatement();
			ResultSet resultSet = stmt.executeQuery(SQL_SELECT_ALL_COMPUTERS);
			while (resultSet.next()) {
				listComputers.add(ComputerMapper.getComputer(resultSet));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listComputers;
	}

	/**
	 * Find every computers in the BDD with a given name
	 * 
	 * @param name String
	 * @return List of {@link Computer}
	 */
	public List<Computer> getListComputersByName(String name) {
		ArrayList<Computer> listComputersFound = new ArrayList<>();
		try {
			Connection connection = connectionManager.getConnection();
			Statement stmt;
			stmt = connection.createStatement();
			String query = String.format(SQL_SELECT_COMPUTERS_FROM_NAME, name);
			ResultSet resultSet = stmt.executeQuery(query);
			while (resultSet.next()) {
				listComputersFound.add(ComputerMapper.getComputer(resultSet));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listComputersFound;
	}

	/**
	 * Create a new computer in the BDD.
	 * 
	 * @param name         String
	 * @param introduced   LocalDate
	 * @param discontinued LocalDate
	 * @param idCompany    Long
	 */
	public void CreateNewComputer(String name, LocalDate introduced, LocalDate discontinued, Long idCompany) {
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

		try {
			Connection connection = connectionManager.getConnection();
			Statement stmt;
			stmt = connection.createStatement();
			String query = String.format(SQL_INSERT_COMPUTER, indices, values);
			stmt.executeUpdate(query);
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete the given computer from the BDD
	 * 
	 * @param computer {@link Computer}
	 * @throws SQLException .
	 */
	public void DeleteComputer(Computer computer) {
		try {
			Connection connection = connectionManager.getConnection();
			Statement stmt;
			stmt = connection.createStatement();
			String query = String.format(SQL_DELETE_COMPUTER, computer.getId());
			stmt.executeUpdate(query);
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete the given computer from the BDD
	 * 
	 * @param computer {@link Computer}
	 * @param field    String field of the Table to be updated
	 */
	public void UpdateComputer(Computer computer, String field) {
		String valueWithQuoteIfNeeded = computer.getStringValue(field);
		if (valueWithQuoteIfNeeded != null)
			valueWithQuoteIfNeeded = "\"" + valueWithQuoteIfNeeded + "\"";

		try {
			Connection connection = connectionManager.getConnection();
			Statement stmt;
			stmt = connection.createStatement();
			String query = String.format(SQL_UPDATE_COMPUTER, field, valueWithQuoteIfNeeded, computer.getId());
			stmt.executeUpdate(query);
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
