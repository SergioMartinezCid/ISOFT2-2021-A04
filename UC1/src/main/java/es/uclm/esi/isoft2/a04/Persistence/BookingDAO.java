package es.uclm.esi.isoft2.a04.Persistence;

import java.sql.SQLException;
import java.util.Vector;


import es.uclm.esi.isoft2.a04.Domain.*;
import es.uclm.esi.isoft2.a04.Persistance.Broker;

public class BookingDAO {
	
	
	public Booking[] readAllBookings() {
		
		Vector<Vector<Object>> result = new Vector<Vector<Object>>();
		Booking[] bookings;
		
		String sql = "SELECT * FROM Bookings"; //Sql sentence
		
		return null;
		
	}
	
	/**
	 * 
	 * @param booking
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException 
	 */
	public void readBooking(Booking booking) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		String sql = "SELECT * FROM Bookings WHERE BookingID ="+ booking.getTableID(); //Sql sentence
		Broker.getBroker().read(sql);
		
	}

	/**
	 * 
	 * @param booking
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException 
	 */
	public int createBooking(Booking booking) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		String sql = "INSERT INTO Bookings VALUES ("+ booking.getTableID()+", "+ booking.getClientID()+")"; //Sql sentence
		return Broker.getBroker().update(sql);
		
	}

	/**
	 * 
	 * @param booking
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException 
	 */
	public int updateBooking(Booking booking) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		String sql = "UPDATE Bookings WHERE BookingID ="+booking.getTableID();  //Sql sentence
		return Broker.getBroker().update(sql);
		
	}

	/**
	 * 
	 * @param booking
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException 
	 */
	public int deleteBooking(Booking booking) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		String sql = "DELETE FROM Bookings WHERE BookingID = "+ booking.getTableID(); //Sql sentence
		return Broker.getBroker().update(sql);
		
	}

}