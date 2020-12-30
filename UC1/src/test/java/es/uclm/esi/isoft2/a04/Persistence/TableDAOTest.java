package es.uclm.esi.isoft2.a04.Persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import es.uclm.esi.isoft2.a04.Domain.Booking;
import es.uclm.esi.isoft2.a04.Domain.TableImplementation;

public class TableDAOTest {

	TableDAO tester = new TableDAO();
	TableImplementation table = new TableImplementation();
	
	/**
	@Test
	public void testReadAllTables() throws Exception {
		assertEquals(Booking.class, tester.readAllTables().getClass());
	}
	**/
	@Test
	public void testReadTable() throws Exception {
		
		int [] seats_list = {2,4,6};
		
		int seats = table.getSeats();
		int state = table.getState();
		int id = table.getID();
		
		tester.readTable(table);
		
		for (int i = 0; i<seats_list.length; i++) {
			table.setSeats(seats_list[i]);
			assertNotEquals(seats, table.getSeats());
		}
		
		//assertNotEquals(state, table.getState());
		//assertNotEquals(id, table.getID());
	}
	/**
	@Test
	public void testCreateTable() throws Exception {
		assertEquals(1, tester.createTable(table));
	}
	**/
	/**
	@Test
	public void testUpdateTable() throws Exception {
		assertEquals(1, tester.updateTable(table));
	}
	**/
	/**
	@Test
	public void testDeleteOrder() throws Exception {
		assertEquals(1, tester.deleteOrder(table));
	}
	**/
}
