package es.uclm.esi.isoft2.a04.General.Domain;

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

}