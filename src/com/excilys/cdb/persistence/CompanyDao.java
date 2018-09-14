package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.mapper.CompanyMapper;
import com.excilys.cdb.model.Company;

/**
 * Singleton that manage interactions with the BDD (for Company).
 * 
 * @author samy
 */
public enum CompanyDao {
	/**
	 * Instance of {@link CompanyDao} (for Singleton pattern).
	 */
	INSTANCE;

	private final static String SQL_SELECT_ALL_COMPANY = "SELECT id, name FROM company;";

	private final ConnectionManager connectionManager = ConnectionManager.INSTANCE;

	/**
	 * Return the list of companies present in the BDD
	 * 
	 * @return List of {@link Company}
	 */
	public List<Company> getListCompanies() {
		ArrayList<Company> listCompanies = new ArrayList<>();
		try {
			Connection connection = connectionManager.getConnection();
			Statement stmt;
			stmt = connection.createStatement();
			ResultSet resultSet = stmt.executeQuery(SQL_SELECT_ALL_COMPANY);
			while (resultSet.next()) {
				listCompanies.add(CompanyMapper.getCompany(resultSet));
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listCompanies;
	}
}
