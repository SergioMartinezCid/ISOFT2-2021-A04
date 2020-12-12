package com.uclm.esi.isoft2.a04.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.uclm.esi.isoft2.a04.Domain.WaiterImplementation;


public class WaiterDAO {

	public WaiterImplementation[] readAllWaiters() {
		// TODO - implement WaiterDAO.readAllWaiters
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param waiter
	 */
	public void readWaiter(WaiterImplementation waiter) {
		
		try {
			String url = "jdbc:mysql://172.20.48.70:3306/XXXdbservice?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(url,"","");
			Statement statement = conn.createStatement();
			ResultSet resultQuery;
			
			resultQuery = statement.executeQuery("SELECT  FROM WHERE ");
			
		}
		catch (Exception e) {
			System.err.println("An exception has occur");
			System.out.println(e.getMessage());
		}
		
	}

	/**
	 * 
	 * @param waiter
	 */
	public int createWaiter(WaiterImplementation waiter) {
		// TODO - implement WaiterDAO.createWaiter
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param waiter
	 */
	public int updateWaiter(WaiterImplementation waiter) {
		// TODO - implement WaiterDAO.updateWaiter
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param waiter
	 */
	public int deleteWaiter(WaiterImplementation waiter) {
		
		try {
			String url = "jdbc:mysql://172.20.48.70:3306/XXXdbservice?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(url,"","");
			Statement statement = conn.createStatement();
			ResultSet resultQuery;
			
			resultQuery = statement.executeQuery("DELETE FROM  WHERE id = ");
			
			return resultQuery.getType();
			
		}
		catch (Exception e) {
			System.err.println("An exception has occur");
			System.out.println(e.getMessage());
		}
		return -1;
	}

}