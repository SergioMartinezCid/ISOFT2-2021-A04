package es.uclm.esi.isoft2.a04.Persistance;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;


import es.uclm.esi.isoft2.a04.Domain.OrderImplementation;
import es.uclm.esi.isoft2.a04.Domain.TableImplementation;
import es.uclm.esi.isoft2.a04.Domain.WaiterImplementation;



public class OrderDAOTest {

	WaiterImplementation waiter = new WaiterImplementation();
	TableImplementation table = new TableImplementation();
	OrderDAO tester = new OrderDAO(waiter, table);


	@Test
	public void testReadAllOrders() throws Exception {
		assertNotNull(tester.readAllOrders());
	}

	@Test
	public void testReadOrder() throws Exception {
		OrderImplementation order = new OrderImplementation(waiter, table);
		tester.readOrder(order);
		assertNotEquals(0, order.getCost());
	}

	@Test
	public void testCreateOrder() throws Exception {
		OrderImplementation order = new OrderImplementation(waiter, table);
		order.setID(-1);
		assertNotEquals(0, tester.createOrder(order));
	}

	@Test
	public void testUpdateOrder() throws Exception {
		OrderImplementation order = new OrderImplementation(waiter, table);
		order.setID(-1);
		assertNotEquals(0, tester.updateOrder(order));
	}

	@Test
	public void testDeleteOrder() throws Exception {
		OrderImplementation order = new OrderImplementation(waiter, table);
		order.setID(-1);
		assertNotEquals(0, tester.deleteOrder(order));
	}

}
