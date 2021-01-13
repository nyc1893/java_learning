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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class custom_page extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldSearch1;
	private JTextField textFieldCity;
	private JTextField textFieldState;
	private JTextField textFieldPho;
	private JTextField textFieldName;
	private JTable table_1;
	private JTextField textFieldqty;
	private JTextField textFieldSearch;
	private JTextField textFieldAddr;
	private JTextField textFieldTax;
	private JTextField textFieldZip;
	String EID = "";
	char temp[] = new char [100];
	String temp2[] = new String [10];
	String temp3 = "";
	String total ="";
	String Number  ="";
//	char temp2[][] = new char [10][100];
	private JTextField textFieldch;
	private JTextField textFielden;
	private JTextField textFieldprice;
	private JTextField textFieldtaxable;
	String tableC = "customlist";
	String tableO = "revorderList";
	String tableI = "InventoryList";
	/**
	 * Launch the application.
	 */
	Connection connection = null;
	private JTable table_2;
	private JTable table_3;
	private JTable table_4;
	private JTextField textFieldt1;
	private JTextField textFieldt2;
	private JTextField textFielditemunit;
	private JTextField textFieldterm;
	private JTextField textFieldpo;
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
	   public static String getSysTime()
	   {
	        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间 
	        
//	        sdf.applyPattern("MM/dd/yyyy");
	        
	        sdf.applyPattern("yyyy-MM-dd");// a为am/pm的标记  
	        Date date = new Date();// 获取当前时间 
	        
	        String shijian  = sdf.format(date);
	        System.out.println(shijian); 
	        return shijian;
	   }
	   
	   
	public void refreshTable3() {
		try {
			
			String query= "select tb1.inv_id, tb1.taxable, tb2.chcate as CATEGORY, "
					+"tb1.en_name ,tb1.itemunit,tb1.itemprice "
					+"from InventoryList as tb1 "
					+ "left join catelist as tb2 on tb1.CATEGORY = tb2.cateid";	
			System.out.println(query); 
//			String query="select * from InventoryList";
				PreparedStatement pst= connection.prepareStatement(query);
				ResultSet rs = pst.executeQuery();
				table_3.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}	
	}

	public void refreshTable2() {
		try {
			
			String sql2= "select * "
					+ "from temporder as tb2 ";
//					+ "left join InventoryList as tb1 on tb1.inv_id = tb2.id";
			
				PreparedStatement pst= connection.prepareStatement(sql2);
				ResultSet rs = pst.executeQuery();
				table_4.setModel(DbUtils.resultSetToTableModel(rs));
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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1241, 754);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(36, 10, 1163, 679);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Customer", null, panel, null);
		panel.setLayout(null);
		
		textFieldSearch1 = new JTextField();
		textFieldSearch1.setBounds(44, 49, 298, 26);
		panel.add(textFieldSearch1);
		textFieldSearch1.setColumns(10);
		
		JButton btnSearch = new JButton("Seach Name");
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
		btnSearch.setBounds(352, 32, 93, 23);
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
						temp2[0] = (rs.getString("id"));
						temp2[1] = (rs.getString("name"));
						temp2[6] = (rs.getString("phone"));
  						
  						temp2[2] = (rs.getString("addr"));
  						temp2[3] = (rs.getString("city"));
  						temp2[4] = (rs.getString("state"));
  						temp2[5] = (rs.getString("zip"));					
  						temp2[7] = (rs.getString("tax"));
  						temp2[9] = textFieldpo.getText() ;						
						
						
						
//						temp2[1] = (rs.getString("name")).toString().toCharArray();
//						temp2[6] = (rs.getString("phone")).toString().toCharArray();
//  						
//  						temp2[2] = (rs.getString("addr")).toString().toCharArray();
//  						temp2[3] = (rs.getString("city")).toString().toCharArray();	
//  						temp2[4] = (rs.getString("state")).toString().toCharArray();
//  						temp2[5] = (rs.getString("zip")).toString().toCharArray();  	 						
//  						temp2[7] = (rs.getString("tax")).toString().toCharArray();
						
					}				
				
					pst.close();
					rs.close();
//					for(int i = 1; i<8;i++)
//					{
//			
//						System.out.println(String.valueOf(temp2[i]));
//					}
					for(String word: temp2)
					{
						System.out.println(word);
					}
					
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}				
				
				
			}
		});
		btnSelect.setBounds(765, 346, 93, 23);
		panel.add(btnSelect);
		
		textFieldCity = new JTextField();
		textFieldCity.setBounds(140, 539, 171, 26);
		panel.add(textFieldCity);
		textFieldCity.setColumns(10);
		
		textFieldState = new JTextField();
		textFieldState.setColumns(10);
		textFieldState.setBounds(366, 539, 66, 26);
		panel.add(textFieldState);
		
		textFieldPho = new JTextField();
		textFieldPho.setColumns(10);
		textFieldPho.setBounds(540, 582, 181, 26);
		panel.add(textFieldPho);
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(122, 582, 310, 26);
		panel.add(textFieldName);
		
		JLabel lblNewLabel = new JLabel("City");
		lblNewLabel.setBounds(58, 544, 54, 15);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("State");
		lblNewLabel_1.setBounds(321, 544, 54, 15);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Address");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_2.setBounds(58, 496, 73, 22);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Name");
		lblNewLabel_3.setBounds(58, 569, 54, 15);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
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
		btnLoad.setBounds(765, 383, 93, 23);
		panel.add(btnLoad);
		
		JLabel lblNewLabel_2_1 = new JLabel("Zip:");
		lblNewLabel_2_1.setBounds(476, 544, 54, 15);
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		panel.add(lblNewLabel_2_1);
		
		textFieldAddr = new JTextField();
		textFieldAddr.setColumns(10);
		textFieldAddr.setBounds(141, 496, 171, 26);
		panel.add(textFieldAddr);
		
		textFieldTax = new JTextField();
		textFieldTax.setColumns(10);
		textFieldTax.setBounds(383, 496, 66, 26);
		panel.add(textFieldTax);
		
		JLabel lblTax = new JLabel("TAX");
		lblTax.setBounds(335, 500, 54, 15);
		lblTax.setFont(new Font("Times New Roman", Font.BOLD, 18));
		panel.add(lblTax);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Phone");
		lblNewLabel_2_1_1.setBounds(476, 587, 54, 15);
		lblNewLabel_2_1_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		panel.add(lblNewLabel_2_1_1);
		
		textFieldZip = new JTextField();
		textFieldZip.setColumns(10);
		textFieldZip.setBounds(553, 539, 171, 26);
		panel.add(textFieldZip);
		
		JScrollPane scrollPane = new JScrollPane();
		
				scrollPane.setBounds(32, 120, 700, 286);
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
				
				JButton btnSeachCity = new JButton("Seach City");
				btnSeachCity.setBounds(352, 64, 93, 23);
				panel.add(btnSeachCity);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Order", null, panel_1, null);
		panel_1.setLayout(null);
		
		table_1 = new JTable();
		table_1.setBounds(366, 5, 0, 0);
		panel_1.add(table_1);
		
		textFieldqty = new JTextField();
		textFieldqty.setBounds(273, 412, 66, 21);
		panel_1.add(textFieldqty);
		textFieldqty.setColumns(10);
		textFieldqty.setText("1");
		
		JButton btnNewButton_1 = new JButton(">");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
				String query= "select *  from temporder where id = '"+ EID +"' ";
				PreparedStatement pst= connection.prepareStatement(query);
				ResultSet rt = pst.executeQuery();
				if(rt.next())
				{
					 String tt =""; 
					 	try {
						//1 读  数值  
						String sql2= "select *  from temporder where id = '"+ EID +"' ";
						 pst= connection.prepareStatement(sql2);
						 ResultSet rs = pst.executeQuery();
						
							 while(rs.next())
							{
								tt =  (rs.getString("qty"));
							}						 

						pst.close();
		
						} 
						catch (Exception e2) 
						{
							e2.printStackTrace();				
						}					
										
						try {
							// 2. update

						
						String sql2= "update temporder set id = '"+ EID
								+"' , qty = '" + String.valueOf((Integer.valueOf(tt).intValue()+ Integer.valueOf(textFieldqty.getText()).intValue()))
								 +"' where id = "+ EID;
						 pst= connection.prepareStatement(sql2);
						 System.out.println(sql2);
						 pst.execute();
						pst.close();
		
						} 
						catch (Exception e2) 
						{
							e2.printStackTrace();				
						}		
				}
				else {

					
					
					System.out.println(textFieldtaxable.getText());
					

					
			
					try {
							String sql= "insert into temporder("
									+ "id , " + 
									"qty," + 
									"unit,"  + 
									"price, " + 
									"taxrate, " + 
									"ch, " + 
									"en " + 
									") "
									+ "values ("+ EID
									+ ", "+textFieldqty.getText()
									+ ", '"+textFielditemunit.getText()
									+ "', "+textFieldprice.getText()
									+ ", '"+ ((textFieldtaxable.getText().equals("1"))? textFieldTax.getText() : textFieldtaxable.getText())
									+ "', '"+textFieldch.getText()
									+ "', '"+textFielden.getText()
									+"'  )";
							
							System.out.println(sql);
							    pst= connection.prepareStatement(sql);
								pst.execute();
								pst.close();
							}
						
						 catch (Exception e2) {
							e2.printStackTrace();
						}				
					}
				}
				 catch (Exception e2) {
						e2.printStackTrace();
					}		
				refreshTable2();
			}
		});
		btnNewButton_1.setBounds(42, 347, 54, 21);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Refresh");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				try {
//					
//					String query= "select tb1.inv_id as ID, tb2.chcate as class, "
//							+"tb1.ch_name as ch_name,tb1.price as price "
//							+"from InventoryList as tb1 "
//							+ "left join catelist as tb2 on tb1.CATEGORY = tb2.cateid";	
//					
//  					PreparedStatement pst= connection.prepareStatement(query);
//
//  					ResultSet rs = pst.executeQuery();
//  		
//					table_2.setModel(DbUtils.resultSetToTableModel(rs));
//					pst.close();
//					rs.close();
//					
//				} catch (Exception e2) {
//					e2.printStackTrace();
//				}	
				refreshTable3();
				
				
			}
		});
		btnNewButton_1_1.setBounds(1001, 311, 93, 23);
		panel_1.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("<");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {


				try {

					String sql2= "DELETE from temporder where id = '"+ String.valueOf(temp) +"' ";
					PreparedStatement pst= connection.prepareStatement(sql2);
					 pst= connection.prepareStatement(sql2);
					System.out.println(sql2);
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
		btnNewButton_1_2.setBounds(42, 414, 54, 23);
		panel_1.add(btnNewButton_1_2);
		
		textFieldSearch = new JTextField();
		textFieldSearch.setColumns(10);
		textFieldSearch.setBounds(786, 27, 207, 28);
		panel_1.add(textFieldSearch);
		
		JButton btnNewButton_1_3 = new JButton("Search(CH)");
		btnNewButton_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String string1 = (textFieldSearch.getText().trim().isEmpty())?("'%'")
							:( "'%"+textFieldSearch.getText() + "%'");
					
					String query = "select tb1.inv_id,  tb2.chcate as CATEGORY, "
							+"tb1.en_name ,tb1.itemunit,tb1.itemprice "
							+"from InventoryList as tb1 "
							+ "left join catelist as tb2 on tb1.CATEGORY = tb2.cateid"
							+ " WHERE ch_name LIKE "
							+ string1;
					System.out.println(query);
					PreparedStatement pst= connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					//get all information to display
					table_3.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
									
				
			}
		});
		
		
		btnNewButton_1_3.setBounds(1003, 10, 124, 23);
		panel_1.add(btnNewButton_1_3);
		
		JButton btnNewButton_1_4 = new JButton("Seach(EN)");
		btnNewButton_1_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String string1 = (textFieldSearch.getText().trim().isEmpty())?("'%'")
							:( "'%"+textFieldSearch.getText() + "%'");
					

					
					
					String query = "select tb1.inv_id,  tb2.chcate as CATEGORY, "
							+"tb1.en_name ,tb1.itemunit,tb1.itemprice "
							+"from InventoryList as tb1 "
							+ "left join catelist as tb2 on tb1.CATEGORY = tb2.cateid"
							+ " WHERE en_name LIKE "
							+ string1;
					System.out.println(query);
					PreparedStatement pst= connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					//get all information to display
					table_3.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
									
				
			}
		});		
		
		
		btnNewButton_1_4.setBounds(1003, 36, 124, 23);
		panel_1.add(btnNewButton_1_4);
		
		JButton btnNewButton_1_2_1 = new JButton("<<");
		btnNewButton_1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		btnNewButton_1_2_1.setBounds(42, 447, 54, 23);
		panel_1.add(btnNewButton_1_2_1);
		
		JButton btnNewButton_1_5 = new JButton("Preview");
		btnNewButton_1_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(temp2[2] == null && temp2[0] == null) 
				{
					JOptionPane.showMessageDialog(null, "No customer information!");
				}
				
				else {
					try {

						String sql2= 	"select * from temporder ";
						
	//				String sql2= "select  tb1.ch_name as ch,tb2.qty as qty, "
	//						+ "tb1.en_name as en, tb1.taxable as tax,tb1.price as price, "
	//						+ "tb1.Units as unit " 
	//						+ "from temporder as tb2 "
	//						+ "left join InventoryList as tb1 on tb1.inv_id = tb2.id";
	
						PreparedStatement pst= connection.prepareStatement(sql2);
	//					System.out.println(sql2);
						ArrayList<String[]> arr = new ArrayList<>();
						ResultSet rs = pst.executeQuery();
						int index = 0;
						while(rs.next())
						{
							String tt[] = new String[6]; 
							System.out.print(rs.getString("en") + " ");
							System.out.print(rs.getString("ch") + " ");
							System.out.print(rs.getString("price") + " ");
							System.out.print(rs.getString("unit") + " ");
							
							System.out.print(rs.getString("qty") + " ");
							System.out.print(rs.getString("taxrate") + " ");
							
							
							
							tt[0] = rs.getString("qty");
							tt[1] = rs.getString("unit");
							tt[2] = rs.getString("en");
							tt[3] = rs.getString("price");
							tt[4] = rs.getString("taxrate");
							tt[5] = rs.getString("ch");
							index++;
							
							System.out.println("");
							 arr.add(tt);
							 
						}
						String goods [][] = (String[][])arr.toArray(new String[0][]);
						temp2[9] = textFieldterm.getText();
						temp2[8] = textFieldpo.getText();
						Preview.top( temp2,goods);
						JOptionPane.showMessageDialog(null, "Save Preview File done!");
	//					System.out.print("Total item:"+index );
						pst.close();				
						rs.close();				
					} catch (Exception e2) {
						e2.printStackTrace();
					}
											
				}
				
			}
		});
		btnNewButton_1_5.setBounds(900, 617, 82, 23);
		panel_1.add(btnNewButton_1_5);
		

		JLabel lblNewLabel_4 = new JLabel("Qty:");
		lblNewLabel_4.setBounds(227, 418, 54, 15);
		panel_1.add(lblNewLabel_4);
		
		textFieldch = new JTextField();
		textFieldch.setColumns(10);
		textFieldch.setBounds(113, 559, 156, 18);
		panel_1.add(textFieldch);
		
		textFielden = new JTextField();
		textFielden.setColumns(10);
		textFielden.setBounds(40, 505, 336, 21);
		panel_1.add(textFielden);
		
		JLabel lblNewLabel_4_1 = new JLabel("CH_name");
		lblNewLabel_4_1.setBounds(42, 561, 70, 15);
		panel_1.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("EN_name");
		lblNewLabel_4_1_1.setBounds(42, 480, 54, 15);
		panel_1.add(lblNewLabel_4_1_1);
		
		JLabel lblNewLabel_4_1_1_1 = new JLabel("Price");
		lblNewLabel_4_1_1_1.setBounds(227, 447, 54, 15);
		panel_1.add(lblNewLabel_4_1_1_1);
		
		textFieldprice = new JTextField();
		textFieldprice.setColumns(10);
		textFieldprice.setBounds(273, 449, 93, 18);
		panel_1.add(textFieldprice);
		
		JLabel lblNewLabel_4_1_1_1_1 = new JLabel("Taxable");
		lblNewLabel_4_1_1_1_1.setBounds(312, 536, 54, 15);
		panel_1.add(lblNewLabel_4_1_1_1_1);
		
		textFieldtaxable = new JTextField();
		textFieldtaxable.setColumns(10);
		textFieldtaxable.setBounds(279, 559, 93, 18);
		panel_1.add(textFieldtaxable);
		
		JButton btnNewButton_1_5_1 = new JButton("Generate Order");
		btnNewButton_1_5_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				//读个 id +1
				try {
					
					String sql = "select * from "+tableO;

					PreparedStatement pst= connection.prepareStatement(sql);

					ResultSet rs = pst.executeQuery();
  					if(rs.next())
  					{
  						
  						//读个 id +1
  						try {
  							
  							String sql2 = "select max(id),min(id) from "+tableO
  									+ " limit 2";
  							PreparedStatement pst2= connection.prepareStatement(sql2);

  							ResultSet rs2 = pst2.executeQuery();
  		  					while(rs2.next())
  		  					{
  		  						temp3=rs2.getString("max(id)");
  		  					}
  		  					
  							pst2.close();

  							
  						} catch (Exception e2) {
  							e2.printStackTrace();
  						}	

  					}
  					else {
  						temp3="0";
  					}
  					
					pst.close();

					
				} catch (Exception e2) {
					e2.printStackTrace();
				}	
				
				
				
				

				
				 Number = Integer.toString(Integer.parseInt(temp3)+1);
				try {
				String sql2= "select  tb1.ch_name as ch,tb2.qty as qty, "
						+ "tb1.en_name as en, tb1.taxable as tax,tb1.price as price, "
						+ "tb1.Units as unit " 
						+ "from temporder as tb2 "
						+ "left join InventoryList as tb1 on tb1.inv_id = tb2.id";

					PreparedStatement pst= connection.prepareStatement(sql2);
//					System.out.println(sql2);
					ArrayList<String[]> arr = new ArrayList<>();
					ResultSet rs = pst.executeQuery();
					int index = 0;
					while(rs.next())
					{
						String tt[] = new String[6]; 
							
						tt[0] = rs.getString("qty");
						tt[1] = rs.getString("unit");
						tt[2] = rs.getString("en");
						tt[3] = rs.getString("price");
						tt[4] = rs.getString("tax");
						tt[5] = rs.getString("ch");
						index++;
						
						System.out.println("");
						 arr.add(tt);
						 
					}
					String goods [][] = (String[][])arr.toArray(new String[0][]);
//					temp2 商务信息
//					good 就是订单详细
//					Number  是分配的 发票号 递增 1
					 total = GenOrder.top( temp2,goods, Number);
					System.out.print("Total :"+total );
					pst.close();				
					rs.close();				
				} catch (Exception e2) {
					e2.printStackTrace();
				}				
				
				//add 一个记录 到  invoicelist

				try {
					
					String query= "insert into "+ tableO
							+"(id,cid,price,orderdate) "
							+ "values (?,?,?,?)";
					PreparedStatement pst= connection.prepareStatement(query);
					
					pst.setString(1, Number);
					pst.setString(2, temp2[0]);
					pst.setString(3, total);
					pst.setString(4, getSysTime());

					pst.execute();

					JOptionPane.showMessageDialog(null, "Data Saved");
					
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}					
			}

			
		});
		btnNewButton_1_5_1.setBounds(992, 617, 135, 23);
		panel_1.add(btnNewButton_1_5_1);
		
		table_2 = new JTable();
		table_2.setBounds(42, 77, 389, 224);
		panel_1.add(table_2);
		
		textFieldt1 = new JTextField();
		textFieldt1.setColumns(10);
		textFieldt1.setBounds(84, 37, 93, 18);
		panel_1.add(textFieldt1);
		
		textFieldt2 = new JTextField();
		textFieldt2.setColumns(10);
		textFieldt2.setBounds(227, 38, 93, 18);
		panel_1.add(textFieldt2);
		
		JButton btnNewButton_1_1_1 = new JButton("SearchTime");
		btnNewButton_1_1_1.setBounds(349, 36, 124, 23);
		panel_1.add(btnNewButton_1_1_1);
		
		JLabel lblNewLabel_4_1_1_2 = new JLabel("FROM");
		lblNewLabel_4_1_1_2.setBounds(42, 40, 54, 15);
		panel_1.add(lblNewLabel_4_1_1_2);
		
		JLabel lblNewLabel_4_1_1_3 = new JLabel("TO");
		lblNewLabel_4_1_1_3.setBounds(185, 40, 22, 15);
		panel_1.add(lblNewLabel_4_1_1_3);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(1104, 299, -313, -193);
		panel_1.add(scrollPane_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(593, 299, 345, -146);
		panel_1.add(scrollPane_2);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(593, 77, 501, 224);
		panel_1.add(scrollPane_3);
		
		table_3 = new JTable();
		table_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
  				try {
//					System.out.println("Hi");
							int row = table_3.getSelectedRow();
						     EID = (table_3.getModel().getValueAt(row,0)).toString();
							System.out.println(EID);
							String query= "select * from "+tableI
									+ " where inv_id = '"+EID +"' ";
							System.out.println(query);
							PreparedStatement pst= connection.prepareStatement(query);

							ResultSet rs = pst.executeQuery();
							while(rs.next())
							{

								temp=rs.getString("inv_id").toString().toCharArray();
//  						textFieldID.setText(rs.getString("id"));


	 						textFielden.setText(rs.getString("en_name"));
	  						textFieldch.setText(rs.getString("ch_name")); 
	  						
	 						textFieldtaxable.setText(rs.getString("taxable"));
	  						textFieldprice.setText(rs.getString("itemprice"));  	
	  						textFielditemunit.setText(rs.getString("itemunit"));
//	  						textFieldZip.setText(rs.getString("zip")); 	 						
//	  						textFieldTax.setText(rs.getString("tax"));
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
		scrollPane_3.setViewportView(table_3);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(593, 349, 513, 210);
		panel_1.add(scrollPane_4);
		
		table_4 = new JTable();
		scrollPane_4.setViewportView(table_4);
		
		JLabel lblNewLabel_4_2 = new JLabel("ItemUnit:");
		lblNewLabel_4_2.setBounds(208, 382, 54, 15);
		panel_1.add(lblNewLabel_4_2);
		
		textFielditemunit = new JTextField();
		textFielditemunit.setText("1");
		textFielditemunit.setColumns(10);
		textFielditemunit.setBounds(273, 381, 66, 21);
		panel_1.add(textFielditemunit);
		
		JLabel lblNewLabel_4_1_1_1_1_1 = new JLabel("Terms(days)");
		lblNewLabel_4_1_1_1_1_1.setBounds(664, 575, 112, 15);
		panel_1.add(lblNewLabel_4_1_1_1_1_1);
		
		textFieldterm = new JTextField();
		textFieldterm.setColumns(10);
		textFieldterm.setBounds(664, 600, 93, 18);
		panel_1.add(textFieldterm);
		refreshTable();
		refreshTable2();
		refreshTable3();
		textFieldterm.setText("30");
		
		textFieldpo = new JTextField();
		textFieldpo.setColumns(10);
		textFieldpo.setBounds(788, 599, 175, 18);
		panel_1.add(textFieldpo);
		
		JLabel lblNewLabel_4_2_1 = new JLabel("PO Number");
		lblNewLabel_4_2_1.setBounds(786, 575, 117, 15);
		panel_1.add(lblNewLabel_4_2_1);
//		textFieldpo.setText("");
	}
}
