package es.uclm.esi.isoft2.a04.Domain;

import es.uclm.esi.isoft2.a04.Persistance.Broker;

import java.util.ArrayList;

/**
 * UI Order controller implementation
 *
 * @version 0.0.1
 */
public class OrderControl implements Subject {

	private ArrayList<Observer> observers = new ArrayList<>();

	/**
	 * 
	 * @param o
	 */
	public void attach(Observer o) {
		observers.add(o);
	}

	/**
	 * 
	 * @param o
	 */
	public void detach(Observer o) {
		observers.remove(o);
	}

	public void notifySubject() {
		// TODO notify all?
		for (Observer o : observers) {
			o.update();
		}
	}

	/**
	 * 
	 * @param id
	 * @param waiter
	 * @param table
	 */
	public OrderImplementation createOrder(int id, Waiter waiter, Table table) {
		// TODO use database?
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

	@Override
	public void notifyMe() {
		notifySubject();
	}

}