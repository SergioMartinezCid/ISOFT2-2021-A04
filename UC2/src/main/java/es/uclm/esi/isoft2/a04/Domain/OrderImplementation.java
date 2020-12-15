package es.uclm.esi.isoft2.a04.Domain;

import es.uclm.esi.isoft2.a04.Domain.*;
import es.uclm.esi.isoft2.a04.Persistance.OrderDAO;
import java.util.Date;

public class OrderImplementation implements Order {

	private static OrderDAO orderDAO;

	private static final int OPEN = 0;
	private static final int CLOSED = 1;
	private static final int PAYED = 2;

	private int state;
	private Date datetime;
	
	private int id;
	private Waiter waiter;
	private Table table;
	
	private Food[] food;

	/**
	 * 
	 * @param id
	 * @param waiter
	 * @param table
	 */
	public OrderImplementation(int id, Waiter waiter, Table table) {
		this.id = id;
		this.waiter = waiter;
		this.table = table;
	}

	public int getID() {
		return this.id;
	}

	public Food[] getFood() {
		return this.food;
	}

	/**
	 * 
	 * @param food
	 */
	public void setFood(Food[] food) {
		this.food = food;
	}

	public float getCost() {
		float cost = 0.0f;
		for (Food f : this.food) {
			cost += f.getCost();
		}
		return cost;
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

	public int create() {
		return orderDAO.createOrder(this);
	}

	public int update() {
		return orderDAO.updateOrder(this);
	}

	@Override
	public void readAll() {
		orderDAO.readAllOrders();
	}

	@Override
	public int read() {
		orderDAO.readOrder(this);;
		return 0;
	}

	@Override
	public int delete() {
		return orderDAO.deleteOrder(this);
	}

}