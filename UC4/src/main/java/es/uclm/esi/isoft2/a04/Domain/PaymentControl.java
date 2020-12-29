package es.uclm.esi.isoft2.a04.Domain;

import java.sql.SQLException;
import java.text.ParseException;

/**
 * @version 0.1.0
 *
 */
public class PaymentControl {

	/**
	 * @param order The order whose clients have asked for the check
	 * @return The number of modified rows
	 * @throws NumberFormatException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 * @throws InvalidStateException
	 */
	public static int askForTheCheck(Order order) throws NumberFormatException, InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException, ParseException, InvalidStateException {
		if (order.getTable().getState() != Table.SERVED)
			throw new InvalidStateException();
		order.getTable().setState(Table.WAITING_FOR_BILL);
		return order.getTable().update();
	}

	/**
	 * @param order The order whose payment stage has started
	 * @return The number of modified rows
	 * @throws InvalidStateException
	 * @throws NumberFormatException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 */
	public static int startPayment(Order order) throws InvalidStateException, NumberFormatException,
			InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, ParseException {
		if (order.getTable().getState() != Table.WAITING_FOR_BILL)
			throw new InvalidStateException();
		order.getTable().setState(Table.PAYING);
		return order.getTable().update();
	}

	/**
	 * @param order         The order being payed
	 * @param isCash        Whether the payment is made in cash or by using a credit
	 *                      card
	 * @param paymentMethod A string representing the payment method; it will be
	 *                      ignored if isCash is true
	 * @return The number of modified rows
	 * @throws InvalidStateException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int makePayment(Order order, boolean isCash, String paymentMethod) throws InvalidStateException,
			InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		if (order.getTable().getState() != Table.PAYING)
			throw new InvalidStateException();
		if (isCash) {
			order.setPaymentMethod("CASH");
			System.out.println("Simulate cash payment");
		} else {
			order.setPaymentMethod(paymentMethod);
			System.out.println("Simulate card payment");
		}
		return order.update();
	}
	
	public static int startPreparation(Table table) throws InvalidStateException, NumberFormatException,
			InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, ParseException {
		if (table.getState() != Table.PAYING)
			throw new InvalidStateException();
		table.setState(Table.IN_PREPARATION);
		return table.update();
	}

	/**
	 * @param table The table that is already prepared
	 * @return The number of modified rows
	 * @throws InvalidStateException
	 * @throws NumberFormatException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 */
	public static int finishPreparation(Table table) throws InvalidStateException, NumberFormatException,
			InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, ParseException {
		if (table.getState() != Table.IN_PREPARATION)
			throw new InvalidStateException();
		table.setState(Table.FREE);
		return table.update();
	}
}
