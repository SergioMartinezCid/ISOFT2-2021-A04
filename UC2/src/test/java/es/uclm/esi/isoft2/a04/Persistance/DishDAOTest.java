package es.uclm.esi.isoft2.a04.Persistance;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import es.uclm.esi.isoft2.a04.Domain.Dish;
import es.uclm.esi.isoft2.a04.Domain.Dish;

public class DishDAOTest {
	
	DishDAO tester = new DishDAO();

	@Test
	public void testReadAllDishes() throws Exception {
		assertNotNull(tester.readAllDishes());
	}

	@Test
	public void testReadDish() throws Exception {
		Dish dish = new Dish();
		tester.readDish(dish);
		assertNotEquals("", dish.getName());
	}

	@Test
	public void testCreateDish() throws Exception {
		Dish dish = new Dish();
		dish.setName("Test Dish");
		assertNotEquals(0, tester.createDish(dish));
	}

	@Test
	public void testUpdateDish() throws Exception {
		Dish dish = new Dish();
		dish.setName("Test Dish");
		assertNotEquals(0, tester.updateDish(dish));
	}

	@Test
	public void testDeleteDish() throws Exception {
		Dish dish = new Dish();
		dish.setName("Test Dish");
		assertNotEquals(0, tester.deleteDish(dish));
	}

}
