package es.uclm.esi.isoft2.a04.Presentation;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.uclm.esi.isoft2.a04.Domain.TableBooking;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Choice;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ButtonGroup;

public class IU_Booking extends JPanel {
	private JPanel contentPane;
	private JLabel lblClient;
	private JTextField txtClient;
	private JLabel lblLunch;
	private JLabel lblDinner;
	private JRadioButton rdbtn1;
	private JRadioButton rdbtn4;
	private JLabel lblTurn;
	private JRadioButton rdbtn2;
	private JRadioButton rdbtn5;
	private JRadioButton rdbtn3;
	private JRadioButton rdbtn6;
	private JLabel lblNewLabel;
	private Choice chSeats;
	private Button btnConfirm;
	private JLabel lblInfo;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private Date turn;
	private TableBooking tB = new TableBooking();

	/**
	 * Create the panel.
	 */
	public IU_Booking() {
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{86, 156, 139, 0, 0};
		gbl_contentPane.rowHeights = new int[]{54, 40, 24, 0, 0, 44, 68, 46, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblClient = new JLabel("Client:");
		lblClient.setForeground(Color.BLUE);
		lblClient.setFont(new Font("Arial Black", Font.BOLD, 11));
		GridBagConstraints gbc_lblClient = new GridBagConstraints();
		gbc_lblClient.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblClient.insets = new Insets(0, 0, 5, 5);
		gbc_lblClient.gridx = 0;
		gbc_lblClient.gridy = 0;
		contentPane.add(lblClient, gbc_lblClient);
		
		txtClient = new JTextField();
		txtClient.addFocusListener(new TxtClientFocusListener());
		txtClient.setColumns(10);
		txtClient.setBackground(Color.WHITE);
		GridBagConstraints gbc_txtClient = new GridBagConstraints();
		gbc_txtClient.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtClient.anchor = GridBagConstraints.SOUTH;
		gbc_txtClient.gridwidth = 2;
		gbc_txtClient.insets = new Insets(0, 0, 5, 5);
		gbc_txtClient.gridx = 1;
		gbc_txtClient.gridy = 0;
		contentPane.add(txtClient, gbc_txtClient);
		
		lblLunch = new JLabel("Lunch");
		GridBagConstraints gbc_lblLunch = new GridBagConstraints();
		gbc_lblLunch.anchor = GridBagConstraints.SOUTH;
		gbc_lblLunch.insets = new Insets(0, 0, 5, 5);
		gbc_lblLunch.gridx = 1;
		gbc_lblLunch.gridy = 1;
		contentPane.add(lblLunch, gbc_lblLunch);
		
		lblDinner = new JLabel("Dinner");
		GridBagConstraints gbc_lblDinner = new GridBagConstraints();
		gbc_lblDinner.anchor = GridBagConstraints.SOUTH;
		gbc_lblDinner.insets = new Insets(0, 0, 5, 5);
		gbc_lblDinner.gridx = 2;
		gbc_lblDinner.gridy = 1;
		contentPane.add(lblDinner, gbc_lblDinner);
		
		rdbtn1 = new JRadioButton("13:00");
		buttonGroup.add(rdbtn1);
		rdbtn1.addActionListener(new RdbtnActionListener());
		GridBagConstraints gbc_rdbtn1 = new GridBagConstraints();
		gbc_rdbtn1.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtn1.gridx = 1;
		gbc_rdbtn1.gridy = 2;
		contentPane.add(rdbtn1, gbc_rdbtn1);
		
		rdbtn4 = new JRadioButton("20:30");
		rdbtn4.addActionListener(new RdbtnActionListener());
		buttonGroup.add(rdbtn4);
		GridBagConstraints gbc_rdbtn4 = new GridBagConstraints();
		gbc_rdbtn4.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtn4.gridx = 2;
		gbc_rdbtn4.gridy = 2;
		contentPane.add(rdbtn4, gbc_rdbtn4);
		
		lblTurn = new JLabel("Turn:");
		lblTurn.setForeground(Color.BLUE);
		lblTurn.setFont(new Font("Arial Black", Font.BOLD, 11));
		GridBagConstraints gbc_lblTurn = new GridBagConstraints();
		gbc_lblTurn.anchor = GridBagConstraints.EAST;
		gbc_lblTurn.insets = new Insets(0, 0, 5, 5);
		gbc_lblTurn.gridx = 0;
		gbc_lblTurn.gridy = 3;
		contentPane.add(lblTurn, gbc_lblTurn);
		
		rdbtn2 = new JRadioButton("14:00");
		rdbtn2.addActionListener(new RdbtnActionListener());
		buttonGroup.add(rdbtn2);
		GridBagConstraints gbc_rdbtn2 = new GridBagConstraints();
		gbc_rdbtn2.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtn2.gridx = 1;
		gbc_rdbtn2.gridy = 3;
		contentPane.add(rdbtn2, gbc_rdbtn2);
		
		rdbtn5 = new JRadioButton("21:30");
		rdbtn5.addActionListener(new RdbtnActionListener());
		buttonGroup.add(rdbtn5);
		GridBagConstraints gbc_rdbtn5 = new GridBagConstraints();
		gbc_rdbtn5.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtn5.gridx = 2;
		gbc_rdbtn5.gridy = 3;
		contentPane.add(rdbtn5, gbc_rdbtn5);
		
		rdbtn3 = new JRadioButton("15:00");
		rdbtn3.addActionListener(new RdbtnActionListener());
		buttonGroup.add(rdbtn3);
		GridBagConstraints gbc_rdbtn3 = new GridBagConstraints();
		gbc_rdbtn3.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtn3.gridx = 1;
		gbc_rdbtn3.gridy = 4;
		contentPane.add(rdbtn3, gbc_rdbtn3);
		
		rdbtn6 = new JRadioButton("22:30");
		rdbtn6.addActionListener(new RdbtnActionListener());
		buttonGroup.add(rdbtn6);
		GridBagConstraints gbc_rdbtn6 = new GridBagConstraints();
		gbc_rdbtn6.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtn6.gridx = 2;
		gbc_rdbtn6.gridy = 4;
		contentPane.add(rdbtn6, gbc_rdbtn6);
		
		lblNewLabel = new JLabel("Seats:");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 11));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 5;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		chSeats = new Choice();
		GridBagConstraints gbc_chSeats = new GridBagConstraints();
		gbc_chSeats.anchor = GridBagConstraints.SOUTH;
		gbc_chSeats.insets = new Insets(0, 0, 5, 5);
		gbc_chSeats.gridx = 1;
		gbc_chSeats.gridy = 5;
		contentPane.add(chSeats, gbc_chSeats);
		
		btnConfirm = new Button("Confirm");
		btnConfirm.addActionListener(new BtnConfirmActionListener());
		btnConfirm.setForeground(Color.BLACK);
		GridBagConstraints gbc_btnConfirm = new GridBagConstraints();
		gbc_btnConfirm.fill = GridBagConstraints.BOTH;
		gbc_btnConfirm.gridwidth = 2;
		gbc_btnConfirm.insets = new Insets(0, 0, 5, 5);
		gbc_btnConfirm.gridx = 1;
		gbc_btnConfirm.gridy = 7;
		contentPane.add(btnConfirm, gbc_btnConfirm);
		
		lblInfo = new JLabel("Information...");
		GridBagConstraints gbc_lblInfo = new GridBagConstraints();
		gbc_lblInfo.insets = new Insets(0, 0, 0, 5);
		gbc_lblInfo.anchor = GridBagConstraints.WEST;
		gbc_lblInfo.gridwidth = 4;
		gbc_lblInfo.gridx = 0;
		gbc_lblInfo.gridy = 8;
		contentPane.add(lblInfo, gbc_lblInfo);

	}

	private class RdbtnActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			turn = new Date();
			turn.setHours(Integer.parseInt(e.getActionCommand().split(":")[0]));
			turn.setMinutes(Integer.parseInt(e.getActionCommand().split(":")[1]));
			turn.setSeconds(0);
		}
	}
	private class TxtClientFocusListener extends FocusAdapter {
		@Override
		public void focusGained(FocusEvent e) {
			txtClient.setBackground(Color.WHITE);
		}
	}
	private class BtnConfirmActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!txtClient.getText().equals("")) {
				if(buttonGroup.getSelection()!=null) {
					switch (chSeats.getSelectedItem()) {
					case "2":
						//tB.bookTable(turn,2,txtClient.getText());
						break;
					case "4":
						//tB.bookTable(turn,4,txtClient.getText());
						break;
					case "6":
						//tB.bookTable(turn,6,txtClient.getText());
						break;
					default:
						System.out.println(chSeats.getSelectedItem());
					}
					System.out.println(turn);
				}else{
					lblInfo.setText("Turn not selected");
				}
			}else {
				txtClient.setBackground(Color.RED);
				lblInfo.setText("Client identification not introduced");
			}
		}
	}
}
