package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.dao.DaoJDBC;
import com.excilys.cdb.model.Computer;

/**
 * Manage computer.
 * 
 * @author samy
 */
public class ComputerService {
	
	private DaoJDBC daoJDBC;
	
	public ComputerService() {
		daoJDBC = DaoJDBC.GetInstance();
	}

	/**
	 * Return the list of computer present in the BDD
	 * 
	 * @return List of Computer
	 */	
	public List<Computer> getListComputers() {
		return daoJDBC.getListComputers();
	}
}
