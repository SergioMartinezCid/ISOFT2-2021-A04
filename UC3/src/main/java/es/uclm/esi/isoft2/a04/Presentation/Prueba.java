package es.uclm.esi.isoft2.a04.Presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.uclm.esi.isoft2.a04.Domain.BeverageImplementation;
import es.uclm.esi.isoft2.a04.Domain.Food;
import es.uclm.esi.isoft2.a04.Domain.FoodImplementation;
import es.uclm.esi.isoft2.a04.Domain.Ingredient;
import es.uclm.esi.isoft2.a04.Domain.IngredientImplementation;
import es.uclm.esi.isoft2.a04.Domain.InvalidTypeException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;

public class Prueba extends JFrame {

	private JPanel contentPane;
	private static JFrame frame;
	private JPanel pnlContenido;
	private JPanel pnlBotones;
	private JButton btnNewButton;
	private JPanel pnlIngredients;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Prueba();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 * @throws InvalidTypeException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public Prueba() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, InvalidTypeException, ParseException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		pnlContenido = new JPanel();
		contentPane.add(pnlContenido, BorderLayout.CENTER);
		pnlContenido.setLayout(new CardLayout(0, 0));
		
		IngredientImplementation ingredient = new IngredientImplementation();
		BeverageImplementation beverage = new BeverageImplementation();
		
		pnlIngredients = new JPanelIngredients(ingredient, beverage);
		pnlContenido.add(pnlIngredients, "Probar");
		
		pnlBotones = new JPanel();
		contentPane.add(pnlBotones, BorderLayout.NORTH);
		
		btnNewButton = new JButton("Probar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
			}
		});
		pnlBotones.add(btnNewButton);
	}
	

}
