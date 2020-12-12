package es.uclm.esi.isoft2.a04.Persistence;

import java.sql.SQLException;
import java.util.Vector;

import es.uclm.esi.isoft2.a04.Domain.*;
import es.uclm.esi.isoft2.a04.Persistance.Broker;

public class TableDAO {

	public TableImplementation[] readAllTables() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		Vector<Vector<Object>> result = new Vector<Vector<Object>>();
		TableImplementation[] table_implementation;
		
		String sql = "SELECT * FROM Tables"; //Sql sentence
		
		return null;
	}

	/**
	 * 
	 * @param table
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void readTable(TableImplementation table) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		String sql = "SELECT * FROM Tables WHERE TableID ="+table.getID(); //Sql sentence
		Broker.getBroker().read(sql);
		
	}

	/**
	 * 
	 * @param table
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException 
	 */
	public int createTable(TableImplementation table) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		String sql = "INSERT INTO Tables VALUES ("+ table.getID()+", "+table.getSeats()+","+table.getSeatsNumber()+","+table.getState()+")"; //Sql sentence
		return Broker.getBroker().update(sql);
		
	}
		
	/**
	 * 
	 * @param table
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException 
	 */
	public int updateTable(TableImplementation table) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		String sql = "UPDATE Tables WHERE TableID ="+table.getID(); //Sql sentence
		return Broker.getBroker().update(sql);
		
	}

	/**
	 * 
	 * @param table
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException 
	 */
	public int deleteOrder(TableImplementation table) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		String sql = "DELETE FROM Tables WHERE TableID ="+table.getID(); //Sql sentence
		return Broker.getBroker().update(sql);
		
	}

}