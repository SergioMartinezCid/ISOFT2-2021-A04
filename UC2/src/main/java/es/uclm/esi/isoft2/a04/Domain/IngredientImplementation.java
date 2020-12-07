import General.Domain.*;

public class IngredientImplementation implements Ingredient {
	
	private static IngredientDAO inegredientDAO;

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

}