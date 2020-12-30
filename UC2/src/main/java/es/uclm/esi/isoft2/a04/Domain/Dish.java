package es.uclm.esi.isoft2.a04.Domain;

import java.sql.SQLException;
import java.text.ParseException;

import es.uclm.esi.isoft2.a04.Persistance.DishDAO;

/**
 * @version 0.1.0
 *
 */
public class Dish extends FoodImplementation {

	private int type;
	private IngredientImplementation[] ingredients;

	private DishDAO dishDAO;

	/**
	 * 
	 */
	public Dish() {
		this.dishDAO = new DishDAO();
	}

	/**
	 * @param id The id of the dish in the database
	 */
	public Dish(int id) {
		super(id);
		this.dishDAO = new DishDAO();
	}

	/**
	 * @param id    The id of the dish in the database
	 * @param order The order in the database that has this dish
	 */
	public Dish(int id, OrderImplementation order) {
		this(id);
		this.setOrder(order);
	}

	@Override
	public void setType(int type) throws InvalidTypeException {
		if (type < Food.DRINK || type > Food.DESSERT)
			throw new InvalidTypeException();
		this.type = type;
	}

	@Override
	public int getType() {
		return this.type;
	}

	@Override
	public int getMaximumAvailable() {
		int maximum = -1, auxAvailable;
		if (getIngredients().length > 0) {
			maximum = (int) Math.floor(this.ingredients[0].getAmount() / this.ingredients[0].getQuantityRequired());
			for (int i = 1; i < getIngredients().length && maximum > 0; i++) {
				auxAvailable = (int) Math
						.floor(this.ingredients[i].getAmount() / this.ingredients[i].getQuantityRequired());
				maximum = auxAvailable < maximum ? auxAvailable : maximum;
			}
		}
		return maximum;
	}

	@Override
	public Ingredient[] getIngredients() {
		return this.ingredients;
	}

	@Override
	public void setIngredients(Ingredient[] ingredients) {
		IngredientImplementation[] aux = new IngredientImplementation[ingredients.length];
		for(int i=0; i<aux.length ;i++) {
			aux[i] = (IngredientImplementation) ingredients[i];
		}
		this.ingredients = aux;
	}

	@Override
	public Food[] readAll() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException,
			InvalidTypeException, ParseException {
		return this.dishDAO.readAllDishes();
	}

	@Override
	public void read() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException,
			InvalidTypeException, ParseException {
		this.dishDAO.readDish(this);
	}

	@Override
	public int create() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return this.dishDAO.createDish(this);
	}

	@Override
	public int update() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return this.dishDAO.updateDish(this);
	}

	@Override
	public int delete() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return this.dishDAO.deleteDish(this);
	}
}