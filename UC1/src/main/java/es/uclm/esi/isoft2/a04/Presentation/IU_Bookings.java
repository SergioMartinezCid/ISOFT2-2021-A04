package es.uclm.esi.isoft2.a04.Presentation;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JScrollPane;

import es.uclm.esi.isoft2.a04.Domain.Booking;
import es.uclm.esi.isoft2.a04.Domain.InsuficientTimeElapsedException;
import es.uclm.esi.isoft2.a04.Domain.TableBooking;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class IU_Bookings extends JPanel {
	private JScrollPane scrollPane;
	private JPanel panel;
	private JButton btnAssignWaiter;
	private JList lstBookings;
	private DefaultListModel listModel;
	private Booking.TURN currentTurn;
	private Timer bookingTimeout, changeTurn;
	private TimerTask tt1, tt2;
	private TableBooking tb;
	

	/**
	 * Create the panel.
	 */
	public IU_Bookings() {
		setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		listModel = new DefaultListModel();
		lstBookings = new JList<Booking>();
		lstBookings.addListSelectionListener(new LstBookingsListSelectionListener());
		lstBookings.setModel(listModel);
		scrollPane.setViewportView(lstBookings);

		panel = new JPanel();
		add(panel, BorderLayout.SOUTH);

		btnAssignWaiter = new JButton("Assign Waiter");
		btnAssignWaiter.addActionListener(new BtnAssignWaiterActionListener());
		btnAssignWaiter.setEnabled(false);
		panel.add(btnAssignWaiter);
		
		tt1 = new TimerTask() {
			public void run() {
				//Cancell the bookings
		    	for(int i=0; i<listModel.size(); i++) {
		    		try {
		    			tb.cancelBooking((Booking)listModel.get(i));
		    		} catch (SQLException x) {
		    			JOptionPane.showMessageDialog(null, "SQLException error", "Booking Error", JOptionPane.ERROR_MESSAGE);
		    		} catch (IllegalAccessException il) {
		    			JOptionPane.showMessageDialog(null, "IllegalAccessException error", "Booking Error",
		    					JOptionPane.ERROR_MESSAGE);
		    		} catch (ClassNotFoundException clnf) {
		    			JOptionPane.showMessageDialog(null, "ClassNotFoundException error", "Booking Error",
		    					JOptionPane.ERROR_MESSAGE);
		    		} catch (InstantiationException inst) {
		    			JOptionPane.showMessageDialog(null, "InstantiationException error", "Booking Error",
		    					JOptionPane.ERROR_MESSAGE);
		    		} catch (InsuficientTimeElapsedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	}
			};
		};
		
		tt2 = new TimerTask() {
			public void run() {
				switch((new Date()).getHours()) {
				case 13:
					currentTurn = Booking.TURN.L1;
					updateList();
					break;
				case 14:
					currentTurn = Booking.TURN.L2;
					updateList();
					break;
				case 15:
					currentTurn = Booking.TURN.L3;
					updateList();
					break;
				case 20:
					currentTurn = Booking.TURN.D1;
					updateList();
					break;
				case 21:
					currentTurn = Booking.TURN.D2;
					updateList();
					break;
				case 22:
					currentTurn = Booking.TURN.D3;
					updateList();
					break;
				default:
		    	}
			};
		};
		scheduleTimers();
		updateList();

	}
	
	public void scheduleTimers() {
		Date time = new Date();
		time.setHours(13);
		time.setMinutes(00);
		time.setSeconds(00);
		changeTurn.schedule(tt2, time, 86400000);
		time.setMinutes(20);
		bookingTimeout.schedule(tt1, time, 86400000);
		
		time = new Date();
		time.setHours(14);
		time.setMinutes(00);
		time.setSeconds(00);
		changeTurn.schedule(tt2, time, 86400000);
		time.setMinutes(20);
		bookingTimeout.schedule(tt1, time, 86400000);
		
		time = new Date();
		time.setHours(15);
		time.setMinutes(00);
		time.setSeconds(00);
		changeTurn.schedule(tt2, time, 86400000);
		time.setMinutes(20);
		bookingTimeout.schedule(tt1, time, 86400000);
		
		time = new Date();
		time.setHours(20);
		time.setMinutes(30);
		time.setSeconds(00);
		changeTurn.schedule(tt2, time, 86400000);
		time.setMinutes(50);
		bookingTimeout.schedule(tt1, time, 86400000);
		
		time = new Date();
		time.setHours(21);
		time.setMinutes(30);
		time.setSeconds(00);
		changeTurn.schedule(tt2, time, 86400000);
		time.setMinutes(50);
		bookingTimeout.schedule(tt1, time, 86400000);
		
		time = new Date();
		time.setHours(22);
		time.setMinutes(30);
		time.setSeconds(00);
		changeTurn.schedule(tt2, time, 86400000);
		time.setMinutes(50);
		bookingTimeout.schedule(tt1, time, 86400000);
	}
	public void updateList() {
		Booking b = new Booking();
		Date date = new Date();
		listModel.clear();
		try {
			Booking[] list = b.readAll();
			for(int i=0; i<list.length; i++) {
				if((list[i].getDate().getYear() == date.getYear())&& (list[i].getDate().getMonth() == date.getMonth()) && (list[i].getDate().getDay() == date.getDay())) {	//Get the bookings for today
					if(list[i].getTurn() == currentTurn) {	//Get the bookings of the current turn
						listModel.addElement(list[i]);
					}	
				}	
			}
			// To do: Set the proper error messages for each exception
		} catch (SQLException x) {
			JOptionPane.showMessageDialog(null, "SQLException error", "Booking Error", JOptionPane.ERROR_MESSAGE);
		} catch (IllegalAccessException il) {
			JOptionPane.showMessageDialog(null, "IllegalAccessException error", "Booking Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (ClassNotFoundException clnf) {
			JOptionPane.showMessageDialog(null, "ClassNotFoundException error", "Booking Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (InstantiationException inst) {
			JOptionPane.showMessageDialog(null, "InstantiationException error", "Booking Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	private class LstBookingsListSelectionListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			btnAssignWaiter.setEnabled(true);
		}
	}
	private class BtnAssignWaiterActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			tb.assignWaiter(((Booking) listModel.get(lstBookings.getSelectedIndex())).getTable());
			updateList();
		}
	}
}
