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
	private DefaultListModel<Booking> listModel;
	private Timer bookingTimeout = new Timer();
	private TimerTask tl1, tl2, tl3, td1, td2, td3;
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

		listModel = new DefaultListModel<Booking>();
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
		/*tl1 = new TimerTask() {
			public void run() {
				cancelBookings(Booking.TURN.L1);
			};
		};
		tl2 = new TimerTask() {
			public void run() {
				cancelBookings(Booking.TURN.L2);
			};
		};
		tl3 = new TimerTask() {
			public void run() {
				cancelBookings(Booking.TURN.L3);
			};
		};
		td1 = new TimerTask() {
			public void run() {
				cancelBookings(Booking.TURN.D1);
			};
		};
		td2 = new TimerTask() {
			public void run() {
				cancelBookings(Booking.TURN.D2);
			};
		};
		td3 = new TimerTask() {
			public void run() {
				cancelBookings(Booking.TURN.D3);
			};
		};*/
		scheduleTimers(Booking.TURN.L1);
		scheduleTimers(Booking.TURN.L2);
		scheduleTimers(Booking.TURN.L3);
		scheduleTimers(Booking.TURN.D1);
		scheduleTimers(Booking.TURN.D2);
		scheduleTimers(Booking.TURN.D3);
	}
	
	public void scheduleTimers(Booking.TURN t) {
		Date time = new Date(),now = new Date();
		tl1 = new TimerTask() {
			public void run() {
				cancelBookings(t);
			};
		};
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		c.set(Calendar.SECOND, 0);
		System.out.println(t);
		switch(t) {
		case L1:
			c.set(Calendar.HOUR_OF_DAY, 13);
			c.set(Calendar.MINUTE, 20);
			time = c.getTime();
			if(now.after(time))
				c.add(Calendar.DATE, 1);
			break;
		case L2:
			c.set(Calendar.HOUR_OF_DAY, 14);
			c.set(Calendar.MINUTE, 20);
			time = c.getTime();
			if(now.after(time))
				c.add(Calendar.DATE, 1);
			break;
		case L3:
			c.set(Calendar.HOUR_OF_DAY, 15);
			c.set(Calendar.MINUTE, 20);
			time = c.getTime();
			if(now.after(time))
				c.add(Calendar.DATE, 1);
			break;
		case D1:
			c.set(Calendar.HOUR_OF_DAY, 20);
			c.set(Calendar.MINUTE, 50);
			time = c.getTime();
			if(now.after(time))
				c.add(Calendar.DATE, 1);
			break;
		case D2:
			c.set(Calendar.HOUR_OF_DAY, 21);
			c.set(Calendar.MINUTE, 50);
			time = c.getTime();
			if(now.after(time))
				c.add(Calendar.DATE, 1);
			break;
		case D3:
			c.set(Calendar.HOUR_OF_DAY, 22);
			c.set(Calendar.MINUTE, 50);
			time = c.getTime();
			if(now.after(time))
				c.add(Calendar.DATE, 1);
			break;
		}
		time = c.getTime();
		bookingTimeout.schedule(tl1, time, 1000*60*60*24);
		System.out.println("Task scheduled at: " + time.getHours() + ":"+time.getMinutes());
	}
	
	public void cancelBookings(Booking.TURN t) {
		//Cancell the bookings
    	while(!listModel.isEmpty()) {
    		try {
				if (((Booking) listModel.get(0)).getTurn().equals(t)) {
					System.out.println("Removing:\n" + (Booking) listModel.get(0));
					tb.cancelBooking((Booking) listModel.get(0));
					listModel.removeElement((Booking) listModel.get(0));
				}
    		} catch (SQLException x) {
    			x.printStackTrace();
    			JOptionPane.showMessageDialog(null, "SQLException error", "Booking Error", JOptionPane.ERROR_MESSAGE);
    		} catch (IllegalAccessException il) {
    			il.printStackTrace();
    			JOptionPane.showMessageDialog(null, "IllegalAccessException error", "Booking Error",
    					JOptionPane.ERROR_MESSAGE);
    		} catch (ClassNotFoundException clnf) {
    			clnf.printStackTrace();
    			JOptionPane.showMessageDialog(null, "ClassNotFoundException error", "Booking Error",
    					JOptionPane.ERROR_MESSAGE);
    		} catch (InstantiationException inst) {
    			inst.printStackTrace();
    			JOptionPane.showMessageDialog(null, "InstantiationException error", "Booking Error",
    					JOptionPane.ERROR_MESSAGE);
    		}
    	}
	}
	
	public void updateList(Booking.TURN turn, Date date) {
		Booking b = new Booking();
		listModel.clear();
		try {
			Booking[] list = b.readAll();
			for(int i=0; i<list.length; i++) {
				if((list[i].getDate().getYear() == date.getYear())&& (list[i].getDate().getMonth() == date.getMonth()) && (list[i].getDate().getDay() == date.getDay())) {	//Get the bookings for today
					if(list[i].getTurn() == turn && !list[i].getClientID().equals("")) {	//Get the bookings of the current turn
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
			try {
				Booking.TURN t = ((Booking) lstBookings.getSelectedValue()).getTurn();
				Date aux = (Date) ((Booking) lstBookings.getSelectedValue()).getDate().clone();
				Calendar c = Calendar.getInstance();
				c.setTime(aux);
				c.set(Calendar.SECOND, 0);
				switch(t) {
				case L1:
					c.set(Calendar.HOUR_OF_DAY, 13);
					c.set(Calendar.MINUTE, 0);
					break;
				case L2:
					c.set(Calendar.HOUR_OF_DAY, 14);
					c.set(Calendar.MINUTE, 0);
					break;
				case L3:
					c.set(Calendar.HOUR_OF_DAY, 15);
					c.set(Calendar.MINUTE, 0);
					break;
				case D1:
					c.set(Calendar.HOUR_OF_DAY, 20);
					c.set(Calendar.MINUTE, 30);
					break;
				case D2:
					c.set(Calendar.HOUR_OF_DAY, 21);
					c.set(Calendar.MINUTE, 30);
					break;
				case D3:
					c.set(Calendar.HOUR_OF_DAY, 22);
					c.set(Calendar.MINUTE, 30);
					break;
				}
				aux = c.getTime();
				if(new Date().after(aux))
				btnAssignWaiter.setEnabled(true);
							
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
				tb.cancelBooking(((Booking) lstBookings.getSelectedValue()));
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
			Calendar c = Calendar.getInstance();
			c.setTime(aux);
			lstBookings.clearSelection();
			c.set(Calendar.SECOND, 0);
			switch ((String)cbTurn.getSelectedItem()) {
			case "Lunch 13:00":
				turn = Booking.TURN.L1;
				c.set(Calendar.HOUR_OF_DAY, 13);
				c.set(Calendar.MINUTE, 20);
				break;
			case "Lunch 14:00":
				turn = Booking.TURN.L2;
				c.set(Calendar.HOUR_OF_DAY, 14);
				c.set(Calendar.MINUTE, 20);
				break;
			case "Lunch 15:00":
				turn = Booking.TURN.L3;
				c.set(Calendar.HOUR_OF_DAY, 15);
				c.set(Calendar.MINUTE, 20);
				break;
			case "Dinner 20:30":
				turn = Booking.TURN.D1;
				c.set(Calendar.HOUR_OF_DAY, 20);
				c.set(Calendar.MINUTE, 50);
				break;
			case "Dinner 21:30":
				turn = Booking.TURN.D2;
				c.set(Calendar.HOUR_OF_DAY, 21);
				c.set(Calendar.MINUTE, 50);
				break;
			case "Dinner 22:30":
				turn = Booking.TURN.D3;
				c.set(Calendar.HOUR_OF_DAY, 22);
				c.set(Calendar.MINUTE, 50);
				break;
			default:
				listModel.clear();
				return;
			}
			aux = c.getTime();
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
