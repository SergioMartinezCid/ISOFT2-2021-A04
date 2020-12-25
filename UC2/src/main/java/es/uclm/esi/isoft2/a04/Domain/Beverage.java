package es.uclm.esi.isoft2.a04.Domain;

import java.sql.SQLException;
import java.text.ParseException;

import es.uclm.esi.isoft2.a04.Persistance.BeverageDAO;

/**
 * @version 0.1.0
 *
 */
public class Beverage extends FoodImplementation {

	private float amount;

	private BeverageDAO beverageDAO;

	/**
	 * 
	 */
	public Beverage() {
		this.beverageDAO = new BeverageDAO();
	}

	/**
	 * @param id The id of the beverage in the database
	 */
	public Beverage(int id) {
		super(id);
		this.beverageDAO = new BeverageDAO();
	}

	/**
	 * @param id    The id of the beverage in the database
	 * @param order The order in the database that has this beverage
	 */
	public Beverage(int id, OrderImplementation order) {
		this(id);
		this.setOrder(order);
	}

	@Override
	public void setType(int type) throws InvalidTypeException {
		// Empty method; the type cannot be changed
	}

	@Override
	public int getType() {
		return Food.DRINK;
	}

	@Override
	public int getMaximumAvailable() {
		return (int) getAmount();
	}

	@Override
	public Ingredient[] getIngredients() {
		// Empty method; it has no ingredients
		return null;
	}

	@Override
	public void setIngredients(Ingredient[] ingredients) {
		// Empty method; it has no ingredients
	}

	/**
	 * @return The amount stored in the warehouse of this drink
	 */
	public float getAmount() {
		return amount;
	}

	/**
	 * @param amount The amount stored in the warehouse
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}

	@Override
	public Food[] readAll() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException,
			InvalidTypeException, ParseException {
		return this.beverageDAO.readAllBeverages();
	}

	@Override
	public void read() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException,
			InvalidTypeException, ParseException {
		this.beverageDAO.readBeverage(this);
	}

	@Override
	public int create() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return this.beverageDAO.createBeverage(this);
	}

	@Override
	public int update() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return this.beverageDAO.updateBeverage(this);
	}

	@Override
	public int delete() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return this.beverageDAO.deleteBeverage(this);
	}
}