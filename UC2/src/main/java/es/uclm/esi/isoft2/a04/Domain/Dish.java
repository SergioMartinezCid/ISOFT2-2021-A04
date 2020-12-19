package es.uclm.esi.isoft2.a04.Domain;

/**
 * Dish type of Food class
 *
 * @version 0.0.1
 */
public class Dish extends FoodImplementation {

	/**
	 * 
	 * @param type
	 */
	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return this.type;
	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param type
	 */
	public Dish(int id, String name, int type) {
		super(id, name);
		setType(type);
	}

}