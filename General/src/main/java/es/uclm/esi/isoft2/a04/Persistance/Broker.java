package es.uclm.esi.isoft2.a04.Persistance;

import java.sql.*;
import java.util.Vector;

/**
 * @version 0.1.2
 *
 */
public class Broker {

	final static String DBSCHEMA = "A04dbservice";
	final static String DBUSER = "A04";
	final static String DBPASS = "@ISoft2.2020#";

	private static final String unformatedString = "jdbc:mysql://172.20.48.70:3306/" + DBSCHEMA
			+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC?user="
			+ DBUSER + "&password=" + DBPASS;
	protected static Connection connection;
	protected static Broker instance;

	/**
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	protected Broker() throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

	}

	/**
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
	 * @param sql The sql sentence for reading from the database
	 * @return A vector containing the result from the query
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

		while (result_set.next()) {
			aux = new Vector<Object>();

			for (int i = 1; i <= result_set.getMetaData().getColumnCount(); i++) {
				aux.add(result_set.getObject(i));
			}
			result.add(aux);
		}

		return result;

	}

	/**
	 * @param sql The sql instance for modifying the database
	 * @return The number of modified columns
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