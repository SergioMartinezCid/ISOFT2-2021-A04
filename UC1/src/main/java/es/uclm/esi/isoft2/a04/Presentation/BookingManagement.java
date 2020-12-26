package es.uclm.esi.isoft2.a04.Presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookingManagement {

	private JFrame frame;
	private JPanel pnlCard;
	private JPanel pnlMakeBooking;
	private JPanel pnlBookingsList;
	private JPanel pnlButtons;
	private JButton btnBookingsList;
	private JButton btnNewBooking;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookingManagement window = new BookingManagement();
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
	public BookingManagement() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 523, 429);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		pnlCard = new JPanel();
		frame.getContentPane().add(pnlCard);
		pnlCard.setLayout(new CardLayout(0, 0));
		
		pnlMakeBooking = new IU_Booking();
		pnlCard.add(pnlMakeBooking, "Booking creator");
		
		pnlBookingsList = new IU_BookingsList();
		pnlCard.add(pnlBookingsList, "List of bookings");
		
		((CardLayout) pnlCard.getLayout()).show(pnlCard, "List of bookings");
		
		pnlButtons = new JPanel();
		frame.getContentPane().add(pnlButtons, BorderLayout.NORTH);
		
		btnBookingsList = new JButton("Bookings List");
		btnBookingsList.addActionListener(new BtnBookingsListActionListener());
		pnlButtons.add(btnBookingsList);
		
		btnNewBooking = new JButton("New Booking");
		btnNewBooking.addActionListener(new BtnNewBookingActionListener());
		pnlButtons.add(btnNewBooking);
	}
	private class BtnBookingsListActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			((CardLayout) pnlCard.getLayout()).show(pnlCard, "List of bookings");
		}
	}
	private class BtnNewBookingActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			((CardLayout) pnlCard.getLayout()).show(pnlCard, "Booking creator");
		}
	}
}
