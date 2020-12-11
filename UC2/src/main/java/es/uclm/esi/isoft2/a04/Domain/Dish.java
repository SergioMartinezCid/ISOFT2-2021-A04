package Domain;

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