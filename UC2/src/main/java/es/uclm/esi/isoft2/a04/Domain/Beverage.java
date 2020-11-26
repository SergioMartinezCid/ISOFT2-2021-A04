public class Beverage extends FoodImplementation {

	public int getType() {
		return this.type;
	}

	/**
	 * 
	 * @param id
	 * @param name
	 */
	public Beverage(int id, string name) {
		super(id, name);
		this.type = 0;
	}

}