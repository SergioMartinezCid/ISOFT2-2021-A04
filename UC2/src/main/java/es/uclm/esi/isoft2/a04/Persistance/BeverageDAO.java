package es.uclm.esi.isoft2.a04.Persistance;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import es.uclm.esi.isoft2.a04.Domain.*;

/**
 * @version 0.1.0
 *
 */
public class BeverageDAO {

	private static SimpleDateFormat mysqlDateTimeSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * @return An array with all the beverages in the database
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws InvalidTypeException
	 * @throws ParseException
	 */
	public BeverageImplementation[] readAllBeverages() throws InstantiationException, IllegalAccessException, ClassNotFoundException,
			SQLException, InvalidTypeException, ParseException {
		Vector<Vector<Object>> query_result;
		query_result = Broker.getBroker().read("SELECT FoodId FROM Drink;");
		BeverageImplementation[] beverages = new BeverageImplementation[query_result.size()];
		for (int i = 0; i < query_result.size(); i++) {
			beverages[i] = new BeverageImplementation(Integer.valueOf(query_result.get(i).get(0).toString()));
			beverages[i].read();
		}
		return beverages;
	}

	/**
	 * @param beverage The beverage to be read
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 */
	public void readBeverage(BeverageImplementation beverage) throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException, ParseException {
		Vector<Vector<Object>> query_result_food = Broker.getBroker()
				.read("SELECT Name, Cost FROM Food WHERE FoodId = " + beverage.getID() + ";");
		for (int i = 0; i < query_result_food.size(); i++) {
			beverage.setName(query_result_food.get(i).get(0).toString());
			beverage.setCost(Float.valueOf(query_result_food.get(i).get(2).toString()));
		}

		Vector<Vector<Object>> query_result_drink = Broker.getBroker()
				.read("SELECT inStorage FROM Drink WHERE FoodId = " + beverage.getID() + ";");
		for (int i = 0; i < query_result_drink.size(); i++) {
			beverage.setAmount(Float.valueOf(query_result_drink.get(i).get(0).toString()));
		}

		if (beverage.getOrder() != null) {
			Vector<Vector<Object>> query_result_quantity = Broker.getBroker()
					.read("SELECT Quantity, TimeReady, TimeDelivered FROM OrderContent WHERE OrderId = "
							+ beverage.getOrder().getID() + " AND FoodId = " + beverage.getID() + ";");
			for (int i = 0; i < query_result_quantity.size(); i++) {
				beverage.setQuantity(Integer.valueOf(query_result_quantity.get(i).get(0).toString()));
				if (query_result_quantity.get(i).get(1) != null)
					beverage.setTimeReady(
							BeverageDAO.mysqlDateTimeSDF.parse(query_result_quantity.get(i).get(1).toString()));
				if (query_result_quantity.get(i).get(2) != null)
					beverage.setTimeDelivered(
							BeverageDAO.mysqlDateTimeSDF.parse(query_result_quantity.get(i).get(2).toString()));
			}
		}
	}

	/**
	 * @param beverage The beverage to be created
	 * @return The number of modified rows
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int createBeverage(BeverageImplementation beverage)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		int modifiedRows = Broker.getBroker().update("INSERT INTO Food (Name, Type, Cost) VALUES ('"
				+ beverage.getName() + "', 'DRINKS', " + beverage.getCost() + ");");
		Vector<Vector<Object>> query_result_id = Broker.getBroker().read("SELECT LAST_INSERT_ID();");
		for (int i = 0; i < query_result_id.size(); i++) {
			beverage.setID(Integer.valueOf(query_result_id.get(i).get(0).toString()));
			modifiedRows += Broker.getBroker().update("INSERT INTO Drink (FoodId, InStorage) VALUES ("
					+ beverage.getID() + ", " + beverage.getAmount() + ");");
		}
		return modifiedRows;
	}

	/**
	 * @param beverage The beverage to be updated
	 * @return The number of modified rows
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int updateBeverage(BeverageImplementation beverage)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		int modifiedRows = Broker.getBroker().update("UPDATE Food SET Name='" + beverage.getName() + ", Cost="
				+ beverage.getCost() + " WHERE FoodId = " + beverage.getID() + ";");

		modifiedRows += Broker.getBroker().update(
				"UPDATE Drink SET InStorage = " + beverage.getAmount() + " WHERE FoodId = " + beverage.getID() + ";");

		if (beverage.getOrder() != null) {
			modifiedRows += Broker.getBroker()
					.update("UPDATE OrderContent SET Quantity=" + beverage.getQuantity() + ", TimeReady='"
							+ BeverageDAO.mysqlDateTimeSDF.format(beverage.getTimeReady()) + "', TimeDelivered='"
							+ BeverageDAO.mysqlDateTimeSDF.format(beverage.getTimeDelivered()) + "' WHERE OrderId="
							+ beverage.getOrder().getID() + " AND FoodId=" + beverage.getID() + ";");
		}
		return modifiedRows;
	}

	/**
	 * @param beverage The beverage to be deleted
	 * @return The number of modified rows
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int deleteBeverage(BeverageImplementation beverage)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		int modifiedRows = Broker.getBroker().update("DELETE FROM Drink WHERE FoodId = " + beverage.getID() + ";");
		modifiedRows += Broker.getBroker().update("DELETE FROM Food WHERE FoodId = " + beverage.getID() + ";");
		return modifiedRows;
	}
}