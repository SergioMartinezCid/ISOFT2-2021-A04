package es.uclm.esi.isoft2.a04.Persistence;

import java.sql.SQLException;
import java.util.Vector;

import es.uclm.esi.isoft2.a04.Domain.Table;
import es.uclm.esi.isoft2.a04.Domain.TableImplementation;
import es.uclm.esi.isoft2.a04.Domain.WaiterImplementation;
import es.uclm.esi.isoft2.a04.Persistance.Broker;

/**
 * @version 0.1.0
 *
 */
public class WaiterDAO {

	/**
	 * @return Instances of all the WaiterImplementations in the database
	 * @throws NumberFormatException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public WaiterImplementation[] readAllWaiters() throws NumberFormatException, InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {

		Vector<Vector<Object>> query_result = new Vector<Vector<Object>>();

		WaiterImplementation[] waiters;

		String sql = "SELECT WaiterId FROM Waiter;";

		query_result = Broker.getBroker().read(sql);

		waiters = new WaiterImplementation[query_result.size()];

		for (int i = 0; i < query_result.size(); i++) {
			waiters[i] = new WaiterImplementation(Integer.valueOf(query_result.get(i).get(0).toString()));
			waiters[i].read();
		}

		return waiters;

	}

	/**
	 * @param waiter The WaiterImplementationInstance to be read
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void readWaiter(WaiterImplementation waiter)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		Vector<Vector<Object>> query_result_waiter, query_result_table = new Vector<Vector<Object>>();
		String sql_waiter = "SELECT Name FROM Waiter WHERE WaiterId =" + waiter.getID() + ";";
		String sql_table = "SELECT TableId FROM OrderRestaurant WHERE State <> 'PAYED' AND WaiterId = " + waiter.getID()
				+ ";";
		TableImplementation auxTable;
		query_result_waiter = Broker.getBroker().read(sql_waiter);
		query_result_table = Broker.getBroker().read(sql_table);

		for (int i = 0; i < query_result_waiter.size(); i++) {
			waiter.setName(query_result_waiter.get(i).get(0).toString());
		}
		waiter.clearTables();
		for (int i = 0; i < query_result_table.size(); i++) {
			auxTable = new TableImplementation(Integer.valueOf(query_result_table.get(i).get(0).toString()));
			auxTable.read();
			waiter.assignTable(auxTable);
		}
	}

	/**
	 * Note: no table must have been assigned to waiter before executing this method
	 * 
	 * @param waiter The WaiterImplementation instance to be created
	 * @return The number of modified columns
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int createWaiter(WaiterImplementation waiter)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Vector<Vector<Object>> query_result_id;
		String sql_waiter = "INSERT INTO Waiter (Name) VALUES ('" + waiter.getName() + "');";
		String sql_getId = "SELECT LAST_INSERT_ID();";
		int modifiedRows = Broker.getBroker().update(sql_waiter);
		query_result_id = Broker.getBroker().read(sql_getId);
		for (int i = 0; i < query_result_id.size(); i++) {
			waiter.setID(Integer.valueOf(query_result_id.get(i).get(0).toString()));
		}
		return modifiedRows;
	}

	/**
	 * @param waiter The WaiterImplementation instance to be updated
	 * @return The number of modified columns
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int updateWaiter(WaiterImplementation waiter)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		String sql_assigned_table_read, sql_assigned_table_write;

		String sql_waiter = "UPDATE Waiter SET Name = '" + waiter.getName() + "' WHERE WaiterID =" + waiter.getID();
		int modifiedRows = Broker.getBroker().update(sql_waiter);

		for (int i = 0; i < waiter.getAssignedTables().size(); i++) {
			sql_assigned_table_read = "SELECT COUNT(*) FROM OrderRestaurant WHERE WaiterId = " + waiter.getID()
					+ " AND TableId = " + waiter.getAssignedTables().get(i).getID() + " AND State <> 'PAYED';";
			if (Integer.valueOf(Broker.getBroker().read(sql_assigned_table_read).get(0).get(0).toString()) == 0) {
				sql_assigned_table_write = "INSERT INTO OrderRestaurant (WaiterId, TableId, State, DateTime) VALUES ("
						+ waiter.getID() + ", " + waiter.getAssignedTables().get(i).getID() + ", 'OPEN', NOW());";
				modifiedRows += Broker.getBroker().update(sql_assigned_table_write);
			}
		}
		return modifiedRows;
	}

	/**
	 * @param waiter The WaiterImplementation instance to be deleted
	 * @return The number of modified columns
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int deleteWaiter(WaiterImplementation waiter)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		String sql_order = "DELETE FROM OrderRestaurant WHERE WaiterId = " + waiter.getID();
		String sql_waiter = "DELETE FROM Waiter WHERE WaiterId =" + waiter.getID();
		return Broker.getBroker().update(sql_order) + Broker.getBroker().update(sql_waiter);

	}

}