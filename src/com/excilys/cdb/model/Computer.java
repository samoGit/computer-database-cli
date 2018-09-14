package com.excilys.cdb.model;

import java.time.LocalDate;

/**
 * Contain data about a given computer.
 * 
 * @author samy
 */
public class Computer {
	private Long id;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	private Long companyId;

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
	 * @param companyId    Long
	 */
	public Computer(Long id, String name, LocalDate introduced, LocalDate discontinued, Long companyId) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
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
		return companyId;
	}

	public void setCompany_id(Long companyId) {
		this.companyId = companyId;
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
			return companyId.toString();
		}
		return null;
	}

	// Generated (hashCode, equals and toString) :
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyId == null) ? 0 : companyId.hashCode());
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
		if (companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!companyId.equals(other.companyId))
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
		return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
				+ ", companyId=" + companyId + "]";
	}
}
