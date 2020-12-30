package es.uclm.esi.isoft2.a04.Domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BeverageTest {

	Beverage tester = new Beverage(); 


	@Test
	public void testType() throws Exception {
		tester.setType(1);
		assertEquals(1, tester.getType());
	}

	
	@Test
	public void testMaximumAvailableAmount() throws Exception {
		tester.setAmount((float) 3.2);
		assertEquals((float) 3.2, tester.getAmount());
		assertEquals((int)3.2, tester.getMaximumAvailable());
	}

	@Test
	public void testIngredients() throws Exception {
		IngredientImplementation[] array = 
			new IngredientImplementation[] {
				new IngredientImplementation(1),
				new IngredientImplementation(2),
				new IngredientImplementation(3),
				new IngredientImplementation(4)
			};
		tester.setIngredients(array);
		int i =0;
		for(Ingredient ing: tester.getIngredients()) {
			assertEquals(ing.getID(), array[i].getID());
			i++;
		}
	}

}
