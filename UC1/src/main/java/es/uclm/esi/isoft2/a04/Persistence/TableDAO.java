package es.uclm.esi.isoft2.a04.Persistence;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import es.uclm.esi.isoft2.a04.Domain.*;
import es.uclm.esi.isoft2.a04.Persistance.Broker;

/**
 * @version 0.1.0
 *
 */
public class TableDAO {
	
	private static SimpleDateFormat mysqlDateTimeSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * @return All the tables in the database
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 */
	public TableImplementation[] readAllTables()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NumberFormatException, ParseException {
		Vector<Vector<Object>> query_result = new Vector<Vector<Object>>();

		TableImplementation[] tables;

		String sql = "SELECT TableId FROM TableRestaurant;";

		query_result = Broker.getBroker().read(sql);

		tables = new TableImplementation[query_result.size()];

		for (int i = 0; i < query_result.size(); i++) {
			tables[i] = new TableImplementation(Integer.valueOf(query_result.get(i).get(0).toString()));
			tables[i].read();
		}
		return tables;
	}
	
	private void updateStateHistory(TableImplementation table)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NumberFormatException, ParseException {
		Vector<Vector<Object>> query_result_statetimes = Broker.getBroker()
				.read("SELECT StartTime, State FROM StateTimes WHERE TableId = " + table.getID() + ";");
		HashMap<Date, Integer> stateHistory = new HashMap<>();
		for (int i = 0; i < query_result_statetimes.size(); i++) {
			stateHistory.put(TableDAO.mysqlDateTimeSDF.parse(query_result_statetimes.get(i).get(0).toString()),
					getIntegerValue((String)query_result_statetimes.get(i).get(1)));
		}
		table.setStateHistory(stateHistory);
	}

	/**
	 * @param table The TableImplementation instance to be read
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 * @throws NumberFormatException
	 */
	public void readTable(TableImplementation table)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NumberFormatException, ParseException {
		Vector<Vector<Object>> query_result_table, query_result_state;
		String sql_table = "SELECT t.TableId, t.RestaurantId, t.Seats, r.City FROM TableRestaurant AS t, Restaurant AS r WHERE t.TableID ="
				+ table.getID() + " AND t.RestaurantId = r.RestaurantId;";
		String sql_state = "SELECT State FROM StateTimes WHERE TableId = " + table.getID()
				+ " ORDER BY StartTime DESC LIMIT 1;";
		query_result_table = Broker.getBroker().read(sql_table);
		query_result_state = Broker.getBroker().read(sql_state);

		for (int i = 0; i < query_result_table.size(); i++) {
			table.setSeats(Integer.valueOf(query_result_table.get(i).get(2).toString()));
			table.setRestaurantID(Integer.valueOf(query_result_table.get(i).get(1).toString()));
			table.setCity(query_result_table.get(i).get(3).toString());
		}

		for (int i = 0; i < query_result_state.size(); i++) {
			switch (query_result_state.get(i).get(0).toString().toUpperCase()) {
			case "FREE":
				table.setState(Table.FREE);
				break;
			case "RESERVED":
				table.setState(Table.RESERVED);
				break;
			case "BUSY":
				table.setState(Table.BUSY);
				break;
			case "ASKING":
				table.setState(Table.ASKING);
				break;
			case "WAITING_FOR_FOOD":
				table.setState(Table.WAITING_FOR_FOOD);
				break;
			case "SERVED":
				table.setState(Table.SERVED);
				break;
			case "WAITING_FOR_BILL":
				table.setState(Table.WAITING_FOR_BILL);
				break;
			case "PAYING":
				table.setState(Table.PAYING);
				break;
			case "IN_PREPARATION":
				table.setState(Table.IN_PREPARATION);
				break;
			}
		}
		updateStateHistory(table);
	}

	/**
	 * @param table The TableImplementation instance to be created
	 * @return The number of modified rows
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 */
	public int createTable(TableImplementation table)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NumberFormatException, ParseException {

		Vector<Vector<Object>> query_result_id;
		String sql_table = "INSERT INTO TableRestaurant (RestaurantId, Seats) VALUES (" + table.getRestaurantID() + ", "
				+ table.getSeats() + ")";
		String sql_state = "INSERT INTO StateTimes VALUES (NOW(), " + table.getID() + ", '" + table.getState() + "');";
		String sql_getId = "SELECT LAST_INSERT_ID();";

		int modifiedRows = Broker.getBroker().update(sql_table) + Broker.getBroker().update(sql_state);
		query_result_id = Broker.getBroker().read(sql_getId);
		for (int i = 0; i < query_result_id.size(); i++) {
			table.setID(Integer.valueOf(query_result_id.get(i).get(0).toString()));
		}
		updateStateHistory(table);
		return modifiedRows;
	}

	/**
	 * @param table The {@link TableImplementation} instance to be updated
	 * @return The number of modified rows
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 */
	public int updateTable(TableImplementation table)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NumberFormatException, ParseException {

		String sql_table = "UPDATE TableRestaurant SET RestaurantId = " + table.getRestaurantID() + ", Seats = "
				+ table.getSeats() + " WHERE TableId = " + table.getID() + ";";
		String sql_state = "INSERT INTO StateTimes VALUES ('"
				+ DateTimeFormatter.ISO_DATE_TIME.format(OffsetDateTime.now()) + "', " + table.getID() + ", '"
				+ (table.getState()+1) + "');";
		updateStateHistory(table);
		return Broker.getBroker().update(sql_table) + Broker.getBroker().update(sql_state);
	}

	/**
	 * @param table The {@link TableImplementation} instance to be deleted
	 * @return The number of modified rows
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int deleteOrder(TableImplementation table)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		String sql_orders = "DELETE FROM OrderRestaurant WHERE TableId = " + table.getID() + ";";
		String sql_state = "DELETE FROM StateTimes WHERE TableId = " + table.getID() + ";";
		String sql_booking = "DELETE FROM Booking WHERE TableId = " + table.getID() + ";";
		String sql_table = "DELETE FROM TableRestaurant WHERE TableId =" + table.getID() + ";";
		return Broker.getBroker().update(sql_orders) + Broker.getBroker().update(sql_state) + Broker.getBroker().update(sql_booking) + Broker.getBroker().update(sql_table);

	}

	private int getIntegerValue(String s) {
		int r = 0;
		switch (s.toUpperCase()) {
		case "FREE":
			r = Table.FREE;
			break;
		case "RESERVED":
			r = Table.RESERVED;
			break;
		case "BUSY":
			r = Table.BUSY;
			break;
		case "ASKING":
			r = Table.ASKING;
			break;
		case "WAITING_FOR_FOOD":
			r = Table.WAITING_FOR_FOOD;
			break;
		case "SERVED":
			r = Table.SERVED;
			break;
		case "WAITING_FOR_BILL":
			r = Table.WAITING_FOR_BILL;
			break;
		case "PAYING":
			r = Table.PAYING;
			break;
		case "IN_PREPARATION":
			r = Table.IN_PREPARATION;
			break;
		}
		return r;
	}
}