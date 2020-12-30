package es.uclm.esi.isoft2.a04.Persistance;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Vector;

import es.uclm.esi.isoft2.a04.Domain.User;
import es.uclm.esi.isoft2.a04.Domain.WaiterImplementation;

/**
 * @version 0.1.0
 *
 */
public class UserDAO {

	/**
	 * @return A list containing all users in the database
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws NumberFormatException
	 * @throws ParseException
	 */
	public User[] readAllUsers() throws InstantiationException, IllegalAccessException, ClassNotFoundException,
			SQLException, NumberFormatException, ParseException {
		Vector<Vector<Object>> query_result = Broker.getBroker().read("SELECT UserId FROM User;");
		User[] users;

		users = new User[query_result.size()];
		for (int i = 0; i < users.length; i++) {
			users[i] = new User(Integer.valueOf(query_result.get(i).get(0).toString()));
			users[i].read();

		}
		return users;
	}

	/**
	 * @param user The user to be read
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws NumberFormatException
	 * @throws ParseException
	 */
	public void readUser(User user) throws InstantiationException, IllegalAccessException, ClassNotFoundException,
			SQLException, NumberFormatException, ParseException {
		Vector<Vector<Object>> query_result = Broker.getBroker()
				.read("SELECT Username, Password, Type, WaiterId FROM User WHERE UserId = " + user.getID() + "';");
		WaiterImplementation auxWaiter = null;
		for (int i = 0; i < query_result.size(); i++) {
			user.setUsername(query_result.get(i).get(0).toString());
			user.setPassword(query_result.get(i).get(1).toString());
			user.setType(User.USERTYPE.valueOf(query_result.get(i).get(2).toString()));
			if (query_result.get(i).get(3) != null) {
				auxWaiter = new WaiterImplementation(Integer.valueOf(query_result.get(i).get(3).toString()));
				auxWaiter.read();
				user.setWaiter(auxWaiter);
			}
		}
	}

	/**
	 * @param user The user to be created
	 * @return The number of modified rows
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int createUser(User user)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Vector<Vector<Object>> query_result = Broker.getBroker().read("SELECT LAST_INSERT_ID();");
		int modifiedRows;
		modifiedRows = Broker.getBroker()
				.update("INSERT INTO User (Username, Passport, Type, WaiterId) VALUES ('" + user.getUsername() + "', '"
						+ user.getPassword() + "', '" + user.getType().toString() + "', " + user.getWaiter() == null
								? "NULL"
								: user.getWaiter().getID() + ");");
		for (int i = 0; i < query_result.size(); i++)
			user.setID(Integer.valueOf(query_result.get(i).get(0).toString()));

		return modifiedRows;
	}

	/**
	 * @param user The user to be updated
	 * @return The number of modified rows
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int updateUser(User user)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return Broker.getBroker()
				.update("UPDATE User SET Username = '" + user.getUsername() + "', Password = '" + user.getPassword()
						+ "', Type = '" + user.getType().toString() + "', WaiterId=" + user.getWaiter() == null ? "NULL"
								: user.getWaiter().getID() + " WHERE UserId=" + user.getID() + ";");
	}

	/**
	 * @param user The user to be deleted
	 * @return The number of modified rows
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int deleteUser(User user)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return Broker.getBroker().update("DELETE FROM User WHERE UserId=" + user.getID() + ";");
	}

}
