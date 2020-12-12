package es.uclm.esi.isoft2.a04.Persistence;



import java.sql.SQLException;
import java.util.Vector;

import es.uclm.esi.isoft2.a04.Domain.WaiterImplementation;
import es.uclm.esi.isoft2.a04.Persistance.Broker;


public class WaiterDAO {

	public WaiterImplementation[] readAllWaiters() {
		
		Vector<Vector<Object>> result = new Vector<Vector<Object>>();
		WaiterImplementation[] waiters;
		
		String sql = "SELECT * FROM Bookings"; //Sql sentence
		
		return null;
	}

	/**
	 * 
	 * @param waiter
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void readWaiter(WaiterImplementation waiter) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		String sql = "SELECT * FROM Waiters WHERE WaiterID ="+ waiter.getID(); //Sql sentence
		Broker.getBroker().read(sql);
		
	}

	/**
	 * 
	 * @param waiter
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public int createWaiter(WaiterImplementation waiter) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		String sql = "INSERT INTO Waiters VALUES ("+ waiter.getID()+", "+waiter.getAssignedTables()+")"; //Sql sentence
		return Broker.getBroker().update(sql);
		
	}

	/**
	 * 
	 * @param waiter
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public int updateWaiter(WaiterImplementation waiter) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		String sql = "UPDATE Waiters WHERE WaiterID ="+waiter.getID(); //Sql sentence
		return Broker.getBroker().update(sql);
		
	}

	/**
	 * 
	 * @param waiter
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public int deleteWaiter(WaiterImplementation waiter) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		String sql = "DELETE FROM Waiters WHERE WaiterID ="+waiter.getID(); //Sql sentence
		return Broker.getBroker().update(sql);
		
	}

}