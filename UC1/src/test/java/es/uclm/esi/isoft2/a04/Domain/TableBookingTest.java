package es.uclm.esi.isoft2.a04.Domain;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.sql.SQLException;
import java.util.Date;

import org.junit.Test;

import com.mysql.cj.jdbc.integration.c3p0.MysqlConnectionTester;

import es.uclm.esi.isoft2.a04.Domain.Booking.TURN;

public class TableBookingTest {
	
	TableBooking tester = new TableBooking();
	Date date = new Date(); //Proper date
	TURN turn = TURN.L3;
	String clientID = "Maria";
	
	@Test
	public void testBookTable() throws Exception {
		assertThrows(TableNotFoundException.class, () -> tester.bookTable(date, turn, 3, clientID));
		//assertThrows(SQLException.class, () -> tester.bookTable(date, turn, 3, clientID));

	}
	
	@Test
	public void testFindTable() {
		assertThrows(Exception.class, () -> tester.findTable(3, date, turn));
		try {
			assertNotNull(tester.findTable(4, date, turn));
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAssignWaiter() {
		//TableImplementation table = new TableImplementation();
		//assertThrows(Exception.class, () -> tester.assignWaiter(table));
		throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testCancelBooking() {
		//tester.bookTable(date, turn, 4, clientID);
		throw new RuntimeException("not yet implemented");
	}

}
