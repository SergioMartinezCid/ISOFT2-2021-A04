package es.uclm.esi.isoft2.a04.Persistance;

import es.uclm.esi.isoft2.a04.Domain.*;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

/**
 * @version 0.1.2
 *
 */
public class OrderDAO {
	private static final DateFormat mysqlDateSDF = new SimpleDateFormat("yyyy-MM-dd");
	private Waiter waiterdb;
	private Table tabledb;

	public OrderDAO(Waiter waiterdb, Table tabledb) {
		this.waiterdb = waiterdb;
		this.tabledb = tabledb;
	}

	/**
	 * @return All orders in the database
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws InvalidStateException
	 * @throws ParseException
	 * @throws InvalidTypeException
	 */
	public OrderImplementation[] readAllOrders() throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException, ParseException, InvalidStateException, InvalidTypeException {
		Vector<Vector<Object>> query_result = new Vector<Vector<Object>>();
		OrderImplementation[] orders;

		String sql = "SELECT OrderId FROM OrderRestaurant;";

		query_result = Broker.getBroker().read(sql);

		orders = new OrderImplementation[query_result.size()];

		for (int i = 0; i < query_result.size(); i++) {
			orders[i] = new OrderImplementation(this.waiterdb, this.tabledb,
					(Integer)query_result.get(i).get(0));
			orders[i].read();
		}
		return orders;
	}

	/**
	 * @param order The order to be read
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 * @throws InvalidStateException
	 * @throws InvalidTypeException
	 */
	public void readOrder(OrderImplementation order) throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException, ParseException, InvalidStateException, InvalidTypeException {
		Vector<Vector<Object>> query_result_order, query_result_food;
		String sql_order = "SELECT * FROM OrderRestaurant WHERE OrderId = " + order.getID() + ";";
		String sql_food = "SELECT o.FoodId, f.Type FROM OrderContent AS o, Food AS f WHERE OrderId = " + order.getID()
				+ " AND f.FoodId = o.FoodId ;";
		query_result_order = Broker.getBroker().read(sql_order);
		query_result_food = Broker.getBroker().read(sql_food);

		for (int i = 0; i < query_result_order.size(); i++) {
			final int waiterId = (Integer)query_result_order.get(i).get(1);
			final int tableId = (Integer)query_result_order.get(i).get(2);
			final String state = query_result_order.get(i).get(3).toString();

			Waiter waiter = Arrays.stream(this.waiterdb.readAll()).filter(w -> w.getID() == waiterId).iterator().next();
			Table table = Arrays.stream(this.tabledb.readAll()).filter(t -> t.getID() == tableId).iterator().next();
			order.setWaiter(waiter);
			order.setTable(table);
			order.setDatetime(OrderDAO.mysqlDateSDF.parse(query_result_order.get(i).get(4).toString()));
			if(query_result_order.get(i).get(5)!=null) {
				order.setPaymentMethod(query_result_order.get(i).get(5).toString().toUpperCase());
			}
			switch (state.toUpperCase()) {
			case "OPEN":
				order.setState(OrderImplementation.OPEN);
				break;
			case "CLOSED":
				order.setState(OrderImplementation.CLOSED);
				break;
			case "PAYED":
				order.setState(OrderImplementation.PAYED);
				break;
			}
		}

		Food auxFood;
		/*ArrayList<Food> food = new ArrayList<>();
		for (int i = 0; i < query_result_food.size(); i++) {
			if (query_result_food.get(i).get(1).toString().equals("DRINKS")) {
				auxFood = new BeverageImplementation(Integer.valueOf(query_result_food.get(i).get(0).toString()),
						order);
			} else {
				auxFood = new Dish(Integer.valueOf(query_result_food.get(i).get(0).toString()), order);
			}
			auxFood.read();
			food.add(auxFood);
		}*/
		Food[] food = new Food[query_result_food.size()];
		for(int i=0; i<food.length; i++) {
			if (query_result_food.get(i).get(1).toString().equals("DRINKS")) {
				auxFood = new BeverageImplementation(Integer.valueOf(query_result_food.get(i).get(0).toString()),
						order);
			} else {
				auxFood = new Dish(Integer.valueOf(query_result_food.get(i).get(0).toString()), order);
			}
			auxFood.read();
			food[i]=auxFood;
		}
		order.setFood(food);
	}

	private static String getStateStringRepresentation(OrderImplementation order) {
		String state = "";
		switch (order.getState()) {
		case OrderImplementation.OPEN:
			state = "OPEN";
			break;
		case OrderImplementation.CLOSED:
			state = "CLOSED";
			break;
		case OrderImplementation.PAYED:
			state = "PAYED";
			break;
		}
		return state;
	}

	/**
	 * @param order The order instance to be created
	 * @return The number of modified rows
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int createOrder(OrderImplementation order)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Vector<Vector<Object>> query_result_id;
		String sql_order = "INSERT INTO OrderRestaurant (WaiterId, TableId, State, Datetime, PaymentMethod) VALUES ("
				+ order.getWaiter().getID() + ", " + order.getTable().getID() + ", '"
				+ getStateStringRepresentation(order) + "', NOW(), " + order.getPaymentMethod() + ");";
		String sql_getId = "SELECT LAST_INSERT_ID();";

		int modifiedRows = Broker.getBroker().update(sql_order);
		query_result_id = Broker.getBroker().read(sql_getId);
		for (int i = 0; i < query_result_id.size(); i++) {
			order.setID(Integer.valueOf(query_result_id.get(i).get(0).toString()));
		}

		for (FoodImplementation food : (FoodImplementation[]) order.getFood()) {
			modifiedRows += Broker.getBroker().update("INSERT INTO OrderContent (OrderId, FoodId, Quantity) VALUES ("
					+ order.getID() + ", " + food.getID() + ", " + food.getQuantity() + ");");
		}

		return modifiedRows;
	}

	/**
	 * @param order The order to be updated
	 * @return The number of modified rows
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int updateOrder(OrderImplementation order)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Vector<Vector<Object>> query_result_food;
		Iterator<Vector<Object>> rowIterator;

		int modifiedRows;
		String sql_order = "UPDATE OrderRestaurant SET WaiterId = " + order.getWaiter().getID() + ", TableId = "
				+ order.getTable().getID() + ", State = '" + getStateStringRepresentation(order) + "', Datetime = '"
				+ mysqlDateSDF.format(order.getDatetime()) + "', PaymentMethod='" + order.getPaymentMethod()
				+ "' WHERE OrderId = " + order.getID() + ";";
		String sql_check_food = "SELECT FoodId FROM OrderContent WHERE OrderId = " + order.getID() + ";";
		modifiedRows = Broker.getBroker().update(sql_order);

		query_result_food = Broker.getBroker().read(sql_check_food);
		for (FoodImplementation food : (FoodImplementation[]) order.getFood()) {
			rowIterator = query_result_food.stream()
					.filter(row -> Integer.valueOf(row.get(0).toString()) == food.getID()).iterator();
			if (rowIterator.hasNext()) {
				modifiedRows += food.update();
				query_result_food.remove(rowIterator.next());
			} else {
				modifiedRows += Broker.getBroker()
						.update("INSERT INTO OrderContent (OrderId, FoodId, Quantity) VALUES (" + order.getID() + ", "
								+ food.getID() + ", " + food.getQuantity() + ");");
			}
		}
		for (Vector<Object> row : query_result_food) {
			modifiedRows += Broker.getBroker().update("DELETE FROM OrderContent WHERE OrderId = " + order.getID()
					+ " AND FoodId = " + row.get(0).toString() + ";");
		}
		return modifiedRows;
	}

	/**
	 * @param order The order to be deleted
	 * @return The number of modified rows
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int deleteOrder(OrderImplementation order)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		int modifiedRows = 0;
		for (Food food : order.getFood()) {
			modifiedRows += Broker.getBroker().update("DELETE FROM OrderContent WHERE OrderId = " + order.getID()
					+ " AND FoodId = " + food.getID() + ";");
		}
		modifiedRows += Broker.getBroker().update("DELETE FROM OrderRestauran WHERE OrderId = " + order.getID() + ";");
		return modifiedRows;
	}

}