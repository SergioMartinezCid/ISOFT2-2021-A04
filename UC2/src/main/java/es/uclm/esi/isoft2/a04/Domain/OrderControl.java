import General.Domain.*;

public class OrderControl implements Subject {

	private static OrderImplementation[] orders;

	/**
	 * 
	 * @param o
	 */
	public void attach(Observer o) {
		// TODO - implement
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param o
	 */
	public void detach(Observer o) {
		// TODO - implement
		throw new UnsupportedOperationException();
	}

	public void notifySubject() {
		// TODO - implement
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 * @param waiter
	 * @param table
	 */
	public OrderImplementation createOrder(int id, Waiter waiter, Table table) {
		return new OrderImplementation(id, waiter, table);
	}

	/**
	 * 
	 * @param order
	 * @param state
	 */
	public void setState(OrderImplementation order, int state) {
		order.setState(state);
	}

	/**
	 * 
	 * @param order
	 * @param food
	 */
	public void setReady(OrderImplementation order, FoodImplementation food) {
		Food[] fs = order.getFood();
		for (Food f : fs) {
			if (food == f)
				((FoodImplementation)f).setReady(food.isReady());
		}
	}

}