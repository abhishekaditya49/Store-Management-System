import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.sql.Connection;
import java.text.SimpleDateFormat;

import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.sql.*;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;


public class updatesales extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					updatesales frame = new updatesales();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection con = null; 
	private JTextField saleidtextField;
	private JTextField saledatetextField;
	private JTextField itemidtextField;
	private JTextField saleqtytextField;
	private JTable table;
	/**
	 * Create the frame.
	 */
	public updatesales() {
		con=sqlplusconnection.dbconnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 605, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sale ID");
		lblNewLabel.setBounds(10, 75, 58, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Sale Date");
		lblNewLabel_1.setBounds(10, 100, 58, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Item ID");
		lblNewLabel_2.setBounds(10, 130, 58, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Sale Qty.");
		lblNewLabel_3.setBounds(10, 155, 58, 14);
		contentPane.add(lblNewLabel_3);
		
		saleidtextField = new JTextField();
		saleidtextField.setBounds(78, 72, 86, 20);
		contentPane.add(saleidtextField);
		saleidtextField.setColumns(10);
		
		saledatetextField = new JTextField();
		saledatetextField.setBounds(78, 97, 86, 20);
		contentPane.add(saledatetextField);
		saledatetextField.setColumns(10);
		
		itemidtextField = new JTextField();
		itemidtextField.setBounds(78, 127, 86, 20);
		contentPane.add(itemidtextField);
		itemidtextField.setColumns(10);
		
		saleqtytextField = new JTextField();
		saleqtytextField.setBounds(78, 152, 86, 20);
		contentPane.add(saleqtytextField);
		saleqtytextField.setColumns(10);
		
		JLabel lblSales = new JLabel("SALES");
		lblSales.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		lblSales.setBounds(29, 21, 135, 31);
		contentPane.add(lblSales);
		
		JButton btnLoadSalesList = new JButton("Load Sales List");
		btnLoadSalesList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String query = "select * from sales";
				PreparedStatement pst = con.prepareStatement(query);
				ResultSet rs = pst.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				pst.close();
				rs.close();
				}
				catch (Exception e3) {
					JOptionPane.showMessageDialog(null, e3);
				}
			}
				
		});
		btnLoadSalesList.setBounds(236, 30, 135, 23);
		contentPane.add(btnLoadSalesList);
		
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
					catch (Exception e3) {
						JOptionPane.showMessageDialog(null, e3);
					}
			}
		});
		btnLoadStock.setBounds(447, 30, 135, 23);
		contentPane.add(btnLoadStock);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(235, 74, 347, 200);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "insert into sales (sale_id,sale_date,item_id,sale_qty) values(?,?,?,?)";
					
							
					PreparedStatement pst = con.prepareStatement(query);
					
					pst.setInt(1,Integer.parseInt(saleidtextField.getText()));
					
					SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date udate=sdf.parse(saledatetextField.getText());
					long l = udate.getTime();
					java.sql.Date sdate = new java.sql.Date(l);
					pst.setDate(2,sdate);
					
					pst.setInt(3, Integer.parseInt(itemidtextField.getText()));
					
					pst.setInt(4, Integer.parseInt(saleqtytextField.getText()));
					
					pst.executeQuery();
					JOptionPane.showMessageDialog(null,"Data Saved!");
					pst.close();

					saleidtextField.setText(null);
					saledatetextField.setText(null);
					itemidtextField.setText(null);
			saleqtytextField.setText(null);
					
					//rs.close();
					}
					catch (Exception e2) {
						JOptionPane.showMessageDialog(null,"Invalid data!");
					 saleidtextField.setText(null);
						saledatetextField.setText(null);
						itemidtextField.setText(null);
						saleqtytextField.setText(null);
					}
			}
		});
		btnSave.setBounds(75, 231, 89, 23);
		contentPane.add(btnSave);
	}

}
