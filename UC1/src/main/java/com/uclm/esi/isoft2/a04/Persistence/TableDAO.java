package com.uclm.esi.isoft2.a04.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.uclm.esi.isoft2.a04.Domain.*;


public class TableDAO {

	public TableImplementation[] readAllTables() {
		// TODO - implement TableDAO.readAllTables
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param table
	 */
	public void readTable(TableImplementation table) {
		
		try {
			String url = "jdbc:mysql://172.20.48.70:3306/XXXdbservice?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(url,"","");
			Statement statement = conn.createStatement();
			ResultSet resultQuery;
			
			resultQuery = statement.executeQuery("SELECT  FROM Booking WHERE ");
			
		}
		catch (Exception e) {
			System.err.println("An exception has occur");
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 
	 * @param table
	 */
	public int createTable(TableImplementation table) {
		
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
		

	/**
	 * 
	 * @param table
	 */
	public int updateTable(TableImplementation table) {
		// TODO - implement TableDAO.updateTable
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param table
	 */
	public int deleteOrder(TableImplementation table) {
		// TODO - implement TableDAO.deleteOrder
		throw new UnsupportedOperationException();
	}

}