package es.uclm.esi.isoft2.a04.Domain;

/**
 * @version 0.1.0
 *
 */
public interface Beverage extends Food {

	/**
	 * @return The amount stored in the warehouse of this drink
	 */
	float getAmount();

	/**
	 * @param amount The amount stored in the warehouse
	 */
	void setAmount(float amount);
	
}
