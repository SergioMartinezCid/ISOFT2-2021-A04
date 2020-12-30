package es.uclm.esi.isoft2.a04.Presentation;

import javax.swing.JPanel;
import java.awt.Label;
import javax.swing.JTextField;
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
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

import es.uclm.esi.isoft2.a04.Domain.*;
import java.awt.Cursor;

/**
 * @version 0.1.1
 * @author Daniel
 *
 */
public class JPanelStatistics extends JPanel {
	private JTextField textPreparationTime;
	private JTextField textAverageMeal;
	private JTextField textTimeTable;
	private JTextField textCommandTime;
	private JTextField textSearch;
	private JComboBox comboBoxOptionRestaurantCity;
	private JComboBox<Table> comboBoxTables;
	private Label labelInsertNameCity;
	private Label labelChooseRestaurant;

	public Table[] tables;
	public TableImplementation tableImplementCombox;
	public TableImplementation[] restaurantTable;
	public TableImplementation tableImplementaitonCity;
	public Table tableDBStatistics;
	private StatisticsControl Control;

	/**
	 * Create the panel.
	 */
	public JPanelStatistics() {
		setLayout(null);

		Label labelInsertNameCity = new Label("Insert City");
		labelInsertNameCity.setEnabled(false);
		labelInsertNameCity.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		labelInsertNameCity.setBounds(49, 160, 152, 21);
		add(labelInsertNameCity);

		Label labelResults = new Label("Results");
		labelResults.setBounds(162, 193, 59, 21);
		add(labelResults);

		Label labelPreparationTIme = new Label("Preparation Time");
		labelPreparationTIme.setBounds(50, 226, 104, 21);
		add(labelPreparationTIme);

		Label labelAverageTime = new Label("Average Time Table:");
		labelAverageTime.setBounds(49, 362, 135, 21);
		add(labelAverageTime);

		Label labelAverageComandTime = new Label("Average Comand Time");
		labelAverageComandTime.setBounds(49, 313, 152, 21);
		add(labelAverageComandTime);

		Label labelAverageMealPreparationTime = new Label("Average Meal preparation");
		labelAverageMealPreparationTime.setBounds(49, 271, 152, 21);
		add(labelAverageMealPreparationTime);

		textPreparationTime = new JTextField();
		textPreparationTime.setBounds(222, 228, 96, 19);
		add(textPreparationTime);
		textPreparationTime.setColumns(10);

		textAverageMeal = new JTextField();
		textAverageMeal.setBounds(222, 273, 96, 19);
		add(textAverageMeal);
		textAverageMeal.setColumns(10);

		textTimeTable = new JTextField();
		textTimeTable.setColumns(10);
		textTimeTable.setBounds(222, 364, 96, 19);
		add(textTimeTable);

		textCommandTime = new JTextField();
		textCommandTime.setColumns(10);
		textCommandTime.setBounds(222, 315, 96, 19);
		add(textCommandTime);

		textSearch = new JTextField();
		textSearch.setEnabled(false);
		textSearch.setBounds(222, 160, 96, 19);
		add(textSearch);
		textSearch.setColumns(10);

		comboBoxOptionRestaurantCity = new JComboBox();
		comboBoxOptionRestaurantCity.setModel(new DefaultComboBoxModel(new String[] { "City", "Restaurant" }));
		comboBoxOptionRestaurantCity.setBounds(222, 64, 29, 21);
		add(comboBoxOptionRestaurantCity);

		Label labelChooseOption = new Label("Choose one option");
		labelChooseOption.setBounds(49, 64, 130, 21);
		add(labelChooseOption);

		Label labelTitleStatisticsResults = new Label("Statistics Results");
		labelTitleStatisticsResults.setBounds(95, 10, 126, 21);
		add(labelTitleStatisticsResults);

		JButton btnMakeStatistics = new JButton("Accept");
		btnMakeStatistics.addActionListener(new BtnMakeStatisticsActionListener());
		btnMakeStatistics.setBounds(50, 416, 85, 21);
		add(btnMakeStatistics);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new BtnDeleteActionListener());
		btnDelete.setBounds(233, 416, 85, 21);
		add(btnDelete);

		comboBoxTables = new JComboBox();
		comboBoxTables.setModel(new DefaultComboBoxModel(new String[] { "", "City", "Restaurant" }));
		comboBoxTables.setEnabled(false);
		comboBoxTables.setBounds(222, 114, 29, 21);
		add(comboBoxTables);

		Label labelChooseRestaurant = new Label("Choose one restaurant");
		labelChooseRestaurant.setEnabled(false);
		labelChooseRestaurant.setBounds(49, 114, 152, 21);
		add(labelChooseRestaurant);

	}

	private class TxtKeyListener extends KeyAdapter {
		@Override
		public void keyTyped(KeyEvent e) {
			if (comboBoxOptionRestaurantCity.getSelectedItem() == "City") {
				labelInsertNameCity.setEnabled(true);
				textSearch.setEnabled(true);
			} else {
				labelInsertNameCity.setEnabled(false);
				textSearch.setEnabled(false);
			}
		}
	}

	private class BtnDeleteActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			textPreparationTime.setText("");
			textAverageMeal.setText("");
			textTimeTable.setText("");
			textCommandTime.setText("");
			textSearch.setText("");
		}
	}

	private class TxtKeyCombox extends KeyAdapter {
		@Override
		public void keyTyped(KeyEvent e) {
			if (comboBoxOptionRestaurantCity.getSelectedItem() == "restaurant") {
				try {
					tables = tableImplementCombox.readAll();
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
				for (int i = 0; i < tables.length; i++) {
					comboBoxTables.addItem(tables[i]);
				}
			} else {
				comboBoxTables.setEnabled(false);
			}
		}
	}

	private class BtnMakeStatisticsActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			double Preparation = 0;
			double Mealtime = 0;
			double Tabletime = 0;
			double Commandtime = 0;

			if (comboBoxOptionRestaurantCity.getSelectedItem() == "restaurant") {
				tableDBStatistics = (Table) comboBoxTables.getSelectedItem();

				try {
					textPreparationTime.setText(String.valueOf(Control.getAverageTablePreparationTime(tableDBStatistics,
							tableDBStatistics.getSeats(), tableDBStatistics.getID(), textSearch.getText())));

					textAverageMeal.setText(String.valueOf(Control.getAverageMealPreparationTime(tableDBStatistics,
							tableDBStatistics.getSeats(), tableDBStatistics.getID(), textSearch.getText())));
					textTimeTable.setText(String.valueOf(Control.getAverageCheckDeliveryTime(tableDBStatistics,
							tableDBStatistics.getSeats(), tableDBStatistics.getID(), textSearch.getText())));
					textCommandTime.setText(String.valueOf(Control.getAverageCommandTime(tableDBStatistics,
							tableDBStatistics.getSeats(), tableDBStatistics.getID(), textSearch.getText())));
				} catch (NumberFormatException | InstantiationException | IllegalAccessException
						| ClassNotFoundException | SQLException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			else {
				restaurantTable = readAllTablesFromCity(textSearch.getText());
				for (int i = 0; i < restaurantTable.length; i++) {
					tableDBStatistics = restaurantTable[i];
					try {
						Preparation += Control.getAverageTablePreparationTime(tableDBStatistics,
								tableDBStatistics.getSeats(), tableDBStatistics.getID(), textSearch.getText());
						Mealtime += Control.getAverageMealPreparationTime(tableDBStatistics,
								tableDBStatistics.getSeats(), tableDBStatistics.getID(), textSearch.getText());
						Tabletime += Control.getAverageCheckDeliveryTime(tableDBStatistics,
								tableDBStatistics.getSeats(), tableDBStatistics.getID(), textSearch.getText());
						Commandtime += Control.getAverageCommandTime(tableDBStatistics, tableDBStatistics.getSeats(),
								tableDBStatistics.getID(), textSearch.getText());

					} catch (NumberFormatException | InstantiationException | IllegalAccessException
							| ClassNotFoundException | SQLException | ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				textPreparationTime.setText(String.valueOf(Preparation/restaurantTable.length));
				textAverageMeal.setText(String.valueOf(Mealtime/restaurantTable.length));
				textTimeTable.setText(String.valueOf(Tabletime/restaurantTable.length));
				textCommandTime.setText(String.valueOf(Commandtime/restaurantTable.length));

			}
		}
	}
	
	public TableImplementation[] readAllTablesFromCity(String city) {
		TableImplementation[] tables = null;
		ArrayList<TableImplementation> aux = new ArrayList<TableImplementation>();
		try {
			tables = (TableImplementation[]) new TableImplementation().readAll();
			for(int i=0; i<tables.length ; i++) {
				if(tables[i].getCity().equals(city)) {
					aux.add(tables[i]);
				}
			}
			tables = new TableImplementation[aux.size()];
			for(int i=0; i<aux.size();i++) {
				tables[i] = aux.get(i);
			}
		} catch (NumberFormatException | InstantiationException | IllegalAccessException | ClassNotFoundException
				| SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tables;
	}
}
