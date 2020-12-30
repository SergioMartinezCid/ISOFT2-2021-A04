package es.uclm.esi.isoft2.a04.Domain;

import java.sql.SQLException;
import java.text.ParseException;

import es.uclm.esi.isoft2.a04.Persistance.UserDAO;

/**
 * @version 0.1.0
 *
 */
public class User {

	public enum USERTYPE {
		ROOM_HEAD, WAITER, COOK, BARTENDER, OWNER
	}

	private int userId;
	private String username;
	private String password;
	private USERTYPE type;
	private WaiterImplementation waiter;

	private UserDAO userDAO;

	/**
	 * 
	 */
	public User() {
		this.userDAO = new UserDAO();
	}

	/**
	 * @param userId The id of the user in the database
	 */
	public User(int userId) {
		this();
		this.setID(userId);
	}

	/**
	 * @return The id of the user in the database
	 */
	public int getID() {
		return userId;
	}

	/**
	 * @param userId The id of the user in the database
	 */
	public void setID(int userId) {
		this.userId = userId;
	}

	/**
	 * @return The username of this user
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username The username of this user
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return The password of this user
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password The password of this user
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return The type of this user
	 */
	public USERTYPE getType() {
		return type;
	}

	/**
	 * @param userType The type of this user
	 */
	public void setType(USERTYPE userType) {
		this.type = userType;
	}

	/**
	 * @return An instance of WaiterImplementation if this user represents a waiter
	 */
	public WaiterImplementation getWaiter() {
		return waiter;
	}

	/**
	 * @param waiter An instance of WaiterImplementation if this user represents a
	 *               waiter
	 */
	public void setWaiter(WaiterImplementation waiter) {
		this.waiter = waiter;
	}

	/**
	 * @return A list containing all users in the database
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws NumberFormatException
	 * @throws ParseException
	 */
	public User[] readAll() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException,
			NumberFormatException, ParseException {
		return this.userDAO.readAllUsers();
	}

	/**
	 * @throws NumberFormatException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 */
	public void read() throws NumberFormatException, InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException, ParseException {
		this.userDAO.readUser(this);
	}

	/**
	 * @return The number of modified rows
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int create() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return this.userDAO.createUser(this);
	}

	/**
	 * @return The number of modified rows
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int update() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return this.userDAO.updateUser(this);
	}

	/**
	 * @return The number of modified rows
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int delete() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return this.userDAO.deleteUser(this);
	}
}
