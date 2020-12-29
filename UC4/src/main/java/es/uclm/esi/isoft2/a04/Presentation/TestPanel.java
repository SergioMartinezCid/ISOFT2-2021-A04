package es.uclm.esi.isoft2.a04.Presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import es.uclm.esi.isoft2.a04.Domain.Waiter;

import java.awt.BorderLayout;

public class TestPanel {

	private JFrame frame;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestPanel window = new TestPanel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TestPanel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanelPayment(null,null);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
	}

}
