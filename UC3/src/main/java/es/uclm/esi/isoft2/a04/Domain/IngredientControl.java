package es.uclm.esi.isoft2.a04.Domain;

import es.uclm.esi.isoft2.a04.Domain.*;

public class IngredientControl {
	
	private Ingredient[] ingredients;

	/**
	 * 
	 * @param id
	 */
	public Ingredient getIngredient(int id) {

		return this.ingredients[id];
		
	}

	public Ingredient[] getAllIngredients() {
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