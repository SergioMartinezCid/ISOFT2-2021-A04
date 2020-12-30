package es.uclm.esi.isoft2.a04.Persistance;

import java.sql.SQLException;
import java.util.Vector;

import es.uclm.esi.isoft2.a04.Domain.*;

/**
 * @version 0.1.0
 *
 */
public class IngredientDAO {

	/**
	 * @return An array with all the ingredients stored in the database
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public IngredientImplementation[] readAllIngredients()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Vector<Vector<Object>> query_result;
		query_result = Broker.getBroker().read("SELECT * FROM Ingredient;");
		IngredientImplementation[] ingredients = new IngredientImplementation[query_result.size()];
		for (int i = 0; i < query_result.size(); i++) {
			ingredients[i] = new IngredientImplementation(Integer.valueOf(query_result.get(i).get(0).toString()));
			ingredients[i].read();
		}
		return ingredients;
	}

	/**
	 * @param ingredient The ingredient to be read
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void readIngredient(IngredientImplementation ingredient)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Vector<Vector<Object>> query_result_ingredient = Broker.getBroker()
				.read("SELECT Name, InStorage FROM Ingredient WHERE IngredientId = " + ingredient.getID() + ";");
		for (int i = 0; i < query_result_ingredient.size(); i++) {
			ingredient.setName(query_result_ingredient.get(i).get(0).toString());
			ingredient.setAmount(Integer.valueOf(query_result_ingredient.get(i).get(0).toString()));
		}

		if (ingredient.getDish() != null) {
			Vector<Vector<Object>> query_result_dish = Broker.getBroker()
					.read("SELECT Quantity FROM DishIngredients WHERE DishId = " + ingredient.getDish().getID()
							+ " AND  IngredientId=" + ingredient.getID() + ";");
			for (int i = 0; i < query_result_dish.size(); i++) {
				ingredient.setQuantityRequired(Float.valueOf(query_result_dish.get(i).get(0).toString()));
			}
		}
	}

	/**
	 * @param ingredient The ingredient to be created
	 * @return The number of modified rows
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int createIngredient(IngredientImplementation ingredient)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Vector<Vector<Object>> query_result_id;
		int modifiedRows;
		modifiedRows = Broker.getBroker().update("INSERT INTO Ingredient (Name, Quantity) VALUES ('"
				+ ingredient.getName() + "', " + ingredient.getAmount() + ");");
		query_result_id = Broker.getBroker().read("SELECT LAST_INSERT_ID();");
		for (int i = 0; i < query_result_id.size(); i++) {
			ingredient.setID(Integer.valueOf(query_result_id.get(i).get(0).toString()));
		}
		return modifiedRows;
	}

	/**
	 * @param ingredient The ingredient to be updated
	 * @return The number of modified rows
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int updateIngredient(IngredientImplementation ingredient)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		int modifiedRows = Broker.getBroker().update("UPDATE Ingredient SET Quantity=" + ingredient.getAmount() + ", "
				+ "Name='" + ingredient.getName() + "' WHERE IngredientId = " + ingredient.getID() + ";");
		if (ingredient.getDish() != null) {
			modifiedRows += Broker.getBroker()
					.update("UPDATE DishIngredients SET Quantity=" + ingredient.getQuantityRequired() + ";");
		}
		return modifiedRows;
	}

	/**
	 * @param ingredient The ingredient to be deleted
	 * @return The number of modified rows
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int deleteIngredient(IngredientImplementation ingredient)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return Broker.getBroker().update("DELETE FROM Ingredient WHERE IngredientId =" + ingredient.getID() + ";");
	}
}