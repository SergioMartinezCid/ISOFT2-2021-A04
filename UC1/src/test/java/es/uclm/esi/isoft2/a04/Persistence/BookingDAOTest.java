package es.uclm.esi.isoft2.a04.Persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

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
	public void testReadAllBookings() throws Exception {
		assertEquals(Booking[].class, tester.readAllBookings());
		
	}

	@Test
	public void testReadBooking() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testCreateBooking() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testUpdateBooking() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testDeleteBooking() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

}
