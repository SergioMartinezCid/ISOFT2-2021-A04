package es.uclm.esi.isoft2.a04.Domain;

import es.uclm.esi.isoft2.a04.Persistance.FoodDAO;

/**
 * Food interface implementation
 *
 * @version 0.0.1
 */
public abstract class FoodImplementation implements Food {

	protected static FoodDAO foodDAO;

	protected int type;
	protected int id;
	protected String name;
	protected boolean ready;
	protected float cost;
	protected Ingredient[] ingredients;

	/**
	 * 
	 * @param id
	 * @param name
	 */
	public FoodImplementation(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * 
	 * @param type
	 */
	public abstract void setType(int type);

	public abstract int getType();

	@Override
	public boolean isReady() {
		return this.ready;
	}

	/**
	 * 
	 * @param ready
	 */
	@Override
	public void setReady(boolean ready) {
		this.ready = ready;
	}

	@Override
	public boolean isAvaible() {
		for (Ingredient i : this.ingredients) {
			if (!(i.getAmount() > 0.0f))
				return false;
		}
		return true;
	}

	@Override
	public int getID() {
		return this.id;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public Ingredient[] getIngredients() {
		return this.ingredients;
	}

	@Override
	public void setIngredients(Ingredient[] ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public float getCost() {
		return this.cost;
	}
	
	@Override
	public void setCost(float cost) {
		this.cost = cost;
	}

	@Override
	public void readAll() {
		try {
			foodDAO.readAllFood();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int read() {
		try {
			foodDAO.readFood(this);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int create() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int update() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int delete() {
		throw new UnsupportedOperationException();
	}

}