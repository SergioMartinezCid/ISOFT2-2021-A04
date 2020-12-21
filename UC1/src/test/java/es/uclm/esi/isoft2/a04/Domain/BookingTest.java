package es.uclm.esi.isoft2.a04.Domain;

import static org.junit.Assert.assertEquals;

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
		TableImplementation table= new TableImplementation();
		new Booking(table, new Date(2021, 5, 2), Booking.TURN.L1);
	}

	@Test
	public void testClientID() throws Exception {
		String id = "María";
		bookingTester.setClientID(id);
		assertEquals(id, bookingTester.getClientID());	
	}

	@Test
	public void testTable() {
		Table table = new TableImplementation();
		bookingTester.setTable(table);
		assertEquals(table, bookingTester.getTable());
	}

	@Test
	public void testDate() {
		Date date = new Date();
		bookingTester.setDate(date);
		assertEquals(date, bookingTester.getDate());
	}

	@Test
	public void testTurn() {
		TURN turn = TURN.D2;
		bookingTester.setTurn(turn);;
		assertEquals(turn, bookingTester.getTurn());
	}
	
	/*
	@Test
	public void testReadAll() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testRead() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testCreate() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testUpdate() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testDelete() throws Exception {
		throw new RuntimeException("not yet implemented");
	}
	*/
}
