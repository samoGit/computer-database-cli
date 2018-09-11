package com.excilys.cdb.ui;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class CommandLineInterface {

	public static void main(String[] args) {
    	System.out.println("START");

    	try {

        	System.out.println("Init Driver...");
        	Class.forName("com.mysql.jdbc.Driver");
        	System.out.println("Success !");

        	System.out.println("Get connection...");
			String url = "jdbc:mysql://127.0.0.1:3306/computer-database-db";
	    	Connection conn = DriverManager.getConnection(url, "admincdb", "qwerty1234");
        	System.out.println("Success !");

        	System.out.println("Select all company...");
        	String query = "SELECT * FROM company;";
        	ResultSet results;
        	Statement stmt = conn.createStatement();
        	results = stmt.executeQuery(query);
        	System.out.println("Success !");
        	
        	System.out.println("\n\nDisplay all name : ");
        	int n=0;
        	while (results.next()) {
        		String name = results.getString("Name");
        		System.out.println(n++ + ") " + name);
        	}
        	System.out.println("Success !\n\n");

        	System.out.println("Close connection...");
        	conn.close();
        	System.out.println("Success !");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

    	System.out.println("END");
	}
}