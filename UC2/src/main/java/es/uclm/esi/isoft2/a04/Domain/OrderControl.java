package es.uclm.esi.isoft2.a04.Domain;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @version 0.1.0
 *
 */
public class OrderControl implements Subject {

	private ArrayList<Observer> observers = new ArrayList<>();

	public OrderImplementation getCurrentOrder(Waiter currentWaiter, Table currentTable)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, ParseException,
			InvalidStateException, InvalidTypeException {
		OrderImplementation orderdb = new OrderImplementation(currentWaiter, currentTable);

		return (OrderImplementation) Arrays.stream(orderdb.readAll())
				.filter(order -> order.getWaiter().getID() == currentWaiter.getID()
						&& order.getTable().getID() == currentTable.getID())
				.reduce((mostRecentOrder, nextOrder) -> mostRecentOrder.getDatetime().before(nextOrder.getDatetime())
						? nextOrder
						: mostRecentOrder)
				.get();
	}

	public void closeOrder(OrderImplementation order) throws InvalidStateException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		order.setState(OrderImplementation.CLOSED);
		order.update();
		notifyMe();
	}
	
	

	@Override
	public void attach(Observer o) {
		observers.add(o);
	}

	@Override
	public void detach(Observer o) {
		observers.remove(o);
	}

	@Override
	public void notifyMe() {
		for (Observer o : observers)
			o.update();
	}

}