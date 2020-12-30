package es.uclm.esi.isoft2.a04.Presentation;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import es.uclm.esi.isoft2.a04.Domain.Beverage;
import es.uclm.esi.isoft2.a04.Domain.Food;
import es.uclm.esi.isoft2.a04.Domain.Ingredient;
import es.uclm.esi.isoft2.a04.Domain.IngredientControl;
import es.uclm.esi.isoft2.a04.Domain.InvalidTypeException;
import es.uclm.esi.isoft2.a04.Domain.NotMatchingLenghtsException;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JTextArea;

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
	private JTextField txtInputValue;
	private JButton btnCheckLimit;
	private JLabel lblTitleLimit;
	private JTextArea textAreaIngredients;
	
	private Ingredient[] ingredientList;
	private Beverage[] beverageList;

	private Ingredient ingredientdb;

	
	private float[] amount_ingredients;
	private int[] amount_drinks;
	
	private final ButtonGroup buttonGroupIngredients = new ButtonGroup();
	private final ButtonGroup buttonGroupDrinks = new ButtonGroup();
	
	private int index_ingredients;
	private int index_drinks;
	
	private IngredientControl ingredient_control_ingredients;
	private IngredientControl ingredient_control_drinks;
	private IngredientControl ingredient_control_threshold;


	

	/**
	 * Create the panel.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ParseException 
	 * @throws InvalidTypeException 
	 */
	public JPanelIngredients(Ingredient ingredientdb, Beverage beveragedb) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, InvalidTypeException, ParseException {
		
		this.ingredientdb = ingredientdb;

		this.ingredientList = ingredientdb.readAll(); //Reed all the ingredients in the database
		this.beverageList = (Beverage[]) beveragedb.readAll(); //Reed all the drinks in the database
		
		this.index_ingredients = 0;
		this.index_drinks = 0;
		
		this.amount_ingredients = new float [this.ingredientList.length];
		this.amount_drinks = new int [this.beverageList.length];

		
		/**
		for(int i = 0; i< ingredientList.length; i++) {
			System.out.println(ingredientList[i].getName());
		}
		**/
		
		/**
		for(int i = 0; i< beverageList.length; i++) {
			System.out.println(beverageList[i].getID());
		}
		**/
	
		designData();
		
		initialData();
		
		addActions();
		

	}
	/**
	 * 
	 * Method to save the new amount
	 * 
	 * @param index
	 * @param new_amount
	 */
	private void AmountIngredient(int index, float new_amount) {
		
		this.amount_ingredients[index] = new_amount;
		
		
	}
	/**
	 * 
	 * Method to save the new amount
	 * 
	 * @param index
	 * @param new_amount
	 */
	private void AmountDrinks(int index, int new_amount) {
		
		this.amount_drinks[index] = new_amount;
		
	
	}
	/**
	 * 
	 * Method to show the first ingredient or drink in the database
	 * 
	 */
	private void initialData() {
		
		//First data for the ingredients and drinks
		
		lblIngredientName.setText(ingredientList[0].getName());
		lblDrink.setText(beverageList[0].getName());
			
		
	}
	private void addActions() {
		
		btnAddAmountDish.addActionListener(new AddDishAmountActionListener());
		btnAddAmountDrinks.addActionListener(new AddDrinkAmountActionListener());
		btnCheckLimit.addActionListener(new checkIngredientsActionListener());
		
	}
	/**
	 * 
	 * Method to see the limit of ingredients
	 *
	 */
	public class checkIngredientsActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			
			ingredient_control_threshold = new IngredientControl();
			int threshold = 0;
			Ingredient[] ingredients_under_threshold = null;
			String ingredients_list = "";
			
			
			threshold = Integer.parseInt(txtInputValue.getText());
			try {
				ingredients_under_threshold = ingredient_control_threshold.getIngredientsBelowThreshold(ingredientdb, threshold);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException
					| InvalidTypeException | ParseException e) {

				e.printStackTrace();
			}
			
			if (ingredients_under_threshold == null) {
				textAreaIngredients.setText("No ingredients");
				//System.out.println("No ingredients");
			}
			
			for(int i = 0; i<ingredients_under_threshold.length; i++) {
				ingredients_list = ingredients_under_threshold[i].getName()+"\n";
				//System.out.println(ingredients_under_threshold[i]);
			}
			textAreaIngredients.setText(ingredients_list);
		}
	}
	/**
	 * 
	 * Description: method to update the ingredients amount data
	 *
	 */
	public class AddDishAmountActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			
			float amount_i= 0;
			
			ingredient_control_ingredients = new IngredientControl();
			
			if(index_ingredients == ingredientList.length) {
				
				lblIngredientName.setText("No more ingredients in the database");
				btnAddAmountDish.enable(false);
				
				try {
					
					/**
					for(int i = 0; i<amount_ingredients.length; i++) {
						
						System.out.println("Before the update"+ingredientList[i].getAmount());
						
					}
					**/
					
					ingredient_control_ingredients.updateIngredientsFromForecast(ingredientList, amount_ingredients);
					
					/**
					for(int i = 0; i<amount_ingredients.length; i++) {
						
						System.out.println("After the update"+ingredientList[i].getAmount());
						
					}
					**/
					
					
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
						| NotMatchingLenghtsException | SQLException e) {

					e.printStackTrace();
				}
				
				
			}
			else {
				
				if(rdbtnUnitsDish1.isSelected()) {
					amount_i = 5;
				}
				else if (rdbtnUnitsDish2.isSelected()) {
					amount_i = 10;
				}
				else if (rdbtnUnitsDish3.isSelected()){
					amount_i = 15;
				}
				else {
					amount_i = 0;
				}
				
				AmountIngredient(index_ingredients, amount_i);

				index_ingredients = index_ingredients + 1;

				lblIngredientName.setText(ingredientList[index_ingredients-1].getName());

			}
		}
	}
	/**
	 * 
	 * Description: method to update the drinks amount data
	 *
	 */
	public class AddDrinkAmountActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			
			int amount_d = 0;
			
			ingredient_control_drinks = new IngredientControl();
			
			if(index_drinks == beverageList.length) {
				
				lblDrink.setText("No more drinks in the database");
				btnAddAmountDrinks.enable(false);
				
				try {
					
					/**
					for(int i = 0; i<amount_drinks.length; i++) {
						
						System.out.println("Before the update"+beverageList[i].getAmount());
						
					}
					**/
					
					ingredient_control_drinks.updateBeveragesFromForecast(beverageList, amount_drinks);

				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
						| NotMatchingLenghtsException | SQLException e) {

					e.printStackTrace();
				}
				
				
			}
			else {
				
				if(rdbtnUnitsDrink1.isSelected()) {
					amount_d = 5;
				}
				else if (rdbtnUnitsDrinks2.isSelected()) {
					amount_d = 10;
				}
				else if (rdbtnUnitsDrinks3.isSelected()){
					amount_d = 15;
				}
				else {
					amount_d = 0;
				}
				
				AmountDrinks(index_drinks, amount_d);

				index_drinks = index_drinks + 1;

				lblDrink.setText(beverageList[index_drinks-1].getName());

			}
		}
	}
	
	/**
	 * 
	 * Method that initialices the JPanel data
	 * 
	 */
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
		btnAddAmountDish.setBounds(252, 148, 122, 35);
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
		
		btnAddAmountDrinks.setBounds(252, 291, 122, 35);
		add(btnAddAmountDrinks);
		
		txtInputValue = new JTextField();
		txtInputValue.setBounds(662, 91, 43, 19);
		add(txtInputValue);
		txtInputValue.setColumns(10);
		
		btnCheckLimit = new JButton("Show");
		btnCheckLimit.setBounds(747, 90, 85, 21);
		add(btnCheckLimit);
		
		lblTitleLimit = new JLabel("Which Ingredients needs an update? Input the limit");
		lblTitleLimit.setBounds(662, 38, 287, 35);
		add(lblTitleLimit);
		
		textAreaIngredients = new JTextArea();
		textAreaIngredients.setBounds(662, 175, 252, 112);
		add(textAreaIngredients);
		
	}
}



