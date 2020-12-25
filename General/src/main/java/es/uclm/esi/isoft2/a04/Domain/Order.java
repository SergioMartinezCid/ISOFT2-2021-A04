package es.uclm.esi.isoft2.a04.Domain;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

/**
 * @version 0.1.0
 *
 */
public interface Order {

	/**
	 * @return The id of the order
	 */
	int getID();

	/**
	 * @return The list of food in the order
	 */
	Food[] getFood();

	/**
	 * @param food The list of food of the order
	 */
	void setFood(Food[] food);

	/**
	 * @return The whole cost of the order
	 */
	float getCost();

	/**
	 * @return The current state of the order
	 */
	int getState();

	/**
	 * @param state The new state of the order
	 * @throws InvalidStateException
	 */
	void setState(int state) throws InvalidStateException;

	/**
	 * @return The date of the order
	 */
	Date getDatetime();

	/**
	 * @param datetime The new time of the order
	 */
	void setDatetime(Date datetime);

	/**
	 * @return The waiter attending this order
	 */
	Waiter getWaiter();

	/**
	 * @return The table where the order was taken
	 */
	Table getTable();

	/**
	 * @return All the orders in the database
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvalidStateException
	 * @throws ParseException
	 * @throws InvalidTypeException
	 */
	Order[] readAll() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException,
			ParseException, InvalidStateException, InvalidTypeException;

	/**
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvalidStateException
	 * @throws ParseException
	 * @throws InvalidTypeException
	 * 
	 */
	void read() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException,
			ParseException, InvalidStateException, InvalidTypeException;

	/**
	 * @return Number of modified lines in the database
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws SQLException
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