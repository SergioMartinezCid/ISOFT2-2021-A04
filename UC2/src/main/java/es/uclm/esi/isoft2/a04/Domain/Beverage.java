package es.uclm.esi.isoft2.a04.Domain;

public class Beverage extends FoodImplementation {

	/**
	 * 
	 * @param type
	 */
	public void setType(int type) {
		// does not allow type change
	}

	public int getType() {
		return this.type;
	}

	/**
	 * 
	 * @param id
	 * @param name
	 */
	public Beverage(int id, String name) {
		super(id, name);
		this.type = 0;
	}

	@Override
	public boolean isAvaible() {
		// TODO Auto-generated method stub
		return false;
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
	public int update() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete() {
		// TODO Auto-generated method stub
		return 0;
	}

}