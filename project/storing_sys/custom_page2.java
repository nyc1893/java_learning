package javaGui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class custom_page extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldSearch1;
	private JTextField textFieldCity;
	private JTextField textFieldState;
	private JTextField textFieldPho;
	private JTextField textFieldName;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private JTextField textFieldQty;
	private JTextField textFieldSearch2;
	private JTextField textFieldAddr;
	private JTextField textFieldTax;
	private JTextField textFieldZip;
	String tableC = "customlist";
	char temp[] = new char [100];
	char temp2[][] = new char [10][100];
	private JTextField textFieldCh_name;
	private JTextField textFieldEn_name;
	private JTextField textFieldPrice;
	private JTextField textFieldTaxable;

	/**
	 * Launch the application.
	 */
	Connection connection = null;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					custom_page frame = new custom_page();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void refreshTable3() {
		try {
			
			String query= "select tb1.inv_id as ID, tb2.chcate as class, "
					+"tb1.ch_name as ch_name,tb1.price as price "
					+"from InventoryList as tb1 "
					+ "left join catelist as tb2 on tb1.CATEGORY = tb2.cateid";	
			
				PreparedStatement pst= connection.prepareStatement(query);

				ResultSet rs = pst.executeQuery();
	
			table_2.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}	
	}

	public void refreshTable2() {
		try {
			
			String sql2= "select tb2.id as id, tb1.ch_name as ch_name,tb2.qty as qty "
					+ "from temporder as tb2 "
					+ "left join InventoryList as tb1 on tb1.inv_id = tb2.id";
			
				PreparedStatement pst= connection.prepareStatement(sql2);

				ResultSet rs = pst.executeQuery();
	
			table_3.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}	
	}
	
	
	
	public void refreshTable() {
		try {
			String query= "select id,name,city,tax from "+tableC;
			PreparedStatement pst= connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			//get all information to display
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
	}
	/**
	 * Create the frame.
	 */
	public custom_page() {
		connection = sqlcon.db();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 825, 603);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(36, 10, 737, 544);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("User", null, panel, null);
		panel.setLayout(null);
		
		textFieldSearch1 = new JTextField();
		textFieldSearch1.setBounds(44, 49, 228, 26);
		panel.add(textFieldSearch1);
		textFieldSearch1.setColumns(10);
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					String string1 = (textFieldSearch1.getText().trim().isEmpty())?("'%'")
							:( "'%"+textFieldSearch1.getText() + "%'");
				
					String query = "select id,name,city,tax  from "+ tableC
							+ " WHERE name LIKE "
							+ string1;
					System.out.println(query);
					PreparedStatement pst= connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					//get all information to display
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
								
				
				
			}
		});
		btnSearch.setBounds(296, 50, 93, 23);
		panel.add(btnSearch);
		
		JButton btnSelect = new JButton("SELECT");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query= "select * from "+tableC
							+ " where id = '"+ String.valueOf(temp) +"' ";
					PreparedStatement pst= connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();

					while(rs.next())
					{

						temp2[1] = (rs.getString("name")).toString().toCharArray();
						temp2[6] = (rs.getString("phone")).toString().toCharArray();
  						
  						temp2[2] = (rs.getString("addr")).toString().toCharArray();
  						temp2[3] = (rs.getString("city")).toString().toCharArray();	
  						temp2[4] = (rs.getString("state")).toString().toCharArray();
  						temp2[5] = (rs.getString("zip")).toString().toCharArray();  	 						
  						temp2[7] = (rs.getString("tax")).toString().toCharArray();
						
					}				
				
					pst.close();
					rs.close();
					for(int i = 1; i<8;i++)
					{
			
						System.out.println(String.valueOf(temp2[i]));
					}
					
					
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}				
				
				
			}
		});
		btnSelect.setBounds(44, 416, 93, 23);
		panel.add(btnSelect);
		
		textFieldCity = new JTextField();
		textFieldCity.setBounds(465, 192, 171, 26);
		panel.add(textFieldCity);
		textFieldCity.setColumns(10);
		
		textFieldState = new JTextField();
		textFieldState.setColumns(10);
		textFieldState.setBounds(465, 145, 66, 26);
		panel.add(textFieldState);
		
		textFieldPho = new JTextField();
		textFieldPho.setColumns(10);
		textFieldPho.setBounds(465, 343, 171, 26);
		panel.add(textFieldPho);
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(465, 293, 171, 26);
		panel.add(textFieldName);
		
		JLabel lblNewLabel = new JLabel("City");
		lblNewLabel.setBounds(401, 197, 54, 15);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("State");
		lblNewLabel_1.setBounds(401, 150, 54, 15);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Address");
		lblNewLabel_2.setBounds(401, 101, 54, 15);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Name");
		lblNewLabel_3.setBounds(401, 298, 54, 15);
		panel.add(lblNewLabel_3);
		
		JButton btnLoad = new JButton("LOAD");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query= "select id,name,city,tax from "+tableC;
					PreparedStatement pst= connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					//get all information to display
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				refreshTable();
			}
		});
		btnLoad.setBounds(156, 416, 93, 23);
		panel.add(btnLoad);
		
		JLabel lblNewLabel_2_1 = new JLabel("Zip code");
		lblNewLabel_2_1.setBounds(401, 244, 54, 15);
		panel.add(lblNewLabel_2_1);
		
		textFieldAddr = new JTextField();
		textFieldAddr.setColumns(10);
		textFieldAddr.setBounds(465, 96, 171, 26);
		panel.add(textFieldAddr);
		
		textFieldTax = new JTextField();
		textFieldTax.setColumns(10);
		textFieldTax.setBounds(594, 145, 66, 26);
		panel.add(textFieldTax);
		
		JLabel lblTax = new JLabel("TAX");
		lblTax.setBounds(549, 150, 54, 15);
		panel.add(lblTax);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Phone");
		lblNewLabel_2_1_1.setBounds(401, 343, 54, 15);
		panel.add(lblNewLabel_2_1_1);
		
		textFieldZip = new JTextField();
		textFieldZip.setColumns(10);
		textFieldZip.setBounds(465, 241, 171, 26);
		panel.add(textFieldZip);
		
		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(32, 120, 326, 286);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override  //For user tab
			public void mouseClicked(MouseEvent e) {
  				try {
//					System.out.println("Hi");
					int row = table.getSelectedRow();
					String EID = (table.getModel().getValueAt(row,0)).toString();
					System.out.println(EID);
					String query= "select * from "+tableC
							+ " where id = '"+EID +"' ";
					PreparedStatement pst= connection.prepareStatement(query);

					ResultSet rs = pst.executeQuery();
					while(rs.next())
					{

						temp=rs.getString("id").toString().toCharArray();
//  						textFieldID.setText(rs.getString("id"));


 						textFieldName.setText(rs.getString("name"));
  						textFieldPho.setText(rs.getString("phone")); 
  						
 						textFieldAddr.setText(rs.getString("addr"));
  						textFieldCity.setText(rs.getString("city"));  	
  						textFieldState.setText(rs.getString("state"));
  						textFieldZip.setText(rs.getString("zip")); 	 						
  						textFieldTax.setText(rs.getString("tax"));
//  						textFieldNote.setText(rs.getString("note"));  						
					}
					pst.close();
					System.out.println(temp);

				} 
				catch (Exception e2) 
				{
					e2.printStackTrace();				
				}			
				
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Order", null, panel_1, null);
		panel_1.setLayout(null);
		
		table_1 = new JTable();
		table_1.setBounds(366, 5, 0, 0);
		panel_1.add(table_1);
		
		textFieldQty = new JTextField();
		textFieldQty.setBounds(343, 277, 66, 21);
		panel_1.add(textFieldQty);
		textFieldQty.setColumns(10);
		textFieldQty.setText("1");
		
		JButton btnNewButton_1 = new JButton("Add");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String query= "insert into temporder(id,qty) "
							+ "values ("+String.valueOf(temp)
							+ ", "+textFieldQty.getText()
							+" )";
						PreparedStatement pst= connection.prepareStatement(query);
						pst.execute();
						pst.close();
					}
				
				 catch (Exception e2) {
					e2.printStackTrace();
				}				

				
				refreshTable2();
			}
		});
		btnNewButton_1.setBounds(343, 329, 93, 23);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Refresh");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String query= "select tb1.inv_id as ID, tb2.chcate as class, "
							+"tb1.ch_name as ch_name,tb1.price as price "
							+"from InventoryList as tb1 "
							+ "left join catelist as tb2 on tb1.CATEGORY = tb2.cateid";	
					
  					PreparedStatement pst= connection.prepareStatement(query);

  					ResultSet rs = pst.executeQuery();
  		
					table_2.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}	
				refreshTable3();
				
				
			}
		});
		btnNewButton_1_1.setBounds(343, 372, 93, 23);
		panel_1.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("Remove");
		btnNewButton_1_2.setBounds(343, 413, 93, 23);
		panel_1.add(btnNewButton_1_2);
		
		textFieldSearch2 = new JTextField();
		textFieldSearch2.setColumns(10);
		textFieldSearch2.setBounds(42, 48, 207, 28);
		panel_1.add(textFieldSearch2);
		
		JButton btnNewButton_1_3 = new JButton("Search(CH)");
		btnNewButton_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String string1 = (textFieldSearch2.getText().trim().isEmpty())?("'%'")
							:( "'%"+textFieldSearch2.getText() + "%'");
					
					String query = "select tb1.inv_id as ID, tb2.chcate as class, "
							+"tb1.ch_name as ch_name,tb1.price as price "
							+"from InventoryList as tb1 "
							+ "left join catelist as tb2 on tb1.CATEGORY = tb2.cateid"
							+ " WHERE ch_name LIKE "
							+ string1;
					System.out.println(query);
					PreparedStatement pst= connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					//get all information to display
					table_2.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
									
				
			}
		});
		
		
		btnNewButton_1_3.setBounds(259, 36, 93, 23);
		panel_1.add(btnNewButton_1_3);
		
		JButton btnNewButton_1_4 = new JButton("Seach(EN)");
		btnNewButton_1_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String string1 = (textFieldSearch2.getText().trim().isEmpty())?("'%'")
							:( "'%"+textFieldSearch2.getText() + "%'");
					
					String query = "select tb1.inv_id as ID, tb2.chcate as class, "
							+"tb1.ch_name as ch_name,tb1.price as price "
							+"from InventoryList as tb1 "
							+ "left join catelist as tb2 on tb1.CATEGORY = tb2.cateid"
							+ " WHERE en_name LIKE "
							+ string1;
					System.out.println(query);
					PreparedStatement pst= connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					//get all information to display
					table_2.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
									
				
			}
		});		
		
		
		btnNewButton_1_4.setBounds(259, 78, 93, 23);
		panel_1.add(btnNewButton_1_4);
		
		JButton btnNewButton_1_2_1 = new JButton("Remove ALL");
		btnNewButton_1_2_1.setBounds(343, 453, 93, 23);
		panel_1.add(btnNewButton_1_2_1);
		
		JButton btnNewButton_1_5 = new JButton("Preview");
		btnNewButton_1_5.setBounds(466, 453, 75, 23);
		panel_1.add(btnNewButton_1_5);
		
		JScrollPane scrollPane_1 = new JScrollPane();

		scrollPane_1.setBounds(40, 124, 246, 303);
		panel_1.add(scrollPane_1);
		
		table_2 = new JTable();
		table_2.addMouseListener(new MouseAdapter() {
			@Override// for oder tab of table I 
			public void mouseClicked(MouseEvent e) {
  				try {
//					System.out.println("Hi");
					int row = table_2.getSelectedRow();
					String EID = (table_2.getModel().getValueAt(row,0)).toString();
					System.out.println(EID);
					String query= "select * from InventoryList where inv_id = '"+EID +"' ";
					PreparedStatement pst= connection.prepareStatement(query);

					ResultSet rs = pst.executeQuery();
					while(rs.next())
					{
						
						temp=rs.getString("inv_id").toString().toCharArray();
						
  						textFieldEn_name.setText(rs.getString("en_name"));
  						textFieldCh_name.setText(rs.getString("ch_name"));
  
  						textFieldPrice.setText(rs.getString("price"));  	 						
  						textFieldTaxable.setText(rs.getString("taxable"));					
					}
					pst.close();
					System.out.println(temp);

				} 
				catch (Exception e2) 
				{
					e2.printStackTrace();				
				}
				
				
				
			}
		});
		scrollPane_1.setViewportView(table_2);
		

		JLabel lblNewLabel_4 = new JLabel("Qty:");
		lblNewLabel_4.setBounds(355, 252, 54, 15);
		panel_1.add(lblNewLabel_4);
		
		textFieldCh_name = new JTextField();
		textFieldCh_name.setColumns(10);
		textFieldCh_name.setBounds(523, 250, 156, 18);
		panel_1.add(textFieldCh_name);
		
		textFieldEn_name = new JTextField();
		textFieldEn_name.setColumns(10);
		textFieldEn_name.setBounds(523, 278, 156, 60);
		panel_1.add(textFieldEn_name);
		
		JLabel lblNewLabel_4_1 = new JLabel("CH_name");
		lblNewLabel_4_1.setBounds(466, 252, 54, 15);
		panel_1.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("EN_name");
		lblNewLabel_4_1_1.setBounds(466, 280, 54, 15);
		panel_1.add(lblNewLabel_4_1_1);
		
		JLabel lblNewLabel_4_1_1_1 = new JLabel("Price");
		lblNewLabel_4_1_1_1.setBounds(466, 350, 54, 15);
		panel_1.add(lblNewLabel_4_1_1_1);
		
		textFieldPrice = new JTextField();
		textFieldPrice.setColumns(10);
		textFieldPrice.setBounds(523, 348, 93, 18);
		panel_1.add(textFieldPrice);
		
		JLabel lblNewLabel_4_1_1_1_1 = new JLabel("Taxable");
		lblNewLabel_4_1_1_1_1.setBounds(466, 376, 54, 15);
		panel_1.add(lblNewLabel_4_1_1_1_1);
		
		textFieldTaxable = new JTextField();
		textFieldTaxable.setColumns(10);
		textFieldTaxable.setBounds(523, 373, 93, 18);
		panel_1.add(textFieldTaxable);
		
		JButton btnNewButton_1_5_1 = new JButton("Generate Order");
		btnNewButton_1_5_1.setBounds(572, 453, 117, 23);
		panel_1.add(btnNewButton_1_5_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(434, 34, 224, 126);
		panel_1.add(scrollPane_2);
		
		table_3 = new JTable();
		table_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
  				try {
//					System.out.println("Hi");
					int row = table_3.getSelectedRow();
					String EID = (table_3.getModel().getValueAt(row,0)).toString();
					System.out.println(EID);
					String query= "select * from temporder where id = '"+EID +"' ";
					PreparedStatement pst= connection.prepareStatement(query);

					ResultSet rs = pst.executeQuery();
					while(rs.next())
					{
						
						temp=rs.getString("id").toString().toCharArray();
				
					}
					pst.close();
					System.out.println(temp);

				} 
				catch (Exception e2) 
				{
					e2.printStackTrace();				
				}
								
				
				
				
			}
		});
		scrollPane_2.setViewportView(table_3);
		refreshTable();
		refreshTable2();
		refreshTable3();
	}
}
