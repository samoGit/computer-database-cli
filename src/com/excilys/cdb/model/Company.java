package com.excilys.cdb.model;

/**
 * Contain data about a given company.
 * 
 * @author samy
 */
public class Company {
	// Attributes : 
	private Long id;
	private String name;

	// Constructors : 
	public Company() {
	}
	public Company(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	// Getters / Setters : 
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
	
	// Generated (hashCode, equals and toString) : 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Company other = (Company) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Company [getId()=" + getId() + ", getName()=" + getName() + "]";
	}
}

