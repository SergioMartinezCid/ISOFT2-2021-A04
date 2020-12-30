package es.uclm.esi.isoft2.a04.Presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import es.uclm.esi.isoft2.a04.Domain.Table;
import es.uclm.esi.isoft2.a04.Domain.User;
import es.uclm.esi.isoft2.a04.Persistance.UserDAO;
import es.uclm.esi.isoft2.a04.Presentation.MainApplicationBartender;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.event.ActionEvent;

public class LoginWindow {

	private JFrame frameLogin;
	private JLabel lblUserName;
	private JLabel lblPassword;
	private JPasswordField txtPassword;
	private JTextField txtUser;
	private JButton btnLogin;

	
	private UserDAO user;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
					window.frameLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginWindow() {
		
		initialize();
		
		addFunctions();
		
	}
	private void addFunctions() {
		
		btnLogin.addActionListener(new LoginApplicationActionListener());
		
	}
	private class LoginApplicationActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			user = new UserDAO();
			User[] user_list = null;
			try {
				user_list = user.readAllUsers();
			} catch (NumberFormatException | InstantiationException | IllegalAccessException | ClassNotFoundException
					| SQLException | ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			int user_system_data = -1;
			int index = 0;

		
			if (txtUser.getText() != null) {
				
				for(int i = 0; i<user_list.length; i++) {
				
					if (txtUser.getText().toString().equals(user_list[i].getUsername().toString())) {
						user_system_data = 0;
						//System.out.println(index);
						index = i;
					}
				}
				
				if(user_system_data == 0) {
					
					if (txtPassword.getText() != null) {
						
						if (txtPassword.getText().toString().equals(user_list[index].getPassword().toString())) {
							
							JOptionPane.showMessageDialog(frameLogin, "Login successfull", "The user data is correct", 1);
						
							switch(user_list[index].getType().toString()) {
							
								case "room_head":
									System.out.println("room_head");
									MainApplicationRoomHead window_room_head = new MainApplicationRoomHead();
									window_room_head.getRoomHeadFrame().setVisible(true);
									break;
								case "waiter":
									System.out.println("waiter");
									MainApplicationWaiter window_waiter = new MainApplicationWaiter();
									window_waiter.getWaiterFrame().setVisible(true);
									break;
								case "cook":
									System.out.println("cook");
									MainApplicationCook window_cook = new MainApplicationCook();
									window_cook.getCookFrame().setVisible(true);
									break;
								case "owner":
									System.out.println("owner");
									MainApplicationOwner window_owner = new MainApplicationOwner();
									 window_owner.getOwnerFrame().setVisible(true);
									break;
								case "bartender":
									System.out.println("bartender");
									MainApplicationBartender window_bartender = new MainApplicationBartender();
									window_bartender.getBartenderFrame().setVisible(true);
									break;
							
							}
							frameLogin.dispose();
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(frameLogin, "Login fail", "Error in the password", 0);
				}	
			}
			else {
				JOptionPane.showMessageDialog(frameLogin, "Login fail", "Error in the login data", 0);
			}
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frameLogin = new JFrame();
		frameLogin.setBounds(100, 100, 586, 246);
		frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameLogin.getContentPane().setLayout(null);
		
		lblUserName = new JLabel("User:");
		lblUserName.setBounds(145, 60, 45, 13);
		frameLogin.getContentPane().add(lblUserName);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(125, 98, 111, 13);
		frameLogin.getContentPane().add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(205, 93, 176, 23);
		frameLogin.getContentPane().add(txtPassword);
		
		txtUser = new JTextField();
		txtUser.setBounds(205, 55, 176, 23);
		frameLogin.getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(296, 151, 85, 21);
		frameLogin.getContentPane().add(btnLogin);
		
	}
}
