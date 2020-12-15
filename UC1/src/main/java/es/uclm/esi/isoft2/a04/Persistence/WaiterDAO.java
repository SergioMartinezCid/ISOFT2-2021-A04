package es.uclm.esi.isoft2.a04.Persistence;



import java.sql.SQLException;
import java.util.Vector;

import es.uclm.esi.isoft2.a04.Domain.Table;
import es.uclm.esi.isoft2.a04.Domain.TableImplementation;
import es.uclm.esi.isoft2.a04.Domain.WaiterImplementation;
import es.uclm.esi.isoft2.a04.Persistance.Broker;


public class WaiterDAO {

	public WaiterImplementation[] readAllWaiters() {
		
		Vector<Vector<Object>> sql_result = new Vector<Vector<Object>>();
		Vector<WaiterImplementation> result = new Vector<>();

		WaiterImplementation[] waiters_implementation;
		
		String sql = "SELECT * FROM Waiters"; //Sql sentence

		sql_result  = Broker.getBroker().read(sql);
		
		if(sql_result.size() > 0) {
			
			for(Vector<Object> waiter : sql_result) {
				
				result.add(new WaiterImplementation(Integer.parseInt(waiter.get(0).toString())));
				
			}
		
		}
		
		waiters_implementation = new WaiterImplementation [result.size()];
		
		for (int i = 0; i<waiters_implementation.length; i++) {
			
			waiters_implementation[i] = result.remove(i); 
			
		}

		return waiters_implementation;
		
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
		
		String sql = "INSERT INTO Waiters VALUES ("+ waiter.getID()+")"; //Sql sentence
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