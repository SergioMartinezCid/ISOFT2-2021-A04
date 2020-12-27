package es.uclm.esi.isoft2.a04.Presentation;

import javax.swing.JPanel;

import es.uclm.esi.isoft2.a04.Domain.*;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.text.TabExpander;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * JPanel for cook and barman
 * 
 * @version 0.0.1
 */
public class JPanelFood extends JPanel implements Observer {
	
	private Table table;
	private Waiter waiter;
	private JScrollPane scrollPane;
	private JButton btnReady;

	/**
	 * Create the panel.
	 */
	public JPanelFood(Waiter waiter, Table table) {
		this.table = table;
		this.waiter = waiter;
		setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "Order contents", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(scrollPane, BorderLayout.CENTER);
		
		
		btnReady = new JButton("Ready");
		btnReady.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		add(btnReady, BorderLayout.EAST);
		
		displayOrder();
	}
	
	private void displayOrder() {
		OrderImplementation order = new OrderImplementation(waiter, table);
		for(Food food : order.getFood())
			scrollPane.add(new FoodItem(food));
	}
	
	
	
	public void update() {
		// TODO - implement
		throw new UnsupportedOperationException();
	}

}