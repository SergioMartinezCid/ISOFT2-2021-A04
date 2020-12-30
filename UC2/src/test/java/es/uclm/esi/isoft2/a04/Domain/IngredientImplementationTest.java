package es.uclm.esi.isoft2.a04.Domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class IngredientImplementationTest {

	IngredientImplementation tester = new IngredientImplementation(); 

	@Test
	public void testID() throws Exception {
		tester.setID(5);
		assertEquals(5, tester.getID());
	}

	@Test
	public void testAmount() throws Exception {
		tester.setAmount((float)4.5);
		assertEquals((float)4.5, tester.getAmount());
	}
	
	@Test
	public void testName() throws Exception {
		String name= "Test name";
		tester.setName(name);
		assertEquals(name, tester.getName());
	}


	@Test
	public void testQuantityRequired() throws Exception {
		tester.setQuantityRequired((float)6.5);
		assertEquals((float)6.5, tester.getQuantityRequired());
	}


	@Test
	public void testGetDish() throws Exception {
		Dish dish = new Dish(5);
		tester.setDish(dish);
		assertEquals(dish, tester.getDish());
	}

}
