package es.uclm.esi.isoft2.a04.Domain;

import java.sql.SQLException;

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

	Table[] readAll() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;

	void read() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;

	int create() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;

	int update() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;

	int delete() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;
}