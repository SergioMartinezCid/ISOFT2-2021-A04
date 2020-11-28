package Domain;

public class TableImplementation {

	private int id;
	private int seatsNumber;
	private int state = free;

	public int getId() {
		return this.id;
	}

	public int getSeatsNumber() {
		return this.seatsNumber;
	}

	/**
	 * 
	 * @param seatsNumber
	 */
	public void Table(int seatsNumber) {a
		// TODO - implement TableImplementation.Table
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param state
	 */
	public void setState(int state) {
		this.state = state;
	}

	public int getSeats() {
		// TODO - implement TableImplementation.getSeats
		throw new UnsupportedOperationException();
	}

	public int getID() {
		// TODO - implement TableImplementation.getID
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param seats
	 */
	public void setSeat(int seats) {
		// TODO - implement TableImplementation.setSeat
		throw new UnsupportedOperationException();
	}

	public int getState() {
		return this.state;
	}

}