import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;


import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;


public class inventoryinfo extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inventoryinfo frame = new inventoryinfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection connection = null;
	private JTable table;
	
	//public static void resulttotable(ResultSet rs , JTable tables){
		//try {
		
	//	DefaultTableModel tablemodel = new DefaultTableModel();
		//ResultSetMetaData metaData= rs.getMetaData();
	//	int columncount;
		//columncount = metaData.getColumnCount();
		//for(int columnindex=1;columnindex <= columncount ; columnindex++) {
			
			//tablemodel.addColumn(metaData.getColumnLabel(columnindex));
		//}
	//	Object[] row= new Object[columncount];
		
	//	while(rs.next()) {
			
		//	for(int i =0; i < columncount++; i++) {
			//	row[i] = rs.getObject(i+1);
				//System.out.println(rs.getString(1));
	//		}
		//	tablemodel.addRow(row);
		
		//tables.setModel(tablemodel);
	//	tables.revalidate();
	//}
		//}
	
	//catch (Exception e) {
		//JOptionPane.showMessageDialog(null,e);
//	}
//	}
	
	

	/**
	 * Create the frame.
	 */
	public inventoryinfo() {
		connection = sqlplusconnection.dbconnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 720, 470);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		
		JButton btnLoadItems = new JButton("Load Items");
		btnLoadItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "select * from items";
					Statement st = connection.createStatement();
					ResultSet rs = st.executeQuery(query);
					table.setModel(DbUtils.resultSetToTableModel(rs));
					st.close();
					rs.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				}
		});
		btnLoadItems.setBounds(29, 135, 118, 23);
		contentPane.add(btnLoadItems);
		
		JButton btnLoadStock = new JButton("Load Stock");
		btnLoadStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "select * from stock";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnLoadStock.setBounds(29, 193, 118, 23);
		contentPane.add(btnLoadStock);
		
		JButton btnLoadSupply = new JButton("Load Supply");
		btnLoadSupply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "select * from supply";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs1 = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs1));
					pst.close();
					rs1.close();
				}
				catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnLoadSupply.setBounds(29, 257, 118, 23);
		contentPane.add(btnLoadSupply);
		
		JButton btnLoadSales = new JButton("Load Sales");
		btnLoadSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "select * from sales";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs2 = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs2));
					pst.close();
					rs2.close();
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnLoadSales.setBounds(29, 314, 118, 23);
		contentPane.add(btnLoadSales);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(171, 81, 511, 289);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblInventory = new JLabel("INVENTORY ");
		lblInventory.setHorizontalAlignment(SwingConstants.LEFT);
		lblInventory.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		lblInventory.setBounds(267, 30, 694, 40);
		contentPane.add(lblInventory);

		
		
	}
}
