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
		// TODO - implement
		throw new UnsupportedOperationException();
	}

	public float getAmount() {
		// TODO - implement
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param amount
	 */
	public void setAmount(float amount) {
		// TODO - implement
		throw new UnsupportedOperationException();
	}

	public String getName() {
		// TODO - implement
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		// TODO - implement
		throw new UnsupportedOperationException();
	}

}