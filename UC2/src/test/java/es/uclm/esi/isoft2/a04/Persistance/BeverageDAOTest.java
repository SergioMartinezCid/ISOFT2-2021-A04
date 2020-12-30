package es.uclm.esi.isoft2.a04.Persistance;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import es.uclm.esi.isoft2.a04.Domain.Beverage;

public class BeverageDAOTest {

	BeverageDAO tester = new BeverageDAO();
	
	
	@Test
	public void testReadAllBeverages() throws Exception {
		assertNotNull(tester.readAllBeverages());
	}

	@Test
	public void testReadBeverage() throws Exception {
		Beverage beverage = new Beverage();
		tester.readBeverage(beverage);
		assertNotEquals("", beverage.getName());
	}

	@Test
	public void testCreateBeverage() throws Exception {
		Beverage beverage = new Beverage();
		beverage.setName("Test beverage");
		assertNotEquals(0, tester.createBeverage(beverage));
	}

	@Test
	public void testUpdateBeverage() throws Exception {
		Beverage beverage = new Beverage();
		beverage.setName("Test beverage");
		assertNotEquals(0, tester.updateBeverage(beverage));
	}

	@Test
	public void testDeleteBeverage() throws Exception {
		Beverage beverage = new Beverage();
		beverage.setName("Test beverage");
		assertNotEquals(0, tester.deleteBeverage(beverage));
	}

}
