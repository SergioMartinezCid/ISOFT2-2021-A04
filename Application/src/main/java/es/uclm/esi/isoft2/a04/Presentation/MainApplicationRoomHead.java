package es.uclm.esi.isoft2.a04.Presentation;

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.JButton;

import es.uclm.esi.isoft2.a04.Presentation.BookingManagement;


public class MainApplicationRoomHead {

	private JFrame frame;
	private JPanel pnlContentRoomHead;
	private JPanel pnlButtoms;
	private JButton btnBookingManagement;
	private JButton btnBList;
	private JButton btnBooking;
	private JPanel pnlBooking;
	private JPanel pnlBookingList;

	private BookingManagement pnlBookingManagement;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApplicationRoomHead window = new MainApplicationRoomHead();
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
	public MainApplicationRoomHead() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 592, 364);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pnlContentRoomHead = new JPanel();
		frame.getContentPane().add(pnlContentRoomHead, BorderLayout.CENTER);
		pnlContentRoomHead.setLayout(new CardLayout(0, 0));
		
		
		//pnlBookingManagement = new BookingManagement();
		//pnlContentRoomHead.add(pnlBookingManagement, "Management");
		
		pnlBooking = new IU_Booking();
		pnlContentRoomHead.add(pnlBooking, "Booking");
		
		//pnlBookingList = new IU_BookingList();
		//pnlContentRoomHead.add(pnlBookingList, "Booking List");
		
		pnlButtoms = new JPanel();
		frame.getContentPane().add(pnlButtoms, BorderLayout.NORTH);
		
		btnBookingManagement = new JButton("Management");
		pnlButtoms.add(btnBookingManagement);
		
		btnBList = new JButton("Booking List");
		pnlButtoms.add(btnBList);
		
		btnBooking = new JButton("Booking");
		pnlButtoms.add(btnBooking);
	}

	public Window getRoomHeadFrame() {
		
		return this.frame;
	}

}
