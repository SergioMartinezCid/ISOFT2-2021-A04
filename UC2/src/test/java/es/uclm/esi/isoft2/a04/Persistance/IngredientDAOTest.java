package es.uclm.esi.isoft2.a04.Persistance;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import es.uclm.esi.isoft2.a04.Domain.Ingredient;
import es.uclm.esi.isoft2.a04.Domain.IngredientImplementation;

public class IngredientDAOTest {

	IngredientDAO tester = new IngredientDAO();
	
	@Test
	public void testReadAllIngredients() throws Exception {
		assertNotNull(tester.readAllIngredients());
	}

	@Test
	public void testReadIngredient() throws Exception {
		IngredientImplementation ingredient = new IngredientImplementation();
		tester.readIngredient(ingredient);
		assertNotEquals("", ingredient.getName());
	}

	@Test
	public void testCreateIngredient() throws Exception {
		IngredientImplementation ingredient = new IngredientImplementation();
		ingredient.setName("Test Ingredient");
		assertNotEquals(0, tester.createIngredient(ingredient));
	}

	@Test
	public void testUpdateIngredient() throws Exception {
		IngredientImplementation Ingredient = new IngredientImplementation();
		Ingredient.setName("Test Ingredient");
		assertNotEquals(0, tester.updateIngredient(Ingredient));
	}

	@Test
	public void testDeleteIngredient() throws Exception {
		IngredientImplementation ingredient = new IngredientImplementation();
		ingredient.setName("Test Ingredient");
		assertNotEquals(0, tester.deleteIngredient(ingredient));
	}

}
