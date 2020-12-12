package es.uclm.esi.isoft2.a04.Domain;

public interface Order {

	int getID();

	Food[] getFood();

	/**
	 * 
	 * @param food
	 */
	void setFood(Food[] food);

	float getCost();

}