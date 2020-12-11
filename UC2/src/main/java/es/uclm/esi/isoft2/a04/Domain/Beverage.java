package Domain;

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