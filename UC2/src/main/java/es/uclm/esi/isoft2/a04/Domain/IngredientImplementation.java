package es.uclm.esi.isoft2.a04.Domain;

import es.uclm.esi.isoft2.a04.Domain.*;
import es.uclm.esi.isoft2.a04.Persistance.IngredientDAO;

public class IngredientImplementation implements Ingredient {
	
	private static IngredientDAO ingredientDAO;

	private int id;
	private float amount;
	private String name;

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

	@Override
	public void readAllIngredients() {
		IngredientDAO.readAll();
	}

	@Override
	public int read() {
		IngredientDAO.readIngredient(this);
		return 0;
	}

	@Override
	public int create() {
		return IngredientDAO.createIngredient(this);
	}

	@Override
	public int delete() {
		return IngredientDAO.deleteIngredient(this);
	}

}