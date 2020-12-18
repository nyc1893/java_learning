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

public class admin_page extends JFrame {
	Connection connection = null;
	private JPanel contentPane;
	private JTable table_1;
	private JTextField textFielden;
	private JTextField textFieldcn;
	private JTextField textFieldunit;
	private JTextField textFieldcate;
	private JTextField textFieldprice;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JTextField textFieldtax;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JTextField textFieldID;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel_7;
	char temp2[] = new char [20];
	int id_num = 0;
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
			String query= "select inv_id,en_name,CATEGORY,ch_name,price"
					+ " from InventoryList";

			PreparedStatement pst= connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			//get all information to display
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
	
	public admin_page() {
		connection = sqlcon.db();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 815, 722);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFielden = new JTextField();
		textFielden.setBounds(164, 103, 358, 36);
		contentPane.add(textFielden);
		textFielden.setColumns(10);
		
		textFieldcn = new JTextField();
		textFieldcn.setColumns(10);
		textFieldcn.setBounds(164, 149, 358, 36);
		contentPane.add(textFieldcn);
		
		textFieldunit = new JTextField();
		textFieldunit.setColumns(10);
		textFieldunit.setBounds(143, 247, 81, 21);
		contentPane.add(textFieldunit);
		
		textFieldcate = new JTextField();
		textFieldcate.setColumns(10);
		textFieldcate.setBounds(143, 292, 81, 21);
		contentPane.add(textFieldcate);
		
		textFieldprice = new JTextField();
		textFieldprice.setColumns(10);
		textFieldprice.setBounds(143, 337, 81, 21);
		contentPane.add(textFieldprice);
		
		JLabel lblNewLabel = new JLabel("Chinese Name");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(22, 149, 137, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enlish_Name");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBounds(22, 110, 107, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Units");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_2.setBounds(22, 239, 81, 34);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Category");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_3.setBounds(22, 284, 94, 35);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Price");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_4.setBounds(26, 332, 72, 28);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Load");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query= "select inv_id,en_name,CATEGORY,ch_name,price"
							+ " from InventoryList";
					
				
					
					PreparedStatement pst= connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					//get all information to display
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				refreshTable();
			}
		});
		btnNewButton.setBounds(53, 540, 93, 23);
		contentPane.add(btnNewButton);
		
		btnAdd = new JButton("ADD");
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
					String query= "insert into InventoryList(inv_id,en_name,CATEGORY,"
							+ "ch_name,pricetime,Taxable,Units,price) values (?,?,?,?,?,?,?,?)";
					
		
					PreparedStatement pst= connection.prepareStatement(query);
					pst.setString(1, Integer.toString(id_num));
					pst.setString(2, textFielden.getText());
					pst.setString(3, textFieldcate.getText());
					pst.setString(4, textFieldcn.getText());
					pst.setString(5, "2020-12-20 12:14");
					pst.setString(6, textFieldtax.getText());
					pst.setString(7, textFieldunit.getText());
					pst.setString(8, textFieldprice.getText());
					pst.execute();

					JOptionPane.showMessageDialog(null, "Data Saved");
					
					pst.close();
		
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				refreshTable() ;				
				
			}

		});
		btnAdd.setBounds(53, 474, 93, 23);
		contentPane.add(btnAdd);
		a
		btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query= "update InventoryList set inv_id="+textFieldID.getText()
							+ ", ch_name='"+ textFieldcn.getText() + "', en_name='"+ textFielden.getText()
							+ "', Taxable='"+ textFieldtax.getText() + "', Units='"+ textFieldunit.getText()
							+ "', price="+ textFieldprice.getText() + ", CATEGORY='"+ textFieldcate.getText()
				
							+"' where inv_id = "+textFieldID.getText()+" ";
					PreparedStatement pst= connection.prepareStatement(query);
			
					System.out.println(query);
//					String query= "insert into InventoryList(inv_id,en_name,CATEGORY,"
//							+ "ch_name,pricetime,Taxable,Units,price) values (?,?,?,?,?,?,?,?)";
								
					
					
					pst.execute();

					JOptionPane.showMessageDialog(null, "Data Updated");
					
					pst.close();
		
					
				} catch (Exception e3) {
					e3.printStackTrace();
				}
				refreshTable() ;
				
			}
		});
		btnUpdate.setBounds(53, 507, 93, 23);
		contentPane.add(btnUpdate);
		
		textFieldtax = new JTextField();
		textFieldtax.setColumns(10);
		textFieldtax.setBounds(143, 383, 81, 21);
		contentPane.add(textFieldtax);
		
		lblNewLabel_5 = new JLabel("Taxable");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_5.setBounds(22, 378, 72, 28);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("ID");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_6.setBounds(22, 417, 81, 34);
		contentPane.add(lblNewLabel_6);
		
		textFieldID = new JTextField();
		textFieldID.setColumns(10);
		textFieldID.setBounds(143, 425, 81, 21);
		contentPane.add(textFieldID);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(294, 208, 452, 393);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		lblNewLabel_7 = new JLabel("To New or change Product Items");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_7.setBounds(345, 10, 421, 56);
		contentPane.add(lblNewLabel_7);
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
  				try {
  					int row = table_1.getSelectedRow();
  					String EID = (table_1.getModel().getValueAt(row,0)).toString();
  					System.out.println(EID);
  					String query= "select * from InventoryList where inv_id = '"+EID +"' ";
  					PreparedStatement pst= connection.prepareStatement(query);

  					ResultSet rs = pst.executeQuery();
  					while(rs.next())
  					{
  						textFieldID.setText(rs.getString("inv_id"));
  						textFielden.setText(rs.getString("en_name"));
  						textFieldcn.setText(rs.getString("ch_name"));
  						
  						textFieldtax.setText(rs.getString("Taxable"));
  						textFieldunit.setText(rs.getString("Units"));  	
  						textFieldprice.setText(rs.getString("price"));
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
		refreshTable();
	}
}
