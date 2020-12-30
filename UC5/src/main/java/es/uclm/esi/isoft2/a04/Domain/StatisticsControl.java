package es.uclm.esi.isoft2.a04.Domain;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

/**
 * @version 0.1.0
 *
 */
public class StatisticsControl {

	/**
	 * @param tabledb        A table needed for accessing the database
	 * @param seatNumber     The number of seats of the tables being evaluated, or a
	 *                       negative number for all tables
	 * @param restaurantID   The id of the restaurant where the tables must be
	 *                       located, or a negative number for all tables
	 * @param city           The name of the city where the tables must be located,
	 *                       or null for all tables
	 * @param startingPhases A list containing all the states that start the period
	 *                       being measured
	 * @param endingPhases   A list containing all the states that end the period
	 *                       being measured
	 * @return The average command time in milliseconds
	 * @throws NumberFormatException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 */
	private double getAverageTime(Table tabledb, int seatNumber, int restaurantID, String city,
			List<Integer> startingPhases, List<Integer> endingPhases) throws NumberFormatException,
			InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, ParseException {
		double totalCommandTime = 0;
		int commandCount = 0;
		Date dateStarted;
		ArrayList<Date> dateSuccession;

		for (Table table : tabledb.readAll()) {

			if (seatNumber > 0 && table.getSeats() != seatNumber)
				continue;
			if (restaurantID > 0 && table.getRestaurantID() != restaurantID)
				continue;
			if (city != null && !table.getCity().equals(city))
				continue;

			dateSuccession = new ArrayList<Date>(new TreeSet<Date>(table.getStateHistory().keySet()));
			dateStarted = null;

			for (Date dateIterator : dateSuccession) {
				if (endingPhases.contains(table.getStateHistory().get(dateIterator)) && dateStarted != null) {
					totalCommandTime += dateIterator.getTime() - dateStarted.getTime();
					commandCount++;
					dateStarted = null;
				}
				if (startingPhases.contains(table.getStateHistory().get(dateIterator)) && dateStarted == null)
					dateStarted = dateIterator;
			}
		}

		if (commandCount == 0)
			return 0;
		else
			return totalCommandTime / commandCount;
	}

	/**
	 * @param tabledb      A table needed for accessing the database
	 * @param seatNumber   The number of seats of the tables being evaluated, or a
	 *                     negative number for all tables
	 * @param restaurantID The id of the restaurant where the tables must be
	 *                     located, or a negative number for all tables
	 * @param city         The name of the city where the tables must be located, or
	 *                     null for all tables
	 * @return The average command time in milliseconds
	 * @throws NumberFormatException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 */
	public double getAverageCommandTime(Table tabledb, int seatNumber, int restaurantID, String city)
			throws NumberFormatException, InstantiationException, IllegalAccessException, ClassNotFoundException,
			SQLException, ParseException {
		return getAverageTime(tabledb, seatNumber, restaurantID, city, Arrays.asList(Table.ASKING),
				Arrays.asList(Table.WAITING_FOR_FOOD));
	}

	/**
	 * @param tabledb      A table needed for accessing the database
	 * @param seatNumber   The number of seats of the tables being evaluated, or a
	 *                     negative number for all tables
	 * @param restaurantID The id of the restaurant where the tables must be
	 *                     located, or a negative number for all tables
	 * @param city         The name of the city where the tables must be located, or
	 *                     null for all tables
	 * @return The average command time in milliseconds
	 * @throws NumberFormatException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 */
	public double getAverageMealPreparationTime(Table tabledb, int seatNumber, int restaurantID, String city)
			throws NumberFormatException, InstantiationException, IllegalAccessException, ClassNotFoundException,
			SQLException, ParseException {
		return getAverageTime(tabledb, seatNumber, restaurantID, city, Arrays.asList(Table.WAITING_FOR_FOOD),
				Arrays.asList(Table.SERVED));
	}

	/**
	 * @param tabledb      A table needed for accessing the database
	 * @param seatNumber   The number of seats of the tables being evaluated, or a
	 *                     negative number for all tables
	 * @param restaurantID The id of the restaurant where the tables must be
	 *                     located, or a negative number for all tables
	 * @param city         The name of the city where the tables must be located, or
	 *                     null for all tables
	 * @return The average command time in milliseconds
	 * @throws NumberFormatException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 */
	public double getAverageCheckDeliveryTime(Table tabledb, int seatNumber, int restaurantID, String city)
			throws NumberFormatException, InstantiationException, IllegalAccessException, ClassNotFoundException,
			SQLException, ParseException {
		return getAverageTime(tabledb, seatNumber, restaurantID, city, Arrays.asList(Table.WAITING_FOR_BILL),
				Arrays.asList(Table.PAYING));
	}

	/**
	 * @param tabledb      A table needed for accessing the database
	 * @param seatNumber   The number of seats of the tables being evaluated, or a
	 *                     negative number for all tables
	 * @param restaurantID The id of the restaurant where the tables must be
	 *                     located, or a negative number for all tables
	 * @param city         The name of the city where the tables must be located, or
	 *                     null for all tables
	 * @return The average command time in milliseconds
	 * @throws NumberFormatException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 */
	public double getAverageTablePreparationTime(Table tabledb, int seatNumber, int restaurantID, String city)
			throws NumberFormatException, InstantiationException, IllegalAccessException, ClassNotFoundException,
			SQLException, ParseException {
		return getAverageTime(tabledb, seatNumber, restaurantID, city, Arrays.asList(Table.IN_PREPARATION),
				Arrays.asList(Table.FREE));
	}
}
