package es.uclm.esi.isoft2.a04.Presentation;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;
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
import javax.swing.JLabel;

public class IU_BookingsList extends JPanel {
	private JScrollPane scrollPane;
	private JPanel pnlAssign;
	private JButton btnAssignWaiter;
	private JList lstBookings;
	private DefaultListModel listModel;
	private Timer bookingTimeout = new Timer();
	private TimerTask tt1;
	private TableBooking tb = new TableBooking();
	
	private Booking [] borrarLuego = new Booking[6];
	private JPanel pnlTurn;
	private JComboBox cbTurn;
	private JLabel lblTurn;

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
		
		cbTurn = new JComboBox();
		cbTurn.addActionListener(new CbTurnActionListener());
		
		lblTurn = new JLabel("Turn: ");
		pnlTurn.add(lblTurn);
		cbTurn.setModel(new DefaultComboBoxModel(new String[] {"Select turn...", "Lunch 13:00", "Lunch 14:00", "Lunch 15:00", "Dinner 20:30", "Dinner 21:30", "Dinner 22:30"}));
		pnlTurn.add(cbTurn);
		
		//listModel.addElement(new Booking(new TableImplementation(5), new Date(), Booking.TURN.L2));
		//listModel.addElement(new Booking(new TableImplementation(7), new Date(), Booking.TURN.L3));
		borrarLuego[0] = new Booking(new TableImplementation(1), new Date(), Booking.TURN.L1);
		borrarLuego[1] = new Booking(new TableImplementation(2), changeToTomorrow(new Date()), Booking.TURN.L2);
		borrarLuego[1].setClientID("Alfredo");
		borrarLuego[2] = new Booking(new TableImplementation(7), changeToTomorrow(new Date()), Booking.TURN.L2);
		borrarLuego[2].setClientID("Manolo");
		borrarLuego[3] = new Booking(new TableImplementation(4), new Date(), Booking.TURN.D1);
		borrarLuego[4] = new Booking(new TableImplementation(5), new Date(), Booking.TURN.D2);
		borrarLuego[5] = new Booking(new TableImplementation(6), new Date(), Booking.TURN.D3);
		
		
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
		    	//Schedule timer for tomorrow
		    	scheduleTimer();
			};
		};
		scheduleTimer();

	}
	
	public void scheduleTimer() {
		Date time = new Date(),now = new Date();
		
		time.setHours(13);
		time.setMinutes(20);
		time.setSeconds(00);
		if(now.after(time)) {
			time.setHours(14);
			if(now.after(time)) {
				time.setHours(15);
				if(now.after(time)) {
					time.setHours(20);
					time.setMinutes(50);
					if(now.after(time)) {
						time.setHours(21);
						if(now.after(time)) {
							time.setHours(22);
							if(now.after(time)) {
								time = changeToTomorrow(time);
								time.setHours(13);
								time.setMinutes(20);
								bookingTimeout.schedule(tt1, time);
							}else {
								bookingTimeout.schedule(tt1, time);
							}
						}else {
							bookingTimeout.schedule(tt1, time);
						}
					}else {
						bookingTimeout.schedule(tt1, time);
					}
				}else {
					bookingTimeout.schedule(tt1, time);
				}
			}else {
				bookingTimeout.schedule(tt1, time);
			}
		}else {
			bookingTimeout.schedule(tt1, time);
		}
	}
	
	public void updateList(Booking.TURN turn, Date date) {
		Booking b = new Booking();
		listModel.clear();
		//try {
			//Booking[] list = b.readAll();
			Booking[] list = borrarLuego; //-------------------------------------------------------------------------
			for(int i=0; i<list.length; i++) {
				if((list[i].getDate().getYear() == date.getYear())&& (list[i].getDate().getMonth() == date.getMonth()) && (list[i].getDate().getDay() == date.getDay())) {	//Get the bookings for today
					if(list[i].getTurn() == turn && !list[i].getClientID().equals("")) {	//Get the bookings of the current turn
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
			try {
				Booking.TURN t = ((Booking) lstBookings.getSelectedValue()).getTurn();
				Date aux = (Date) ((Booking) lstBookings.getSelectedValue()).getDate().clone();
				aux.setSeconds(0);
				switch(t) {
				case L1:
					aux.setHours(13);
					aux.setMinutes(0);
					break;
				case L2:
					aux.setHours(14);
					aux.setMinutes(0);
					break;
				case L3:
					aux.setHours(15);
					aux.setMinutes(0);
					break;
				case D1:
					aux.setHours(20);
					aux.setMinutes(30);
					break;
				case D2:
					aux.setHours(21);
					aux.setMinutes(30);
					break;
				case D3:
					aux.setHours(22);
					aux.setMinutes(30);
					break;
				}
				if(new Date().after(aux)) {
					btnAssignWaiter.setEnabled(true);
				}			
			}catch(NullPointerException n) {
				btnAssignWaiter.setEnabled(false);
			}
		}
	}
	private class BtnAssignWaiterActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				WaiterImplementation waiter;
				waiter = tb.assignWaiter((TableImplementation) ((Booking) lstBookings.getSelectedValue()).getTable());
				((Booking)lstBookings.getSelectedValue()).setClientID("");
				//((Booking) lstBookings.getSelectedValue()).update();
				listModel.removeElement(lstBookings.getSelectedValue());
				JOptionPane.showMessageDialog(null, "Table assigned to waiter '"+waiter.getName()+"'("+waiter.getID()+")", "Assignation",
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
	private class CbTurnActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Booking.TURN turn = null;
			Date aux = new Date(), date = new Date();
			lstBookings.clearSelection();
			aux.setSeconds(0);
			switch ((String)cbTurn.getSelectedItem()) {
			case "Lunch 13:00":
				turn = Booking.TURN.L1;
				aux.setHours(13);
				aux.setMinutes(20);
				break;
			case "Lunch 14:00":
				turn = Booking.TURN.L2;
				aux.setHours(14);
				aux.setMinutes(20);
				break;
			case "Lunch 15:00":
				turn = Booking.TURN.L3;
				aux.setHours(15);
				aux.setMinutes(20);
				break;
			case "Dinner 20:30":
				turn = Booking.TURN.D1;
				aux.setHours(20);
				aux.setMinutes(50);
				break;
			case "Dinner 21:30":
				turn = Booking.TURN.D2;
				aux.setHours(21);
				aux.setMinutes(50);
				break;
			case "Dinner 22:30":
				turn = Booking.TURN.D3;
				aux.setHours(22);
				aux.setMinutes(50);
				break;
			default:
				listModel.clear();
				return;
			}
			if(date.after(aux)) {
				date = changeToTomorrow(date);
			}
			updateList(turn, date);
		}
	}
	
	/*******************
	 * Change the current date to the next day
	 * @param date
	 */
	public Date changeToTomorrow(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        date = c.getTime();
        return date;
	}
}
