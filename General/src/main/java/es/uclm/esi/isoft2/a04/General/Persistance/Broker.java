package es.uclm.esi.isoft2.a04.General.Persistance;
import java.util.Vector;

public class Broker {

	protected static Broker instance;
	private String unformatedString;

	protected Broker Broker() {
		// TODO - implement Broker.Broker
		throw new UnsupportedOperationException();
	}

	public static Broker getBroker() {
		// TODO - implement Broker.getBroker
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param sql
	 */
	public Vector<Vector<Object>> read(String sql) {
		// TODO - implement Broker.read
		throw new UnsupportedOperationException();
	}

	/**
	 * The return value is the number of modified rows
	 * @param sql
	 */
	public int update(String sql) {
		// TODO - implement Broker.update
		throw new UnsupportedOperationException();
	}

}