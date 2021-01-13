package javaGui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import java.awt.Font;

public class ad_custom extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldNote;
	private JTextField textFieldZip;
	private JTextField textFieldPho;
	private JTextField textFieldState;
	private JTextField textFieldAddr;
	private JTextField textFieldTax;
	private JTextField textFieldName;
	private JLabel lblTax;
	private JTextField textFieldCity;
	private JButton btnNewButton;
	private JButton btnUpdate;
	private JButton btnAdd_1;
	private JButton btnAdd;
	Connection connection = null;
	String tablename = "customlist";
	private JLabel lblId;
	private JTextField textFieldID;
	
	char temp[] = new char [100];
	char temp2[] = new char [20];
	int id_num = 0;
	private JScrollPane scrollPane;
	private JTextField textFieldSearch;
	private JLabel lblEditCustomers;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ad_custom frame = new ad_custom();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	
	public void refreshTable() {
		try {
			String query= "select * from "+tablename;
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
	public ad_custom() {
		connection = sqlcon.db();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1284, 744);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldNote = new JTextField();
		textFieldNote.setBounds(49, 626, 1160, 44);
		contentPane.add(textFieldNote);
		textFieldNote.setColumns(10);
		
		textFieldZip = new JTextField();
		textFieldZip.setColumns(10);
		textFieldZip.setBounds(846, 562, 83, 21);
		contentPane.add(textFieldZip);
		
		textFieldPho = new JTextField();
		textFieldPho.setColumns(10);
		textFieldPho.setBounds(619, 519, 167, 21);
		contentPane.add(textFieldPho);
		
		textFieldState = new JTextField();
		textFieldState.setColumns(10);
		textFieldState.setBounds(714, 562, 54, 21);
		contentPane.add(textFieldState);
		
		textFieldAddr = new JTextField();
		textFieldAddr.setColumns(10);
		textFieldAddr.setBounds(131, 562, 302, 21);
		contentPane.add(textFieldAddr);
		
		JLabel lblNewLabel = new JLabel("State");
		lblNewLabel.setBounds(662, 564, 42, 15);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		contentPane.add(lblNewLabel);
		
		JLabel lblName = new JLabel("Zip");
		lblName.setBounds(805, 564, 54, 15);
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 18));
		contentPane.add(lblName);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(549, 521, 83, 15);
		lblPhone.setFont(new Font("Times New Roman", Font.BOLD, 18));
		contentPane.add(lblPhone);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(49, 557, 118, 28);
		lblAddress.setFont(new Font("Times New Roman", Font.BOLD, 18));
		contentPane.add(lblAddress);
		
		JLabel lblNote = new JLabel("Note");
		lblNote.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNote.setBounds(49, 595, 75, 21);
		contentPane.add(lblNote);
		
		textFieldTax = new JTextField();
		textFieldTax.setColumns(10);
		textFieldTax.setBounds(131, 475, 54, 21);
		contentPane.add(textFieldTax);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(467, 564, 54, 15);
		lblCity.setFont(new Font("Times New Roman", Font.BOLD, 18));
		contentPane.add(lblCity);
		
		JLabel lblName_1 = new JLabel("Name");
		lblName_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblName_1.setBounds(55, 521, 54, 15);
		contentPane.add(lblName_1);
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(131, 519, 390, 21);
		contentPane.add(textFieldName);
		
		lblTax = new JLabel("Tax");
		lblTax.setBounds(55, 478, 42, 15);
		lblTax.setFont(new Font("Times New Roman", Font.BOLD, 18));
		contentPane.add(lblTax);
		
		textFieldCity = new JTextField();
		textFieldCity.setColumns(10);
		textFieldCity.setBounds(515, 562, 118, 21);
		contentPane.add(textFieldCity);
		
		btnNewButton = new JButton("DELETE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
  				try {
  					JOptionPane.showConfirmDialog(null, "DELETE it?");
					String query= "DELETE from "+ tablename
							+ " where id = '"+ String.valueOf(temp) +"' ";
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
		btnNewButton.setBounds(677, 421, 132, 44);
		btnNewButton.setFont(new Font("Arial Black", Font.PLAIN, 16));
		contentPane.add(btnNewButton);
		
		btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					

					String query= "update "+tablename
							+ " set ID='"+textFieldID.getText()
							+"' ,name='"+ textFieldName.getText()
							+"' ,phone='"+ textFieldPho.getText()
							+"' ,addr='"+ textFieldAddr.getText()
							+"' ,city='"+ textFieldCity.getText()
							+"' ,state='"+ textFieldState.getText()
							+"' ,zip='"+ textFieldZip.getText()
							+"' ,tax='"+ textFieldTax.getText()
							+"' ,note='"+ textFieldNote.getText()
							+"' where ID = "+textFieldID.getText()+" ";
				
				
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
		btnUpdate.setBounds(831, 421, 118, 44);
		btnUpdate.setFont(new Font("Arial Black", Font.PLAIN, 16));
		contentPane.add(btnUpdate);
		
		btnAdd_1 = new JButton("ADD");
		btnAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				
					String sql = "select max(id),min(id) from " + tablename
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
			//		System.out.println(Integer.parseInt(String.valueOf(temp2))+1);
					
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}	
				
				try {
					String query= "insert into "+ tablename
							+"(id,name,phone,addr,city,state,zip,tax,note) "
							+ "values (?,?,?,?,?,?,?,?,?)";
					PreparedStatement pst= connection.prepareStatement(query);
					
					pst.setString(1, Integer.toString(id_num));
					pst.setString(2, textFieldName.getText());
					pst.setString(3, textFieldPho.getText());
					pst.setString(4, textFieldAddr.getText());
					pst.setString(5, textFieldCity.getText());
					pst.setString(6, textFieldState.getText());
					pst.setString(7, textFieldZip.getText());
					pst.setString(8, textFieldTax.getText());
					pst.setString(9, textFieldNote.getText());
					pst.execute();

					JOptionPane.showMessageDialog(null, "Data Saved");
					
					pst.close();
		
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}				
				refreshTable();
			}
		});
		btnAdd_1.setBounds(959, 421, 113, 44);
		btnAdd_1.setFont(new Font("Arial Black", Font.PLAIN, 16));
		contentPane.add(btnAdd_1);
		
		btnAdd = new JButton("LOAD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				refreshTable();
			}
		});
		btnAdd.setBounds(1091, 421, 118, 44);
		btnAdd.setFont(new Font("Arial Black", Font.PLAIN, 16));
		contentPane.add(btnAdd);
		
		lblId = new JLabel("ID");
		lblId.setBounds(805, 522, 54, 15);
		lblId.setFont(new Font("Times New Roman", Font.BOLD, 18));
		contentPane.add(lblId);
		
		textFieldID = new JTextField();
		textFieldID.setColumns(10);
		textFieldID.setBounds(846, 519, 83, 21);
		contentPane.add(textFieldID);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 100, 1187, 295);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		textFieldSearch = new JTextField();
		textFieldSearch.setColumns(10);
		textFieldSearch.setBounds(264, 15, 257, 44);
		contentPane.add(textFieldSearch);
		
		JButton btnSearch = new JButton("Search Name");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					String string1 = (textFieldSearch.getText().trim().isEmpty())?("'%'")
							:( "'%"+textFieldSearch.getText() + "%'");
					
					String query = "select * from "+ tablename
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
		btnSearch.setBounds(549, 15, 118, 22);
		contentPane.add(btnSearch);
		
		lblEditCustomers = new JLabel("Edit Customers");
		lblEditCustomers.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblEditCustomers.setBounds(28, 10, 182, 28);
		contentPane.add(lblEditCustomers);
		
		JButton btnSearch_1 = new JButton("Search City");
		btnSearch_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String string1 = (textFieldSearch.getText().trim().isEmpty())?("'%'")
							:( "'%"+textFieldSearch.getText() + "%'");
					
					String query = "select * from "+ tablename
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
		btnSearch_1.setBounds(549, 47, 118, 22);
		contentPane.add(btnSearch_1);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
  				try {
//					System.out.println("Hi");
					int row = table.getSelectedRow();
					String EID = (table.getModel().getValueAt(row,0)).toString();
					System.out.println(EID);
					String query= "select * from "+tablename
							+ " where id = '"+EID +"' ";
					PreparedStatement pst= connection.prepareStatement(query);

					ResultSet rs = pst.executeQuery();
					while(rs.next())
					{

						temp=rs.getString("id").toString().toCharArray();
  						textFieldID.setText(rs.getString("id"));
  						textFieldName.setText(rs.getString("name"));
  						textFieldPho.setText(rs.getString("phone"));
  						
  						textFieldAddr.setText(rs.getString("addr"));
  						textFieldCity.setText(rs.getString("city"));  	
  						textFieldState.setText(rs.getString("state"));
  						textFieldZip.setText(rs.getString("zip"));  	 						
  						textFieldTax.setText(rs.getString("tax"));
  						textFieldNote.setText(rs.getString("note"));  						
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
		refreshTable();
	}
}
