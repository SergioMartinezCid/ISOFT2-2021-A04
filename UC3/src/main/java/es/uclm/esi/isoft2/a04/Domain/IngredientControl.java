package es.uclm.esi.isoft2.a04.Domain;

import java.util.Arrays;

/**
 * Ingredient Control implementation to allow managing of ingredients in the database
 * @version 0.0.1
 */
public class IngredientControl implements Subject {
	
	private Ingredient[] ingredients;

	/**
	 * Get Ingredient at given index
	 * @param id database array index
	 */
	public Ingredient getIngredient(int id) {
		return this.ingredients[id];
	}

	/**
	 * Retrieves ingredients array
	 * @return array of Ingredient
	 */
	public Ingredient[] getAllIngredients() {
		return ingredients;
	}

	/**
	 * Set new amount on existing ingredient
	 * @param ingredient Ingredient reference to find
	 * @param amount New amount of desired ingredient
	 */
	public void updateIngredientAmount(Ingredient ingredient, float amount) {
		Ingredient i = Arrays.stream(ingredients).filter(x -> x == ingredient).findFirst().get();
		i.setAmount(amount);
	}

	@Override
	public void attach(Observer o) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public void detach(Observer o) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public void notifyMe() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

}