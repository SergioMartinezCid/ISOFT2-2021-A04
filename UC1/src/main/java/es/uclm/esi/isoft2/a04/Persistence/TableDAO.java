package es.uclm.esi.isoft2.a04.Persistence;

import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

import es.uclm.esi.isoft2.a04.Domain.*;
import es.uclm.esi.isoft2.a04.Persistance.Broker;

public class TableDAO {

	public TableImplementation[] readAllTables() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
	
		Vector<Vector<Object>> sql_result = new Vector<Vector<Object>>();
		Vector<TableImplementation> result = new Vector<>();
		
		TableImplementation[] table_implementation;
		
		String sql = "SELECT * FROM Tables"; //Sql sentence
		
		sql_result  = Broker.getBroker().read(sql);
		
		if(sql_result.size() > 0) {
			
			for(Vector<Object> table : sql_result ) {
				
				result.add(new TableImplementation(Integer.parseInt(table.get(0).toString()), Integer.parseInt(table.get(0).toString())));
				
			}
		
		}
		
		table_implementation = new TableImplementation [result.size()];
		
		for (int i = 0; i<table_implementation.length; i++) {
			
			table_implementation[i] = result.remove(i); 
			
		}

		return table_implementation;

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