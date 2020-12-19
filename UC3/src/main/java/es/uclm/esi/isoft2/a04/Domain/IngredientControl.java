package es.uclm.esi.isoft2.a04.Domain;

import es.uclm.esi.isoft2.a04.Persistance.Broker;

import java.sql.SQLException;
import java.util.Vector;
import java.util.Arrays;

/**
 * Ingredient Control implementation to allow managing of ingredients in the database
 * @version 0.0.1
 */
public class IngredientControl implements Subject {
	

	/**
	 * Get Ingredient at given index
	 * @param id database array index
	 */
	public Ingredient getIngredient(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		int id_result = -1;
		String name_result = null;
		float amount_result = -1;
		
		Ingredient ingredient;
		
		String sql = "SELECT * FROM Ingredients WHERE IngredientId="+id; //Sql sentence
		
		Vector<Vector<Object>> result_query = Broker.getBroker().read(sql);
		
		if(result_query.size() == 1) {
			
			id_result = (int)(result_query.get(0).get(0));
			name_result = (result_query.get(0).get(1)).toString();
			amount_result = (float)(result_query.get(0).get(2));
			
		}
		
		ingredient = new Ingredient (id_result, name_result, amount_result);
		
		return ingredient;
	}

	public Ingredient[] getAllIngredients() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		Vector<Ingredient> ingredients_list = new Vector<>();
		
		String sql = "SELECT * FROM Ingredients"; //Sql sentence
		
		Vector<Vector<Object>> result_query = Broker.getBroker().read(sql);
		
		if(result_query.size() > 0) {
			
			for(Vector<Object> ingredient: result_query) {
				
				ingredients_list.add(new Ingredient((int)(result_query.get(0).get(0)), (result_query.get(0).get(1)).toString(), (float)(result_query.get(0).get(2))));
			
			}
		}
			
		Ingredient [] ingredients = new Ingredient [ingredients_list.size()];
		
		for(int i = 0; i<ingredients.length; i++) {
			
			ingredients[i] = ingredients_list.remove(0);
			
		}
  }
		

	/**
	 * Set new amount on existing ingredient
	 * @param ingredient Ingredient reference to find
	 * @param amount New amount of desired ingredient
	 */
	public void updateIngredientAmount(Ingredient ingredient, float ammount) {
		
		ingredient.setAmount(ammount);
		
	}

	@Override
	public void attach(Observer o) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public void detach(Observer o) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public void notifyMe() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

}