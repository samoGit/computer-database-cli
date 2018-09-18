package com.excilys.cdb.ui;

import java.util.Optional;

public enum UserChoice {
	DISPLAY_COMPUTERS(1, "Display the list of Computers"),
	DISPLAY_COMPANIES(2, "Display the list of Companies"),
	SHOW_COMPUTER_DETAILS(3, "Show computer details"),
	CREATE_COMPUTER(4, "Create a computer"),
	UPDATE_COMPUTER(5, "Update a computer"),
	DELETE_COMPUTER(6, "Delete a computer"),
	QUIT(0, "Quit");
	
	private final Integer value;
	private final String message;
	
	private UserChoice(Integer value, String message) {
		this.value = value;
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Integer getValue() {
		return value;
	}
	
	public static Optional<UserChoice> fromString(String stringValue) {
		for (UserChoice userChoice : UserChoice.values()) {
			if (userChoice.value.toString().equals(stringValue))
				return Optional.of(userChoice);
		}
		return Optional.empty();
	}
}
