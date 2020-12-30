package es.uclm.esi.isoft2.a04.Domain;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.List;

import org.junit.Test;

public class IngredientControlTest {

	public IngredientControl tester = new IngredientControl();
	
	@Test
	public void testUpdateIngredientsFromForecast() throws Exception {
		IngredientImplementation[] list = new Ingredient[] {new IngredientImplementation(), new IngredientImplementation()};
		Integer[] amounts = new Integer[] {2, 4};
		assertNotEquals(0, tester.updateIngredientsFromForecast(list, amounts));;
	}

	@Test
	public void testUpdateBeveragesFromForecast() throws Exception {
		BeverageImplementation[] list = new BeverageImplementation[] {new BeverageImplementation(), new BeverageImplementation()};
		Integer[] amounts = new Integer[] {2, 4};
		assertNotEquals(0, tester.updateBeveragesFromForecast(list, amounts));;
	}

	@Test
	public void testGetIngredientsBelowThreshold() throws Exception {
		IngredientImplementation ingredient = IngredientImplementation();
		int threshold = 2;
		assertNotNull(tester.getIngredientsBelowThreshold(ingredient, threshold));
		for(IngredientImplementation ingredient: tester.getIngredientsBelowThreshold(ingredient, threshold))
			assertTrue(ingredient.getAmount() < threshold);
	}

	
	
	
}

