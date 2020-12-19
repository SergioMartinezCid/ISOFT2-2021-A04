package es.uclm.esi.isoft2.a04.Domain;

import java.sql.SQLException;
import java.util.Date;
import es.uclm.esi.isoft2.a04.Persistence.BookingDAO;


public class Booking {

	/**
	 * Date and time of the booking
	 */
	private Date Turn;
	private String clientID;
	private int tableID;
	public static BookingDAO bookingDAO;

	/**
	 * 
	 * @param clientID
	 * @param tableID
	 * @param date
	 */
	public Booking(String clientID, int tableID, Date date) {
		this.clientID = clientID;
		this.tableID = tableID;
		this.Turn = date;
	}

	public String getClientID() {
		return this.clientID;
	}

	public int getTableID() {
		return this.tableID;
	}

	public Date getTurn() {
		return this.Turn;
	}

	/**
	 * 
	 * @param id
	 */
	public void setTableID(int id) {
		this.tableID = id;
	}

	/**
	 * 
	 * @param turn
	 */
	public void setTurn(Date turn) {
		this.Turn = turn;
	}
	
	public BookingDAO getBookingDAO() {
		return this.bookingDAO;
	}
	
	public void readAll() {
		this.bookingDAO.readAllBookings();
	}
	
	public int read() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		this.bookingDAO.readBooking(this);
		return 0;
	}
	
	public int insert() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return this.bookingDAO.createBooking(this);
	}

	public int update() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return this.bookingDAO.updateBooking(this);
	}

	public int delete() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return this.bookingDAO.deleteBooking(this);
	}
}
