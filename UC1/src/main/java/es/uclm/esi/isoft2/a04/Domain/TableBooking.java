package es.uclm.esi.isoft2.a04.Domain;

import java.util.Arrays;
import java.util.Date;

import sun.awt.AWTIcon32_security_icon_bw24_png;

public class TableBooking {

	/**
	 * Returns true if a booking was possible and false if there was no possibility of such a booking
	 * @param turn
	 * @param guests
	 * @param clientID
	 */
	public boolean bookTable(Date turn, int guests, String clientID) {
		int tableID;
		//First check if there is a free table for the Date of booking with the proper number of seats
		tableID = findTable(guests, turn);
		if(tableID != -1){
			assignWaiter(tableID); //If the client arrives on time
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns an id of the table and -1 if no table could be assigned
	 * @param seats
	 * @param Turn
	 */
	public int findTable(int seats, Date Turn) {
		TableImplementation table = new TableImplementation();
		
		return table.checkAvailability(Turn, seats);
		
	}

	/**
	 * Assigns a waiter to the given table
	 * @param tableID
	 */
	public int assignWaiter(int tableID) {
		WaiterImplementation waiter = new WaiterImplementation();
		waiter.readAll();
		WaiterImplementation[] waiters = waiter.getWaiterDAO().readAllWaiters();
		waiter = Arrays.stream(waiters) //Get the waiter with less assigned tables
				.reduce((w1, w2) -> w1.getAssignedTables().size() < w2.getAssignedTables().size()
									? w1 : w2)
				.get();
		
		waiter.assignTable(tableID);
		return waiter.getID();
	}

}