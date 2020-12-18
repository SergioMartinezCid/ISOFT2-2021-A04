package es.uclm.esi.isoft2.a04.Domain;

import es.uclm.esi.isoft2.a04.Persistance.IngredientDAO;

/**
 * Ingredient interface implementation
 *
 * @version 0.0.1
 */
public class IngredientImplementation implements Ingredient {
	
	private static IngredientDAO ingredientDAO;

	private int id;
	private float amount;
	private String name;

	public int update() {
		try {
			return ingredientDAO.updateIngredient(this);
		}
		catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
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

	@Override
	public void readAll() {
		try {
			ingredientDAO.readAlllIngredients();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int read() {
		try {
			ingredientDAO.readIngredient(this);
		}
		catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

}