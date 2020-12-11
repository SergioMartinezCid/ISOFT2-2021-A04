package Domain;

import General.Domain.*;
import Persistance.FoodDAO;

public abstract class FoodImplementation implements Food {

	private static FoodDAO foodDAO;

	protected int type;
	private int id;
	private String name;
	private boolean ready;
	private float cost;
	private Ingredient[] ingredients;

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

	public boolean isAvailable() {
		// TODO - this method is a stub, replace with better implementation
		throw new UnsupportedOperationException();
		/*for (Ingredient i : this.ingredients) {
			if (i.getAmount() <= 0.0)
				return false;
		}
		return true;*/
	}

	public int getID() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
	public Ingredient[] getIngredients() {
		return this.ingredients;
	}

	public void setIngredients(Ingredient[] ingredients) {
		this.ingredients = ingredients;
	}

	public float getCost() {
		return this.cost;
	}
	
	public void setCost(float cost) {
		this.cost = cost;
	}

}