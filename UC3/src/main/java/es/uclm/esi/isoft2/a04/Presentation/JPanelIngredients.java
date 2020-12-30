package es.uclm.esi.isoft2.a04.Presentation;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import es.uclm.esi.isoft2.a04.Domain.Ingredient;
import es.uclm.esi.isoft2.a04.Domain.IngredientControl;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class JPanelIngredients extends JPanel {
	
	private JTextField txtIngredientName;
	private JTextField txtIngredientID;
	private JTextField txtIngredientAmount;
	private JLabel lblState;
	private JTextPane txtResult;
	private JLabel lblIngredientID;
	private JLabel lblIngredientAmount;
	private JLabel lblIngredientName;
	private JLabel lblResult;
	private JButton btnShowResults;
	private JLabel lblShowIngredients;
	private JLabel lblTitle;
	private JButton btnInsertData;

	/**
	 * Create the panel.
	 */
	public JPanelIngredients() {
		
		designData();
		
		addActions();
		

	}
	private void addActions() {
		
		btnInsertData.addActionListener(new BtnInsertIngredientsActionListener());
		btnShowResults.addActionListener(new BtnShowIngredientsActionListener());
		
	}
	private class BtnShowIngredientsActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			/*
			IngredientControl ingredient_control = new IngredientControl();
			
			try {
				Ingredient[] ingredient_list = ingredient_control.getAllIngredients();
				
				for (int i = 0; i<ingredient_list.length; i++) {
					txtResult.setText("Ingredient"+i);
					txtResult.setText("Ingredient id"+ingredient_list[i].getID());
					txtResult.setText("Ingredient name"+ingredient_list[i].getName());
					txtResult.setText("Ingredient amount"+ingredient_list[i].getAmount());
				}
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			*/
			
			
		}
	}
	private class BtnInsertIngredientsActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			/*
			String name = null;
			float amount = 0;
			int id = 0;
			
			if (txtIngredientName.getText() == null || txtIngredientID.getText() == null || txtIngredientAmount.getText() == null) {
				
				lblState.setText("Input the data that is missing");
				
			}
			else {
				
				name = txtIngredientName.getText();
				amount = Float.parseFloat(txtIngredientAmount.getText());
				id = Integer.parseInt(txtIngredientID.getText());
				
				IngredientControl ingredient_control = new IngredientControl();
				
				Ingredient ingredient = new Ingredient(id, name, amount);
				
				//Check if the ingredient is already in the database
				
				try {
					
					ingredient = ingredient_control.getIngredient(ingredient.getID());
					
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				
				
				ingredient_control.updateIngredientAmount(ingredient, amount);
				
			}*/
	
		}
	}
	private void designData() {
		
		//Layout data
		
		setLayout(null);
		
		//Txt Data
		
		txtIngredientName = new JTextField();
		txtIngredientName.setBounds(122, 172, 166, 26);
		add(txtIngredientName);
		txtIngredientName.setColumns(10);
		
		txtIngredientID = new JTextField();
		txtIngredientID.setBounds(122, 80, 166, 26);
		add(txtIngredientID);
		txtIngredientID.setColumns(10);
		
		txtIngredientAmount = new JTextField();
		txtIngredientAmount.setBounds(123, 126, 165, 26);
		add(txtIngredientAmount);
		txtIngredientAmount.setColumns(10);
		
		txtResult = new JTextPane();
		txtResult.setBounds(371, 80, 240, 225);
		add(txtResult);
		
		//Labels Data
		
		lblState = new JLabel("");
		lblState.setBounds(41, 310, 282, 49);
		add(lblState);
				
		lblIngredientID = new JLabel("ID:");
		lblIngredientID.setBounds(67, 86, 45, 13);
		add(lblIngredientID);
		
		lblIngredientAmount = new JLabel("Amount:");
		lblIngredientAmount.setBounds(54, 132, 45, 13);
		add(lblIngredientAmount);
		
		lblIngredientName = new JLabel("Name:");
		lblIngredientName.setBounds(67, 178, 45, 13);
		add(lblIngredientName);
		
		lblResult = new JLabel("Result:");
		lblResult.setBounds(463, 46, 45, 13);
		add(lblResult);
		
		lblShowIngredients = new JLabel("Ingredients in the warehore at this moment:");
		lblShowIngredients.setBounds(33, 278, 243, 27);
		add(lblShowIngredients);
		
		lblTitle = new JLabel("Panel for the Use Case 3");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTitle.setBounds(122, 27, 247, 26);
		add(lblTitle);
		
		//Botons data
		
		btnShowResults = new JButton("Results");
		btnShowResults.setBounds(238, 281, 85, 21);
		add(btnShowResults);
		
		btnInsertData = new JButton("Add Ingredient");
		btnInsertData.setBounds(177, 222, 111, 21);
		add(btnInsertData);
		
	}
}



