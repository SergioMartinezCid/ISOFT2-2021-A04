package es.uclm.esi.isoft2.a04.Persistance;

import java.sql.*;
import java.util.Vector;

public class Broker {
	
	final static String DBUSER = "A04dbservice";
	final static String DBPASS = "@ISoft2.2020#";
	
	private static final String unformatedString = "jdbc:mysql://172.20.48.70:3306/"+DBUSER+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	protected static Connection connection;
	protected static Broker instance;
	
	
	/**
	 * 
	 * Description: Constructor method.
	 * 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	protected Broker() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		
	}
	/**
	 * 
	 * @return An instance of the Broker
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static Broker getBroker() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		if (instance == null) {
			instance = new Broker();
		}
		return instance;
	}

	/**
	 * 
	 * @param sql
	 * @throws SQLException 
	 */
	public Vector<Vector<Object>> read(String sql) throws SQLException {
		
		Statement statement;
		ResultSet result_set;
		Vector<Object> aux;
		Vector<Vector<Object>> result = new Vector<Vector<Object>>();
		
		connection = DriverManager.getConnection(unformatedString);
		statement = connection.createStatement();
		result_set = statement.executeQuery(sql);
		
		while(result_set.next()) {
			aux = new Vector<Object>();
			
			for(int i = 1; i<= result_set.getMetaData().getColumnCount(); i++) {
				aux.add(result_set.getObject(i));
			}
			result.add(aux);
		}
		
		return result;
		
	}

	/**
	 * The return value is the number of modified rows
	 * @param sql
	 * @throws SQLException 
	 */
	public int update(String sql) throws SQLException {
		
		int update = 0;
		
		connection = DriverManager.getConnection(unformatedString);
		PreparedStatement prepared_statement = connection.prepareStatement(sql);
		update = prepared_statement.executeUpdate();
		
		prepared_statement.close();
		connection.close();
		
		return update;
		
	}

}