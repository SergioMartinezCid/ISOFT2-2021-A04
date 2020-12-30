package es.uclm.esi.isoft2.a04.Domain;

import java.util.Arrays;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

import es.uclm.esi.isoft2.a04.Persistance.Broker;
import es.uclm.esi.isoft2.a04.Persistence.TableDAO;

/**
 * @version 0.1.3
 *
 */
public class TableImplementation implements Table {

	private int id;
	private int seatsNumber;
	private int state;
	private int restaurantID;
	private String city;
	private TableDAO tableDAO;
	private HashMap<Date, Integer> stateHistory;

	/**
	 * 
	 */
	public TableImplementation() {
		this.tableDAO = new TableDAO();
	}

	/**
	 * @param id The id of the table in the database
	 */
	public TableImplementation(int id) {
		this();
		this.id = id;
	}

	@Override
	public void setState(int state) {
		this.state = state;
	}

	@Override
	public int getState() {
		return this.state;
	}
	
	@Override
	public void setStateHistory(HashMap<Date, Integer> stateHistory) {
		this.stateHistory = stateHistory;
	}

	@Override
	public HashMap<Date, Integer> getStateHistory() {
		return stateHistory;
	}

	@Override
	public void setSeats(int seatsNumber) {
		this.seatsNumber = seatsNumber;
	}

	@Override
	public int getSeats() {
		return this.seatsNumber;
	}

	@Override
	public int getID() {
		return this.id;
	}

	/**
	 * @param id The id of this new instance
	 */
	public void setID(int id) {
		this.id = id;
	}

	@Override
	public int getRestaurantID() {
		return this.restaurantID;
	}

	/**
	 * @param restaurantID The new restaurantID of this table
	 */
	public void setRestaurantID(int restaurantID) {
		this.restaurantID = restaurantID;
	}

	@Override
	public String getCity() {
		return this.city;
	}

	/**
	 * @param city The name of the city the restaurant is in
	 */
	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public Table[] readAll()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return this.tableDAO.readAllTables();
	}

	@Override
	public void read() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		this.tableDAO.readTable(this);
	}

	@Override
	public int create() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return this.tableDAO.createTable(this);
	}

	@Override
	public int update() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException,
			NumberFormatException, ParseException {
		return this.tableDAO.updateTable(this);
	}

	@Override
	public int delete() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return this.tableDAO.deleteOrder(this);
	}
  
	public String toString() {
		return "Id:"+this.id+ " / Seats: "+ this.getSeats()+" / State: " +this.state;
	}
}