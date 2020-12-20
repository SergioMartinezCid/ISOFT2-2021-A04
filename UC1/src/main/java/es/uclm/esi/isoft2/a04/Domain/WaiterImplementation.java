package es.uclm.esi.isoft2.a04.Domain;

import java.sql.SQLException;
import java.util.ArrayList;
import es.uclm.esi.isoft2.a04.Persistence.WaiterDAO;

/**
 * @version 0.1.0
 *
 */
public class WaiterImplementation implements Waiter {

	private int id;
	private String name;
	private ArrayList<TableImplementation> assignedTables;
	private WaiterDAO waiterDAO;
	private ArrayList<Observer> observers;

	/**
	 * 
	 */
	public WaiterImplementation() {
		this.waiterDAO = new WaiterDAO();
		this.observers = new ArrayList<>();
	}

	/**
	 * @param id The id of the Waiter in the database
	 */
	public WaiterImplementation(int id) {
		this();
		this.id = id;
		this.assignedTables = new ArrayList<>();
	}

	@Override
	public int getID() {
		return this.id;
	}

	/**
	 * @param id The id of the Waiter in the database
	 */
	public void setID(int id) {
		this.id = id;
	}

	/**
	 * @return the full name of the waiter
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name The full name of the waiter
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return The list of tables currently assigned to the waiter
	 */
	public ArrayList<TableImplementation> getAssignedTables() {
		return this.assignedTables;
	}

	/**
	 * 
	 */
	public void clearTables() {
		this.assignedTables = new ArrayList<>();
	}

	/**
	 * @param table The new table to be assigned to this waiter
	 */
	public void assignTable(TableImplementation table) {
		this.assignedTables.add(table);
	}

	@Override
	public Waiter[] readAll() throws NumberFormatException, InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException {
		return this.waiterDAO.readAllWaiters();
	}

	@Override
	public void read() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		this.waiterDAO.readWaiter(this);
	}

	@Override
	public int create() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return this.waiterDAO.createWaiter(this);
	}

	@Override
	public int update() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return this.waiterDAO.updateWaiter(this);
	}

	@Override
	public int delete() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return this.waiterDAO.deleteWaiter(this);
	}

	@Override
	public void attach(Observer o) {
		this.observers.add(o);
	}

	@Override
	public void detach(Observer o) {
		this.observers.remove(o);
	}

	@Override
	public void notifyMe() {
		for(Observer o: this.observers) {
			o.notify();
		}
	}
}