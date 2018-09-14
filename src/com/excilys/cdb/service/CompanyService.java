package com.excilys.cdb.service;

import java.sql.SQLException;
import java.util.List;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.DaoJDBC;

/**
 * Manage company.
 * 
 * @author samy
 */
public class CompanyService {

	private DaoJDBC daoJDBC;

	public CompanyService() {
		try {
			daoJDBC = DaoJDBC.GetInstance();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Return the list of companies present in the BDD
	 * 
	 * @return List of Company
	 */
	public List<Company> getListCompanies() {
		return daoJDBC.getListCompanies();
	}
}
