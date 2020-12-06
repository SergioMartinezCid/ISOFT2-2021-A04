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
		// TODO - implement TableBooking.bookTable
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns an id of the table and -1 if no table could be assigned
	 * @param seats
	 * @param Turn
	 */
	public int findTable(int seats, Date Turn) {
		// TODO - implement TableBooking.findTable
		throw new UnsupportedOperationException();
	}

	/**
	 * Assigns a waiter to the given table
	 * @param tableID
	 */
	public int assignWaiter(int tableID) {
		// TODO - implement TableBooking.assignWaiter
		throw new UnsupportedOperationException();
	}

}