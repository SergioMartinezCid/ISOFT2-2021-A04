package es.uclm.esi.isoft2.a04.Domain;

/**
 * Beverage type of Food class
 *
 * @version 0.0.1
 */
public class Beverage extends FoodImplementation {

	/**
	 * 
	 * @param type
	 */
	public void setType(int type) {
		// does not allow type change
	}

	public int getType() {
		return this.type;
	}

	/**
	 * 
	 * @param id
	 * @param name
	 */
	public Beverage(int id, String name) {
		super(id, name);
		this.type = 0;
	}

}