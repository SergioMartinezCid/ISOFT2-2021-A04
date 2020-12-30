package es.uclm.esi.isoft2.a04.Presentation;

import javax.swing.JPanel;

import es.uclm.esi.isoft2.a04.Domain.BeverageImplementation;
import es.uclm.esi.isoft2.a04.Domain.Dish;
import es.uclm.esi.isoft2.a04.Domain.Food;
import es.uclm.esi.isoft2.a04.Domain.FoodImplementation;
import es.uclm.esi.isoft2.a04.Domain.InvalidTypeException;
import es.uclm.esi.isoft2.a04.Persistance.BeverageDAO;
import es.uclm.esi.isoft2.a04.Persistance.DishDAO;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @version 0.2.1
 */
public class FoodItem extends JPanel {

	private FoodImplementation food;
	private JSpinner spQuantity;
	private JLabel lblCost;
	private JComboBox<String> cbxName;
	private JLabel lblType;
	private JLabel lblStatus;
	private JButton btnDelete;
	
	public FoodItem() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, InvalidTypeException, ParseException {
		this(false);
	}
	
	public FoodItem(Food food) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, InvalidTypeException, ParseException {
		this(true);
		this.food = (FoodImplementation) food;
	}
	
	public FoodItem(boolean displayOnly) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, InvalidTypeException, ParseException {
		this(displayOnly, Food.FIRST_COURSE);
	}
	
	public FoodItem(boolean displayOnly, int type) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, InvalidTypeException, ParseException {
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setVgap(15);
		flowLayout.setHgap(15);
		
		lblStatus = new JLabel("Status:");
		add(lblStatus);
		
		lblType = new JLabel("Type:");
		add(lblType);
		
		cbxName = new JComboBox<>();
		add(cbxName);
		
		lblCost = new JLabel("Cost:");
		add(lblCost);
		
		JPanel panel = new JPanel();
		add(panel);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		panel.add(lblQuantity);
		
		spQuantity = new JSpinner();
		spQuantity.setModel(new SpinnerNumberModel(1, 1, null, 1));
		panel.add(spQuantity);
		
		btnDelete = new JButton(displayOnly ? "Ready" : "Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getParent().remove(FoodItem.this);
				if (displayOnly)
					JPanelOrder.getOrderControl().notifyMe();
				else
					JPanelFood.getOrderControl().notifyMe();
			}
		});
		add(btnDelete);
		
		setDisplayOnly(displayOnly);

		if (type == FoodImplementation.DRINK) {
			this.food = new BeverageImplementation();
			setListOfFood((new BeverageDAO()).readAllBeverages());
		}
		else {
			this.food = new Dish();
			setListOfFood((new DishDAO()).readAllDishes());
		}

		refresh();
	}
	
	public void setFood(FoodImplementation food) {
		this.food = food;
		refresh();
	}

	public FoodImplementation getFood() {
		return food;
	}
	
	public void setDisplayOnly(boolean displayOnly) {
		cbxName.setEnabled(!displayOnly);
		spQuantity.setEnabled(!displayOnly);
		btnDelete.setEnabled(!displayOnly);
	}
	
	public void setListOfFood(FoodImplementation[] listOfFood) {
		String[] listOfNames = Arrays.stream(listOfFood).map((x) -> x.getName()).toArray(String[]::new);
		cbxName.setModel(new DefaultComboBoxModel<String>(listOfNames));
	}
	
	public void refresh() {
		if (food == null)
			return;

		String type = "";
		String status = "";

		switch (food.getStatus()) {
		case FoodImplementation.READY:
			status = "READY";
			break;
		case FoodImplementation.BEING_PREPARED:
			status = "IN PREPARATION";
			break;
		case FoodImplementation.DELIVERED:
			status = "DELIVERED";
			break;
		}
		lblStatus.setText("Status: " + status);

		switch (food.getType()) {
		case Food.DESSERT:
			type = "Dessert";
			break;
		case Food.DRINK:
			type = "Drink";
			break;
		case Food.FIRST_COURSE:
			type = "First course";
			break;
		case Food.SECOND_COURSE:
			type = "Second course";
			break;
		case Food.STARTER:
			type = "Starter";
			break;
		}
		lblType.setText("Type: " + type);

		lblCost.setText("Cost: " + food.getCost());
		spQuantity.setValue(food.getQuantity());
		
		if (cbxName.getModel().getSize() > 0)
			cbxName.setSelectedItem(food.getName());
	}
}
