/**
 * 
 */
package com.excilys.cdb.ui;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

/**
 * @author excilys
 *
 */
class TestCommandLineInterface {

	/**
	 * Test method for {@link com.excilys.cdb.ui.CommandLineInterface#displayAllCompanies()}.
	 */
	@Test
	void testDisplayAllCompanies() {
		ByteArrayOutputStream strDisplayedInSysout = new ByteArrayOutputStream();
		System.setOut(new PrintStream(strDisplayedInSysout));	
		
		CommandLineInterface cli = new CommandLineInterface();
		cli.displayAllCompanies();
		
		System.err.println(">>> testDisplayAllCompanies >>>");
		System.err.println(strDisplayedInSysout.toString());
		System.err.println("<<< testDisplayAllCompanies <<<\n");

		String expected = "\n" + 
				"List of companies : \n" + 
				"/---------------------------------------------------------------\\\n" + 
				"|       id |                                               name |\n" + 
				"|---------------------------------------------------------------|\n" + 
				"|        1 |                                         Apple Inc. |\n" + 
				"|        2 |                                  Thinking Machines |\n" + 
				"|        3 |                                                RCA |\n" + 
				"|        4 |                                          Netronics |\n" + 
				"|        5 |                                  Tandy Corporation |\n" + 
				"|        6 |                            Commodore International |\n" + 
				"|        7 |                                     MOS Technology |\n" + 
				"|        8 |        Micro Instrumentation and Telemetry Systems |\n" + 
				"|        9 |                               IMS Associates, Inc. |\n" + 
				"|       10 |                      Digital Equipment Corporation |\n" + 
				"|       11 |                                 Lincoln Laboratory |\n" + 
				"|       12 |             Moore School of Electrical Engineering |\n" + 
				"|       13 |                                                IBM |\n" + 
				"|       14 |                                  Amiga Corporation |\n" + 
				"|       15 |                                              Canon |\n" + 
				"|       16 |                                              Nokia |\n" + 
				"|       17 |                                               Sony |\n" + 
				"|       18 |                                                OQO |\n" + 
				"|       19 |                                               NeXT |\n" + 
				"|       20 |                                              Atari |\n" + 
				"|       22 |                                     Acorn computer |\n" + 
				"|       23 |                                     Timex Sinclair |\n" + 
				"|       24 |                                           Nintendo |\n" + 
				"|       25 |                              Sinclair Research Ltd |\n" + 
				"|       26 |                                              Xerox |\n" + 
				"|       27 |                                    Hewlett-Packard |\n" + 
				"|       28 |                                             Zemmix |\n" + 
				"|       29 |                                               ACVS |\n" + 
				"|       30 |                                              Sanyo |\n" + 
				"|       31 |                                               Cray |\n" + 
				"|       32 |                                 Evans & Sutherland |\n" + 
				"|       33 |                                        E.S.R. Inc. |\n" + 
				"|       34 |                                              OMRON |\n" + 
				"|       35 |                                   BBN Technologies |\n" + 
				"|       36 |                                       Lenovo Group |\n" + 
				"|       37 |                                               ASUS |\n" + 
				"|       38 |                                            Amstrad |\n" + 
				"|       39 |                                   Sun Microsystems |\n" + 
				"|       40 |                                  Texas Instruments |\n" + 
				"|       41 |                                    HTC Corporation |\n" + 
				"|       42 |                                 Research In Motion |\n" + 
				"|       43 |                                Samsung Electronics |\n" + 
				"\\---------------------------------------------------------------/\n";
		assertEquals(strDisplayedInSysout.toString(), expected);
	}

	/**
	 * Test method for {@link com.excilys.cdb.ui.CommandLineInterface#displayAllComputers()}.
	 */
	@Test
	void testDisplayAllComputers() {
		ByteArrayOutputStream strDisplayedInSysout = new ByteArrayOutputStream();
		System.setOut(new PrintStream(strDisplayedInSysout));	

		ByteArrayInputStream in = new ByteArrayInputStream("3".getBytes());
		System.setIn(in);
		
		CommandLineInterface cli = new CommandLineInterface();
		cli.displayAllComputers();
		
		System.err.println(">>> testDisplayAllComputers >>>");
		
		String strDisplayed = strDisplayedInSysout.toString();

		System.err.println(strDisplayed );
		System.err.println("<<< testDisplayAllComputers <<<\n");

		String expected = "/--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\\\n" + 
				"|       id |                                                                   name |      date introduced |    date discontinued |                                            company |\n" + 
				"|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|\n" + 
				"|      559 |                                                                DECmate |                    ? |                    ? |                      Digital Equipment Corporation |\n" + 
				"|      560 |                                                              DECsystem |                    ? |                    ? |                      Digital Equipment Corporation |\n" + 
				"|      561 |                                                           NetApp Filer |                    ? |                    ? |                                                  ? |\n" + 
				"|      562 |                                                               DEC GT40 |                    ? |                    ? |                      Digital Equipment Corporation |\n" + 
				"|      563 |                                                                 ecoATM |                    ? |                    ? |                                                  ? |\n" + 
				"|      564 |                                   MindWave BrainCubed Education Bundle |                    ? |                    ? |                                                  ? |\n" + 
				"|      565 |                                                              PalmPilot |                    ? |                    ? |                                                  ? |\n" + 
				"|      567 |                                    Dell Inspiron 560 Desktop Computer  |                    ? |                    ? |                                                  ? |\n" + 
				"|      568 |                                                                 IPad 2 |                    ? |                    ? |                                         Apple Inc. |\n" + 
				"|      569 |                                                            HP TouchPad |           2011-02-09 |                    ? |                                    Hewlett-Packard |\n" + 
				"|      570 |                                                                HP Veer |           2011-02-09 |                    ? |                                    Hewlett-Packard |\n" + 
				"|      571 |                                                Lenovo Thinkpad Edge 11 |                    ? |                    ? |                                       Lenovo Group |\n" + 
				"|      572 |                                                            Dell Vostro |                    ? |                    ? |                                                  ? |\n" + 
				"|      573 |                                                        Gateway LT3103U |           2008-01-01 |                    ? |                                                  ? |\n" + 
				"|      580 |                                                                      1 |           1975-01-01 |           1975-01-01 |                                                  ? |\n" + 
				"|      583 |                                                                      1 |           2001-01-01 |           2002-01-01 |                                                  ? |\n" + 
				"|      588 |                                                                     44 |                    ? |                    ? |                                                  ? |\n" + 
				"|      592 |                                                                      a |           2003-03-03 |           2004-04-04 |                                                  ? |\n" + 
				"|      666 |                                                                    ZZe |           2003-02-01 |           2020-05-04 |                                  Texas Instruments |\n" + 
				"|      690 |                                                                     AA |                    ? |                    ? |                                                  ? |\n" + 
				"\\--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------/\n" + 
				"\n\n" + 
				"What do you want to do ?\n" + 
				"	1) Display the next page\n" + 
				"	2) Display the previous page\n" + 
				"	3) Go back to the main menu\n" + 
				"Please enter a number between 1 and 3 : ";
		assertEquals(strDisplayed, expected);
	}

	/**
	 * Test method for {@link com.excilys.cdb.ui.CommandLineInterface#launchMenuShowDetailComputer()}.
	 */
	@Test
	void testLaunchMenuShowDetailComputer() {
		ByteArrayOutputStream strDisplayedInSysout = new ByteArrayOutputStream();
		System.setOut(new PrintStream(strDisplayedInSysout));	
		
		ByteArrayInputStream in = new ByteArrayInputStream("Lenovo Thinkpad Edge 11".getBytes());
		System.setIn(in);

		CommandLineInterface cli = new CommandLineInterface();
		cli.launchMenuShowDetailComputer();
		
		System.err.println(">>> testLaunchMenuShowDetailComputer >>>");
		System.err.println(strDisplayedInSysout.toString());
		System.err.println("<<< testLaunchMenuShowDetailComputer <<<\n");

		String expected = "\n" + 
				"\n" + 
				"Please enter the name of a computer : \n" + 
				"/--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\\\n" + 
				"|       id |                                                                   name |      date introduced |    date discontinued |                                            company |\n" + 
				"|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|\n" + 
				"|      571 |                                                Lenovo Thinkpad Edge 11 |                    ? |                    ? |                                       Lenovo Group |\n" + 
				"\\--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------/\n";
		assertEquals(strDisplayedInSysout.toString(), expected);
	}

	/**
	 * Test that we can open and close the menu displayed by the method {@link com.excilys.cdb.ui.CommandLineInterface#launchMainMenu()}.
	 */
	@Test
	void testLaunchMainMenuAndCloseIt() {
		ByteArrayOutputStream strDisplayedInSysout = new ByteArrayOutputStream();
		System.setOut(new PrintStream(strDisplayedInSysout));	
		
		ByteArrayInputStream in = new ByteArrayInputStream("0".getBytes());
		System.setIn(in);
		
		CommandLineInterface cli = new CommandLineInterface();
		cli.launchMainMenu();
		
		System.err.println(">>> testLaunchMainMenuAndCloseIt >>>");
		System.err.println(strDisplayedInSysout.toString());
		System.err.println("<<< testLaunchMainMenuAndCloseIt <<<\n");

		String expected = "Load config.properties file.\n" + 
				"Init Driver.\n" + 
				"\n" + 
				"\n" + 
				"What do you want to do ?\n" + 
				"	1) Display the list of Computers\n" + 
				"	2) Display the list of Companies\n" + 
				"	3) Show computer details\n" + 
				"	4) Create a computer\n" + 
				"	5) Update a computer\n" + 
				"	6) Delete a computer\n" + 
				"	0) Quit\n" + 
				"Please enter a number between 0 and 6 : ";
		assertEquals(strDisplayedInSysout.toString(), expected);
		
	}
}
