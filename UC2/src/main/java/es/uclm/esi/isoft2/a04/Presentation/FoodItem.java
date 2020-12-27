package es.uclm.esi.isoft2.a04.Presentation;

import javax.swing.JPanel;

import es.uclm.esi.isoft2.a04.Domain.BeverageImplementation;
import es.uclm.esi.isoft2.a04.Domain.Dish;
import es.uclm.esi.isoft2.a04.Domain.Food;
import es.uclm.esi.isoft2.a04.Domain.FoodImplementation;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;

/**
 * 
 * @version 0.0.1
 */
public class FoodItem extends JPanel {

	private FoodImplementation food;
	private JSpinner spQuantity;
	private JLabel lblCost;
	private JComboBox<String> cbxName;
	private JLabel lblType;
	private JLabel lblStatus;
	
	public FoodItem() {
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setVgap(15);
		flowLayout.setHgap(15);
		
		lblStatus = new JLabel("Status:");
		add(lblStatus);
		
		lblType = new JLabel("Type:");
		add(lblType);
		
		cbxName = new JComboBox<>();
		cbxName.setModel(new DefaultComboBoxModel());
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
		refresh();
	}
	
	public FoodItem(int type) {
		food = type == FoodImplementation.DRINK ? new BeverageImplementation() : new Dish();
		refresh();
	}
	
	public FoodItem(Food food){
		this.food = (FoodImplementation)food;
		refresh();
	}
	
	private void refresh() {
		if (food == null)
			return;
		// TODO: fix missing status field
		lblStatus.setText("Status: ");
		String type = "";
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
	}
}
