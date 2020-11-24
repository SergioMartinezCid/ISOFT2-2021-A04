import General.Domain.*;

public class OrderImplementation implements Order {

	private int state;
	private static int OPEN = 0;
	private static int CLOSED = 1;
	private static int PAYED = 2;
	private Date datetime;

	/**
	 * 
	 * @param id
	 * @param waiter
	 * @param table
	 */
	public OrderImplementation(int id, Waiter waiter, Table table) {
		// TODO - implement OrderImplementation.OrderImplementation
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param state
	 */
	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return this.state;
	}

	public static OrderImplementation[] readAll() {
		// TODO - implement OrderImplementation.readAll
		throw new UnsupportedOperationException();
	}

	public void read() {
		// TODO - implement OrderImplementation.read
		throw new UnsupportedOperationException();
	}

	public int create() {
		// TODO - implement OrderImplementation.create
		throw new UnsupportedOperationException();
	}

	public int update() {
		// TODO - implement OrderImplementation.update
		throw new UnsupportedOperationException();
	}

}