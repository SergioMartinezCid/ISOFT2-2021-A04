package es.uclm.esi.isoft2.a04.Persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import es.uclm.esi.isoft2.a04.Domain.Booking;
import es.uclm.esi.isoft2.a04.Domain.WaiterImplementation;

public class WaiterDAOTest {
	
	WaiterDAO tester = new WaiterDAO();
	WaiterImplementation waiter = new WaiterImplementation(3);
	/**
	@Test
	public void testReadAllWaiters() throws Exception {
		assertEquals(WaiterImplementation.class, tester.readAllWaiters().getClass());
	}
	**/
	/**
	@Test
	public void testReadWaiter() throws Exception {
		String name = waiter.getName();
		int id = waiter.getID();
		
		tester.readWaiter(waiter);
		
		assertNotEquals(name, waiter.getName());
		assertNotEquals(id, waiter.getID());
	}
	**/
	@Test
	public void testCreateWaiter() throws Exception {
		assertEquals(1, tester.createWaiter(waiter));
	}
	/**
	@Test
	public void testUpdateWaiter() throws Exception {
		assertEquals(1, tester.updateWaiter(waiter));
	}
	**/
	/**
	@Test
	public void testDeleteWaiter() throws Exception {
		assertEquals(1, tester.deleteWaiter(waiter));
	}
	**/
}
