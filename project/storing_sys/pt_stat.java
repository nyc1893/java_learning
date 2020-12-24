package javaGui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class pt_stat extends JFrame {

	private JPanel contentPane;

	private JTextField textFieldT1;
	private JTextField textFieldT2;
	private JTextField textFieldSearch2;
	Connection connection = null;
	
	String tableC = "customlist";
	String tableO = "orderList";
	String temp = "";
	private JTable table;
	String temp2[] = new String [10];
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pt_stat frame = new pt_stat();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public void refreshTable2() {	
		try {
			String query= "select  tb1.id as Invoice_number, tb2.id as cid,tb2.name as name, tb1.price as price, tb1.orderdate as orderdate " 
					+ "from "+tableO+" as tb1 "
					+ "left join "+tableC+" as tb2 "
					+ "on tb2.id = tb1.cid";
			PreparedStatement pst= connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
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
	public pt_stat() {
		connection = sqlcon.db();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 536);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Invoice Record:");
		lblNewLabel.setBounds(50, 38, 119, 20);
		contentPane.add(lblNewLabel);
		
		textFieldT1 = new JTextField();
		textFieldT1.setBounds(202, 339, 102, 20);
		contentPane.add(textFieldT1);
		textFieldT1.setColumns(10);
		
		textFieldT2 = new JTextField();
		textFieldT2.setColumns(10);
		textFieldT2.setBounds(368, 339, 102, 20);
		contentPane.add(textFieldT2);
		
		JLabel lblNewLabel_1 = new JLabel("Date from");
		lblNewLabel_1.setBounds(82, 342, 71, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("to");
		lblNewLabel_1_1.setBounds(325, 342, 24, 15);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Company Name:");
		lblNewLabel_1_2.setBounds(82, 380, 110, 15);
		contentPane.add(lblNewLabel_1_2);
		
		textFieldSearch2 = new JTextField();
		textFieldSearch2.setColumns(10);
		textFieldSearch2.setBounds(202, 377, 268, 20);
		contentPane.add(textFieldSearch2);
		
		JButton btnNewButton = new JButton("SearchTime");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {

					String string1 = (textFieldT1.getText().trim().isEmpty())?("''")
							:( "'"+textFieldT1.getText() + "'");

					String string2 = (textFieldT2.getText().trim().isEmpty())?("''")
							:( "'"+textFieldT2.getText() + "'");					
					
					
					String query = "select  tb1.id as Invoice_number, tb2.id as cid,tb2.name as name, tb1.price as price, tb1.orderdate as orderdate "+ 
							"from (select * from orderlist where orderdate >"+string1 +" and orderdate <"+string2+") as tb1 " + 
							"inner join CustomList as tb2 " + 
							"on tb2.id = tb1.cid";
	

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
		btnNewButton.setBounds(497, 338, 119, 23);
		contentPane.add(btnNewButton);
		
		JButton btnSearchname = new JButton("SearchName");
		btnSearchname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {

					String string1 = (textFieldSearch2.getText().trim().isEmpty())?("'%'")
							:( "'%"+textFieldSearch2.getText() + "%'");
					
					String query = "select  tb1.id as Invoice_number, tb2.id as cid,tb2.name as name, tb1.price as price, tb1.orderdate as orderdate " 
							+ " from orderlist as tb1"
							+ " inner join (select * from CustomList where name like "+string1+") as tb2"
							+ " on tb2.id = tb1.cid";

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
		btnSearchname.setBounds(497, 376, 119, 23);
		contentPane.add(btnSearchname);
		
		JButton btnSearchboth = new JButton("SearchBoth");
		btnSearchboth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {

					String string1 = (textFieldT1.getText().trim().isEmpty())?("''")
							:( "'"+textFieldT1.getText() + "'");

					String string2 = (textFieldT2.getText().trim().isEmpty())?("''")
							:( "'"+textFieldT2.getText() + "'");					
					
					String string3 = (textFieldSearch2.getText().trim().isEmpty())?("'%'")
							:( "'%"+textFieldSearch2.getText() + "%'");		
					
					String query = "select  tb1.id as Invoice_number, tb2.id as cid,tb2.name as name, tb1.price as price, tb1.orderdate as orderdate " +
							"from (select * from orderlist where orderdate >"+string1 +" and orderdate <"+string2+") as tb1 " + 
							"inner join (select * from CustomList where name like " +string3+") as tb2 " + 
							"on tb2.id = tb1.cid";
	

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
		btnSearchboth.setBounds(497, 413, 119, 23);
		contentPane.add(btnSearchboth);
		
		JButton btnComfirm = new JButton("Print");
		btnComfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String query= "select * from "+tableC
							+ " where id = '"+ temp +"' ";
					PreparedStatement pst= connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();

					while(rs.next())
					{

						temp2[1] = (rs.getString("name"));
						temp2[6] = (rs.getString("phone"));
  						
  						temp2[2] = (rs.getString("addr"));
  						temp2[3] = (rs.getString("city"));
  						temp2[4] = (rs.getString("state"));
  						temp2[5] = (rs.getString("zip"));					
  						temp2[7] = (rs.getString("tax"));
												

					}				

					
					pst.close();
					rs.close();
//
//					for(String word: temp2)
//					{
//						System.out.println(word);
//					}
					
					System.out.println(temp2[1]);
				} catch (Exception e2) {
					e2.printStackTrace();
				}				
				
				
				try {
					
					if(!textFieldT1.getText().trim().isEmpty() && !textFieldT2.getText().trim().isEmpty())
					{							
						String string1 = (textFieldT1.getText().trim().isEmpty())?("''")
								:( "'"+textFieldT1.getText() + "'");
	
						String string2 = (textFieldT2.getText().trim().isEmpty())?("''")
								:( "'"+textFieldT2.getText() + "'");					

						temp2[8] = (textFieldT1.getText());
						temp2[9] = (textFieldT2.getText());
						
						String query = "select  tb1.id as Invoice_number, tb2.name as name, tb1.price as price, tb1.orderdate as orderdate " +
		
								"from (select * from orderlist where orderdate >"+string1 +" and orderdate <"+string2+") as tb1 " + 
								"inner join (select * from CustomList where id = '" +temp+"') as tb2 " + 
								"on tb2.id = tb1.cid";
	
							PreparedStatement pst= connection.prepareStatement(query);
	//						System.out.println(sql2);
							ArrayList<String[]> arr = new ArrayList<>();
							ResultSet rs = pst.executeQuery();
							int index = 0;
							while(rs.next())
							{
								String tt[] = new String[3]; 
//								System.out.print(rs.getString("Invoice_number") + " ");
//								System.out.print(rs.getString("name") + " ");
//								System.out.print(rs.getString("price") + " ");
//								System.out.print(rs.getString("orderdate") + " ");
////		
//	
								
								tt[0] = rs.getString("Invoice_number");
//								tt[1] = rs.getString("name");
								tt[2] = rs.getString("price");
								tt[1] = rs.getString("orderdate");
	
								index++;
								
								System.out.println("");
								 arr.add(tt);
								 
							}
							pst.close();				
							rs.close();	
							
							String statelist [][] = (String[][])arr.toArray(new String[0][]);
							for (int i =0;i<statelist.length;i++)
							{
								for(int j =0;j<statelist[0].length;j++)
								{
									System.out.print( statelist[i][j] + " ");
								}
								System.out.println("");
								//						System.out.print("Total item:"+index );
							}
							
							Statement.top(statelist,temp2);
						}
						else 
						{
							JOptionPane.showMessageDialog(null, "Please have both time period and  selected company by mouse!");
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				
				
				

				
				
			}
		});
		btnComfirm.setBounds(387, 425, 83, 41);
		contentPane.add(btnComfirm);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(82, 68, 534, 182);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
  				try {
//					System.out.println("Hi");
					int row = table.getSelectedRow();
					temp = (table.getModel().getValueAt(row,1)).toString();
				
					System.out.println(temp);

				} 
				catch (Exception e2) 
				{
					e2.printStackTrace();				
				}				
								
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_2 = new JButton("LOAD");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshTable2();
				
			}
		});
		btnNewButton_2.setBounds(497, 293, 93, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Date Format: yyyy-mm-dd");
		lblNewLabel_1_3.setBounds(82, 297, 216, 15);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblPrintStatement = new JLabel("Print Statement");
		lblPrintStatement.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblPrintStatement.setBounds(449, 10, 200, 20);
		contentPane.add(lblPrintStatement);
		refreshTable2();
	}
}
