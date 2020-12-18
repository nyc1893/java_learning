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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 627, 667);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldNote = new JTextField();
		textFieldNote.setBounds(49, 447, 501, 128);
		contentPane.add(textFieldNote);
		textFieldNote.setColumns(10);
		
		textFieldZip = new JTextField();
		textFieldZip.setColumns(10);
		textFieldZip.setBounds(110, 327, 83, 21);
		contentPane.add(textFieldZip);
		
		textFieldPho = new JTextField();
		textFieldPho.setColumns(10);
		textFieldPho.setBounds(262, 327, 167, 21);
		contentPane.add(textFieldPho);
		
		textFieldState = new JTextField();
		textFieldState.setColumns(10);
		textFieldState.setBounds(496, 327, 54, 21);
		contentPane.add(textFieldState);
		
		textFieldAddr = new JTextField();
		textFieldAddr.setColumns(10);
		textFieldAddr.setBounds(110, 381, 302, 21);
		contentPane.add(textFieldAddr);
		
		JLabel lblNewLabel = new JLabel("State");
		lblNewLabel.setBounds(444, 330, 42, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblName = new JLabel("Zip");
		lblName.setBounds(70, 330, 54, 15);
		contentPane.add(lblName);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(210, 330, 42, 15);
		contentPane.add(lblPhone);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(49, 384, 54, 15);
		contentPane.add(lblAddress);
		
		JLabel lblNote = new JLabel("Note");
		lblNote.setBounds(60, 417, 54, 15);
		contentPane.add(lblNote);
		
		textFieldTax = new JTextField();
		textFieldTax.setColumns(10);
		textFieldTax.setBounds(496, 296, 54, 21);
		contentPane.add(textFieldTax);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(430, 384, 54, 15);
		contentPane.add(lblCity);
		
		JLabel lblName_1 = new JLabel("Name");
		lblName_1.setBounds(60, 299, 54, 15);
		contentPane.add(lblName_1);
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(110, 296, 318, 21);
		contentPane.add(textFieldName);
		
		lblTax = new JLabel("Tax");
		lblTax.setBounds(444, 299, 42, 15);
		contentPane.add(lblTax);
		
		textFieldCity = new JTextField();
		textFieldCity.setColumns(10);
		textFieldCity.setBounds(467, 381, 83, 21);
		contentPane.add(textFieldCity);
		
		btnNewButton = new JButton("DELETE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
  				try {

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
		btnNewButton.setBounds(467, 109, 93, 23);
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
		btnUpdate.setBounds(467, 153, 93, 23);
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
		btnAdd_1.setBounds(467, 201, 93, 23);
		contentPane.add(btnAdd_1);
		
		btnAdd = new JButton("LOAD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
				
				refreshTable();
			}
		});
		btnAdd.setBounds(467, 245, 93, 23);
		contentPane.add(btnAdd);
		
		lblId = new JLabel("ID");
		lblId.setBounds(430, 417, 54, 15);
		contentPane.add(lblId);
		
		textFieldID = new JTextField();
		textFieldID.setColumns(10);
		textFieldID.setBounds(467, 416, 83, 21);
		contentPane.add(textFieldID);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(56, 70, 401, 198);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
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
