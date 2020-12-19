package es.uclm.esi.isoft2.a04.Domain;

public interface Ingredient {

	int getID();

	float getAmount();

	/**
	 * 
	 * @param amount
	 */
	void setAmount(float amount);

	String getName();

	/**
	 * 
	 * @param name
	 */
	void setName(String name);

	void readAll();

	int read();


	int create();

	int update();

	int delete();
}