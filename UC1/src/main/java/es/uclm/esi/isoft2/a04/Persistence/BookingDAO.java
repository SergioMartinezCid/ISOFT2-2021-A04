package es.uclm.esi.isoft2.a04.Persistence;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import es.uclm.esi.isoft2.a04.Domain.*;
import es.uclm.esi.isoft2.a04.Persistance.Broker;

/**
 * @version 0.1.0
 *
 */
public class BookingDAO {
	private static final DateFormat mysqlDateSDF = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * @return An array of all the Bookings in the database
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Booking[] readAllBookings()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		Vector<Vector<Object>> query_result = new Vector<Vector<Object>>();
		Booking[] bookings;
		TableImplementation tmpTable;

		String sql = "SELECT * FROM Booking;";

		query_result = Broker.getBroker().read(sql);

		bookings = new Booking[query_result.size()];
		for (int i = 0; i < bookings.length; i++) {
			tmpTable = new TableImplementation(Integer.parseInt(query_result.get(i).get(0).toString()));
			tmpTable.read();
			bookings[i] = new Booking(tmpTable, (Date)query_result.get(i).get(1),
					Booking.TURN.valueOf(query_result.get(i).get(2).toString().toUpperCase()));
			bookings[i].setClientID(query_result.get(i).get(3).toString());

		}

		return bookings;

	}

	/**
	 * @param booking The Booking instance to be read
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void readBooking(Booking booking)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Vector<Vector<Object>> query_result;
		String sql = "SELECT Client FROM Booking WHERE TableId = " + booking.getTable().getID() + " AND Date = '"
				+ this.mysqlDateSDF.format(booking.getDate()) + "' AND Turn = '" + booking.getTurn().toString() + "';";
		query_result = Broker.getBroker().read(sql);
		for (int i = 0; i < query_result.size(); i++) {
			booking.setClientID(query_result.get(i).get(0).toString());
		}
	}

	/**
	 * @param booking The Booking instance to be created
	 * @return The number of modified columns
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int createBooking(Booking booking)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		String sql = "INSERT INTO Booking VALUES (" + booking.getTable().getID() + ", '"
				+ this.mysqlDateSDF.format(booking.getDate()) + "', '" + booking.getTurn().toString() + "', '"
				+ booking.getClientID() + "')";
		return Broker.getBroker().update(sql);
	}

	/**
	 * @param booking The Booking instance to be updated
	 * @return The number of modified columns
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int updateBooking(Booking booking)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		String sql = "UPDATE Booking SET Client = '" + booking.getClientID() + "' WHERE TableId = "
				+ booking.getTable().getID() + " AND Date = '" + this.mysqlDateSDF.format(booking.getDate())
				+ "' AND Turn = '" + booking.getTurn().toString() + "';";
		return Broker.getBroker().update(sql);

	}

	/**
	 * @param booking The Booking instance to be deleted
	 * @return The number of modified columns
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int deleteBooking(Booking booking)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		String sql = "DELETE FROM Booking WHERE TableId = " + booking.getTable().getID() + " AND Date = '"
				+ this.mysqlDateSDF.format(booking.getDate()) + "' AND Turn = '" + booking.getTurn().toString() + "';";
		return Broker.getBroker().update(sql);

	}

}