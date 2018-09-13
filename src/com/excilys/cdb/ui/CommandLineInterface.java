package com.excilys.cdb.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;

/**
 * Display information in the terminal and manages interactions with the user.
 * 
 * @author samy
 */
public class CommandLineInterface {

	private CompanyService companyService;
	private ComputerService computerService;

	/**
     * Manage interaction with the user.
     */
	private Scanner scanner;

    /**
     * Constructor, init daoJDBC .
     */
	public CommandLineInterface() {
		companyService = new CompanyService();
		computerService = new ComputerService();
    	scanner = new Scanner(System.in);
	}

    /**
     * Display info about all companies.
     */
	private void displayAllCompanies() {
    	List<Company> listCompanies = companyService.getListCompanies();

    	System.out.println("\nList of companies : ");
		System.out.println("/---------------------------------------------------------------\\");
		System.out.println("|       id |                                               name |");
		System.out.println("|---------------------------------------------------------------|");
    	for (Company c : listCompanies) {
    		String strId = String.valueOf(c.getId());
    		String strName = String.valueOf(c.getName());
    		System.out.println("| " + String.format("%1$8s", strId) + " | " + String.format("%1$50s", strName) + " |");
    	}
		System.out.println("\\---------------------------------------------------------------/");
	}

	/**
     * Display info about all computers.
     */
	private void displayAllComputers() {
		// TODO : "The list of computers should also be pageable"
    	List<Computer> listComputers = computerService.getListComputers();
		if (listComputers == null || listComputers.isEmpty()) {
			System.out.println("No computers found.");
		}
		else {
			this.displayTableComputers(listComputers);
		}
	}
	
	/**
     * Display all the given computer in a table
     */
	private void displayTableComputers(List<Computer> listComputers) {
		System.out.println("/-------------------------------------------------------------------------------------------------------------------------------------------\\");
		this.displayRowComputer("id", "name", "introduced", "discontinued");
		System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------|");
    	for (Computer c : listComputers) {
    		String strId = String.valueOf(c.getId());
    		String strName = String.valueOf(c.getName()); 
    		String strIntroduced = String.valueOf( c.getIntroduced() == null ? "?" : c.getIntroduced() );
    		String strDiscontinued = String.valueOf( c.getDiscontinued() == null ? "?" : c.getDiscontinued() );

    		this.displayRowComputer(strId, strName, strIntroduced, strDiscontinued);
    	}
		System.out.println("\\-------------------------------------------------------------------------------------------------------------------------------------------/");
	}
	
	/**
     * Display a row in the table of computers.
     */
	private void displayRowComputer(String strId, String strName, String strIntroduced, String strDiscontinued) {
		System.out.println(	 "| " + String.format("%1$8s", strId) + 
							" | " + String.format("%1$80s", strName) + 
							" | " + String.format("%1$20s", strIntroduced) + 
							" | " + String.format("%1$20s", strDiscontinued) + " |");		
	}
	
	/**
     * Launch the menu which allows the user to select a computer (with its name) and displays all the information known about this computer
     */ 
	private void launchMenuShowDetailComputer() {
		System.out.println("\n\nPlease enter the name of a computer : ");
		String name = scanner.nextLine();

		List<Computer> listComputersFound = computerService.getListComputersByName(name);
		if (listComputersFound == null || listComputersFound.isEmpty()) {
			System.out.println("The computer '" + name + "' is not found.");
		}
		else {
			this.displayTableComputers(listComputersFound);
		}
	}
	
	
	/**
     * Return a date with the format "d/MM/yyyy" (or null if the user enter "?")
     * 
     * @param message String the text to be display until the user enter a date with the expected format (or "?")
     * 
     * @return a LocalDate object or null if the user enter "?"
     */ 
	private LocalDate getDate(String message) {
		LocalDate date = null;
		
		boolean dateFormatIsOk = false;
		while (!dateFormatIsOk) {
			System.out.println(message);
			
			String strDate = scanner.nextLine();
			try {
				date = LocalDate.parse(strDate, DateTimeFormatter.ofPattern("d/MM/yyyy"));
				dateFormatIsOk = true;
			}
			catch (Exception e) {
				if (!strDate.equals("?")) {
					System.out.println("invalid date format.");
				}
				else
					dateFormatIsOk = true;					
			}
		}

		return date;
	}
	
	/**
     * Ask the user to enter a number and return it
     * 
     * @param message String the text to be display until the user enter a number
     * 
     * @return a long value
     */ 
	private long getNumberFromUser(String message) {
		long num = 0;
		
		boolean numIsOk = false;
		while (!numIsOk) {
			System.out.println(message);
			
			String strNum = scanner.nextLine();
			try {
				num = Integer.parseInt(strNum);
				numIsOk = true;
			}
			catch (Exception e) {
				System.out.println("Please enter a number.");
			}
		}

		return num;
	}
	
	
	/**
     * Launch the menu which allows the user to create a new computer
     */ 
	private void launchMenuCreateComputer() {
		// Choose name 
		System.out.println("\nName : ");
		String newComputerName = scanner.nextLine();

		// Choose dates : 
		LocalDate dateIntroduced = this.getDate("\n(Expected format = 'DD/MM/YYYY'    or    '?' if unknown)\nDate when introduced : ");
		LocalDate dateDiscontinued = this.getDate("\n(Expected format = 'DD/MM/YYYY'    or    '?' if unknown)\nDate when discontinued : ");

		// TODO : Choose company
		Company companyNewComputer = null;

		computerService.CreateNewComputer(newComputerName, dateIntroduced, dateDiscontinued, companyNewComputer);		
	}
	
	/**
     * Launch the menu which allows the user to choose a computer
	 * 
	 * @return {@link Computer}
	 */
	private Computer launchMenuChooseComputer() {
		// Choose name 
		System.out.println("\nEnter the name of the computer : ");
		String name = scanner.nextLine();
		
		Computer computer = null;
		List<Computer> listComputersFound = computerService.getListComputersByName(name);
		
		if (listComputersFound == null || listComputersFound.isEmpty()) {
			System.out.println("No computer found with this name.");			
		}
		else if (listComputersFound.size() == 1) {
			computer = listComputersFound.get(0);
		}
		else {
			this.displayTableComputers(listComputersFound);
			System.out.println("Multiple computer have the same name, please enter the id of the computer : ");
			String strID = scanner.nextLine();
			
			for (Computer c : listComputersFound) {
				if (c.getId().toString().equals(strID)) {
					computer = c;
					break;
				}
			}
		}
		
		return computer;
	}
	
	/**
     * Launch the menu which allows the user to delete a computer
     */ 
	private void launchMenuDeleteComputer() {
		Computer computerToBeDeleted = this.launchMenuChooseComputer();
		if (computerToBeDeleted != null)
			computerService.DeleteComputer(computerToBeDeleted);
	}
	
	/**
     * Launch the menu which allows the user to update a computer
     */ 
	private void launchMenuUpdateComputer() {
		Computer computerToBeUpdate = this.launchMenuChooseComputer();
		if (computerToBeUpdate == null)
			return ;

		String field = "";
		boolean fieldIsOK = false;
		while (!fieldIsOK) {
			System.out.println("What field do you want to update ('name', 'introduced', 'discontinued', 'company_id')");
			field = scanner.nextLine();
			
			if (field.equals("id"))
				System.out.println("You can not update this field.");
			else if (field.equals("name")  || field.equals("introduced")  || field.equals("discontinued")  || field.equals("company_id"))
				fieldIsOK = true;
			else
				System.out.println("This field do not exist.");
		}
		
		if (field.equals("name")) {
			System.out.println("Enter the new name : ");
			String newName = scanner.nextLine();
			computerToBeUpdate.setName(newName);
		}
		else if (field.equals("introduced")){
			LocalDate dateIntroduced = this.getDate("Enter the new date : ");
			computerToBeUpdate.setIntroduced(dateIntroduced);
		}
		else if (field.equals("introduced")){
			LocalDate dateIntroduced = this.getDate("Enter the new date : ");
			computerToBeUpdate.setIntroduced(dateIntroduced);
		}
		else if (field.equals("company_id")) {
			this.displayAllCompanies();
			long newCompanyId = this.getNumberFromUser("Enter the new company_id : ");// TODO: check that the companies exist
			computerToBeUpdate.setCompany_id(newCompanyId);
		}	
		
		if (computerToBeUpdate != null)
			computerService.UpdateComputer(computerToBeUpdate, field);
	}
	
	/**
     * Launch the main menu.
     * Allow the user to : 
     *  - Display the list of Companies
     *  - Display the list of Computers
     */ 
	public void launchMainMenu() {

		String strChoice;
		do {
			System.out.println("\n\nWhat do you want to do ?");
			System.out.println("\t1) Display the list of Computers");
			System.out.println("\t2) Display the list of Companies");
			System.out.println("\t3) Show computer details");
			System.out.println("\t4) Create a computer");
			System.out.println("\t5) Update a computer");
			System.out.println("\t6) Delete a computer");
			System.out.println("\t0) Quit");
			System.out.print("Please enter a number between 0 and 4 : ");

			strChoice = scanner.nextLine();// TODO : use getNumberFromUser
			
			// TODO :  use enum
			if (strChoice.equals("1")) {
				this.displayAllComputers();
			}
			else if (strChoice.equals("2")) {
				this.displayAllCompanies();
			}
			else if (strChoice.equals("3")) {
				this.launchMenuShowDetailComputer();
			}
			else if (strChoice.equals("4")) {
				this.launchMenuCreateComputer();
			}
			else if (strChoice.equals("5")) {
				this.launchMenuUpdateComputer();
			}
			else if (strChoice.equals("6")) {
				this.launchMenuDeleteComputer();
			}
		} while (!strChoice.equals("0"));
	}

	/**
     * Entry point of the application.
     * 
     * @param args String[] not used
     */
	public static void main(String[] args) {
    	System.out.println("\nHELLO");
    	
    	CommandLineInterface CLI = new CommandLineInterface();
    	CLI.launchMainMenu();

    	System.out.println("\nGOODBYE");
	}
}
