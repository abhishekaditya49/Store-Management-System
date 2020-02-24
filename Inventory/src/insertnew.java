import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.text.SimpleDateFormat;

import javax.swing.*;

public class insertnew extends JFrame {

	private JPanel contentPane;
	private JTextField supidtextField;
	private JTextField supdatetextField;
	private JTextField itemidtextField;
	private JTextField supqtytextField;
	private JTextField mfgdatetextField;
	private JTextField expdatetextField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					insertnew frame = new insertnew();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection = null;
	private JTextField deptidtextField;
	private JTextField itemnametextField;
	private JTextField cptextField;
	private JTextField sptextField;
	/**
	 * Create the frame.
	 */
	public insertnew() {
		connection = sqlplusconnection.dbconnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 646, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Load Stock");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String query = "select * from stock";
				PreparedStatement pst = connection.prepareStatement(query);
				ResultSet rs = pst.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				pst.close();
				rs.close();
				
				}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null,e1);
				}
			}
		});
		btnNewButton.setBounds(423, 40, 197, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblSupplyId = new JLabel("Supply ID");
		lblSupplyId.setBounds(10, 52, 80, 14);
		contentPane.add(lblSupplyId);
		
		JLabel lblSupplyDate = new JLabel("Supply Date");
		lblSupplyDate.setBounds(10, 152, 80, 14);
		contentPane.add(lblSupplyDate);
		
		JLabel lblItemId = new JLabel("Item ID");
		lblItemId.setBounds(10, 77, 80, 14);
		contentPane.add(lblItemId);
		
		JLabel lblSupplyQty = new JLabel("Supply Qty");
		lblSupplyQty.setBounds(10, 177, 80, 14);
		contentPane.add(lblSupplyQty);
		
		JLabel lblMfgDate = new JLabel("Mfg. Date");
		lblMfgDate.setBounds(10, 202, 80, 14);
		contentPane.add(lblMfgDate);
		
		JLabel lblExpDate = new JLabel("Exp. Date");
		lblExpDate.setBounds(10, 227, 80, 14);
		contentPane.add(lblExpDate);
		
		supidtextField = new JTextField();
		supidtextField.setBounds(100, 49, 86, 20);
		contentPane.add(supidtextField);
		supidtextField.setColumns(10);
		
		supdatetextField = new JTextField();
		supdatetextField.setBounds(100, 149, 86, 20);
		contentPane.add(supdatetextField);
		supdatetextField.setColumns(10);
		
		itemidtextField = new JTextField();
		itemidtextField.setBounds(100, 74, 86, 20);
		contentPane.add(itemidtextField);
		itemidtextField.setColumns(10);
		
		supqtytextField = new JTextField();
		supqtytextField.setBounds(100, 174, 86, 20);
		contentPane.add(supqtytextField);
		supqtytextField.setColumns(10);
		
		mfgdatetextField = new JTextField();
		mfgdatetextField.setBounds(100, 199, 86, 20);
		contentPane.add(mfgdatetextField);
		mfgdatetextField.setColumns(10);
		
		expdatetextField = new JTextField();
		expdatetextField.setBounds(100, 224, 86, 20);
		contentPane.add(expdatetextField);
		expdatetextField.setColumns(10);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				//inserting into parent item table
				String query1 = "insert into items (dept_id,item_id,item_name,c_price,s_price) values(?,?,?,?,?)";
				PreparedStatement pst2 = connection.prepareStatement(query1);
				pst2.setInt(1,Integer.parseInt(deptidtextField.getText()));
				pst2.setInt(2,Integer.parseInt(itemidtextField.getText()));
				pst2.setString(3, itemnametextField.getText());
				pst2.setInt(4,Integer.parseInt(cptextField.getText()));
				pst2.setInt(5,Integer.parseInt(sptextField.getText()));
				pst2.executeUpdate();
				
				//inserting into child supply table
				
				String query2 = "insert into supply (sup_id,sup_date,item_id,sup_qty,man_date,exp_date) values(?,?,?,?,?,?)";
				System.out.println("after query");
				PreparedStatement pst = connection.prepareStatement(query2);
				System.out.println("after query");
				
				pst.setInt(1,Integer.parseInt(supidtextField.getText()));
				System.out.println("after supid");
				
				SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date udate=sdf.parse(supdatetextField.getText());
				long l = udate.getTime();
				java.sql.Date sdate = new java.sql.Date(l);
				pst.setDate(2,sdate);
				
				System.out.println("after sdate");
				
				
				pst.setInt(3,Integer.parseInt(itemidtextField.getText()));
				pst.setInt(4,Integer.parseInt(supqtytextField.getText()));
				
				SimpleDateFormat sdf1= new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date udate1=sdf1.parse(mfgdatetextField.getText());
				long l1 = udate1.getTime();
				java.sql.Date sdate1 = new java.sql.Date(l1);
				pst.setDate(5,sdate1);
				
				SimpleDateFormat sdf2= new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date udate2=sdf2.parse(expdatetextField.getText());
				long l2 = udate2.getTime();
				java.sql.Date sdate2 = new java.sql.Date(l2);
				pst.setDate(6,sdate2);
				
				
				System.out.println("before resultset");
				
				pst.executeUpdate();
				
				
				JOptionPane.showMessageDialog(null,"Data Saved!");
				pst.close();
				
				String query3 = "insert into stock (item_id,qty) values(?,?)";
				PreparedStatement pst1 = connection.prepareStatement(query3);
				pst1.setInt(1,Integer.parseInt(itemidtextField.getText()));
				pst1.setInt(2,Integer.parseInt(supqtytextField.getText()));
				pst1.executeUpdate();
				
				
			supidtextField.setText(null);
				itemidtextField.setText(null);
				deptidtextField.setText(null);
				itemnametextField.setText(null);
				supdatetextField.setText(null);
				supqtytextField.setText(null);
				mfgdatetextField.setText(null);
				expdatetextField.setText(null);
				cptextField.setText(null);
				sptextField.setText(null);
				
				}
				catch (Exception e2) {
					JOptionPane.showMessageDialog(null,e2);
					supidtextField.setText(null);
					itemidtextField.setText(null);
					deptidtextField.setText(null);
					itemnametextField.setText(null);
					supdatetextField.setText(null);
					supqtytextField.setText(null);
					mfgdatetextField.setText(null);
					expdatetextField.setText(null);
					cptextField.setText(null);
					sptextField.setText(null);
				}
			}
		});
		btnSave.setBounds(50, 302, 89, 23);
		contentPane.add(btnSave);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(196, 91, 424, 257);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblUpdateSupply = new JLabel("NEW ENTRY");
		lblUpdateSupply.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		lblUpdateSupply.setBounds(8, 23, 178, 18);
		contentPane.add(lblUpdateSupply);
		
		JButton btnNewButton_1 = new JButton("Load Supply");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "select * from supply";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
					
					}
					catch (Exception e1) {
						JOptionPane.showMessageDialog(null,e1);
					}
			}
		});
		btnNewButton_1.setBounds(196, 40, 186, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblDeptId = new JLabel("Dept ID");
		lblDeptId.setBounds(10, 102, 46, 14);
		contentPane.add(lblDeptId);
		
		deptidtextField = new JTextField();
		deptidtextField.setBounds(100, 99, 86, 20);
		contentPane.add(deptidtextField);
		deptidtextField.setColumns(10);
		
		JLabel lblItemName = new JLabel("Item Name");
		lblItemName.setBounds(10, 127, 64, 14);
		contentPane.add(lblItemName);
		
		itemnametextField = new JTextField();
		itemnametextField.setBounds(100, 124, 86, 20);
		contentPane.add(itemnametextField);
		itemnametextField.setColumns(10);
		
		JLabel lblCostPrice = new JLabel("Cost Price");
		lblCostPrice.setBounds(10, 252, 64, 14);
		contentPane.add(lblCostPrice);
		
		cptextField = new JTextField();
		cptextField.setBounds(100, 249, 86, 20);
		contentPane.add(cptextField);
		cptextField.setColumns(10);
		
		JLabel lblSellingPrice = new JLabel("Selling Price");
		lblSellingPrice.setBounds(10, 277, 64, 14);
		contentPane.add(lblSellingPrice);
		
		sptextField = new JTextField();
		sptextField.setBounds(100, 274, 86, 20);
		contentPane.add(sptextField);
		sptextField.setColumns(10);
	}
}
