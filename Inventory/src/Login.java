import java.awt.EventQueue;

import java.sql.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Login {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	Connection con = null;
	private JTextField usernametext;
	private JPasswordField passwordtext;
	public Login() {
		initialize();
		con = sqlplusconnection.dbconnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 447, 277);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(199, 71, 84, 17);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(199, 111, 84, 17);
		frame.getContentPane().add(lblNewLabel_1);
		
		usernametext = new JTextField();
		usernametext.setBounds(299, 69, 89, 20);
		frame.getContentPane().add(usernametext);
		usernametext.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernametext.getText();
				String password = passwordtext.getText();
				
				if (username.contains("a") && password.contains("1")) {
					
					
					usernametext.setText(null);
					passwordtext.setText(null);
					JOptionPane.showMessageDialog(null,"Username and password is correct.");
					frame.dispose();
					mainmenu mm = new mainmenu();
					mm.setVisible(true);
					try {
						con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				else {
					JOptionPane.showMessageDialog(null,"ERROR! System doesn't recognize your username or password.");
					usernametext.setText(null);
					passwordtext.setText(null);
				}
			}
		});
		btnNewButton.setBounds(194, 159, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		passwordtext = new JPasswordField();
		passwordtext.setBounds(299, 109, 89, 19);
		frame.getContentPane().add(passwordtext);
		
		JLabel lblLoginPage = new JLabel("LOGIN");
		lblLoginPage.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		lblLoginPage.setBounds(169, 24, 190, 17);
		frame.getContentPane().add(lblLoginPage);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(79, 52, 268, 2);
		frame.getContentPane().add(separator);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setBounds(299, 159, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\MAHE\\Downloads\\Apps-preferences-system-login-icon.png"));
		label.setBounds(59, 69, 96, 102);
		frame.getContentPane().add(label);
		
	}
}
