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
import es.uclm.esi.isoft2.a04.Domain.Table;
import es.uclm.esi.isoft2.a04.Domain.TableBooking;
import es.uclm.esi.isoft2.a04.Domain.TableImplementation;
import es.uclm.esi.isoft2.a04.Domain.WaiterImplementation;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class IU_BookingsList extends JPanel {
	private JScrollPane scrollPane;
	private JPanel pnlAssign;
	private JButton btnAssignWaiter;
	private JList lstBookings;
	private DefaultListModel listModel;
	private Booking.TURN currentTurn;
	private Timer bookingTimeout, changeTurn;
	private TimerTask tt1, tt2;
	private TableBooking tb = new TableBooking();
	
	private Booking [] borrarLuego = new Booking[6];
	private JPanel pnlTurn;
	private JComboBox comboBox;

	/**
	 * Create the panel.
	 */
	public IU_BookingsList() {
		setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		listModel = new DefaultListModel();
		lstBookings = new JList<Booking>();
		lstBookings.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstBookings.addListSelectionListener(new LstBookingsListSelectionListener());
		lstBookings.setModel(listModel);
		scrollPane.setViewportView(lstBookings);

		pnlAssign = new JPanel();
		add(pnlAssign, BorderLayout.SOUTH);

		btnAssignWaiter = new JButton("Assign Waiter");
		btnAssignWaiter.addActionListener(new BtnAssignWaiterActionListener());
		btnAssignWaiter.setEnabled(false);
		pnlAssign.add(btnAssignWaiter);
		
		pnlTurn = new JPanel();
		add(pnlTurn, BorderLayout.NORTH);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Lunch 13:00", "Lunch 14:00", "Lunch 15:00", "Dinner 20:30", "Dinner 21:30", "Dinner 22:30"}));
		pnlTurn.add(comboBox);
		
		//listModel.addElement(new Booking(new TableImplementation(5), new Date(), Booking.TURN.L2));
		//listModel.addElement(new Booking(new TableImplementation(7), new Date(), Booking.TURN.L3));
		borrarLuego[0] = new Booking(new TableImplementation(1), new Date(), Booking.TURN.L1);
		borrarLuego[1] = new Booking(new TableImplementation(2), new Date(), Booking.TURN.L2);
		borrarLuego[2] = new Booking(new TableImplementation(3), new Date(1999,10,12), Booking.TURN.D2);
		borrarLuego[3] = new Booking(new TableImplementation(4), new Date(), Booking.TURN.D1);
		borrarLuego[4] = new Booking(new TableImplementation(5), new Date(), Booking.TURN.D2);
		borrarLuego[5] = new Booking(new TableImplementation(6), new Date(), Booking.TURN.D3);
		
		/*
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
		scheduleTimers();*/
		currentTurn=Booking.TURN.D2;
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
		//try {
			//Booking[] list = b.readAll();
			Booking[] list = borrarLuego; //-------------------------------------------------------------------------
			for(int i=0; i<list.length; i++) {
				if((list[i].getDate().getYear() == date.getYear())&& (list[i].getDate().getMonth() == date.getMonth()) && (list[i].getDate().getDay() == date.getDay())) {	//Get the bookings for today
					if(list[i].getTurn() == currentTurn) {	//Get the bookings of the current turn
						listModel.addElement(list[i]);
					}	
				}	
			}
			// To do: Set the proper error messages for each exception
		/*} catch (SQLException x) {
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
		}*/
	}
	private class LstBookingsListSelectionListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			btnAssignWaiter.setEnabled(true);
			System.out.println(((Booking) lstBookings.getSelectedValue()).getTurn());
		}
	}
	private class BtnAssignWaiterActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				WaiterImplementation waiter;
				waiter = tb.assignWaiter((TableImplementation) ((Booking) lstBookings.getSelectedValue()).getTable());
				//updateList();
				JOptionPane.showMessageDialog(null, "Table assigned to waiter '"+waiter.getID()+"'", "Assignation",
						JOptionPane.INFORMATION_MESSAGE);
			}catch (SQLException sql) {
				sql.printStackTrace();
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
