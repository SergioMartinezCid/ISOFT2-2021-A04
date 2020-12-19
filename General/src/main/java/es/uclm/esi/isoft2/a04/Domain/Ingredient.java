package es.uclm.esi.isoft2.a04.Domain;

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
	 */
	Ingredient[] readAll();

	/**
	 * @return The number of lines modified
	 */
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