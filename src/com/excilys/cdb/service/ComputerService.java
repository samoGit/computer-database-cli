package com.excilys.cdb.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.mapper.ComputerMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.DaoJDBC;

/**
 * Manage computer.
 * 
 * @author samy
 */
public class ComputerService {
	
	private DaoJDBC daoJDBC;
	
	public ComputerService() {
		try {
			daoJDBC = DaoJDBC.GetInstance();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Return the list of computer present in the BDD.
	 * 
	 * @return List of {@link Computer}
	 */	
	public List<Computer> getListComputers() {
		ArrayList<Computer> listComputers = new ArrayList<>();

		try {
			ResultSet resultSet = daoJDBC.getListComputers();
	    	while (resultSet.next()) {
	    		listComputers.add( ComputerMapper.getComputer(resultSet));
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
    		ResultSet resultSet = daoJDBC.getListComputersByName(name);
	    	while (resultSet.next()) {
	    		listComputersFound.add( ComputerMapper.getComputer(resultSet) );
	    	}
    	} catch (SQLException e) {
 			e.printStackTrace();
 		}

    	return listComputersFound;
	}

	/**
	 * Create a new computer in the BDD
	 * 
	 * @param name String
	 * @param introduced LocalDate
	 * @param discontinued LocalDate
	 * @param company {@link Company}
	 */
	public void CreateNewComputer(String name, LocalDate introduced, LocalDate discontinued, Company company) {
		try {
			daoJDBC.CreateNewComputer(name, introduced, discontinued, company == null ? null : company.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete the given computer from the BDD
	 * 
	 * @param c {@link Computer}
	 */	
	public void DeleteComputer(Computer c) {
		try {
			daoJDBC.DeleteComputer(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Delete the given computer from the BDD
	 * 
	 * @param field String field of the Table to be updated
	 * @param c {@link Computer}
	 */	
	public void UpdateComputer(Computer c, String field) {
		try {
			daoJDBC.UpdateComputer(c, field);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
