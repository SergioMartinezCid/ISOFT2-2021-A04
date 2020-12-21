package es.uclm.esi.isoft2.a04.Domain;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class WaiterImplementationTest {

	WaiterImplementation tester = new WaiterImplementation(5);
	
	@Test
	public void testID() {
		int id = 8;
		tester.setID(id);
		assertEquals(id, tester.getID());
	}

	@Test
	public void testName() {
		String name = "Francisco";
		tester.setName(name);
		assertEquals(name, tester.getName());
	}


	@Test
	public void testAssignedTables()  {
		assertNotNull(tester.getAssignedTables());
		TableImplementation table = new TableImplementation();
		
		tester.assignTable(table);
		assertEquals(table, tester.getAssignedTables().get(0));
	}

	@Test
	public void testClearTables() throws Exception {
		for(int i = 0; i < 10; i++)
			tester.assignTable(new TableImplementation(i));
		tester.clearTables();
		assertEquals(0, tester.getAssignedTables().size());
	}

}
