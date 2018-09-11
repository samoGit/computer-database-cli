package com.excilys.cdb.ui;

import java.util.List;
import java.util.Scanner;

import com.excilys.cdb.dao.DaoJDBC;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

/**
 * Display information in the terminal and manages interactions with the user.
 * 
 * @author samy
 */
public class CommandLineInterface {
    /**
     * Manage interaction with the BDD.
     */
	private DaoJDBC daoJDBC;
	private Scanner scanner;

    /**
     * Constructor, init daoJDBC .
     */
	public CommandLineInterface() {
    	daoJDBC = DaoJDBC.GetInstance();
    	scanner = new Scanner(System.in);
	}

    /**
     * Display info about all companies.
     */
	public void displayAllCompanies() {
    	List<Company> listCompanies = daoJDBC.getListCompanies();

    	System.out.println("\nList of companies : ");
    	for (Company c : listCompanies) {
    		System.out.println(" - " + c.getId() + " / " + c.getName());
    	}
	}

	/**
     * Display info about all computers.
     */
	public void displayAllComputers() {
    	List<Computer> listComputers = daoJDBC.getListComputers();

    	System.out.println("\nList of computers : ");
    	for (Computer c : listComputers) {
    		System.out.println(" - " + c.getName() + " / " + c.getIntroduced() + " / " + c.getDiscontinued());
    	}
	}

	/**
     * Display the main menu.
     * Allow the user to : 
     *  - Display the list of Companies
     *  - Display the list of Computers
     */ 
	public void displayMenu() {
		
		boolean stop = false;
		while (!stop) {
			System.out.println("\n\n\tWhat do you want to do ?");
			System.out.println("1) Display the list of Companies");
			System.out.println("2) Display the list of Computers");
			System.out.println("3) Quit");
			System.out.print("Please enter a number between 1 and 3 : ");

			String strChoice = scanner.nextLine();
			if (strChoice.equals("1")) {
				this.displayAllCompanies();
			}
			else if (strChoice.equals("2")) {
				this.displayAllComputers();
			}
			else if (strChoice.equals("3")) {
				stop = true;
			}
		}
	}

	/**
     * Entry point of the application.
     * 
     * @param args String[] not used
     */
	public static void main(String[] args) {
    	System.out.println("\nHELLO");
    	
    	CommandLineInterface CLI = new CommandLineInterface();
    	CLI.displayMenu();

    	System.out.println("\nGOODBYE");
	}
}