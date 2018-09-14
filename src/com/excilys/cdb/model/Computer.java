package com.excilys.cdb.model;

import java.time.LocalDate;

/**
 * Contain data about a given computer.
 * 
 * @author samy
 */
public class Computer {
	// Attributes :
	private Long id;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	private Long company_id;

	// Constructors :
	/**
	 * Default constructor (should not be used "manually").
	 */
	public Computer() {
	}

	/**
	 * Constructor, only id and name are mandatory (introduced, discontinued and
	 * company_id can be null.
	 * 
	 * @param id           Long
	 * @param name         String
	 * @param introduced   LocalDate
	 * @param discontinued LocalDate
	 * @param company_id   Long
	 */
	public Computer(Long id, String name, LocalDate introduced, LocalDate discontinued, Long company_id) {
		/*
		 * TODO : throw exception if : - id == null - name == null (?or == ""?) -
		 * introduced is after discontinued (??? same in setters ???)
		 */
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company_id = company_id;
	}

	// Getters and Setters :
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getIntroduced() {
		return introduced;
	}

	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}

	public LocalDate getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}

	public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}

	public String getStringValue(String field) {
		switch (field) {
		case "id":
			return id.toString();
		case "name":
			return name.toString();
		case "introduced":
			return introduced.toString();
		case "discontinued":
			return discontinued.toString();
		case "company_id":
			return company_id.toString();
		}
		return null;
	}

	// Generated (hashCode, equals and toString) :
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company_id == null) ? 0 : company_id.hashCode());
		result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (company_id == null) {
			if (other.company_id != null)
				return false;
		} else if (!company_id.equals(other.company_id))
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Computer [getName()=" + getName() + ", getIntroduced()=" + getIntroduced() + ", getDiscontinued()="
				+ getDiscontinued() + ", getCompany_id()=" + getCompany_id() + "]";
	}
}
