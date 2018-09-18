package com.excilys.cdb.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
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
     * Constructor, init services.
     */
	public CommandLineInterface() {
		companyService = new CompanyService();
		computerService = new ComputerService();
    	scanner = new Scanner(System.in);
	}

    /**
     * Display info about all companies.
     */
	protected void displayAllCompanies() {
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
	protected void displayAllComputers() {
    	List<Computer> listComputers = computerService.getListComputers();
		if (listComputers.isEmpty()) {
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
    		String strIntroduced = String.valueOf( c.getDateIntroduced().isPresent() ? c.getDateIntroduced().get() : "?" );
    		String strDiscontinued = String.valueOf( c.getDateDiscontinued().isPresent() ? c.getDateDiscontinued().get() : "?");

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
	protected void launchMenuShowDetailComputer() {
		System.out.println("\n\nPlease enter the name of a computer : ");
		String name = scanner.nextLine();
		List<Computer> listComputersFound = computerService.getListComputersByName(name);
		if (listComputersFound.isEmpty()) {
			System.out.println("The computer '" + name + "' is not found.");
		}
		else {
			this.displayTableComputers(listComputersFound);
		}
	}
	
	
	/**
     * Ask the user to enter a date with the format "dd/MM/yyyy" or "?"
     * 
     * @param message String The text to be display until the user enter a date with the expected format (or "?")
     * @return A LocalDate object or Optional.empty() if the user enter "?"
     */ 
	private Optional<LocalDate> getDate(String message) {		
		Optional<LocalDate> date = Optional.empty();

		boolean dateFormatIsOk = false;
		while (!dateFormatIsOk) {
			System.out.println(message);
			String strDate = scanner.nextLine();
			try {
				LocalDate localDate = LocalDate.parse(strDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				dateFormatIsOk = true;
				date = Optional.ofNullable(localDate);
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
	protected void launchMenuCreateComputer() {
		System.out.println("\nName : ");
		String newComputerName = scanner.nextLine();
	 	Optional<LocalDate> dateIntroduced = this.getDate("\n(Expected format = 'DD/MM/YYYY'    or    '?' if unknown)\nDate when introduced : ");
	 	Optional<LocalDate> dateDiscontinued = this.getDate("\n(Expected format = 'DD/MM/YYYY'    or    '?' if unknown)\nDate when discontinued : ");
		Company companyNewComputer = null;
		computerService.CreateNewComputer(	newComputerName, 
											dateIntroduced, 
											dateDiscontinued, 
											Optional.ofNullable(companyNewComputer));		
	}
	
	/**
     * Launch the menu which allows the user to choose a computer
	 * 
	 * @return {@link Computer}
	 */
	private Optional<Computer> launchMenuChooseComputer() {
		System.out.println("\nEnter the name of the computer : ");
		String name = scanner.nextLine();
		
		List<Computer> listComputersFound = computerService.getListComputersByName(name);
		
		if (listComputersFound.isEmpty()) {
			System.out.println("No computer found with this name.");			
		}
		else if (listComputersFound.size() == 1) {
			return Optional.ofNullable(listComputersFound.get(0));
		}
		else {
			this.displayTableComputers(listComputersFound);
			System.out.println("Multiple computer have the same name, please enter the id of the computer : ");
			String strID = scanner.nextLine();
			
			for (Computer computer : listComputersFound) {
				if (computer.getId().toString().equals(strID)) {
					return Optional.ofNullable(computer);
				}
			}
		}
		
		return Optional.empty();
	}
	
	/**
     * Launch the menu which allows the user to delete a computer
     */ 
	protected void launchMenuDeleteComputer() {
		Optional<Computer> computerToBeDeleted = this.launchMenuChooseComputer();
		if (computerToBeDeleted.isPresent())
			computerService.DeleteComputer(computerToBeDeleted.get());
	}
	
	/**
     * Launch the menu which allows the user to update a computer
     */ 
	protected void launchMenuUpdateComputer() {
		Optional<Computer> computerToBeUpdate = this.launchMenuChooseComputer();
		if (!computerToBeUpdate.isPresent())
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
			computerToBeUpdate.get().setName(newName);
		}
		else if (field.equals("introduced")){
			Optional<LocalDate> dateIntroduced = this.getDate("Enter the new date : ");
			computerToBeUpdate.get().setDateIntroduced(dateIntroduced);
		}
		else if (field.equals("discontinued")){
			Optional<LocalDate> dateDiscontinued = this.getDate("Enter the new date : ");
			computerToBeUpdate.get().setDateDiscontinued(dateDiscontinued);
		}
		else if (field.equals("company_id")) {
			this.displayAllCompanies();
			long newCompanyId = this.getNumberFromUser("Enter the new company_id : ");
			Company company = null;
			computerToBeUpdate.get().setCompany(Optional.ofNullable(company));
		}

		computerService.UpdateComputer(computerToBeUpdate.get(), field);
	}

	/**
     * Launch the main menu.
     */
	public void launchMainMenu() {
		boolean stop = false;
		while (!stop) {
			System.out.println("\n\nWhat do you want to do ?");
			Integer minValue = Integer.MAX_VALUE;
			Integer maxValue = Integer.MIN_VALUE;
			for (UserChoice userChoice : UserChoice.values()) {
				if (minValue > userChoice.getValue())
					minValue = userChoice.getValue();
				if (maxValue < userChoice.getValue())
					maxValue = userChoice.getValue();

				System.out.println("\t" + userChoice.getValue() + ") " + userChoice.getMessage());				
			}
			System.out.print("Please enter a number between " + minValue + " and " + maxValue + " : ");
			String strChoice = scanner.nextLine();
			
			Optional<UserChoice> userChoice = UserChoice.fromString(strChoice);
			if (userChoice.isPresent()) {
				switch (userChoice.get()) {
				case DISPLAY_COMPUTERS:
					this.displayAllComputers();
					break;
				case DISPLAY_COMPANIES:
					this.displayAllCompanies();
					break;
				case SHOW_COMPUTER_DETAILS:
					this.launchMenuShowDetailComputer();
					break;
				case CREATE_COMPUTER:
					this.launchMenuCreateComputer();
					break;
				case UPDATE_COMPUTER:
					this.launchMenuUpdateComputer();
					break;
				case DELETE_COMPUTER:
					this.launchMenuDeleteComputer();
					break;
				case QUIT:
					stop = true;
					break;
				}
			}
		}
	}

	/**
     * Entry point of the application.
     * 
     * @param args String[] not used
     */
	public static void main(String[] args) {
		System.out.println("Hello !");
    	CommandLineInterface CLI = new CommandLineInterface();
    	CLI.launchMainMenu();
		System.out.println("Goodbye !");
	}
}
