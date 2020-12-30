package es.uclm.esi.isoft2.a04.Domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DishTest {

	Dish tester = new Dish();
	

	@Test
	public void testType() throws Exception {
		tester.setType(3);
		assertEquals(3, tester.getType());
	}

	@Test
	public void testMaximumAvailableIngredients() throws Exception {
		IngredientImplementation ingredient = new IngredientImplementation(1);
		ingredient.setAmount(5);
		IngredientImplementation[] array = 
				new IngredientImplementation[] {
					ingredient
				};
		
		tester.setIngredients(array);
		assertEquals((int)5, tester.getMaximumAvailable());
		int i =0;
		for(Ingredient ing: tester.getIngredients()) {
			assertEquals(ing.getID(), array[i].getID());
			i++;
		}
	}

}
