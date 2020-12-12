package es.uclm.esi.isoft2.a04.Persistence;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.spi.Resolver;

import es.uclm.esi.isoft2.a04.Domain.*;

public class BookingDAO {
	
	
	public Booking[] readAllBookings() {
		// TODO - implement BookingDao.readAllBookings
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param booking
	 */
	public void readBooking(Booking booking) {
		
		
		try {
			String url = "jdbc:mysql://172.20.48.70:3306/XXXdbservice?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(url,"","");
			Statement statement = conn.createStatement();
			ResultSet resultQuery;
			
			resultQuery = statement.executeQuery("SELECT booking.getClientID() FROM Booking WHERE id = booking.getClientID()");
			
		}
		catch (Exception e) {
			System.err.println("An exception has occur");
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 
	 * @param booking
	 */
	public int createBooking(Booking booking) {
		// TODO - implement BookingDao.createBooking
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param booking
	 */
	public int updateBooking(Booking booking) {
		// TODO - implement BookingDao.updateBooking
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param booking
	 */
	public int deleteBooking(Booking booking) {
		
		try {
			String url = "jdbc:mysql://172.20.48.70:3306/XXXdbservice?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(url,"","");
			Statement statement = conn.createStatement();
			ResultSet resultQuery;
			
			resultQuery = statement.executeQuery("DELETE FROM Booking WHERE id = booking.getClientID()");
			
			return resultQuery.getType();
			
		}
		catch (Exception e) {
			System.err.println("An exception has occur");
			System.out.println(e.getMessage());
		}
		return -1;
		
	}

}