package com.excilys.cdb.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.mapper.CompanyMapper;
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
		ArrayList<Company> listCompanies = new ArrayList<>();
    	try {
    		ResultSet resultSet = daoJDBC.getListCompanies();
	    	while (resultSet.next()) {
	    		listCompanies.add( CompanyMapper.getCompany(resultSet));
	    	}
    	} catch (SQLException e) {
 			e.printStackTrace();
 		}
    	
    	return listCompanies;
	}	
}
