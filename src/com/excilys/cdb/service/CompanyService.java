package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.dao.DaoJDBC;
import com.excilys.cdb.model.Company;

/**
 * Manage company.
 * 
 * @author samy
 */
public class CompanyService {
	
	private DaoJDBC daoJDBC;
	
	public CompanyService() {
		daoJDBC = DaoJDBC.GetInstance();
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
