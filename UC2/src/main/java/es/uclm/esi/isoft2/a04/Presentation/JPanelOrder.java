package es.uclm.esi.isoft2.a04.Presentation;

import javax.swing.JPanel;

import es.uclm.esi.isoft2.a04.Domain.*;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPanelOrder extends JPanel implements Observer {
	
	private Table table;
	private Waiter waiter;
	private JButton btnCloseOrder;
	private JPanel panel;
	private JButton btnFood;
	private JButton btnBeverage;
	
	/**
	 * Create the panel.
	 */
	public JPanelOrder(Waiter waiter, Table table) {
		this.waiter = waiter;
		this.table = table;
		setLayout(new BorderLayout(0, 0));
		
		btnCloseOrder = new JButton("Close order");
		add(btnCloseOrder, BorderLayout.SOUTH);
		
		panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		btnFood = new JButton("Add food");
		btnFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.add(new FoodItem());
			}
		});
		panel.add(btnFood);
		
		btnBeverage = new JButton("Add beverage");
		btnBeverage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.add(new FoodItem(Food.DRINK));
			}
		});
		panel.add(btnBeverage);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "Order contents", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(scrollPane, BorderLayout.CENTER);
	}

	public void update() {
		// TODO - implement
		throw new UnsupportedOperationException();
	}

}