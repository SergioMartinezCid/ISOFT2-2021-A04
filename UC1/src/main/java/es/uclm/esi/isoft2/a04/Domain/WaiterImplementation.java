package es.uclm.esi.isoft2.a04.Domain;

import java.sql.SQLException;
import java.util.ArrayList;
import es.uclm.esi.isoft2.a04.Persistence.WaiterDAO;

public class WaiterImplementation implements Waiter{

	private int id;
	private ArrayList<Integer> assignedTables;
	private WaiterDAO waiterDAO;

	public WaiterImplementation() {
		this.waiterDAO = new WaiterDAO();
	}

	public WaiterImplementation(int id) {
		this.id = id;
		this.waiterDAO = new WaiterDAO();
	}
	
	public int getID() {
		return this.id;
	}
	public ArrayList<Integer> getAssignedTables(){
		return this.assignedTables;
	}
	public WaiterDAO getWaiterDAO() {
		return this.waiterDAO;
	}
		
	public void assignTable(int tableID) {
		this.assignedTables.add(tableID);
	}

	public void readAll() {
		this.waiterDAO.readAllWaiters();
	}
	
	public int read() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		this.waiterDAO.readWaiter(this);
		return 0;
	}
	
	public int insert() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		this.waiterDAO.createWaiter(this);
		return 0;
	}

	public int update() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		this.waiterDAO.updateWaiter(this);
		return 0;
	}

	public int delete() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		this.waiterDAO.deleteWaiter(this);
		return 0;
	}

	public void attach(Observer o) {
		// TODO Auto-generated method stub
		
	}

	public void detach(Observer o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyMe() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int create() {
		// TODO Auto-generated method stub
		return 0;
	}
}