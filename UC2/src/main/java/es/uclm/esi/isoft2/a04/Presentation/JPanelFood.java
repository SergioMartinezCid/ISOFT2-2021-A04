package es.uclm.esi.isoft2.a04.Presentation;

import javax.swing.JPanel;

import es.uclm.esi.isoft2.a04.Domain.*;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.text.TabExpander;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

/**
 * JPanel for cook and barman
 * 
 * @version 0.2.1
 */
public class JPanelFood extends JPanel implements Observer {
	
	private static OrderControl orderCtl = new OrderControl();
	public static OrderControl getOrderControl() {
		return orderCtl;
	}

	private ArrayList<FoodItem> contents = new ArrayList<>();
	
	private Table table;
	private Waiter waiter;
	private JScrollPane scrollPane;
	private JButton btnReady;

	/**
	 * Create the panel.
	 * @throws ParseException 
	 * @throws InvalidTypeException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public JPanelFood(Waiter waiter, Table table) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, InvalidTypeException, ParseException {
		this.table = table;
		this.waiter = waiter;
		setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "Order contents", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(scrollPane, BorderLayout.CENTER);
		
		
		btnReady = new JButton("Ready");
		btnReady.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OrderImplementation order = new OrderImplementation(waiter, table);
				for (Food fi : order.getFood()) {
					((FoodImplementation) fi).setStatus(FoodImplementation.READY);
				}
			}
		});
		add(btnReady, BorderLayout.EAST);
		
		displayOrder();
	}
	
	private void displayOrder() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, InvalidTypeException, ParseException {
		OrderImplementation order = new OrderImplementation(waiter, table);
		for(Food food : order.getFood()) {
			FoodItem fi = new FoodItem(food);
			contents.add(fi);
			scrollPane.add(fi);
		}
	}
	
	public void update() {
		try {
			displayOrder();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}