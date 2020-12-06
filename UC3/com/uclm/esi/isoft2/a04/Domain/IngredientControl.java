import General.Domain.*;

package Domain;

public class IngredientControl {
	
	private Ingredient[] ingredients;

	/**
	 * 
	 * @param id
	 */
	public Ingredient getIngredient(int id) {

		return IngredientImplementation[id];
		
	}

	public Ingredients[] getAllIngredients() {
		// TODO - implement IngredientControl.getAllIngredients
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param ingredient
	 * @param ammount
	 */
	public void updateIngredientAmount(Ingredient ingredient, float ammount) {
		// TODO - implement IngredientControl.updateIngredientAmount
		throw new UnsupportedOperationException();
	}

}