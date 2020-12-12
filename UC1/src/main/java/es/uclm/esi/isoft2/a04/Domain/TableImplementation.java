package es.uclm.esi.isoft2.a04.Domain;

import java.util.Date;

import es.uclm.esi.isoft2.a04.Persistence.TableDAO;


public class TableImplementation implements Table{

	private int id;
	private int seatsNumber;
	private int state = FREE;
	private TableDAO tableDAO;

	public TableImplementation() {
		this.tableDAO = new TableDAO();
	}
	/**
	 * 
	 * @param seatsNumber
	 */
	public TableImplementation(int id, int seatsNumber) {
		this.id = id;
		this.seatsNumber = seatsNumber;
		this.tableDAO = new TableDAO();
	}
	
	public int getId() {
		return this.id;
	}

	public int getSeatsNumber() {
		return this.seatsNumber;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getState() {
		return this.state;
	}
	
	public TableDAO getTableDAO() {
		return this.tableDAO;
	}
	
	public int checkAvailability(Date turn, int seats) {
		//this.tableDAO.checkAvailability(turn, seats);
		return 0;
	}

	public void readAll() {
		this.tableDAO.readAllTables();
	}
	
	public int read() {
		this.tableDAO.readTable(this);
		return 0;
	}
	
	public int insert() {
		//this.tableDAO.insert(this);
		return 0;
	}

	public int update() {
		this.tableDAO.updateTable(this);
		return 0;
	}

	public int delete() {
		this.tableDAO.deleteOrder(this);
		return 0;
	}
	public void setState() {
		// TODO Auto-generated method stub
		
	}
	public void setSeats() {
		//TODO
	}
	public int getSeats() {
		return seatsNumber;
	}
	public int getID() {
		return id;
	}
}