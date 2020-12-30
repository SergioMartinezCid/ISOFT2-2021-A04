package es.uclm.esi.isoft2.a04.Presentation;

import javax.swing.JPanel;
import java.awt.Label;
import javax.swing.JTextField;

import es.uclm.esi.isoft2.a04.Domain.StatisticsControl;
import es.uclm.esi.isoft2.a04.Domain.Table;

import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

import java.awt.Cursor;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JLabel;

/**
 * @version 0.1.2
 * @author Daniel
 *
 */
public class JPanelStatistics extends JPanel {
	private JTextField txtDeliveryTime;
	private JTextField txtMealPreparation;
	private JTextField txtTablePreparation;
	private JTextField txtCommandTime;
	private JComboBox<String> cbOption;
	private JComboBox<Integer> cbRestaurant;
	private Label lblRestaurant;
	private Label lblCity;
	private JComboBox<String> cbCity;
	private Label lblChooseOption;
	private JButton btnCalculate;
	private JButton btnClear;
	
	private StatisticsControl sc = new StatisticsControl();
	private Table tableDB;
	private JLabel lblSeats;
	private JComboBox cbSeats;

	/**
	 * Create the panel.
	 */
	public JPanelStatistics(Table t) {
		tableDB = t;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 54, 135, 59, 96 };
		gridBagLayout.rowHeights = new int[] { 21, 33, 21, 21, 21, 21, 25, 21, 21, 21, 33, 33, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 1.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		Label lblTitle = new Label("Statistics Results");
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 14));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.fill = GridBagConstraints.VERTICAL;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitle.gridwidth = 5;
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		add(lblTitle, gbc_lblTitle);

		lblChooseOption = new Label("Choose Option:");
		GridBagConstraints gbc_lblChooseOption = new GridBagConstraints();
		gbc_lblChooseOption.anchor = GridBagConstraints.EAST;
		gbc_lblChooseOption.fill = GridBagConstraints.VERTICAL;
		gbc_lblChooseOption.insets = new Insets(0, 0, 5, 5);
		gbc_lblChooseOption.gridx = 1;
		gbc_lblChooseOption.gridy = 1;
		add(lblChooseOption, gbc_lblChooseOption);

		cbOption = new JComboBox();
		cbOption.addItemListener(new CbOptionItemListener());
		cbOption.setModel(new DefaultComboBoxModel(new String[] { "Select option...", "City", "Restaurant" }));
		GridBagConstraints gbc_cbOption = new GridBagConstraints();
		gbc_cbOption.fill = GridBagConstraints.BOTH;
		gbc_cbOption.insets = new Insets(0, 0, 5, 5);
		gbc_cbOption.gridx = 2;
		gbc_cbOption.gridy = 1;
		add(cbOption, gbc_cbOption);

		lblRestaurant = new Label("Choose Restaurant:");
		lblRestaurant.setEnabled(false);
		GridBagConstraints gbc_lblRestaurant = new GridBagConstraints();
		gbc_lblRestaurant.anchor = GridBagConstraints.EAST;
		gbc_lblRestaurant.fill = GridBagConstraints.VERTICAL;
		gbc_lblRestaurant.insets = new Insets(0, 0, 5, 5);
		gbc_lblRestaurant.gridx = 1;
		gbc_lblRestaurant.gridy = 2;
		add(lblRestaurant, gbc_lblRestaurant);

		cbRestaurant = new JComboBox<Integer>();
		cbRestaurant.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));
		cbRestaurant.setEnabled(false);
		GridBagConstraints gbc_cbRestaurant = new GridBagConstraints();
		gbc_cbRestaurant.fill = GridBagConstraints.BOTH;
		gbc_cbRestaurant.insets = new Insets(0, 0, 5, 5);
		gbc_cbRestaurant.gridx = 2;
		gbc_cbRestaurant.gridy = 2;
		add(cbRestaurant, gbc_cbRestaurant);

		lblCity = new Label("Choose City:");
		lblCity.setEnabled(false);
		lblCity.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		GridBagConstraints gbc_lblCity = new GridBagConstraints();
		gbc_lblCity.anchor = GridBagConstraints.EAST;
		gbc_lblCity.fill = GridBagConstraints.VERTICAL;
		gbc_lblCity.insets = new Insets(0, 0, 5, 5);
		gbc_lblCity.gridx = 1;
		gbc_lblCity.gridy = 3;
		add(lblCity, gbc_lblCity);

		btnCalculate = new JButton("Calculate");
		btnCalculate.setEnabled(false);
		btnCalculate.addActionListener(new BtnCalculateActionListener());

		cbCity = new JComboBox();
		cbCity.setModel(
				new DefaultComboBoxModel(new String[] { "Madrid", "London", "Paris", "New York", "Barcelona" }));
		cbCity.setEnabled(false);
		GridBagConstraints gbc_cbCity = new GridBagConstraints();
		gbc_cbCity.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbCity.insets = new Insets(0, 0, 5, 5);
		gbc_cbCity.gridx = 2;
		gbc_cbCity.gridy = 3;
		add(cbCity, gbc_cbCity);

		lblSeats = new JLabel("Select Seats:");
		lblSeats.setEnabled(false);
		GridBagConstraints gbc_lblSeats = new GridBagConstraints();
		gbc_lblSeats.anchor = GridBagConstraints.EAST;
		gbc_lblSeats.insets = new Insets(0, 0, 5, 5);
		gbc_lblSeats.gridx = 1;
		gbc_lblSeats.gridy = 4;
		add(lblSeats, gbc_lblSeats);
		
		cbSeats = new JComboBox();
		cbSeats.setEnabled(false);
		cbSeats.setModel(new DefaultComboBoxModel(new String[] {"All", "2", "4", "6"}));
		GridBagConstraints gbc_cbSeats = new GridBagConstraints();
		gbc_cbSeats.insets = new Insets(0, 0, 5, 5);
		gbc_cbSeats.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbSeats.gridx = 2;
		gbc_cbSeats.gridy = 4;
		add(cbSeats, gbc_cbSeats);
		GridBagConstraints gbc_btnCalculate = new GridBagConstraints();
		gbc_btnCalculate.gridwidth = 2;
		gbc_btnCalculate.anchor = GridBagConstraints.NORTH;
		gbc_btnCalculate.insets = new Insets(0, 0, 5, 5);
		gbc_btnCalculate.gridx = 1;
		gbc_btnCalculate.gridy = 5;
		add(btnCalculate, gbc_btnCalculate);

		Label lblResults = new Label("Results");
		lblResults.setFont(new Font("Dialog", Font.BOLD, 14));
		GridBagConstraints gbc_lblResults = new GridBagConstraints();
		gbc_lblResults.anchor = GridBagConstraints.SOUTH;
		gbc_lblResults.gridwidth = 4;
		gbc_lblResults.insets = new Insets(0, 0, 5, 0);
		gbc_lblResults.gridx = 0;
		gbc_lblResults.gridy = 6;
		add(lblResults, gbc_lblResults);

		Label labelAverageComandTime = new Label("Avg. Comand Time:");
		GridBagConstraints gbc_labelAverageComandTime = new GridBagConstraints();
		gbc_labelAverageComandTime.anchor = GridBagConstraints.EAST;
		gbc_labelAverageComandTime.fill = GridBagConstraints.VERTICAL;
		gbc_labelAverageComandTime.insets = new Insets(0, 0, 5, 5);
		gbc_labelAverageComandTime.gridx = 1;
		gbc_labelAverageComandTime.gridy = 7;
		add(labelAverageComandTime, gbc_labelAverageComandTime);

		txtCommandTime = new JTextField();
		txtCommandTime.setEditable(false);
		txtCommandTime.setColumns(10);
		GridBagConstraints gbc_txtCommandTime = new GridBagConstraints();
		gbc_txtCommandTime.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCommandTime.anchor = GridBagConstraints.SOUTH;
		gbc_txtCommandTime.insets = new Insets(0, 0, 5, 5);
		gbc_txtCommandTime.gridx = 2;
		gbc_txtCommandTime.gridy = 7;
		add(txtCommandTime, gbc_txtCommandTime);

		Label labelAverageMealPreparationTime = new Label("Avg. Meal preparation:");
		GridBagConstraints gbc_labelAverageMealPreparationTime = new GridBagConstraints();
		gbc_labelAverageMealPreparationTime.anchor = GridBagConstraints.EAST;
		gbc_labelAverageMealPreparationTime.fill = GridBagConstraints.VERTICAL;
		gbc_labelAverageMealPreparationTime.insets = new Insets(0, 0, 5, 5);
		gbc_labelAverageMealPreparationTime.gridx = 1;
		gbc_labelAverageMealPreparationTime.gridy = 8;
		add(labelAverageMealPreparationTime, gbc_labelAverageMealPreparationTime);

		txtMealPreparation = new JTextField();
		txtMealPreparation.setEditable(false);
		GridBagConstraints gbc_txtMealPreparation = new GridBagConstraints();
		gbc_txtMealPreparation.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMealPreparation.anchor = GridBagConstraints.SOUTH;
		gbc_txtMealPreparation.insets = new Insets(0, 0, 5, 5);
		gbc_txtMealPreparation.gridx = 2;
		gbc_txtMealPreparation.gridy = 8;
		add(txtMealPreparation, gbc_txtMealPreparation);
		txtMealPreparation.setColumns(10);

		Label labelPreparationTIme = new Label("Avg. delivery:");
		GridBagConstraints gbc_labelPreparationTIme = new GridBagConstraints();
		gbc_labelPreparationTIme.anchor = GridBagConstraints.EAST;
		gbc_labelPreparationTIme.fill = GridBagConstraints.VERTICAL;
		gbc_labelPreparationTIme.insets = new Insets(0, 0, 5, 5);
		gbc_labelPreparationTIme.gridx = 1;
		gbc_labelPreparationTIme.gridy = 9;
		add(labelPreparationTIme, gbc_labelPreparationTIme);

		txtDeliveryTime = new JTextField();
		txtDeliveryTime.setEditable(false);
		GridBagConstraints gbc_txtDeliveryTime = new GridBagConstraints();
		gbc_txtDeliveryTime.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDeliveryTime.anchor = GridBagConstraints.SOUTH;
		gbc_txtDeliveryTime.insets = new Insets(0, 0, 5, 5);
		gbc_txtDeliveryTime.gridx = 2;
		gbc_txtDeliveryTime.gridy = 9;
		add(txtDeliveryTime, gbc_txtDeliveryTime);
		txtDeliveryTime.setColumns(10);

		Label labelAverageTime = new Label("Avg.Table Preparation:");
		GridBagConstraints gbc_labelAverageTime = new GridBagConstraints();
		gbc_labelAverageTime.anchor = GridBagConstraints.EAST;
		gbc_labelAverageTime.fill = GridBagConstraints.VERTICAL;
		gbc_labelAverageTime.insets = new Insets(0, 0, 5, 5);
		gbc_labelAverageTime.gridx = 1;
		gbc_labelAverageTime.gridy = 10;
		add(labelAverageTime, gbc_labelAverageTime);

		txtTablePreparation = new JTextField();
		txtTablePreparation.setEditable(false);
		txtTablePreparation.setColumns(10);
		GridBagConstraints gbc_txtTablePreparation = new GridBagConstraints();
		gbc_txtTablePreparation.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTablePreparation.anchor = GridBagConstraints.SOUTH;
		gbc_txtTablePreparation.insets = new Insets(0, 0, 5, 5);
		gbc_txtTablePreparation.gridx = 2;
		gbc_txtTablePreparation.gridy = 10;
		add(txtTablePreparation, gbc_txtTablePreparation);

		btnClear = new JButton("Clear");
		btnClear.setEnabled(false);
		btnClear.addActionListener(new BtnClearActionListener());
		GridBagConstraints gbc_btnClear = new GridBagConstraints();
		gbc_btnClear.insets = new Insets(0, 0, 0, 5);
		gbc_btnClear.gridwidth = 2;
		gbc_btnClear.anchor = GridBagConstraints.NORTH;
		gbc_btnClear.gridx = 1;
		gbc_btnClear.gridy = 11;
		add(btnClear, gbc_btnClear);

	}

	private class BtnClearActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			txtDeliveryTime.setText("");
			txtMealPreparation.setText("");
			txtTablePreparation.setText("");
			txtCommandTime.setText("");
			btnClear.setEnabled(false);
			cbOption.setSelectedIndex(0);
		}
	}



	private class BtnCalculateActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			double CommandTime = 0,MealTime = 0, DeliveryTime = 0, TableTime = 0;
			String option = (String)cbOption.getSelectedItem();
			int seats;
			switch((String)cbSeats.getSelectedItem()) {
			case "2":
				seats = 2;
				break;
			case "4":
				seats = 4;
				break;
			case "6":
				seats = 6;
				break;
			default:
				seats = -1;
			}
			try {
				switch (option) {
				case "City":
					System.out.println(tableDB + "\n"+ seats+ "\n"+(String) cbCity.getSelectedItem());
					CommandTime = sc.getAverageCommandTime(tableDB, seats, -1, (String) cbCity.getSelectedItem());
					MealTime = sc.getAverageMealPreparationTime(tableDB, seats, -1, (String) cbCity.getSelectedItem());
					DeliveryTime = sc.getAverageCheckDeliveryTime(tableDB, seats, -1,
							(String) cbCity.getSelectedItem());
					TableTime = sc.getAverageTablePreparationTime(tableDB, seats, -1,
							(String) cbCity.getSelectedItem());
					break;
				case "Restaurant":
					System.out.println(tableDB + "\n"+ seats+ "\n"+Integer.parseInt((String) cbRestaurant.getSelectedItem()));
					CommandTime = sc.getAverageCommandTime(tableDB, seats,
							Integer.parseInt((String) cbRestaurant.getSelectedItem()), null);
					MealTime = sc.getAverageMealPreparationTime(tableDB, seats,
							Integer.parseInt((String) cbRestaurant.getSelectedItem()), null);
					DeliveryTime = sc.getAverageCheckDeliveryTime(tableDB, seats,
							Integer.parseInt((String) cbRestaurant.getSelectedItem()), null);
					TableTime = sc.getAverageTablePreparationTime(tableDB, seats,
							Integer.parseInt((String) cbRestaurant.getSelectedItem()), null);
					break;
				default:
					// Should never happen
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
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				txtCommandTime.setText((CommandTime / 1000) + " s");
			} catch (ArithmeticException x) {
				txtCommandTime.setText("*No registered times*");
			}
			try {
				txtMealPreparation.setText((MealTime/1000) + " s");
			}catch(ArithmeticException x) {
				txtMealPreparation.setText("*No registered times*");
			}
			try {
				txtDeliveryTime.setText((DeliveryTime/1000) + " s");
			}catch(ArithmeticException x) {
				txtDeliveryTime.setText("*No registered times*");
			}
			try {
				txtTablePreparation.setText((TableTime/1000) + " s");
			}catch(ArithmeticException x) {
				txtTablePreparation.setText("*No registered times*");
			}
			btnClear.setEnabled(true);
		}
	}
	
	private class CbOptionItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			switch ((String) cbOption.getSelectedItem()) {
			case "City":
				lblCity.setEnabled(true);
				cbCity.setEnabled(true);
				btnCalculate.setEnabled(true);
				lblRestaurant.setEnabled(false);
				cbRestaurant.setEnabled(false);
				lblSeats.setEnabled(true);
				cbSeats.setEnabled(true);
				break;
			case "Restaurant":
				lblRestaurant.setEnabled(true);
				cbRestaurant.setEnabled(true);
				btnCalculate.setEnabled(true);
				lblCity.setEnabled(false);
				cbCity.setEnabled(false);
				lblSeats.setEnabled(true);
				cbSeats.setEnabled(true);
				break;
			default:
				lblRestaurant.setEnabled(false);
				cbRestaurant.setEnabled(false);
				btnCalculate.setEnabled(false);
				lblCity.setEnabled(false);
				cbCity.setEnabled(false);
				lblSeats.setEnabled(false);
				cbSeats.setEnabled(false);
			}
		}
	}
}
