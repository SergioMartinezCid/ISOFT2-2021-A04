package com.uclm.esi.isoft2.a04.Domain;

import java.util.Date;

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
			//assignWaiter(tableID); //If the client arrives on time
			return true;
		}else {
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
		for(int i=0; i<waiters.length; i++) {
			if(waiters[i].getAssignedTables().size() < waiter.getAssignedTables().size()) {	//Get the waiter with less assigned tables
				waiter = waiters[i];
			}
		}
		waiter.assignTable(tableID);
		return waiter.getID();
	}

}