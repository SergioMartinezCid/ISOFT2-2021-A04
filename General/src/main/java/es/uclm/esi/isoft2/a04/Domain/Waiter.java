package es.uclm.esi.isoft2.a04.Domain;

import java.sql.SQLException;
import java.text.ParseException;

/**
 * @version 0.2.0
 *
 */
public interface Waiter extends Subject {

	/**
	 * @return The id of the waiter
	 */
	int getID();

	/**
	 * @return All the waiters from the database
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws NumberFormatException
	 * @throws ParseException
	 */
	Waiter[] readAll() throws NumberFormatException, InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException, ParseException;

	/**
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ParseException
	 * @throws NumberFormatException
	 * 
	 */
	void read() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException,
			NumberFormatException, ParseException;

	/**
	 * @return Number of modified lines in the database
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	int create() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;

	/**
	 * @return Number of modified lines in the database
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	int update() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;

	/**
	 * @return Number of modified lines in the database
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	int delete() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;
}