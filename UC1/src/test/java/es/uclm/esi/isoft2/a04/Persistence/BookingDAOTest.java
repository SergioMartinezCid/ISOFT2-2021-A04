package es.uclm.esi.isoft2.a04.Persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import org.junit.Test;

import es.uclm.esi.isoft2.a04.Domain.Booking;
import es.uclm.esi.isoft2.a04.Domain.TableImplementation;

public class BookingDAOTest {
	
	BookingDAO tester = new BookingDAO(); 
	Booking booking = new Booking(
			new TableImplementation(1),
			new Date(2021, 3, 2), 
			Booking.TURN.D1
			);
	
	@Test
	public void testReadAllBookings() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NumberFormatException, ParseException {
		assertEquals(Booking.class, tester.readAllBookings().getClass());
	}

	@Test
	public void testReadBooking() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		/*
		Booking booking = new Booking(
				new TableImplementation(1),
				new Date(2021, 3, 2), 
				Booking.TURN.D1
				);
		*/
		String previous = booking.getClientID();
		tester.readBooking(booking);
		assertEquals(previous, booking.getClientID());
	}

	@Test
	public void testCreateBooking() throws Exception {
		assertEquals(1, tester.createBooking(booking));
	}

	@Test
	public void testUpdateBooking() throws Exception {
		assertEquals(1, tester.updateBooking(booking));
	}

	@Test
	public void testDeleteBooking() throws Exception {
		assertEquals(1, tester.deleteBooking(booking));
	}

}
