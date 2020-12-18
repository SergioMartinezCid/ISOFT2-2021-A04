package es.uclm.esi.isoft2.a04.Persistance;

import java.sql.SQLException;
import java.util.Vector;

import es.uclm.esi.isoft2.a04.Domain.*;
import jdk.internal.joptsimple.internal.Rows;


public class FoodDAO {

	public FoodImplementation[] readAllFood() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		FoodImplementation[] foods;
		Vector<Vector<Object>> rows;
		rows = Broker.getBroker().read("SELECT * FROM Foods;");
		foods = new FoodImplementation[rows.size()];
		int i = 0;
		for(Vector<Object> row :rows) {
			foods[i] = null; // HERE it's needed to decide what's the type of the abstact class
			foods[i].read();
			i++;
		}
		return foods;
	}

	/**
	 * 
	 * @param food
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void readFood(FoodImplementation food) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Vector<Vector<Object>> col;
		IngredientImplementation[] ingredients;
		col = Broker.getBroker().read("SELECT * FROM Foods WHERE FoodId = "+food.getID()+";");
		Vector<Object> row = col.get(0);
			/*aux = col[i];
			order.setWaiter(aux[1]);
			order.setTable(aux[2]);
			order.setState(aux[3]);
			order.setDatetime(aux[4]);
			col = br.getBroker().read("SELECT * FROM FoodIngredients WHERE FoodId = "+food.getID()+";");
			ingredients = new IngredientImplementation[col.size()];
			for (int j=0; j<col.size(); j++) {
				ingredients[i] = new IngredientImplementation(aux[1]);
			}
			order.setIngredients(ingredients);
			*/
	}

}