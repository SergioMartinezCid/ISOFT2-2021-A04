package es.uclm.esi.isoft2.a04.Domain;

import java.sql.SQLException;

/**
 * @version 0.1.0
 *
 */
public interface Ingredient {

	/**
	 * @return The id of the ingredient
	 */
	int getID();

	/**
	 * @return Get the amount of this ingredient in the warehouse
	 */
	float getAmount();

	/**
	 * 
	 * @param amount Set the amount of this ingredient in the warehouse
	 */
	void setAmount(float amount);

	/**
	 * @return The name of the ingredient
	 */
	String getName();

	/**
	 * 
	 * @param name The name of the ingredient
	 */
	void setName(String name);

	/**
	 * @return Read all the ingredients from the database
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	Ingredient[] readAll() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;

	/**
	 * @return The number of lines modified
	 */
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