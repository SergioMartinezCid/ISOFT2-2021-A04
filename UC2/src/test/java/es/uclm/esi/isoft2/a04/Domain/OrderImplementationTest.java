package es.uclm.esi.isoft2.a04.Domain;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Date;
import java.util.function.Function;

import org.junit.Test;

public class OrderImplementationTest {

	WaiterImplementation waiter = new WaiterImplementation(3);
	TableImplementation table = new TableImplementation(4);
	OrderImplementation tester = new OrderImplementation(waiter, table); 
	

	@Test
	public void testID() throws Exception {
		tester.setID(5);
		assertEquals(5, tester.getID());
	}

	@Test
	public void testFood() throws Exception {
		FoodImplementation[] array =
			new FoodImplementation[] {
			new Dish(),
			new Beverage()
		};
		tester.setFood(array);
		
		int i = 0;
		for(Food food: tester.getFood()){
			assertEquals(food, array[i]);
			i++;
		}
	}

	@Test
	public void testGetCost() throws Exception {
		FoodImplementation[] array =
				new FoodImplementation[] {
				new Dish(),
				new Beverage()
			};
		tester.setFood(array);
		float cost = Arrays.stream(array).map(f -> (double)f.getCost()* f.getQuantity()).reduce(Double::sum).get().floatValue();
		assertEquals(cost, tester.getCost());
	}

	@Test
	public void testState() throws Exception {
		tester.setState(2);
		assertEquals(2, tester.getState());
	}


	@Test
	public void testGetDatetime() throws Exception {
		Date time = new Date(2000, 3, 3);
		tester.setDatetime(time);
		assertEquals(time, tester.getDatetime());
	}

	@Test
	public void testGetWaiter() throws Exception {
		assertEquals(waiter, tester.getWaiter());
	}
	
	@Test
	public void testGetTable() throws Exception {
		assertEquals(table, tester.getTable());
	}
}
