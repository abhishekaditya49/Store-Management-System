import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;
import java.text.SimpleDateFormat;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;

public class updateexisting extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					updateexisting frame = new updateexisting();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection con = null;
	private JTextField supidtextField;
	private JTextField supdatetextField;
	private JTextField itemidtextField;
	private JTextField supqtytextField;
	private JTextField mfgdatetextField;
	private JTextField expdatetextField;
	private JTable table;
	/**
	 * Create the frame.
	 */
	public updateexisting() {
		con = sqlplusconnection.dbconnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 537, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUpdateExisting = new JLabel("UPDATE EXISTING");
		lblUpdateExisting.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		lblUpdateExisting.setBounds(22, 11, 195, 29);
		contentPane.add(lblUpdateExisting);
		
		JLabel lblSupplyId = new JLabel("Supply ID");
		lblSupplyId.setBounds(10, 75, 84, 14);
		contentPane.add(lblSupplyId);
		
		JLabel lblSupplyDate = new JLabel("Supply Date");
		lblSupplyDate.setBounds(10, 107, 84, 14);
		contentPane.add(lblSupplyDate);
		
		JLabel lblItemId = new JLabel("Item ID");
		lblItemId.setBounds(10, 132, 84, 14);
		contentPane.add(lblItemId);
		
		JLabel lblSuppliedQty = new JLabel("Supplied Qty");
		lblSuppliedQty.setBounds(10, 157, 84, 14);
		contentPane.add(lblSuppliedQty);
		
		JLabel lblMfgDate = new JLabel("Mfg. Date");
		lblMfgDate.setBounds(10, 182, 84, 14);
		contentPane.add(lblMfgDate);
		
		JLabel lblExpDate = new JLabel("Exp. Date");
		lblExpDate.setBounds(10, 207, 58, 14);
		contentPane.add(lblExpDate);
		
		supidtextField = new JTextField();
		supidtextField.setBounds(104, 72, 86, 20);
		contentPane.add(supidtextField);
		supidtextField.setColumns(10);
		
		supdatetextField = new JTextField();
		supdatetextField.setBounds(104, 104, 86, 20);
		contentPane.add(supdatetextField);
		supdatetextField.setColumns(10);
		
		itemidtextField = new JTextField();
		itemidtextField.setBounds(104, 129, 86, 20);
		contentPane.add(itemidtextField);
		itemidtextField.setColumns(10);
		
		supqtytextField = new JTextField();
		supqtytextField.setBounds(104, 154, 86, 20);
		contentPane.add(supqtytextField);
		supqtytextField.setColumns(10);
		
		mfgdatetextField = new JTextField();
		mfgdatetextField.setBounds(104, 179, 86, 20);
		contentPane.add(mfgdatetextField);
		mfgdatetextField.setColumns(10);
		
		expdatetextField = new JTextField();
		expdatetextField.setBounds(104, 204, 86, 20);
		contentPane.add(expdatetextField);
		expdatetextField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(200, 75, 311, 214);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int itemid = Integer.parseInt(itemidtextField.getText());
					if(itemid==11||itemid==12||itemid==21||itemid==22||itemid==31||itemid==32||itemid==41||itemid==42) {
						
					String query = "insert into supply (sup_id,sup_date,item_id,sup_qty,man_date,exp_date) values(?,?,?,?,?,?)";
					System.out.println("after query");
					PreparedStatement pst = con.prepareStatement(query);
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
					
					supidtextField.setText(null);
					itemidtextField.setText(null);
					supdatetextField.setText(null);
					supqtytextField.setText(null);
					mfgdatetextField.setText(null);
					expdatetextField.setText(null);
					
					
					}
					
					else {
						JOptionPane.showMessageDialog(null, "Department does not Exist.");
						supidtextField.setText(null);
						itemidtextField.setText(null);
						supdatetextField.setText(null);
						supqtytextField.setText(null);
						mfgdatetextField.setText(null);
						expdatetextField.setText(null);
					}
					}
					catch (Exception e2) {
						JOptionPane.showMessageDialog(null,e2);
						supidtextField.setText(null);
						itemidtextField.setText(null);
						supdatetextField.setText(null);
						supqtytextField.setText(null);
						mfgdatetextField.setText(null);
						expdatetextField.setText(null);
					}
			}
		});
		btnSave.setBounds(37, 244, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnLoadSupply = new JButton("Load Supply");
		btnLoadSupply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "select * from supply";
					PreparedStatement pst = con.prepareStatement(query);
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
		btnLoadSupply.setBounds(199, 44, 110, 23);
		contentPane.add(btnLoadSupply);
		
		JButton btnLoadStock = new JButton("Load Stock");
		btnLoadStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "select * from stock";
					PreparedStatement pst = con.prepareStatement(query);
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
		btnLoadStock.setBounds(401, 44, 110, 23);
		contentPane.add(btnLoadStock);
	}

}
