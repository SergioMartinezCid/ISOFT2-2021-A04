package com.uclm.esi.isoft2.a04.Domain;
import java.util.Date;

public class Booking {

	/**
	 * Date and time of the booking
	 */
	private Date Turn;
	private String clientID;
	private int tableID;

	/**
	 * 
	 * @param clientID
	 * @param tableID
	 * @param date
	 */
	public void Booking(String clientID, int tableID, Date date) {
		// TODO - implement Booking.Booking
		throw new UnsupportedOperationException();
	}

	public String getClientID() {
		return this.clientID;
	}

	public int getTableID() {
		return this.tableID;
	}

	public Date getTurn() {
		// TODO - implement Booking.getTurn
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 */
	public void setTableID(int id) {
		this.tableID = id;
	}

	/**
	 * 
	 * @param turn
	 */
	public void setTurn(Date turn) {
		// TODO - implement Booking.setTurn
		throw new UnsupportedOperationException();
	}

}