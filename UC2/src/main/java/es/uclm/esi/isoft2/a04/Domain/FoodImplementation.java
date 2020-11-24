import General.Domain.*;

public abstract class FoodImplementation implements Food {

	private int id;
	private string name;
	private int ready;

	/**
	 * 
	 * @param id
	 * @param name
	 */
	public FoodImplementation(int id, string name) {
		// TODO - implement FoodImplementation.FoodImplementation
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param type
	 */
	public abstract void setType(int type);

	public abstract int getType();

	public static FoodImplementation[] readAll() {
		// TODO - implement FoodImplementation.readAll
		throw new UnsupportedOperationException();
	}

	public void read() {
		// TODO - implement FoodImplementation.read
		throw new UnsupportedOperationException();
	}

	public boolean isReady() {
		// TODO - implement FoodImplementation.isReady
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param ready
	 */
	public void setReady(boolean ready) {
		// TODO - implement FoodImplementation.setReady
		throw new UnsupportedOperationException();
	}

}