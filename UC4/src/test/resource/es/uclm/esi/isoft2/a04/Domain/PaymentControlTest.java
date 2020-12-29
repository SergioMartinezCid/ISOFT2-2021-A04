package es.uclm.esi.isoft2.a04.Domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PaymentControlTest {
 
	
	@Test
	public void testAskForTheCheck() throws Exception {
		WaiterImplementation waiter = new WaiterImplementation();;
		TableImplementation table = new TableImplementation();;
		OrderImplementation order = new OrderImplementation(waiter, table);
		assertNotEquals(0, PaymentControl.askForTheCheck(order));
		assertEquals(Table.WAITING_FOR_BILL, order.getTable().getState());
	}

	@Test
	public void testStartPayment() throws Exception {
		WaiterImplementation waiter = new WaiterImplementation();;
		TableImplementation table = new TableImplementation();;
		OrderImplementation order = new OrderImplementation(waiter, table);
		assertNotEquals(0, PaymentControl.startPayment(order));
		assertEquals(Table.PAYING, order.getTable().getState());
	}

	@Test
	public void testMakePayment() throws Exception {
		WaiterImplementation waiter = new WaiterImplementation();;
		TableImplementation table = new TableImplementation();;
		OrderImplementation order = new OrderImplementation(waiter, table);
		assertNotEquals(0, PaymentControl.makePayment(order, true, ""));
		assertEquals("CASH", order.getPaymentMethod());
		String method= "VISA";
		PaymentControl.makePayment(order, false, method)
		assertEquals(method, order.getPaymentMethod());
		
	}

	@Test
	public void testFinishPreparation() throws Exception {
		TableImplementation table = new TableImplementation();
		table.setState(Table.IN_PREPARATION);
		assertNotEquals(0, PaymentControl.finishPreparation(table));
		assertEquals(Table.FREE, table.getState());
	}

}

