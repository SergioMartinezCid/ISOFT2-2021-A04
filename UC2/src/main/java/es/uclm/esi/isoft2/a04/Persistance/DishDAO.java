package es.uclm.esi.isoft2.a04.Persistance;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import es.uclm.esi.isoft2.a04.Domain.*;

/**
 * @version 0.1.0
 *
 */
public class DishDAO {

	private static SimpleDateFormat mysqlDateTimeSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * @return An array with all the dishes in the database
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 * @throws InvalidTypeException
	 */
	public Dish[] readAllDishes() throws InstantiationException, IllegalAccessException, ClassNotFoundException,
			SQLException, InvalidTypeException, ParseException {
		Vector<Vector<Object>> query_result = Broker.getBroker().read("SELECT FoodId FROM Dish;");
		Dish[] dishes = new Dish[query_result.size()];
		for (int i = 0; i < query_result.size(); i++) {
			dishes[i] = new Dish(Integer.valueOf(query_result.get(i).get(0).toString()));
			dishes[i].read();
		}
		return dishes;
	}

	/**
	 * @param type The string used in the database for representing the type of dish
	 * @return The integer used in the Food class for representing the type of dish
	 */
	private int getIntRepresentationOfType(String type) {
		int intRepresentation = 0;
		switch (type.toUpperCase()) {
		case "DRINKS":
			intRepresentation = 0;
			break;
		case "STARTERS":
			intRepresentation = 1;
			break;
		case "FIRST_COURSE":
			intRepresentation = 2;
			break;
		case "SECOND_COURSE":
			intRepresentation = 3;
			break;
		case "DESSERT":
			intRepresentation = 4;
			break;
		}
		return intRepresentation;
	}

	private String getStringRepresentationOfType(int type) {
		String stringRepresentation = null;
		switch (type) {
		case 0:
			stringRepresentation = "drinks";
		case 1:
			stringRepresentation = "starters";
			break;
		case 2:
			stringRepresentation = "first_course";
			break;
		case 3:
			stringRepresentation = "second_course";
			break;
		case 4:
			stringRepresentation = "dessert";
			break;
		}
		return stringRepresentation;
	}

	/**
	 * @param dish The instance to be read
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws InvalidTypeException
	 * @throws ParseException
	 */
	public void readDish(Dish dish) throws InstantiationException, IllegalAccessException, ClassNotFoundException,
			SQLException, InvalidTypeException, ParseException {
		Vector<Vector<Object>> query_result_food = Broker.getBroker()
				.read("SELECT Name, Type, Cost FROM Food WHERE FoodId = " + dish.getID() + ";");
		for (int i = 0; i < query_result_food.size(); i++) {
			dish.setName(query_result_food.get(i).get(0).toString());
			dish.setType(getIntRepresentationOfType(query_result_food.get(i).get(1).toString()));
			dish.setCost(Float.valueOf(query_result_food.get(i).get(2).toString()));
		}
		
		Vector<Vector<Object>> query_result_ingredient = Broker.getBroker()
				.read("SELECT IngredientId FROM DishIngredients WHERE DishId = " + dish.getID() + ";");

		Ingredient[] ingredients = new Ingredient[query_result_ingredient.size()];
		for(int i=0; i<ingredients.length; i++) {
			ingredients[i] = new IngredientImplementation((Integer)query_result_ingredient.get(i).get(0), dish);
			ingredients[i].read();
		}
		dish.setIngredients(ingredients);

		if (dish.getOrder() != null) {
			Vector<Vector<Object>> query_result_order = Broker.getBroker()
					.read("SELECT Quantity, TimeReady, TimeDelivered FROM OrderContent WHERE OrderId = "
							+ dish.getOrder().getID() + " AND FoodId = " + dish.getID() + ";");
			for (int i = 0; i < query_result_order.size(); i++) {
				dish.setQuantity(Integer.valueOf(query_result_order.get(i).get(0).toString()));
				if (query_result_order.get(i).get(1) != null)
					dish.setTimeReady(DishDAO.mysqlDateTimeSDF.parse(query_result_order.get(i).get(1).toString()));
				if (query_result_order.get(i).get(2) != null)
					dish.setTimeDelivered(DishDAO.mysqlDateTimeSDF.parse(query_result_order.get(i).get(2).toString()));
			}
		}
	}

	/**
	 * This method will not create the relation with Order, such relation must be
	 * created by calling update
	 * 
	 * @param dish The dish to be created
	 * @return The number of modified rows
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int createDish(Dish dish)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		int modifiedRows = Broker.getBroker().update("INSERT INTO Food (Name, Type, Cost) VALUES ('" + dish.getName()
				+ "', '" + getStringRepresentationOfType(dish.getType()) + "', " + dish.getCost() + ");");

		Vector<Vector<Object>> query_result_id = Broker.getBroker().read("SELECT LAST_INSERT_ID();");
		for (int i = 0; i < query_result_id.size(); i++) {
			dish.setID(Integer.valueOf(query_result_id.get(i).get(0).toString()));
			modifiedRows += Broker.getBroker().update("INSERT INTO Dish (FoodId) VALUES (" + dish.getID() + ");");
		}

		for (IngredientImplementation ingredient : (IngredientImplementation[]) dish.getIngredients()) {
			modifiedRows += Broker.getBroker()
					.update("INSERT INTO DishIngredients (DishId, IngredientId, Quantity) VALUES (" + dish.getID()
							+ ", " + ingredient.getID() + ", " + ingredient.getQuantityRequired() + ");");
		}

		return modifiedRows;
	}

	/**
	 * @param dish The dish to be updated in the database
	 * @return The number of modified rows
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int updateDish(Dish dish)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		int modifiedRows = Broker.getBroker()
				.update("UPDATE Food SET Name='" + dish.getName() + "', Type='"
						+ getStringRepresentationOfType(dish.getType()) + "', Cost=" + dish.getCost()
						+ " WHERE FoodId = " + dish.getID() + ";");

		Vector<Vector<Object>> query_result_dishingredient = Broker.getBroker()
				.read("SELECT IngredientId FROM DishIngredients WHERE DishId=" + dish.getID() + ";");
		Iterator<Vector<Object>> rowIterator;
		for (IngredientImplementation ingredient : (IngredientImplementation[]) dish.getIngredients()) {
			rowIterator = query_result_dishingredient.stream()
					.filter(row -> Integer.valueOf(row.get(0).toString()) == ingredient.getID()).iterator();
			if (rowIterator.hasNext()) {
				modifiedRows += ingredient.update();
				query_result_dishingredient.remove(rowIterator.next());
			} else {
				modifiedRows += Broker.getBroker()
						.update("INSERT INTO DishIngredients (DishId, IngredientId, Quantity) VALUES (" + dish.getID()
								+ ", " + ingredient.getID() + ", " + ingredient.getQuantityRequired() + ");");
			}
		}

		if (dish.getOrder() != null) {
			modifiedRows += Broker.getBroker()
					.update("UPDATE OrderContent SET Quantity=" + dish.getQuantity() + ", TimeReady='"
							+ DishDAO.mysqlDateTimeSDF.format(dish.getTimeReady()) + "', TimeDelivered='"
							+ DishDAO.mysqlDateTimeSDF.format(dish.getTimeDelivered()) + "' WHERE OrderId="
							+ dish.getOrder().getID() + " AND FoodId=" + dish.getID() + ";");
		}
		for (Vector<Object> row : query_result_dishingredient) {
			modifiedRows += Broker.getBroker().update("DELETE FROM DishIngredients WHERE DishId = " + dish.getID()
					+ " AND IngredientId = " + row.get(0).toString() + ";");
		}
		return modifiedRows;
	}

	/**
	 * @param dish The dish to be deleted from the database
	 * @return The number of modified rows
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int deleteDish(Dish dish)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		int modifiedRows = Broker.getBroker().update("DELETE FROM Dish WHERE FoodId = " + dish.getID() + ";");
		modifiedRows += Broker.getBroker().update("DELETE FROM Food WHERE FoodId = " + dish.getID() + ";");
		return modifiedRows;
	}
}