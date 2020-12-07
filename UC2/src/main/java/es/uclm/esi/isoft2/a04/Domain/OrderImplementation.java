import General.Domain.*;
import java.util.Date;

public class OrderImplementation implements Order {

	private static OrderDAO orderDAO;

	private static int OPEN = 0;
	private static int CLOSED = 1;
	private static int PAYED = 2;

	private int state;
	private Date datetime;
	
	private int id;
	private Waiter waiter;
	private Table table;

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
		return orderDAO.readAllOrders();
	}

	public void read() {
		orderDAO.readOrder(this);
	}

	public int create() {
		return orderDAO.createOrder(this);
	}

	public int update() {
		return orderDAO.updateOrder(this);
	}

}