import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.sql.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class mainmenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainmenu frame = new mainmenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public mainmenu() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 304);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("INVENTORY");
		btnNewButton_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
		btnNewButton_2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				
				inventoryinfo inv = new inventoryinfo();
				inv.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(248, 171, 121, 32);
		contentPane.add(btnNewButton_2);
		
		JLabel lblMenu = new JLabel("MENU");
		lblMenu.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		lblMenu.setBounds(189, 56, 77, 25);
		contentPane.add(lblMenu);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(135, 231, 155, 23);
		contentPane.add(btnExit);
		
		JButton btnNewEntry = new JButton("NEW ENTRY");
		btnNewEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertnew innew = new insertnew();
				innew.setVisible(true);
			}
		});
		btnNewEntry.setBounds(81, 97, 121, 32);
		contentPane.add(btnNewEntry);
		
		JButton btnNewButton = new JButton("SALES");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatesales upsales = new updatesales();
				upsales.setVisible(true);
			}
		});
		btnNewButton.setBounds(248, 97, 121, 32);
		contentPane.add(btnNewButton);
		
		JButton btnUpdateExisting = new JButton("UPDATE EXISTING");
		btnUpdateExisting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateexisting upex = new updateexisting();
				upex.setVisible(true);
			}
		});
		btnUpdateExisting.setBounds(81, 171, 121, 32);
		contentPane.add(btnUpdateExisting);
	}
}
