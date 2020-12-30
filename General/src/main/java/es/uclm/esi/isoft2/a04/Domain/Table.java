package es.uclm.esi.isoft2.a04.Domain;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

/**
 * @version 0.1.1
 *
 */
public interface Table {

	static int FREE = 0;
	static int RESERVED = 1;
	static int BUSY = 2;
	static int ASKING = 3;
	static int WAITING_FOR_FOOD = 4;
	static int SERVED = 5;
	static int WAITING_FOR_BILL = 6;
	static int PAYING = 7;
	static int IN_PREPARATION = 8;

	void setState(int state);

	int getState();

	void setSeats(int seatsNumber);

	int getSeats();

	int getID();
	
	int getRestaurantID();
	
	String getCity();
	
	HashMap<Date, Integer> getStateHistory();
	
	void setStateHistory(HashMap<Date, Integer> stateHistory);

	Table[] readAll() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NumberFormatException, ParseException;

	void read() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NumberFormatException, ParseException;

	int create() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NumberFormatException, ParseException;

	int update() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NumberFormatException, ParseException;

	int delete() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;
}