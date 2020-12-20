package es.uclm.esi.isoft2.a04.Domain;

import java.sql.SQLException;

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
	 */
	Waiter[] readAll() throws NumberFormatException, InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException;

	/**
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * 
	 */
	void read() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;

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