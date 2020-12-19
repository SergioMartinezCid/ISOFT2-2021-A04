package es.uclm.esi.isoft2.a04.Domain;

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
	 */
	void setType(int type);

	/**
	 * @return the type of the food
	 */
	int getType();

	/**
	 * @return true if this food is available
	 */
	boolean isAvaible();

	/**
	 * @return the id of the food
	 */
	int getID();

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
	 * @return whether it is ready from the kitchen
	 */
	boolean isReady();

	/**
	 * @param ready whether it is ready from the kitchen
	 */
	void setReady(boolean ready);

	/**
	 * @return All the foods in the database
	 */
	Food[] readAll();

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