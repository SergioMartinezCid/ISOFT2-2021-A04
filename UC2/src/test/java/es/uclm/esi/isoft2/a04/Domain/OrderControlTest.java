package es.uclm.esi.isoft2.a04.Domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mysql.cj.jdbc.integration.c3p0.MysqlConnectionTester;

public class OrderControlTest {

	OrderControl tester = new OrderControl();
	
	/*
	@Test
	public void testGetCurrentOrder() throws Exception {
		throw new RuntimeException("not yet implemented");
	}
	 */
	
	
	@Test
	public void testCloseOrder() throws Exception {
		WaiterImplementation waiter = new WaiterImplementation(3);
		TableImplementation table = new TableImplementation(4);
		OrderImplementation order = new OrderImplementation(waiter, table);
		tester.closeOrder(order);
		assertEquals(OrderImplementation.CLOSED, order.getState());
	}

	/*
	@Test
	public void testAttach() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testDetach() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testNotifyMe() throws Exception {
		throw new RuntimeException("not yet implemented");
	}
	*/
}
