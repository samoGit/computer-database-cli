/**
 * 
 */
package com.excilys.cdb.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

/**
 * @author excilys
 *
 */
class TestComputerService {
	
	public static ComputerService computerService;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		computerService = new ComputerService();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Test method for {@link com.excilys.cdb.service.ComputerService#getListComputersByName(java.lang.String)}.
	 */
	@Test
	void testGetListComputersByName() {
		List<Computer> expectedComputerList = new ArrayList<Computer>();
		Computer expectedComputer = new Computer(	Long.valueOf(319), 
													"HP Mini 1000", 
													Optional.ofNullable(LocalDate.parse("29/10/2008", DateTimeFormatter.ofPattern("dd/MM/yyyy"))), 
													Optional.empty(), 
													Optional.ofNullable(new Company(Long.valueOf(27), "Hewlett-Packard")));
		expectedComputerList.add(expectedComputer);
		List<Computer> actualComputerList = computerService.getListComputersByName("HP Mini 1000");
		
		assertEquals(expectedComputerList, actualComputerList);
	}

	/**
	 * Test method for {@link com.excilys.cdb.service.ComputerService#CreateNewComputer(java.lang.String, java.time.LocalDate, java.time.LocalDate, com.excilys.cdb.model.Company)}.
	 */
	@Test
	void testCreateNewComputer() {
		List<Computer> computerListShouldBeEmpty = computerService.getListComputersByName("testCreateNewComputer");
		assertTrue(computerListShouldBeEmpty.isEmpty());

		String nameNewPC = "testCreateNewComputer";
		LocalDate dateIntoducedNewPC = LocalDate.parse("01/02/2003", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		LocalDate dateDiscontinuedNewPC = LocalDate.parse("04/05/2006", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		computerService.CreateNewComputer(nameNewPC, Optional.ofNullable(dateIntoducedNewPC), Optional.ofNullable(dateDiscontinuedNewPC),  Optional.empty());

		List<Computer> computerListFound = computerService.getListComputersByName("testCreateNewComputer");
		assertFalse(computerListFound.isEmpty());

		Computer computerFound = computerListFound.get(0);
		assertEquals(computerFound.getName(), nameNewPC);
		assertEquals(computerFound.getDateIntroduced().get(), dateIntoducedNewPC);
		assertEquals(computerFound.getDateDiscontinued().get(), dateDiscontinuedNewPC);
		
		computerService.DeleteComputer(computerFound);
	}

	/**
	 * Test method for {@link com.excilys.cdb.service.ComputerService#DeleteComputer(com.excilys.cdb.model.Computer)}.
	 */
	@Test
	void testDeleteComputer() {
		String nameNewPC = "testCreateNewComputer";
		LocalDate dateIntoducedNewPC = LocalDate.parse("01/02/2003", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		LocalDate dateDiscontinuedNewPC = LocalDate.parse("04/05/2006", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		computerService.CreateNewComputer(nameNewPC, Optional.ofNullable(dateIntoducedNewPC), Optional.ofNullable(dateDiscontinuedNewPC),  Optional.empty());		

		List<Computer> computerListShouldNotBeEmpty = computerService.getListComputersByName("testCreateNewComputer");
		assertFalse(computerListShouldNotBeEmpty.isEmpty());
		
		computerService.DeleteComputer(computerListShouldNotBeEmpty.get(0));

		List<Computer> computerListShouldBeEmpty = computerService.getListComputersByName("testCreateNewComputer");
		assertTrue(computerListShouldBeEmpty.isEmpty());
	}

	/**
	 * Test method for {@link com.excilys.cdb.service.ComputerService#UpdateComputer(com.excilys.cdb.model.Computer, java.lang.String)}.
	 */
	@Test
	void testUpdateComputer() {
		String nameNewPC = "testCreateNewComputer";
		LocalDate dateIntoducedNewPC = LocalDate.parse("01/02/2003", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		LocalDate dateDiscontinuedNewPC = LocalDate.parse("04/05/2006", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		computerService.CreateNewComputer(nameNewPC, Optional.ofNullable(dateIntoducedNewPC), Optional.ofNullable(dateDiscontinuedNewPC),  Optional.empty());

		List<Computer> computerListShouldNotBeEmpty = computerService.getListComputersByName("testCreateNewComputer");
		assertFalse(computerListShouldNotBeEmpty.isEmpty());

		Computer computerToBeUpdate = computerListShouldNotBeEmpty.get(0);
		computerToBeUpdate.setName("testCreateNewComputer_RENAMED");
		computerService.UpdateComputer(computerToBeUpdate, "name");
		
		List<Computer> computerListAfterRename = computerService.getListComputersByName("testCreateNewComputer_RENAMED");
		assertFalse(computerListAfterRename.isEmpty());

		Computer computerAfterRename = computerListAfterRename.get(0);
		assertEquals(computerToBeUpdate, computerAfterRename);

		computerService.DeleteComputer(computerAfterRename);
	}
}
