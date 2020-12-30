package es.uclm.esi.isoft2.a04.Domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class TableImplementationTest {

	TableImplementation tester = new TableImplementation(0);
	

	@Test
	public void testState() {
		tester.setState(TableImplementation.SERVED);
		assertEquals(TableImplementation.SERVED, tester.getState());
		assertNotEquals(TableImplementation.BUSY, tester.getState());
		
	}

	@Test
	public void testSeats() {
		tester.setSeats(4);
		assertEquals(4, tester.getSeats());
		assertNotEquals(2, tester.getSeats());
	}

	@Test
	public void testID(){
		int id = 5;
		tester.setID(id);
		assertEquals(id, tester.getID());
	}


	@Test
	public void testRestaurantID() {
		int id = 5;
		tester.setRestaurantID(id);
		assertEquals(id, tester.getRestaurantID());
	}
	
	@Test
	public void testGetCity() {
		String name = "Cádiz";
		tester.setCity(name);
		assertEquals(name, tester.getCity());
	}
}
