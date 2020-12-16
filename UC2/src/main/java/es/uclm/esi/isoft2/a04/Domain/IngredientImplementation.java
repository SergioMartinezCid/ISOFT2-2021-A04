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
	public void readAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int read() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int create() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete() {
		// TODO Auto-generated method stub
		return 0;
	}

}