import General.Domain.*;

public abstract class FoodImplementation implements Food {

	private static FoodDAO foodDAO;

	protected int type;
	private int id;
	private String name;
	private boolean ready;

	/**
	 * 
	 * @param id
	 * @param name
	 */
	public FoodImplementation(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * 
	 * @param type
	 */
	public abstract void setType(int type);

	public abstract int getType();

	public static FoodImplementation[] readAll() {
		return foodDAO.readAllFood();
	}

	public void read() {
		foodDAO.readFood(this);
	}

	public boolean isReady() {
		return this.ready;
	}

	/**
	 * 
	 * @param ready
	 */
	public void setReady(boolean ready) {
		this.ready = ready;
	}

	public boolean isAvaible() {
		// TODO - implement
		throw new UnsupportedOperationException();
	}

	public int getID() {
		// TODO - implement
		throw new UnsupportedOperationException();
	}

	public void setName(String name) {
		// TODO - implement
		throw new UnsupportedOperationException();
	}

	public String getName() {
		// TODO - implement
		throw new UnsupportedOperationException();
	}
	
	public Ingredient[] getIngredients() {
		// TODO - implement
		throw new UnsupportedOperationException();
	}

	public void setIngredients(Ingredient[] ingredients) {
		// TODO - implement
		throw new UnsupportedOperationException();
	}

	public float getCost() {
		// TODO - implement
		throw new UnsupportedOperationException();
	}
	
	public void setCost(float cost) {
		// TODO - implement
		throw new UnsupportedOperationException();
	}

}