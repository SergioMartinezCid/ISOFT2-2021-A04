package es.uclm.esi.isoft2.a04.Persistence;

import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;


import es.uclm.esi.isoft2.a04.Domain.*;
import es.uclm.esi.isoft2.a04.Persistance.Broker;

public class BookingDAO {
	
	
	public Booking[] readAllBookings() {
		
		Vector<Vector<Object>> sql_result = new Vector<Vector<Object>>();
		Vector<Booking> result = new Vector<>();
		
		Booking[] bookings;
		
		String sql = "SELECT * FROM Bookings"; //Sql sentence
		
		sql_result = Broker.getBroker().read(sql);
		
		if(sql_result.size() > 0) {
			
			for(Vector<Object> booking : sql_result) {
				
				result.add(new Booking(booking.get(1).toString(), Integer.parseInt(booking.get(2).toString()), new Date()));
				
			}
		
		}
		
		bookings = new Booking [result.size()];
		
		for (int i = 0; i<bookings.length; i++) {
			
			bookings [i] = result.remove(i); 
			
		}

		return bookings;
		
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
		
		String sql = "INSERT INTO Bookings VALUES ("+ booking.getClientID()+", "+ booking.getTableID()+ ","+booking.getTurn()+")"; //Sql sentence
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