package es.uclm.esi.isoft2.a04.Domain;

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
	 */
	void setState(int state);

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
	 */
	Order[] readAll();

	/**
	 * 
	 */
	void read();

	/**
	 * @return Number of modified lines in the database
	 */
	int create();

	/**
	 * @return Number of modified lines in the database
	 */
	int update();

	/**
	 * @return Number of modified lines in the database
	 */
	int delete();
}