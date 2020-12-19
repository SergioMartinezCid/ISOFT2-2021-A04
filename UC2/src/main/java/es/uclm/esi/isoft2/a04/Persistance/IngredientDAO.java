package es.uclm.esi.isoft2.a04.Persistance;

import java.sql.SQLException;
import java.util.Vector;

import es.uclm.esi.isoft2.a04.Domain.*;

public class IngredientDAO {

	public IngredientImplementation[] readAlllIngredients() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Vector<Vector<Object>> cols; 	
		cols = Broker.getBroker().read("SELECT * FROM Ingredients");
		IngredientImplementation[] ingredients = new IngredientImplementation[cols.size()];
		IngredientImplementation ingredient;
		for(Vector<Object> row: cols) {
			ingredient = new IngredientImplementation();
			//TODO set id, or implement id in constructor
			ingredient.setName((String)row.get(1));
			ingredient.setAmount((float)row.get(2));
			ingredient.read();
		}
		return ingredients;
	}

	/**
	 * 
	 * @param ingredient
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void readIngredient(IngredientImplementation ingredient) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Vector<Vector<Object>> col;
		IngredientImplementation[] ingredients;
		col = Broker.getBroker().read("SELECT * FROM ingredients WHERE IngredientId = " + ingredient.getID()+";");
		Vector<Object> row = col.get(0);
		ingredient = new IngredientImplementation();
		ingredient.setName((String)row.get(1));
		ingredient.setAmount((float) row.get(2));
		ingredient.read();
	}

	/**
	 * 
	 * @param ingredient
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public int updateIngredient(IngredientImplementation ingredient) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return  Broker.getBroker().update("UPDATE Ingredients SET Amount="+ingredient.getAmount() + ", "
				+ "Name=" + ingredient.getName() +" WHERE IngredientId = " +ingredient.getID()+";");
	}

}