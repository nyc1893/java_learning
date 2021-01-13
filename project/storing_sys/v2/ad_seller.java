package javaGui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ad_seller extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldcity;
	private JTextField textFieldstate;
	private JTextField textFieldzip;
	private JTextField textFieldphone;
	private JTextField textFieldfax;
	private JTextField textFieldcname;
	private JTextField textFieldctitle;
	private JTextField textFieldemail;
	private JTextField textFieldnote;
	private JTextField textFieldid;
	private JTextField textFieldaddr;
	private JTextField textFieldname;
	private JTable table;
	
	char temp2[] = new char [20];
	int id_num = 0;
	char temp[] = new char [100];


	
	Connection connection = null;
	String EID = "";
	private JTextField textFieldsearch;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ad_seller frame = new ad_seller();
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
					+"from sellerList ";	

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
	
	public ad_seller() {
		connection = sqlcon.db();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1239, 736);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("LOAD");
		btnNewButton.setFont(new Font("Arial Black", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				refreshTable();
			}
		});
		btnNewButton.setBounds(1008, 490, 141, 37);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(360, 513, 54, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblName = new JLabel("Seller Company");
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblName.setBounds(31, 127, 156, 15);
		contentPane.add(lblName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblAddress.setBounds(31, 183, 92, 21);
		contentPane.add(lblAddress);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblCity.setBounds(31, 244, 54, 15);
		contentPane.add(lblCity);
		
		JLabel lblState = new JLabel("State");
		lblState.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblState.setBounds(189, 280, 54, 15);
		contentPane.add(lblState);
		
		JLabel lblZip = new JLabel("Zip");
		lblZip.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblZip.setBounds(31, 280, 54, 15);
		contentPane.add(lblZip);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblPhone.setBounds(31, 324, 54, 15);
		contentPane.add(lblPhone);
		
		JLabel lblFax = new JLabel("Fax");
		lblFax.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblFax.setBounds(31, 359, 54, 15);
		contentPane.add(lblFax);
		
		JLabel lblContactName = new JLabel("Contact Name");
		lblContactName.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblContactName.setBounds(31, 384, 141, 29);
		contentPane.add(lblContactName);
		
		JLabel lblContactTitle = new JLabel("Contact Title");
		lblContactTitle.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblContactTitle.setBounds(31, 443, 123, 25);
		contentPane.add(lblContactTitle);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblEmail.setBounds(31, 513, 54, 15);
		contentPane.add(lblEmail);
		
		JLabel lblNote = new JLabel("Note");
		lblNote.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNote.setBounds(31, 549, 54, 15);
		contentPane.add(lblNote);
		
		textFieldcity = new JTextField();
		textFieldcity.setBounds(73, 242, 223, 21);
		contentPane.add(textFieldcity);
		textFieldcity.setColumns(10);
		
		textFieldstate = new JTextField();
		textFieldstate.setColumns(10);
		textFieldstate.setBounds(242, 278, 54, 21);
		contentPane.add(textFieldstate);
		
		textFieldzip = new JTextField();
		textFieldzip.setColumns(10);
		textFieldzip.setBounds(83, 278, 89, 21);
		contentPane.add(textFieldzip);
		
		textFieldphone = new JTextField();
		textFieldphone.setColumns(10);
		textFieldphone.setBounds(95, 322, 148, 21);
		contentPane.add(textFieldphone);
		
		textFieldfax = new JTextField();
		textFieldfax.setColumns(10);
		textFieldfax.setBounds(95, 357, 148, 21);
		contentPane.add(textFieldfax);
		
		textFieldcname = new JTextField();
		textFieldcname.setColumns(10);
		textFieldcname.setBounds(31, 412, 265, 21);
		contentPane.add(textFieldcname);
		
		textFieldctitle = new JTextField();
		textFieldctitle.setColumns(10);
		textFieldctitle.setBounds(31, 472, 265, 21);
		contentPane.add(textFieldctitle);
		
		textFieldemail = new JTextField();
		textFieldemail.setColumns(10);
		textFieldemail.setBounds(95, 511, 227, 21);
		contentPane.add(textFieldemail);
		
		textFieldnote = new JTextField();
		textFieldnote.setColumns(10);
		textFieldnote.setBounds(45, 574, 1104, 66);
		contentPane.add(textFieldnote);
		
		textFieldid = new JTextField();
		textFieldid.setColumns(10);
		textFieldid.setBounds(398, 511, 99, 21);
		contentPane.add(textFieldid);
		
		textFieldaddr = new JTextField();
		textFieldaddr.setColumns(10);
		textFieldaddr.setBounds(31, 210, 273, 21);
		contentPane.add(textFieldaddr);
		
		textFieldname = new JTextField();
		textFieldname.setColumns(10);
		textFieldname.setBounds(31, 152, 265, 21);
		contentPane.add(textFieldname);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(375, 107, 774, 361);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
  				try {
  					int row = table.getSelectedRow();
  				    EID = (table.getModel().getValueAt(row,0)).toString();
  					System.out.println(EID);
  					String query= "select * from sellerList where id = '"+EID +"' ";
  					PreparedStatement pst= connection.prepareStatement(query);

  					ResultSet rs = pst.executeQuery();
  					while(rs.next())
  					{
  						textFieldid.setText(rs.getString("id"));
  						textFieldname.setText(rs.getString("name"));
  						textFieldaddr.setText(rs.getString("addr"));
  						
  						textFieldcity.setText(rs.getString("city"));
  						textFieldstate.setText(rs.getString("state")); 
  						textFieldzip.setText(rs.getString("zip")); 
  						textFieldphone.setText(rs.getString("phone"));
  						textFieldfax.setText(rs.getString("fax"));
  						textFieldcname.setText(rs.getString("cname"));
  						textFieldctitle.setText(rs.getString("ctitle"));  	
  						textFieldemail.setText(rs.getString("email"));
  						textFieldnote.setText(rs.getString("note"));   						
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
		
		textFieldsearch = new JTextField();
		textFieldsearch.setColumns(10);
		textFieldsearch.setBounds(58, 39, 265, 37);
		contentPane.add(textFieldsearch);
		
		JButton btnSearchName = new JButton("Search Name");
		btnSearchName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String string1 = (textFieldsearch.getText().trim().isEmpty())?("'%'")
							:( "'%"+textFieldsearch.getText() + "%'");
					
					String query = "select * from sellerlist "
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
		btnSearchName.setBounds(356, 24, 141, 21);
		contentPane.add(btnSearchName);
		
		JButton btnSearchCity = new JButton("Search City");
		btnSearchCity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String string1 = (textFieldsearch.getText().trim().isEmpty())?("'%'")
							:( "'%"+textFieldsearch.getText() + "%'");
					
					String query = "select * from sellerlist "
							+ " WHERE city LIKE "
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
		btnSearchCity.setBounds(360, 55, 141, 21);
		contentPane.add(btnSearchCity);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.setFont(new Font("Arial Black", Font.PLAIN, 16));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String sql = "select max(id),min(id) from sellerlist"
							+ " limit 2";
					PreparedStatement pst2= connection.prepareStatement(sql);

					ResultSet rs = pst2.executeQuery();
  					while(rs.next())
  					{
  						temp2=rs.getString("max(id)").toString().toCharArray();
  					}
  					
					pst2.close();
//					temp2
					
					id_num = Integer.parseInt(String.valueOf(temp2))+1;
					System.out.println(Integer.parseInt(String.valueOf(temp2))+1);
					
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}					
				
				
				
				try {
					String query= "insert into sellerlist("+
							"id, \r\n"+
							"name , \r\n" + 
							"addr ,  \r\n" + 
							"city ,\r\n" + 
							" state , \r\n" + 
							"zip , \r\n" + 
							"phone ,\r\n" + 
							"Fax ,\r\n" + 
							"cname ,\r\n" + 
							"ctitle ,\r\n" + 
							"email ,\r\n" + 
							"note \r\n" + 

							") values (?,?,?,?,?,  ?,?,?,?,?, ?,?)";
					
		
					PreparedStatement pst= connection.prepareStatement(query);
					pst.setString(1, Integer.toString(id_num));
					pst.setString(2, textFieldname.getText());
					pst.setString(3, textFieldaddr.getText());
					pst.setString(4, textFieldcity.getText());
					pst.setString(5, textFieldstate.getText());
					pst.setString(6, textFieldzip.getText());
					pst.setString(7, textFieldphone.getText());
					pst.setString(8, textFieldfax.getText());
					pst.setString(9, textFieldcname.getText());
					pst.setString(10, textFieldctitle.getText());
					pst.setString(11, textFieldemail.getText());
					pst.setString(12, textFieldnote.getText());
					pst.execute();

					JOptionPane.showMessageDialog(null, "Data Saved");
					
					pst.close();
		
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}				
				refreshTable();
				
			}
		});
		btnAdd.setBounds(690, 490, 141, 37);
		contentPane.add(btnAdd);
		
		JButton btnRemove = new JButton("REMOVE");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
  				try {
  					JOptionPane.showConfirmDialog(null, "remove it?");
					String query= "DELETE from sellerlist "
							+ " where id = "+textFieldid.getText()+" ";
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
		btnRemove.setFont(new Font("Arial Black", Font.PLAIN, 16));
		btnRemove.setBounds(851, 490, 141, 37);
		contentPane.add(btnRemove);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					

					String query= "update sellerlist"
							+ " set ID='"+textFieldid.getText()
							+"' ,name='"+ textFieldname.getText()
							+"' ,addr='"+ textFieldaddr.getText()
							+"' ,city='"+ textFieldcity.getText()
							+"' ,state='"+ textFieldstate.getText()
							+"' ,zip='"+ textFieldzip.getText()
							+"' ,phone='"+ textFieldphone.getText()
							+"' ,fax='"+ textFieldfax.getText()
							+"' ,cname='"+ textFieldcname.getText()
							+"' ,ctitle='"+ textFieldctitle.getText()
							+"' ,email='"+ textFieldemail.getText()
							+"' ,note='"+ textFieldnote.getText()
							+"' where ID = "+textFieldid.getText()+" ";
				
				
//           		+ "(id int not null,name varchar(80), phone varchar(20),addr varchar(80),  "
//           		+ "city varchar(20), state varchar(20), zip varchar(20) ,"
//           		+ "tax varchar(20), note varchar(250))");		
								
					PreparedStatement pst= connection.prepareStatement(query);
			
					pst.execute();

					JOptionPane.showMessageDialog(null, "Data Updated");
					
					pst.close();
		
					
				} catch (Exception e3) {
					e3.printStackTrace();
				}
				refreshTable() ;

			}
		});
		btnUpdate.setFont(new Font("Arial Black", Font.PLAIN, 16));
		btnUpdate.setBounds(539, 490, 141, 37);
		contentPane.add(btnUpdate);
		refreshTable();
	}
}
