package es.uclm.esi.isoft2.a04.Domain;

import java.sql.SQLException;

import es.uclm.esi.isoft2.a04.Persistance.IngredientDAO;

/**
 * @version 0.1.0
 *
 */
public class IngredientImplementation implements Ingredient {

	private int id;
	private float amount;
	private float quantityRequired;
	private String name;

	private IngredientDAO ingredientDAO;
	private Dish dish;

	/**
	 * 
	 */
	public IngredientImplementation() {
		this.ingredientDAO = new IngredientDAO();
	}

	/**
	 * @param id The id of the ingredient in the database
	 */
	public IngredientImplementation(int id) {
		this();
		setID(id);
	}

	public IngredientImplementation(int id, Dish dish) {
		this(id);
		this.dish = dish;
	}

	@Override
	public int getID() {
		return this.id;
	}

	public void setID(int id) {
		this.id = id;
	}

	@Override
	public float getAmount() {
		return this.amount;
	}

	@Override
	public void setAmount(float amount) {
		this.amount = amount;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return The quantity required of this ingredient for a dish
	 */
	public float getQuantityRequired() {
		return this.quantityRequired;
	}

	/**
	 * @param quantityRequired The quantity required of this ingredient for a dish
	 */
	public void setQuantityRequired(float quantityRequired) {
		this.quantityRequired = quantityRequired;
	}

	/**
	 * @return The dish that requires this ingredient
	 */
	public Dish getDish() {
		return dish;
	}

	/**
	 * @param dish The dish that requires this ingredient
	 */
	public void setDish(Dish dish) {
		this.dish = dish;
	}

	@Override
	public Ingredient[] readAll()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return this.ingredientDAO.readAllIngredients();
	}

	@Override
	public void read() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		this.ingredientDAO.readIngredient(this);
	}

	@Override
	public int create() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return this.ingredientDAO.createIngredient(this);
	}

	@Override
	public int update() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return this.ingredientDAO.updateIngredient(this);
	}

	@Override
	public int delete() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return this.ingredientDAO.deleteIngredient(this);
	}
}