package es.uclm.esi.isoft2.a04.Domain;

import es.uclm.esi.isoft2.a04.Persistance.OrderDAO;

import java.util.Date;

/**
 * Order interface implementation
 *
 * @version 0.0.1
 */
public class OrderImplementation implements Order {

	private static OrderDAO orderDAO;

	public static final int OPEN = 0;
	public static final int CLOSED = 1;
	public static final int PAYED = 2;

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
		this.setWaiter(waiter);
		this.setTable(table);
	}

	/**
	 * @return the datetime
	 */
	@Override
	public Date getDatetime() {
		return datetime;
	}

	/**
	 * @param datetime the datetime to set
	 */
	@Override
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	/**
	 * @return the waiter
	 */
	@Override
	public Waiter getWaiter() {
		return waiter;
	}

	/**
	 * @param waiter the waiter to set
	 */
	private void setWaiter(Waiter waiter) {
		this.waiter = waiter;
	}

	/**
	 * @return the table
	 */
	@Override
	public Table getTable() {
		return table;
	}

	/**
	 * @param table the table to set
	 */
	private void setTable(Table table) {
		this.table = table;
	}

	@Override
	public int getID() {
		return this.id;
	}

	@Override
	public Food[] getFood() {
		return this.food;
	}

	/**
	 * 
	 * @param food
	 */
	@Override
	public void setFood(Food[] food) {
		this.food = food;
	}

	@Override
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
	@Override
	public void setState(int state) {
		this.state = state;
	}

	@Override
	public int getState() {
		return this.state;
	}

	@Override
	public int create() {
		try {
			return orderDAO.createOrder(this);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int update() {
		try {
			return orderDAO.updateOrder(this);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public void readAll() {
		try {
			orderDAO.readAllOrders();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int read() {
		try {
			orderDAO.readOrder(this);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int delete() {
		try {
			return orderDAO.deleteOrder(this);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
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
	public int delete() {
		// TODO Auto-generated method stub
		return 0;
	}

}