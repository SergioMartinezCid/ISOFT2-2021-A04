package es.uclm.esi.isoft2.a04.Persistance;

import es.uclm.esi.isoft2.a04.Domain.*;

import java.sql.SQLException;
import java.util.Vector;


public class OrderDAO {

	public OrderImplementation[] readAllOrders() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		OrderImplementation[] orders;
		Vector<Vector<Object>> rows;
		rows = Broker.getBroker().read("SELECT * FROM Orders;");
		orders = new OrderImplementation[rows.size()];
		int i = 0;
		for(Vector<Object> row: rows) {
			orders[i] = new OrderImplementation((int)row.get(0), null, null);
			orders[i].read();
		}
		return orders;
	}

	/**
	 * 
	 * @param order
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void readOrder(OrderImplementation order) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Vector<Vector<Object>> rows;
		FoodImplementation[] food;
		rows = Broker.getBroker().read("SELECT * FROM Orders WHERE OrderId = "+order.getID()+";");
		
		for(Vector<Object> row: rows) {
			//TODO there's no order implementation
			
			/*order.setWaiter((Waiter)row.get(0));
			order.setTable((Teble)row.get(2));
			order.setState(row.get(3));
			order.setDatetime(row.get(4));
			aux = Broker.getBroker().read("SELECT * FROM OrdersContent WHERE OrderId = "+order.getID()+";");
			food = new FoodImplementation[aux.size()];
			for (int j=0; j<aux.size(); j++) {
				//food[i] = new FoodImplementation(row.get(1));
			}
			order.setFood(food);
			order.read();
			*/
		}
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param order
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public int createOrder(OrderImplementation order) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Broker br = new Broker();
		/*
		FoodImplementation[] food = order.getFood();
		int n= br.update("INSERT INTO Orders VALUES("+order.getID()+","+order.getWaiter()+","+order.getTable()+","+order.getState()+","+order.getDatetime()+");");
		for (int i=0; i < food.length < i++) {
			br.update("INSERT INTO OrdersContent VALUES("+order.getID()+","+food[i]+");");
		}
		
		return n;
		*/
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param order
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public int updateOrder(OrderImplementation order) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// TODO - implement OrderDAO.updateOrder
		//FoodImplementation[] food = order.getFood();
		return  Broker.getBroker().update("UPDATE Orders SET state="+order.getState()+" WHERE OrderId = "+order.getID()+";");
	}
	
	/**
	 * 
	 * @param order
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException 
	 */
	public int deleteOrder(OrderImplementation order) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Broker br = new Broker();
		br.update("DELETE FROM OrdersContent WHERE OrderId = "+order.getID()+";");
		return br.update("DELETE FROM Orders WHERE OrderId = "+order.getID()+";");
	}

}