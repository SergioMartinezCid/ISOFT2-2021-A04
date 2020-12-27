package es.uclm.esi.isoft2.a04.Domain;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import es.uclm.esi.isoft2.a04.Persistence.BookingDAO;

/**
 * @version 0.1.0
 *
 */
public class Booking {

	public enum TURN {
		L1, L2, L3, D1, D2, D3
	}

	private Date date;
	private TURN turn;
	private String clientID;
	private Table table;
	public BookingDAO bookingDAO;

	/**
	 * 
	 */
	public Booking() {
		this.bookingDAO = new BookingDAO();
	}

	/**
	 * @param table The table being booked
	 * @param date  The day the table is booked
	 * @param turn  The turn out of the six it is booked
	 */
	public Booking(Table table, Date date, TURN turn) {
		this();
		this.table = table;
		this.date = date;
		this.turn = turn;
	}

	/**
	 * @return The String identifying the client
	 */
	public String getClientID() {
		return this.clientID;
	}

	/**
	 * @param clientId the String identifying the client
	 */
	public void setClientID(String clientId) {
		this.clientID = clientId;
	}

	/**
	 * @return The table being booked
	 */
	public Table getTable() {
		return this.table;
	}

	/**
	 * @param table The table being booked
	 */
	public void setTable(Table table) {
		this.table = table;
	}

	/**
	 * @return The date of the booking
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * @param date The date of the booking
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return The turn of the booking
	 */
	public TURN getTurn() {
		return this.turn;
	}

	/**
	 * @param turn The turn of the booking
	 */
	public void setTurn(TURN turn) {
		this.turn = turn;
	}

	/**
	 * @return An array with all the bookings in the database
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Booking[] readAll()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return this.bookingDAO.readAllBookings();
	}

	/**
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void read() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		this.bookingDAO.readBooking(this);
	}

	/**
	 * @return The number of modified columns
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int create() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return this.bookingDAO.createBooking(this);
	}

	/**
	 * @return The number of modified columns
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int update() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return this.bookingDAO.updateBooking(this);
	}

	/**
	 * @return The number of modified columns
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int delete() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return this.bookingDAO.deleteBooking(this);
	}
	
	public String toString() {
		Date date = this.getDate();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		String strDate = dateFormat.format(date); 
		return "Table: " + this.getTable().getID() + " / Turn: " + this.getTurn() + " / Date: " + strDate + " / Client: " + this.getClientID();
	}
}
