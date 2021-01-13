package javaGui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

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
import java.awt.Color;

public class admin_page extends JFrame {
	Connection connection = null;
	private JPanel contentPane;
	private JTextField textFielden;
	private JTextField textFieldcn;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	char temp2[] = new char [20];
	int id_num = 0;
	String EID = "";
	private JTextField textFieldSearch;
	private JButton btnSearch;
	private JButton btnSearchch;
	private JLabel lblNewLabel_7;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JTable table;
	private JTextField textFieldID;
	private JTextField textFieldcate;
	private JTextField textFieldcaseprice;
	private JTextField textFieldcaseunit;
	private JTextField textFielditemprice;
	private JTextField textFielditemunit;
	private JTextField textFieldtaxable;
	private JTextField textFielditempercase;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin_page frame = new admin_page();
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
			String query= 					
					"select * "
					+"from InventoryList ";	

			System.out.println(query);
			PreparedStatement pst= connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			//get all information to display
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			

			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
	
	public admin_page() {
		connection = sqlcon.db();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFielden = new JTextField();
		textFielden.setBounds(164, 103, 514, 36);
		contentPane.add(textFielden);
		textFielden.setColumns(10);
		
		textFieldcn = new JTextField();
		textFieldcn.setColumns(10);
		textFieldcn.setBounds(164, 149, 514, 36);
		contentPane.add(textFieldcn);
		
		JLabel lblNewLabel = new JLabel("Chinese Name");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(22, 149, 137, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enlish_Name");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBounds(22, 110, 107, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("ItemperCase");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_2.setBounds(24, 571, 115, 34);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Category");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_3.setBounds(22, 284, 94, 35);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("CasePrice");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_4.setBounds(22, 329, 103, 28);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Load");
		btnNewButton.setFont(new Font("Arial Black", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshTable();
			}
		});
		btnNewButton.setBounds(987, 559, 93, 23);
		contentPane.add(btnNewButton);
		
		btnAdd = new JButton("ADD");
		btnAdd.setFont(new Font("Arial Black", Font.PLAIN, 16));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String sql = "select max(inv_id),min(inv_id) from InventoryList"
							+ " limit 2";
					PreparedStatement pst2= connection.prepareStatement(sql);

					ResultSet rs = pst2.executeQuery();
  					while(rs.next())
  					{
  						temp2=rs.getString("max(inv_id)").toString().toCharArray();
  					}
  					
					pst2.close();
//					temp2
					
					id_num = Integer.parseInt(String.valueOf(temp2))+1;
			//		System.out.println(Integer.parseInt(String.valueOf(temp2))+1);
					
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}					
				
				
				
				try {
					String query= "insert into InventoryList(inv_id,en_name,"
							+"caseprice ," + 
							 "CATEGORY,"+
							"ch_name ,  " + 
							"Itempercase ," + 
							"Itemprice ," + 
							"Taxable ," + 
							"caseunit ," + 
							"itemunit " + 
							") values (?,?,?,?,?,?,?,?,?,?)";
					
		
					PreparedStatement pst= connection.prepareStatement(query);
					pst.setString(1, Integer.toString(id_num));
					pst.setString(2, textFielden.getText());
					pst.setString(3, textFieldcaseprice.getText());
					pst.setString(4, textFieldcate.getText());
					pst.setString(5, textFieldcn.getText());
					pst.setString(6, textFielditempercase.getText());
					pst.setString(7, textFielditemprice.getText());
					pst.setString(8, textFieldtaxable.getText());
					pst.setString(9, textFieldcaseunit.getText());
					pst.setString(10, textFielditemunit.getText());

					pst.execute();

					JOptionPane.showMessageDialog(null, "Data Saved");
					
					pst.close();
		
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				refreshTable() ;				
				
			}

		});
		btnAdd.setBounds(158, 647, 111, 36);
		contentPane.add(btnAdd);
		
		btnUpdate = new JButton("UPDATE");
		btnUpdate.setFont(new Font("Arial Black", Font.PLAIN, 16));
		btnUpdate.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
						String query= "update InventoryList set inv_id="+textFieldID.getText()
						+ ", ch_name='"+ textFieldcn.getText() + "', en_name='"+ textFielden.getText()
						+ "', Taxable='"+ textFieldtaxable.getText() + "', caseUnit='"+ textFieldcaseunit.getText()
						+ "', caseprice="+ textFieldcaseprice.getText() + ", CATEGORY="+ textFieldcate.getText()
						+ ", itemprice="+ textFielditemprice.getText() + ", itemunit='"+ textFielditemunit.getText()
						+ "', Itempercase="+ textFielditempercase.getText() 
						+" where inv_id = "+textFieldID.getText()+" ";
				PreparedStatement pst= connection.prepareStatement(query);
		
				System.out.println(query);
		//		String query= "insert into InventoryList(inv_id,en_name,CATEGORY,"
		//				+ "ch_name,pricetime,Taxable,Units,price) values (?,?,?,?,?,?,?,?)";
							
				
				
				pst.execute();
		
				JOptionPane.showMessageDialog(null, "Data Updated");
				
				pst.close();
		
				
			} catch (Exception e3) {
				e3.printStackTrace();
			}

				refreshTable() ;
				
			}
		});
		btnUpdate.setBounds(22, 647, 117, 36);
		contentPane.add(btnUpdate);
		
		lblNewLabel_5 = new JLabel("CaseUnit");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_5.setBounds(22, 378, 117, 28);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("ID");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_6.setBounds(24, 240, 81, 34);
		contentPane.add(lblNewLabel_6);
		
		textFieldSearch = new JTextField();
		textFieldSearch.setColumns(10);
		textFieldSearch.setBounds(164, 48, 514, 36);
		contentPane.add(textFieldSearch);
		
		btnSearch = new JButton("Search(EN)");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {

					String string1 = (textFieldSearch.getText().trim().isEmpty())?("'%'")
							:( "'%"+textFieldSearch.getText() + "%'");
					
					String query = "select * from InventoryList"
							+ " WHERE en_name LIKE "
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
		btnSearch.setBounds(720, 29, 101, 30);
		contentPane.add(btnSearch);
		
		btnSearchch = new JButton("Search(CH)");
		btnSearchch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {

					String string1 = (textFieldSearch.getText().trim().isEmpty())?("'%'")
							:( "'%"+textFieldSearch.getText() + "%'");
					
					String query = "select * from InventoryList"
							+ " WHERE ch_name LIKE "
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
		btnSearchch.setBounds(720, 69, 101, 30);
		contentPane.add(btnSearchch);
		
		lblNewLabel_7 = new JLabel("EDIT Production");
		lblNewLabel_7.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblNewLabel_7.setBounds(22, 10, 199, 21);
		contentPane.add(lblNewLabel_7);
		
		btnNewButton_1 = new JButton("REMOVE");
		btnNewButton_1.setFont(new Font("Arial Black", Font.PLAIN, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
  				try {

					String query= "DELETE from InventoryList where inv_id = "+ EID;
					PreparedStatement pst= connection.prepareStatement(query);
					System.out.println(query);
					pst.execute();
					pst.close();
	
				} 
				catch (Exception e2) 
				{
					e2.printStackTrace();				
				}
  				refreshTable() ;
				
			}
		});
		btnNewButton_1.setForeground(Color.RED);
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBounds(1074, 647, 115, 33);
		contentPane.add(btnNewButton_1);
		
		lblNewLabel_8 = new JLabel("100->\u852C\u83DC  200->\u51B0\u51BB\u98DF\u54C1  300->\u5E72\u8D27  400->\u996E\u6599  ");
		lblNewLabel_8.setBounds(443, 582, 446, 44);
		contentPane.add(lblNewLabel_8);
		
		lblNewLabel_9 = new JLabel(" 500->\u96F6\u98DF  600->\u8C03\u5473\u6599  700->\u65E5\u7528\u54C1  800->\u5176\u4ED6");
		lblNewLabel_9.setBounds(453, 636, 296, 44);
		contentPane.add(lblNewLabel_9);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(341, 224, 785, 325);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
  				try {
  					int row = table.getSelectedRow();
  				    EID = (table.getModel().getValueAt(row,0)).toString();
  					System.out.println(EID);
  					String query= "select * from InventoryList where inv_id = '"+EID +"' ";
  					PreparedStatement pst= connection.prepareStatement(query);

  					ResultSet rs = pst.executeQuery();
  					while(rs.next())
  					{
  						textFieldID.setText(rs.getString("inv_id"));
  						textFielden.setText(rs.getString("en_name"));
  						textFieldcn.setText(rs.getString("ch_name"));
  						
  						textFieldtaxable.setText(rs.getString("Taxable"));
  						textFielditemunit.setText(rs.getString("itemUnit")); 
  						textFieldcaseunit.setText(rs.getString("caseUnit")); 
  						textFieldcaseprice.setText(rs.getString("caseprice"));
  						textFielditemprice.setText(rs.getString("itemprice"));
  						textFielditempercase.setText(rs.getString("itempercase"));
  						textFieldcate.setText(rs.getString("CATEGORY"));  	 						
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
		
		JLabel lblNewLabel_5_1 = new JLabel("ItemPrice");
		lblNewLabel_5_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_5_1.setBounds(22, 424, 107, 28);
		contentPane.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_5_2 = new JLabel("ItemUnit");
		lblNewLabel_5_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_5_2.setBounds(22, 470, 94, 28);
		contentPane.add(lblNewLabel_5_2);
		
		JLabel lblNewLabel_5_3 = new JLabel("Taxable");
		lblNewLabel_5_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_5_3.setBounds(22, 521, 72, 28);
		contentPane.add(lblNewLabel_5_3);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(115, 248, 106, 26);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);
		
		textFieldcate = new JTextField();
		textFieldcate.setColumns(10);
		textFieldcate.setBounds(115, 292, 106, 26);
		contentPane.add(textFieldcate);
		
		textFieldcaseprice = new JTextField();
		textFieldcaseprice.setColumns(10);
		textFieldcaseprice.setBounds(115, 334, 106, 26);
		contentPane.add(textFieldcaseprice);
		
		textFieldcaseunit = new JTextField();
		textFieldcaseunit.setColumns(10);
		textFieldcaseunit.setBounds(115, 383, 106, 26);
		contentPane.add(textFieldcaseunit);
		
		textFielditemprice = new JTextField();
		textFielditemprice.setColumns(10);
		textFielditemprice.setBounds(115, 429, 106, 26);
		contentPane.add(textFielditemprice);
		
		textFielditemunit = new JTextField();
		textFielditemunit.setColumns(10);
		textFielditemunit.setBounds(115, 475, 106, 26);
		contentPane.add(textFielditemunit);
		
		textFieldtaxable = new JTextField();
		textFieldtaxable.setColumns(10);
		textFieldtaxable.setBounds(115, 526, 106, 26);
		contentPane.add(textFieldtaxable);
		
		textFielditempercase = new JTextField();
		textFielditempercase.setColumns(10);
		textFielditempercase.setBounds(149, 577, 106, 26);
		contentPane.add(textFielditempercase);
		refreshTable();
	}
}
