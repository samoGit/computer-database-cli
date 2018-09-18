package com.excilys.cdb.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

/**
 * Static class which convert a {@link ResultSet} into an object
 * {@link Computer}.
 * 
 * @author samy
 *
 */
public class ComputerMapper {

	/**
	 * This class only provides static methods and should not be instantiated
	 */
	private ComputerMapper() {
	}

	/**
	 * Convert a {@link ResultSet} into an object {@link Computer}.
	 * 
	 * @param resultSet {@link ResultSet}
	 * @return {@link Computer}
	 * @throws SQLException if the columnLabel is not valid; if a database access
	 *                      error occurs or this method is called on a closed result
	 *                      set
	 */
	public static Computer getComputer(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		String name = resultSet.getString("name");

		Date dateIntroduced = resultSet.getDate("introduced");
		LocalDate localDateintroduced = dateIntroduced != null ? dateIntroduced.toLocalDate() : null;

		Date dateDiscontinued = resultSet.getDate("discontinued");
		LocalDate localDateDiscontinued = dateDiscontinued != null ? dateDiscontinued.toLocalDate() : null;

		Long company_id = resultSet.getLong("company_id");
		Company company = null;

		return new Computer(id, name, Optional.ofNullable(localDateintroduced), Optional.ofNullable(localDateDiscontinued), Optional.ofNullable(company));
	}

}
