import General.Domain.*;

public class IngredientImplementation implements Ingredient {
	
	private static IngredientDAO ingredientDAO;

	private int id;
	private float amount;
	private String name;

	public static IngredientImplementation[] readAll() {
		return ingredientDAO.readAlllIngredients();
	}

	public void read() {
		ingredientDAO.readIngredient(this);
	}

	public int update() {
		return ingredientDAO.updateIngredient(this);
	}

	public int getID() {
		return this.id;
	}

	public float getAmount() {
		return this.amount;
	}

	/**
	 * 
	 * @param amount
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

}