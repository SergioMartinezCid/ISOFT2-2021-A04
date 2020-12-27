package es.uclm.esi.isoft2.a04.Domain;

import es.uclm.esi.isoft2.a04.Persistance.OrderDAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

/**
 * @version 0.1.0
 *
 */
public class OrderImplementation implements Order {

	private int id;
	private int state;
	private Date datetime;
	private String paymentMethod;
	private Waiter waiter;
	private Table table;
	private FoodImplementation[] food;

	private OrderDAO orderDAO;

	/**
	 * 
	 */
	public OrderImplementation(Waiter waiterdb, Table tabledb) {
		orderDAO = new OrderDAO(waiterdb, tabledb);
	}

	/**
	 * @param id The id of the order in the database
	 */
	public OrderImplementation(Waiter waiterdb, Table tabledb, int id) {
		this(waiterdb, tabledb);
		this.id = id;
	}

	@Override
	public int getID() {
		return this.id;
	}

	/**
	 * @param id The new id of the order
	 */
	public void setID(int id) {
		this.id = id;
	}

	@Override
	public Food[] getFood() {
		return this.food;
	}

	@Override
	public void setFood(Food[] food) {
		this.food = (FoodImplementation[]) food;
	}

	@Override
	public float getCost() {
		float cost = 0.0f;
		for (FoodImplementation food : (FoodImplementation[]) this.getFood()) {
			cost += food.getCost() * food.getQuantity();
		}
		return cost;
	}

	@Override
	public int getState() {
		return this.state;
	}

	@Override
	public void setState(int state) throws InvalidStateException {
		if (state != OrderImplementation.OPEN && state != OrderImplementation.CLOSED
				&& state != OrderImplementation.PAYED)
			throw new InvalidStateException();
		this.state = state;
	}

	@Override
	public Date getDatetime() {
		return datetime;
	}

	@Override
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	@Override
	public Waiter getWaiter() {
		return waiter;
	}

	/**
	 * @param waiter The new waiter of the order
	 */
	public void setWaiter(Waiter waiter) {
		this.waiter = waiter;
	}

	@Override
	public Table getTable() {
		return table;
	}

	/**
	 * @param table The new table of the order
	 */
	public void setTable(Table table) {
		this.table = table;
	}
	
	@Override
	public String getPaymentMethod() {
		return this.paymentMethod;
	}
	
	@Override
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Override
	public Order[] readAll() throws InstantiationException, IllegalAccessException, ClassNotFoundException,
			SQLException, ParseException, InvalidStateException, InvalidTypeException {
		return (Order[]) orderDAO.readAllOrders();
	}

	@Override
	public void read() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException,
			ParseException, InvalidStateException, InvalidTypeException {
		orderDAO.readOrder(this);
	}

	@Override
	public int create() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return orderDAO.createOrder(this);
	}

	@Override
	public int update() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return orderDAO.updateOrder(this);
	}

	@Override
	public int delete() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return orderDAO.deleteOrder(this);
	}
}