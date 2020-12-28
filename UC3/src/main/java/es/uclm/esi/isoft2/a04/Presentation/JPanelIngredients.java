package es.uclm.esi.isoft2.a04.Presentation;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import es.uclm.esi.isoft2.a04.Domain.Beverage;
import es.uclm.esi.isoft2.a04.Domain.Ingredient;
import es.uclm.esi.isoft2.a04.Domain.IngredientControl;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;

public class JPanelIngredients extends JPanel {
	
	private JLabel lblIngredientName;
	private JLabel lblTitle;
	private JRadioButton rdbtnUnitsDish1;
	private JRadioButton rdbtnUnitsDish2;
	private JRadioButton rdbtnUnitsDish3;
	private JLabel lblAmountInUnits;
	private JButton btnAddAmountDish;
	private JLabel lblDrink;
	private JLabel lblAmountInUnitsDrink2;
	private JRadioButton rdbtnUnitsDrink1;
	private JRadioButton rdbtnUnitsDrinks2;
	private JRadioButton rdbtnUnitsDrinks3;
	private JButton btnAddAmountDrinks;
	
	private Ingredient[] ingredientList;
	private Beverage[] beverageList;
	
	private float[] amount_ingredients;
	private float[] amount_drinks;
	private final ButtonGroup buttonGroupIngredients = new ButtonGroup();
	private final ButtonGroup buttonGroupDrinks = new ButtonGroup();
	
	private int index_ingredients = 1;
	private int index_drinks = 1;
	
	private IngredientControl ingredient_control_ingredients;
	private IngredientControl ingredient_control_drinks;
	

	/**
	 * Create the panel.
	 */
	public JPanelIngredients(Ingredient[] ingredientList, Beverage[] beverageList) {
		
		this.ingredientList = ingredientList; //Leer todos los ingredientes bases de datos
		this.beverageList = beverageList; //Leer todas las bebidas de la base de datos
		
		designData();
		
		lblIngredientName.setText(ingredientList[0]);
		lblDrink.setText(beverageList[0]);
		
		
		//addActions();
		

	}
	private void addActions() {
		
	}
	private void designData() {
		
		//Layout data
		
		setLayout(null);
		
		//Labels Data
		
		lblIngredientName = new JLabel("");
		lblIngredientName.setBounds(41, 83, 155, 35);
		add(lblIngredientName);
		
		lblTitle = new JLabel("Panel for the Use Case 3");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTitle.setBounds(41, 30, 247, 26);
		add(lblTitle);
		
		rdbtnUnitsDish1 = new JRadioButton("5 units");
		buttonGroupIngredients.add(rdbtnUnitsDish1);
		rdbtnUnitsDish1.setBounds(252, 97, 103, 21);
		add(rdbtnUnitsDish1);
		
		rdbtnUnitsDish2 = new JRadioButton("10 units");
		buttonGroupIngredients.add(rdbtnUnitsDish2);
		rdbtnUnitsDish2.setBounds(357, 97, 103, 21);
		add(rdbtnUnitsDish2);
		
		rdbtnUnitsDish3 = new JRadioButton("15 units");
		buttonGroupIngredients.add(rdbtnUnitsDish3);
		rdbtnUnitsDish3.setBounds(463, 97, 103, 21);
		add(rdbtnUnitsDish3);
		
		lblAmountInUnits = new JLabel("Amount in units of ingredients in a week");
		lblAmountInUnits.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAmountInUnits.setBounds(252, 54, 247, 26);
		add(lblAmountInUnits);
		
		btnAddAmountDish = new JButton("Add Amount");
		btnAddAmountDish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				float amount_i= 0;
				
				ingredient_control_ingredients = new IngredientControl();
				
				if(index_ingredients == this.ingredientList.length) {
					
					lblIngredientName.setText("No more ingredients in the database");
					ingredient_control_ingredients.updateIngredientsFromForecast(ingredientList, amount_ingredients);
					
					
				}
				else {
					
					if(rdbtnUnitsDish1.isSelected()) {
						amount_i = 5;
					}
					else if (rdbtnUnitsDish2.isSelected()) {
						amount_i = 10;
					}
					else {
						amount_i = 15;
					}
					
					amount_ingredients[index_ingredients] = amount_i;
					lblIngredientName.setText(ingredientList[index_ingredients]);
					
					index_ingredients = index_ingredients + 1;
					
				}
				
			}
		});
		btnAddAmountDish.setBounds(252, 148, 95, 35);
		add(btnAddAmountDish);
		
		lblDrink = new JLabel("");
		lblDrink.setBounds(41, 204, 155, 35);
		add(lblDrink);
		
		lblAmountInUnitsDrink2 = new JLabel("Amount in units of drinks in a week");
		lblAmountInUnitsDrink2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAmountInUnitsDrink2.setBounds(252, 204, 247, 26);
		add(lblAmountInUnitsDrink2);
		
		rdbtnUnitsDrink1 = new JRadioButton("5 units");
		buttonGroupDrinks.add(rdbtnUnitsDrink1);
		rdbtnUnitsDrink1.setBounds(252, 244, 103, 21);
		add(rdbtnUnitsDrink1);
		
		rdbtnUnitsDrinks2 = new JRadioButton("10 units");
		buttonGroupDrinks.add(rdbtnUnitsDrinks2);
		rdbtnUnitsDrinks2.setBounds(357, 244, 103, 21);
		add(rdbtnUnitsDrinks2);
		
		rdbtnUnitsDrinks3 = new JRadioButton("15 units");
		buttonGroupDrinks.add(rdbtnUnitsDrinks3);
		rdbtnUnitsDrinks3.setBounds(463, 244, 103, 21);
		add(rdbtnUnitsDrinks3);
		
		btnAddAmountDrinks = new JButton("Add Amount");
		btnAddAmountDrinks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				float amount_d = 0;
				
				ingredient_control_drinks = new IngredientControl();
				
				if(index_ingredients == this.beverageList.length) {
					
					lblIngredientName.setText("No more drinks in the database");
					ingredient_control_drinks.updateIngredientsFromForecast(this.beverageList, amount_ingredients);
					
					
				}
				else {
					
					if(rdbtnUnitsDrink1.isSelected()) {
						amount_d = 5;
					}
					else if (rdbtnUnitsDrinks2.isSelected()) {
						amount_d = 10;
					}
					else {
						amount_d = 15;
					}
					
					amount_drinks[index_drinks] = amount_d;
					lblIngredientName.setText(this.beverageList[index_drinks]);
					
					index_drinks = index_drinks + 1;
					
				}
					
			}
		});
		btnAddAmountDrinks.setBounds(252, 291, 103, 35);
		add(btnAddAmountDrinks);
		
	}
}



