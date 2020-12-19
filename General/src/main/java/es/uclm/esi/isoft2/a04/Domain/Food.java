package es.uclm.esi.isoft2.a04.Domain;

public interface Food {

	static int DRINK = 0;
	static int STARTER = 1;
	static int FIRST_COURSE = 2;
	static int SECOND_COURSE = 3;
	static int DESSERT = 4;

	/**
	 * 
	 * @param type
	 */
	void setType(int type);

	int getType();

	boolean isReady();

	void setReady(boolean ready);

	boolean isAvaible();

	int getID();

	/**
	 * 
	 * @param name
	 */
	void setName(String name);

	String getName();

	Ingredient[] getIngredients();

	/**
	 * 
	 * @param ingredients
	 */
	void setIngredients(Ingredient[] ingredients);

	/**
	 * 
	 * @param cost
	 */
	void setCost(float cost);

	float getCost();
	
	void readAll();
	
	int read();
	
	int create();
	
	int update();
	
	int delete();

}