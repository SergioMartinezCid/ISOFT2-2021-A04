package es.uclm.esi.isoft2.a04.Presentation;

import javax.swing.JPanel;

import es.uclm.esi.isoft2.a04.Domain.Food;
import es.uclm.esi.isoft2.a04.Domain.InvalidStateException;
import es.uclm.esi.isoft2.a04.Domain.InvalidTypeException;
import es.uclm.esi.isoft2.a04.Domain.Order;
import es.uclm.esi.isoft2.a04.Domain.PaymentControl;
import es.uclm.esi.isoft2.a04.Domain.Table;
import es.uclm.esi.isoft2.a04.Domain.Waiter;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ListSelectionModel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JTextArea;

/**
 * @version 0.1.2
 *
 */
public class JPanelPayment extends JPanel {
	private JSplitPane splitPane;
	private JScrollPane spList;
	private JList lstOrders;
	private JPanel panel;
	private JLabel lblOrderContent;
	private DefaultListModel<Order> listModel;
	private JLabel lblTotalCost;
	private JLabel lblOrderCost;
	private JButton btnPay;
	private PaymentControl pc;
	private JScrollPane scrollPane;
	private JTextArea txtAContent;
	private Timer preparation = new Timer();
	private TimerTask tablePrepared;

	/**
	 * Create the panel.
	 */
	public JPanelPayment(Waiter waiterDB, Order orderDB) {
		setLayout(new BorderLayout(0, 0));
		
		splitPane = new JSplitPane();
		add(splitPane, BorderLayout.CENTER);
		
		spList = new JScrollPane();
		spList.setMaximumSize(new Dimension(300, 32767));
		spList.setMinimumSize(new Dimension(200, 23));
		splitPane.setLeftComponent(spList);
		
		listModel = new DefaultListModel<Order>();
		lstOrders = new JList<Order>();
		lstOrders.addListSelectionListener(new LstOrdersListSelectionListener());
		lstOrders.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstOrders.setMinimumSize(new Dimension(200, 0));
		lstOrders.setMaximumSize(new Dimension(300, 0));
		lstOrders.setModel(listModel);
		spList.setViewportView(lstOrders);
		
		panel = new JPanel();
		splitPane.setRightComponent(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{28, 0, 41, 46, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		lblOrderContent = new JLabel("Order Content:");
		GridBagConstraints gbc_lblOrderContent = new GridBagConstraints();
		gbc_lblOrderContent.anchor = GridBagConstraints.NORTH;
		gbc_lblOrderContent.insets = new Insets(0, 0, 5, 5);
		gbc_lblOrderContent.gridx = 0;
		gbc_lblOrderContent.gridy = 1;
		panel.add(lblOrderContent, gbc_lblOrderContent);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		panel.add(scrollPane, gbc_scrollPane);
		
		txtAContent = new JTextArea();
		txtAContent.setEditable(false);
		scrollPane.setViewportView(txtAContent);
		
		lblTotalCost = new JLabel("Total cost:");
		GridBagConstraints gbc_lblTotalCost = new GridBagConstraints();
		gbc_lblTotalCost.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalCost.gridx = 0;
		gbc_lblTotalCost.gridy = 2;
		panel.add(lblTotalCost, gbc_lblTotalCost);
		
		lblOrderCost = new JLabel("€");
		lblOrderCost.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblOrderCost = new GridBagConstraints();
		gbc_lblOrderCost.insets = new Insets(0, 0, 5, 5);
		gbc_lblOrderCost.gridx = 1;
		gbc_lblOrderCost.gridy = 2;
		panel.add(lblOrderCost, gbc_lblOrderCost);
		
		btnPay = new JButton("Pay");
		btnPay.addActionListener(new BtnPayActionListener());
		GridBagConstraints gbc_btnPay = new GridBagConstraints();
		gbc_btnPay.insets = new Insets(0, 0, 0, 5);
		gbc_btnPay.gridx = 1;
		gbc_btnPay.gridy = 3;
		panel.add(btnPay, gbc_btnPay);

		updateOrdersList(waiterDB.getID(), orderDB);
	}
	
	public void updateOrdersList(int waiterID, Order orderDB) {
		listModel.clear();
		try {
			Order[] orders = orderDB.readAll();
			System.out.println("WaiterID: "+waiterID);
			for(int i=0; i<orders.length;i++) {
				System.out.println("Id:"+orders[i].getID()+" / Waiter:"+orders[i].getWaiter().getID()+" / State:"+orders[i].getState());
				if ((orders[i].getWaiter().getID()==waiterID)&&(orders[i].getState()==Order.CLOSED)) {
					listModel.addElement(orders[i]);
				}
			}
		}catch(InvalidStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private class BtnPayActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				Order order = (Order) lstOrders.getSelectedValue();
				pc.askForTheCheck(order);
				int method = JOptionPane.showOptionDialog(null, "Select payment method", "Payment method", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new String[]{"cash","credit card"}, 0);
				System.out.println(method);
				if(method!=-1) {
					pc.startPayment(order);
					boolean cash = true;
					String paymentM = "CASH";
					if (method == 1) {
						cash = false;
						paymentM = "CREDIT CARD";
					}
					pc.makePayment(order, cash, paymentM);
					listModel.removeElement(order);
					txtAContent.setText("");
					lblOrderCost.setText("€");
					btnPay.setEnabled(false);
					//Simulate that the preparation of the table lasts 15 minutes
					prepareTable(order.getTable());
				}else {
					order.getTable().setState(Table.SERVED);
					order.getTable().update();
				}
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
			} catch (InvalidStateException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void prepareTable(Table t) {
		
		try {
			Date time;
			pc.startPreparation(t);
			time = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(time);
			c.add(Calendar.MINUTE, 15);
			time = c.getTime();
			tablePrepared = new TimerTask() {
				public void run() {
					try {
						pc.finishPreparation(t);
					} catch (NumberFormatException | InstantiationException | IllegalAccessException
							| ClassNotFoundException | InvalidStateException | SQLException | ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				};
			};
			preparation.schedule(tablePrepared, time);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private class LstOrdersListSelectionListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			try {
				Order order = (Order) lstOrders.getSelectedValue();
				Food [] content = order.getFood();
				String aux = "";
				for(int i=0; i<content.length;i++) {
					aux += content[i].getName() + " (" + content[i].getCost() + "€) x"+ content[i].getQuantity(); 
				}
				txtAContent.setText(aux);
				lblOrderCost.setText(order.getCost() + " €");
				btnPay.setEnabled(true);
			}catch(NullPointerException x) {
			}
		}
	}
}
