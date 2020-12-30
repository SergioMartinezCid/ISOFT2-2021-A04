package es.uclm.esi.isoft2.a04.Domain;

import java.sql.SQLException;
import java.text.ParseException;

/**
 * @version 0.1.0
 *
 */
public interface Food {

	static int DRINK = 0;
	static int STARTER = 1;
	static int FIRST_COURSE = 2;
	static int SECOND_COURSE = 3;
	static int DESSERT = 4;

	/**
	 * @param type the type of the food
	 * @throws InvalidTypeException 
	 */
	void setType(int type) throws InvalidTypeException;

	/**
	 * @return the type of the food
	 */
	int getType();

	/**
	 * @return the number of times this food can be ordered before running out
	 */
	int getMaximumAvailable();

	/**
	 * @return the id of the food
	 */
	int getID();

	/**
	 * @return the quantity of the food
	 */
	int getQuantity();
	/**
	 * @param name the name of the food
	 */
	void setName(String name);

	/**
	 * @return the name of the food
	 */
	String getName();

	/**
	 * @return The ingredients of the food
	 */
	Ingredient[] getIngredients();

	/**
	 * @param ingredients The ingredients of the food
	 */
	void setIngredients(Ingredient[] ingredients);

	/**
	 * @param cost the cost of this food
	 */
	void setCost(float cost);

	/**
	 * @return the cost of this food
	 */
	float getCost();

	/**
	 * @return All the foods in the database
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ParseException
	 * @throws InvalidTypeException
	 */
	Food[] readAll() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, InvalidTypeException, ParseException;

	/**
	 * @throws ParseException 
	 * @throws InvalidTypeException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * 
	 */
	void read() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, InvalidTypeException, ParseException;

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