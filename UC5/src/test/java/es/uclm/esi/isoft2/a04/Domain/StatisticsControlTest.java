package es.uclm.esi.isoft2.a04.Domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StatisticsControlTest {

	StatisticsControl tester = new StatisticsControl();
	
	TableImplementation table = new TableImplementation(2); //CHECK WITH DATABASE
	int seatNumber = 2; //CHECK WITH DATABASE
	int restaurantID = 2; //CHECK WITH DATABASE
	String city = "Córdoba"; //CHECK WITH DATABASE
	
	@Test
	public void testGetAverageCommandTime() throws Exception {
		assertTrue(tester.getAverageCommandTime(table, seatNumber, restaurantID, city) > 0);
	}

	@Test
	public void testGetAverageMealPreparationTime() throws Exception {
		assertTrue(tester.getAverageCommandTime(table, seatNumber, restaurantID, city) > 0);
	}

	@Test
	public void testGetAverageCheckDeliveryTime() throws Exception {
		assertTrue(tester.getAverageCommandTime(table, seatNumber, restaurantID, city) > 0);
	}

	@Test
	public void testGetAverageTablePreparationTime() throws Exception {
		assertTrue(tester.getAverageCommandTime(table, seatNumber, restaurantID, city) > 0);
	}

}
