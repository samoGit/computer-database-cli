package com.excilys.cdb.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

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
		return daoJDBC.getListComputers();
	}

	/**
	 * Find every computers in the BDD with a given name
	 * 
	 * @param name String
	 * @return a list of {@link Computer} 
	 */
	public List<Computer> getListComputersByName(String name) {
		return daoJDBC.getListComputersByName(name);
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
		daoJDBC.CreateNewComputer(name, introduced, discontinued, company == null ? null : company.getId());
	}

	/**
	 * Delete the given computer from the BDD
	 * 
	 * @param c {@link Computer}
	 */	
	public void DeleteComputer(Computer c) {
		daoJDBC.DeleteComputer(c);
	}

	/**
	 * Delete the given computer from the BDD
	 * 
	 * @param field String field of the Table to be updated
	 * @param c {@link Computer}
	 */	
	public void UpdateComputer(Computer c, String field) {
		daoJDBC.UpdateComputer(c, field);
	}
}
