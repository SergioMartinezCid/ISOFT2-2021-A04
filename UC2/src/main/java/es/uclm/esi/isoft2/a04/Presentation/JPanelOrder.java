package es.uclm.esi.isoft2.a04.Presentation;

import javax.swing.JPanel;

import es.uclm.esi.isoft2.a04.Domain.*;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

/**
 * JPanel for Waiter
 * 
 * @version 0.2.0
 */
public class JPanelOrder extends JPanel implements Observer {
	
	private static OrderControl orderCtl = new OrderControl();
	public static OrderControl getOrderControl() {
		return orderCtl;
	}
	
	private ArrayList<FoodItem> contents = new ArrayList<>();
	
	private Table table;
	private Waiter waiter;
	private OrderImplementation order;
	private JButton btnCloseOrder;
	private JPanel panel;
	private JButton btnFood;
	private JButton btnBeverage;
	private JLabel lblStatus;
	private JScrollPane scrollPane;
	
	/**
	 * Create the panel.
	 * @throws InvalidTypeException 
	 * @throws InvalidStateException 
	 * @throws ParseException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public JPanelOrder(Waiter waiter, Table table) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, ParseException, InvalidStateException, InvalidTypeException {
		this.waiter = waiter;
		this.table = table;
		this.order = orderCtl.getCurrentOrder(this.waiter, this.table);
		setLayout(new BorderLayout(0, 0));
		
		btnCloseOrder = new JButton("Close order");
		btnCloseOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					order.setState(Order.CLOSED);
				} catch (InvalidStateException e) {
					e.printStackTrace();
				}
				FoodImplementation[] lst = Arrays.stream(contents.toArray(FoodItem[]::new)).map(x -> x.getFood()).toArray(FoodImplementation[]::new);
				order.setFood(lst);
			}
		});
		add(btnCloseOrder, BorderLayout.SOUTH);
		
		panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		btnFood = new JButton("Add food");
		btnFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					FoodItem fi = new FoodItem(false);
					contents.add(fi);
					scrollPane.add(fi);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		panel.add(btnFood);
		
		btnBeverage = new JButton("Add beverage");
		btnBeverage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					FoodItem fi = new FoodItem(false, Food.DRINK);
					contents.add(fi);
					scrollPane.add(fi);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		panel.add(btnBeverage);
		
		lblStatus = new JLabel("Status:");
		panel.add(lblStatus);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "Order contents", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(scrollPane, BorderLayout.CENTER);
	}

	@Override
	public void update() {
		try {
			order.read();
			lblStatus.setText("Status: "+order.getState());
			FoodImplementation[] fs = (FoodImplementation[])order.getFood();
			for (int i = 0; i < fs.length; i++) {
				if (i >= contents.size()) {
					FoodItem fi = new FoodItem(fs[i]);
					contents.add(fi);
					scrollPane.add(fi);
				}
				else
					contents.get(i).setFood(fs[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
