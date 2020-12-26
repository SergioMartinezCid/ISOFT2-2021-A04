package es.uclm.esi.isoft2.a04.Presentation;

import javax.swing.JPanel;

import es.uclm.esi.isoft2.a04.Domain.*;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

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
		add(btnReady, BorderLayout.EAST);
	}
	
	public void update() {
		// TODO - implement
		throw new UnsupportedOperationException();
	}

}