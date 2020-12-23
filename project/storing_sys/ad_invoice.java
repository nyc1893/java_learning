package javaGui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JScrollPane;

public class ad_invoice extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTextField textFieldSearch1;
	private JTextField textFieldInvioce;
	private JTextField textFieldPrice;
	private JTextField textFieldTime;
	private JTextField textFieldSearch2;
	private JTextField textFieldName;
	Connection connection = null;
	/**
	 * Launch the application.
	 */
	String tableC = "customlist";
	String tableO = "orderList";
	String temp = "";
	String temp2 = "";
	int id_num = 0;
	private JTextField textFieldCid;
	
	public void refreshTable() {
		try {
			String query= "select id, name from "+tableC;
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
	
	
	public void refreshTable2() {	
		try {
			String query= "select  tb1.id as id, tb2.name as name, tb1.price as price, tb1.orderdate as time "
					+ "from "+tableO+" as tb1 "
					+ "left join "+tableC+" as tb2 "
					+ "on tb2.id = tb1.cid";
			PreparedStatement pst= connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			//get all information to display
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}	
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ad_invoice frame = new ad_invoice();
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
	public ad_invoice() {
		connection = sqlcon.db();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 702, 554);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldSearch1 = new JTextField();
		textFieldSearch1.setBounds(38, 91, 185, 21);
		contentPane.add(textFieldSearch1);
		textFieldSearch1.setColumns(10);
		
		textFieldInvioce = new JTextField();
		textFieldInvioce.setColumns(10);
		textFieldInvioce.setBounds(480, 312, 130, 21);
		contentPane.add(textFieldInvioce);
		
		textFieldPrice = new JTextField();
		textFieldPrice.setColumns(10);
		textFieldPrice.setBounds(480, 355, 130, 21);
		contentPane.add(textFieldPrice);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String sql = "select max(id),min(id) from " +tableO
							+ " limit 2";
					PreparedStatement pst2= connection.prepareStatement(sql);

					ResultSet rs = pst2.executeQuery();
  					while(rs.next())
  					{
  						temp2=rs.getString("max(id)");
  					}
  					
					pst2.close();
//					temp2
					
					id_num = Integer.valueOf(temp2).intValue()+1;
			//		System.out.println(Integer.parseInt(String.valueOf(temp2))+1);
					
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}					
				
				
				
				try {
					String query= "insert into "+tableO
							+ "(id,cid,orderdate,price)"
							+ "values (?,?,?,?)";
					
		
					PreparedStatement pst= connection.prepareStatement(query);
					pst.setString(1, Integer.toString(id_num));
					pst.setString(2, textFieldCid.getText());
					pst.setString(3, textFieldTime.getText());
					pst.setString(4, textFieldPrice.getText());

					pst.execute();

					JOptionPane.showMessageDialog(null, "Data Saved");
					
					pst.close();
		
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				refreshTable2() ;				
				
				
				
			}
		});
		btnNewButton.setBounds(225, 339, 77, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("<");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
  				try {

					String query= "DELETE from "+tableO
							+ " where id = '"+ temp +"' ";
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
		btnNewButton_1.setBounds(480, 253, 49, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("UPDATE");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query= "update "+tableO
							+ " set id="+textFieldInvioce.getText()
							+ ", cid='"+ textFieldCid.getText() 
							+ "', price='"+ textFieldPrice.getText() 
							+ "', orderdate='"+ textFieldTime.getText() 
							+"' where id = "+textFieldInvioce.getText()+" ";
					PreparedStatement pst= connection.prepareStatement(query);
			
					System.out.println(query);

					pst.execute();

					JOptionPane.showMessageDialog(null, "Data Updated");
					
					pst.close();
		
					
				} catch (Exception e3) {
					e3.printStackTrace();
				}
				refreshTable2() ;
				
				
				
				
			}
		});
		btnNewButton_1_1.setBounds(368, 253, 84, 23);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_2 = new JButton("Search");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String string1 = (textFieldSearch1.getText().trim().isEmpty())?("'%'")
							:( "'%"+textFieldSearch1.getText() + "%'");
					
					String query = "select id, name  from "+ tableC
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
		btnNewButton_2.setBounds(225, 90, 77, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("Invioce Number");
		lblNewLabel.setBounds(350, 315, 106, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Order Price");
		lblNewLabel_1.setBounds(368, 358, 66, 15);
		contentPane.add(lblNewLabel_1);
		
		textFieldTime = new JTextField();
		textFieldTime.setColumns(10);
		textFieldTime.setBounds(480, 393, 130, 21);
		contentPane.add(textFieldTime);
		
		JLabel lblNewLabel_1_1 = new JLabel("Order Time(yyyy-mm-dd)");
		lblNewLabel_1_1.setBounds(302, 396, 168, 15);
		contentPane.add(lblNewLabel_1_1);
		
		textFieldSearch2 = new JTextField();
		textFieldSearch2.setColumns(10);
		textFieldSearch2.setBounds(335, 49, 185, 21);
		contentPane.add(textFieldSearch2);
		
		JButton btnNewButton_2_1 = new JButton("Search");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_2_1.setBounds(530, 48, 80, 23);
		contentPane.add(btnNewButton_2_1);
		
		JLabel lblCustomName = new JLabel("Custom Name");
		lblCustomName.setBounds(244, 430, 91, 15);
		contentPane.add(lblCustomName);
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(361, 427, 249, 21);
		contentPane.add(textFieldName);
		
		JButton btnNewButton_3 = new JButton("Load");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query= "select id, name from "+tableC;
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
		btnNewButton_3.setBounds(146, 338, 77, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_3_1 = new JButton("LOAD");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String query= "select  tb1.id as id, tb2.name as name, tb1.price as price, tb1.orderdate as time "
							+ "from "+tableO+" as tb1 "
							+ "left join "+tableC+" as tb2 "
							+ "on tb2.id = tb1.cid";
					PreparedStatement pst= connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					//get all information to display
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}		
				
				
			}
		});
		btnNewButton_3_1.setBounds(539, 253, 77, 23);
		contentPane.add(btnNewButton_3_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 122, 267, 196);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(337, 87, 288, 156);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		textFieldCid = new JTextField();
		textFieldCid.setColumns(10);
		textFieldCid.setBounds(480, 463, 130, 21);
		contentPane.add(textFieldCid);
		
		JLabel lblCustomId = new JLabel("Custom ID");
		lblCustomId.setBounds(350, 466, 91, 15);
		contentPane.add(lblCustomId);
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
  				try {
//					System.out.println("Hi");
					int row = table_1.getSelectedRow();
					String EID = (table_1.getModel().getValueAt(row,0)).toString();
					System.out.println(EID);
					String query= "select * from "+tableO
							+ " where id = '"+EID +"' ";
					PreparedStatement pst= connection.prepareStatement(query);

					ResultSet rs = pst.executeQuery();
					while(rs.next())
					{

						temp=rs.getString("id");
//  						textFieldID.setText(rs.getString("id"));
						textFieldInvioce.setText(rs.getString("id"));
						textFieldTime.setText(rs.getString("orderdate"));
  						textFieldPrice.setText(rs.getString("price"));
  						textFieldCid.setText(rs.getString("cid"));
					}
					pst.close();
//					System.out.println(temp);

				} 
				catch (Exception e2) 
				{
					e2.printStackTrace();				
				}				
								
				
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
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

//						temp=rs.getString("id").toString().toCharArray();
//  						textFieldID.setText(rs.getString("id"));
  						textFieldName.setText(rs.getString("name"));
  						textFieldCid.setText(rs.getString("id"));
					}
					pst.close();
//					System.out.println(temp);

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
