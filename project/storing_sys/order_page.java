// interface for custom ordering

package javaGui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import javax.swing.JTextArea;
public class order_page extends JFrame {
	Connection connection = null;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textFieldQty;
	private JTable table_1;
	private JLabel lblNumber;
	/**
	 * Launch the application.
	 */
	
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB?characterEcoding=utf-8&useSSL=false&serverTimezone=UTC";
 
 
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "123";
    
    
    char temp[] = new char [100];
    char temp2[] = new char [100];
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					order_page frame = new order_page();
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

	
	public void refreshTable() {
		try {
			
			String query= "select tb1.inv_id as ID, tb2.chcate as class, "
					+"tb1.ch_name as ch_name,tb1.price as price "
					+"from InventoryList as tb1 "
					+ "left join catelist as tb2 on tb1.CATEGORY = tb2.cateid";
			
			PreparedStatement pst= connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			//get all information to display
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	public void refreshTable2() {
		try {
			

			String sql2= "select tb2.id as id, tb1.ch_name as ch_name,tb2.qty as qty "
					+ "from temporder as tb2 "
					+ "left join InventoryList as tb1 on tb1.inv_id = tb2.id";
			
			PreparedStatement pst= connection.prepareStatement(sql2);
			ResultSet rs = pst.executeQuery();
			//get all information to display
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	
    public static String AddET5(String tablename,String id, String qty, String chn, String en ,String price)
	{
    	String result = String.format("insert into %s values(\"%s\",\"%s\",\"%s\",\"%s\",\"%s\")", tablename,id,qty,chn,en,price);
    	System.out.println(result);
    	return result;

	}   
	
	public order_page() {
		connection = sqlcon.db();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 738, 608);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Select");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

//			           stat.executeUpdate("create table IF NOT EXISTS temporder(id int,qty int, price decimal(6,2),"
//			              		+ "en_name varchar(80),ch_name varchar(80), tax varchar(10), unit varchar(10))");	
					
					String query= "insert into temporder(id,qty) "
							+ "values ("+String.valueOf(temp)
							+ ", "+textFieldQty.getText()
							+" )";
					
						PreparedStatement pst= connection.prepareStatement(query);

//					System.out.println(query);	
						
//						String query= "insert into test(ID,ch_name) values (?,?)";



						pst.execute();

//						JOptionPane.showMessageDialog(null, "Data selected");
						
						pst.close();
						

											
						String sql2= "select tb2.id as id, tb1.ch_name as ch_name,tb2.qty as qty "
								+ "from temporder as tb2 "
								+ "left join InventoryList as tb1 on tb1.inv_id = tb2.id";
//						System.out.println(sql2);
						PreparedStatement pst2= connection.prepareStatement(sql2);
						ResultSet rs = pst2.executeQuery();
							
						table_1.setModel(DbUtils.resultSetToTableModel(rs));
						pst2.close();
						rs.close();
					}
				
				 catch (Exception e2) {
					e2.printStackTrace();
				}

//			     String.valueOf(temp[1])
//			     
		     
			     
			     
			     

//				
			}
		});
		btnNewButton.setBounds(378, 345, 93, 23);
		contentPane.add(btnNewButton);
		
		JButton btnDeselect = new JButton("Remove");
		btnDeselect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
  				try {

					String query= "DELETE from temporder where id = '"+ String.valueOf(temp2) +"' ";
					PreparedStatement pst= connection.prepareStatement(query);
					System.out.println(query);
					pst.execute();
					pst.close();
	
				} 
				catch (Exception e2) 
				{
					e2.printStackTrace();				
				}
  				refreshTable2();
			}
		});
		btnDeselect.setBounds(546, 345, 93, 23);
		contentPane.add(btnDeselect);
		
		JButton btnPrint = new JButton("Preview");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String sql2= "select  tb1.ch_name as ch,tb2.qty as qty, "
						+ "tb1.en_name as en, tb1.taxable as tax,tb1.price as price, "
						+ "tb1.Units as unit " 
						+ "from temporder as tb2 "
						+ "left join InventoryList as tb1 on tb1.inv_id = tb2.id";

					PreparedStatement pst= connection.prepareStatement(sql2);
//					System.out.println(sql2);
					
					ResultSet rs = pst.executeQuery();
					int index = 0;
					while(rs.next())
					{
						System.out.print(rs.getString("en") + " ");
						System.out.print(rs.getString("price") + " ");
						System.out.print(rs.getString("unit") + " ");
						System.out.print(rs.getString("ch") + " ");
						System.out.print(rs.getString("qty") + " ");
						System.out.print(rs.getString("tax") + " ");
						index++;
						System.out.println("");
				
					}
					pst.close();				
					rs.close();				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
						
				
			}
		});
		btnPrint.setBounds(546, 434, 93, 23);
		contentPane.add(btnPrint);
		
		JLabel lblNewLabel = new JLabel("Customer Information");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(56, 10, 195, 61);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(261, 31, 392, 52);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String query= "select tb1.inv_id as ID, tb2.chcate as class, "
							+"tb1.ch_name as ch_name,tb1.price as price "
							+"from InventoryList as tb1 "
							+ "left join catelist as tb2 on tb1.CATEGORY = tb2.cateid";	
					
  					PreparedStatement pst= connection.prepareStatement(query);

  					ResultSet rs = pst.executeQuery();
  					while(rs.next())
  					{
  						
  						temp=rs.getString("inv_id").toString().toCharArray();
					
  					}
  					pst.close();				
					


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
		btnLoad.setBounds(378, 392, 93, 23);
		contentPane.add(btnLoad);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(56, 91, 93, 23);
		contentPane.add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(73, 200, 271, 301);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
  				try {
//  					System.out.println("Hi");
  					int row = table.getSelectedRow();
  					String EID = (table.getModel().getValueAt(row,0)).toString();
  					System.out.println(EID);
  					String query= "select * from InventoryList where inv_id = '"+EID +"' ";
  					PreparedStatement pst= connection.prepareStatement(query);

  					ResultSet rs = pst.executeQuery();
  					while(rs.next())
  					{
  						
  						temp=rs.getString("inv_id").toString().toCharArray();
					
  					}
  					pst.close();
  					

  				} 
  				catch (Exception e2) 
  				{
  					e2.printStackTrace();				
  				}

				
			}
		});
		scrollPane.setViewportView(table);
		
		textFieldQty = new JTextField();
		textFieldQty.setText("1");
		textFieldQty.setBounds(234, 146, 93, 23);
		contentPane.add(textFieldQty);
		textFieldQty.setColumns(10);
		
	    lblNumber = new JLabel("Qty Order:");
		lblNumber.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNumber.setBounds(111, 137, 93, 39);
		contentPane.add(lblNumber);
		
		JButton btnClear = new JButton("Remove All");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					String query= "Truncate Table temporder";

						PreparedStatement pst= connection.prepareStatement(query);

//						System.out.println(query);	

						pst.execute();
						JOptionPane.showMessageDialog(null, "Selection Table Cleared");
						pst.close();
					}
				
				 catch (Exception e2) {
					e2.printStackTrace();
				}
				refreshTable2();
				
				
			}
		});
		btnClear.setBounds(546, 392, 93, 23);
		contentPane.add(btnClear);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(378, 173, 239, 154);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		table_1.addMouseListener(new MouseAdapter() {
			@Override 
			// the remove one item
			public void mouseClicked(MouseEvent arg0) {
				
  				try {
//					System.out.println("Hi");
					int row = table_1.getSelectedRow();
					String EID = (table_1.getModel().getValueAt(row,0)).toString();
					System.out.println(EID);
					String query= "select * from temporder where id = '"+EID +"' ";
					PreparedStatement pst= connection.prepareStatement(query);

					ResultSet rs = pst.executeQuery();
					while(rs.next())
					{
						
						temp2=rs.getString("id").toString().toCharArray();
				
					}
					pst.close();
					System.out.println(temp2);

				} 
				catch (Exception e2) 
				{
					e2.printStackTrace();				
				}
				
			}
		});
		refreshTable();
		refreshTable2();
	}
}
