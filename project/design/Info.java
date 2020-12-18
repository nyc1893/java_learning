//A GUI interface that include  save,add,update,delete,combombox
package javaGui;
import java.sql.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class Info extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox comboBoxName;
	private JComboBox comboBoxsearch;
	private JList listName;
	private JList listSelect;
	//private String Row;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Info frame = new Info();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection = null;
	private JTextField textFieldID;
	private JTextField textFieldValue;
	private JTextField textFieldSearch;

	private JButton btnNewButton;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	/**
	 * Create the frame.
	 */
	public void fillcomboBox() {
		try {
			String query= "select * from test";
			PreparedStatement pst= connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				comboBoxName.addItem(rs.getString("ID"));
			}
			//get all information to display
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}			
	
	}
	public void refreshTable() {
		try {
			String query= "select * from test";
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
	
	public void loadList() {
		try {
			String query= "select * from test";
			PreparedStatement pst= connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			DefaultListModel DLM = new DefaultListModel();
			while(rs.next())
			{
				DLM.addElement(rs.getString("ch_name"));
			}
			listName.setModel(DLM);
			pst.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}	

	public Info() {
		connection = sqlcon.db();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 571, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLoaddata = new JButton("Load Info");
		btnLoaddata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String query= "select * from test";
					PreparedStatement pst= connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					//get all information to display
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				refreshTable() ;
			}
		});
		btnLoaddata.setBounds(27, 67, 127, 23);
		contentPane.add(btnLoaddata);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(74, 128, 66, 21);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);
		
		JButton btnsave = new JButton("SAVE");
		btnsave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query= "insert into test(ID,ch_name) values (?,?)";
					PreparedStatement pst= connection.prepareStatement(query);
					pst.setString(1, textFieldID.getText());
					pst.setString(2, textFieldValue.getText());
					
					pst.execute();

					JOptionPane.showMessageDialog(null, "Data Saved");
					
					pst.close();
		
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				refreshTable() ;
			}
			
			
		});
		btnsave.setBounds(27, 271, 93, 23);
		contentPane.add(btnsave);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(38, 131, 34, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblValue = new JLabel("Value");
		lblValue.setBounds(38, 166, 34, 15);
		contentPane.add(lblValue);
		
		textFieldValue = new JTextField();
		textFieldValue.setColumns(10);
		textFieldValue.setBounds(74, 163, 66, 21);
		contentPane.add(textFieldValue);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query= "update test set ID='"+textFieldID.getText()+"' ,ch_name='"+ textFieldValue.getText()+"' where ID = '"+textFieldID.getText()+"' ";
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
		btnUpdate.setBounds(27, 293, 93, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(null, "Delect it?","Delete",JOptionPane.YES_NO_OPTION);
				if(action ==0)
				{
				try {
					String query= "DELETE from test where ID='"+textFieldID.getText()+"' ";
					PreparedStatement pst= connection.prepareStatement(query);
			
					pst.execute();

					JOptionPane.showMessageDialog(null, "Data Delected");
					
					pst.close();
		
					
				} catch (Exception e4) {
					e4.printStackTrace();
				}				
				}
				refreshTable() ;
			}
		});
		btnDelete.setBounds(27, 318, 93, 23);
		contentPane.add(btnDelete);
		
		 comboBoxName = new JComboBox();
		 comboBoxName.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
				try {
					String query= "select * from test where ID=?";
					PreparedStatement pst= connection.prepareStatement(query);
					pst.setString(1,(String) comboBoxName.getSelectedItem());
					ResultSet rs = pst.executeQuery();
					while(rs.next())
					{
						textFieldID.setText(rs.getString("ID"));
						textFieldValue.setText(rs.getString("ch_name"));
					}
					//JOptionPane.showMessageDialog(null, "Data Delected");
					
					pst.close();
		
					
				} catch (Exception e4) {
					e4.printStackTrace();
				}			 		
		 		
		 	}
		 });
		comboBoxName.setBounds(27, 34, 113, 23);
		contentPane.add(comboBoxName);
		
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					
					String selection = (String) comboBoxsearch.getSelectedItem();
					String sql= "select * from test where " +selection+" = ? ";
				//	String sql2 = sql.replace("?",name);
					//String query= "select * from test where " +selection+"like ? ";
					PreparedStatement pst= connection.prepareStatement(sql);
					pst.setString(1,textFieldSearch.getText());
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				//	while(rs.next())

					//JOptionPane.showMessageDialog(null, "Data Delected");
					
					pst.close();
		
					
				} catch (Exception e4) {
					e4.printStackTrace();
				}						
				
			}
		});
		textFieldSearch.setBounds(366, 51, 142, 23);
		contentPane.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
	    comboBoxsearch = new JComboBox();
		comboBoxsearch.setModel(new DefaultComboBoxModel(new String[] {"ID", "ch_name"}));
		comboBoxsearch.setBounds(251, 52, 105, 21);
		contentPane.add(comboBoxsearch);
		
		btnNewButton = new JButton("Choose");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listSelect.setListData(listName.getSelectedValues()); 
			}
		});
		btnNewButton.setBounds(286, 318, 93, 23);
		contentPane.add(btnNewButton);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(163, 293, 113, 71);
		contentPane.add(scrollPane_1);
		 
		 

		 
		 
		 
		 
		  listName = new JList();
		  scrollPane_1.setViewportView(listName);
		  listName.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		  
		  scrollPane_2 = new JScrollPane();
		  scrollPane_2.setBounds(389, 293, 113, 71);
		  contentPane.add(scrollPane_2);
		  
		  
		  		
		  		listSelect = new JList();
		  		scrollPane_2.setViewportView(listSelect);
		  		
		  		scrollPane_3 = new JScrollPane();
		  		scrollPane_3.setBounds(233, 88, 275, 145);
		  		contentPane.add(scrollPane_3);
		  		
		  		JScrollPane scrollPane = new JScrollPane();
		  		scrollPane_3.setViewportView(scrollPane);
		  		
		  		table = new JTable();
		  		table.addMouseListener(new MouseAdapter() {
		  			@Override
		  			public void mouseClicked(MouseEvent arg0) {
		  				
		  			
		  				try {
		  					int row = table.getSelectedRow();
		  					String EID = (table.getModel().getValueAt(row,0)).toString();
		  					
		  					String query= "select * from test where ID = '"+EID +"' ";
		  					PreparedStatement pst= connection.prepareStatement(query);

		  					ResultSet rs = pst.executeQuery();
		  					while(rs.next())
		  					{
		  						textFieldID.setText(rs.getString("ID"));
		  						textFieldValue.setText(rs.getString("ch_name"));
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
		//listName.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		refreshTable();
		fillcomboBox();
		loadList();
	//	add(new JScrollPane(listSelect));
	}
}
