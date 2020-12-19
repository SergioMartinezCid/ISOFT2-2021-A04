package es.uclm.esi.isoft2.a04.Domain;

/**
 * @version 0.1.0
 *
 */
public interface Waiter extends Subject {

	/**
	 * @return The id of the waiter
	 */
	int getID();

	/**
	 * @return All the waiters from the database
	 */
	Waiter[] readAll();

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