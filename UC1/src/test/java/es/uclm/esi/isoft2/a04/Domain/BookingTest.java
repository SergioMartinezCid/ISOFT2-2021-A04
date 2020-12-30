package es.uclm.esi.isoft2.a04.Domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Date;

import org.junit.Test;

import es.uclm.esi.isoft2.a04.Domain.Booking.TURN;

public class BookingTest {

	private Booking bookingTester = new Booking(
			new TableImplementation(), 
			new Date(2021, 5, 2),
			TURN.L1);
	
	@Test
	public void testBooking() {
		Booking tester = new Booking();		
	}

	@Test
	public void testBookingTableDateTURN() {
		TableImplementation table = new TableImplementation();
		new Booking(table, new Date(2021, 5, 2), Booking.TURN.L1);
	}

	@Test
	public void testClientID() throws Exception {
		String id = "María";
		bookingTester.setClientID(id);
		assertEquals(id, bookingTester.getClientID());
		assertNotEquals("Ana", bookingTester.getClientID());	
	}

	@Test
	public void testTable() {
		Table table = new TableImplementation(5);
		bookingTester.setTable(table);
		assertEquals(table, bookingTester.getTable());
		assertEquals(new TableImplementation(6), bookingTester.getTable());
	}

	@Test
	public void testDate() {
		Date date = new Date();
		bookingTester.setDate(date);
		assertEquals(date, bookingTester.getDate());
		assertNotEquals(new Date(2019, 5, 3), bookingTester.getDate());
	}

	@Test
	public void testTurn() {
		TURN turn = TURN.D2;
		bookingTester.setTurn(turn);;
		assertEquals(turn, bookingTester.getTurn());
		assertNotEquals(TURN.L1,  bookingTester.getTurn());
	}
}
