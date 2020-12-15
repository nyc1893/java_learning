package javaGui;

import java.sql.*;
import javax.swing.*;

import java.util.*;
import java.awt.Container;
import java.sql.*;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;    
public class loginTest {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginTest window = new loginTest();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection = null;
	private JTextField textField;
	private JPasswordField passwordField;
	/**
	 * Create the application.
	 */
	public loginTest() {
		initialize();
		
		connection = sqlcon.db();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Name");
		lblNewLabel.setBounds(96, 72, 54, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(96, 123, 54, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(160, 58, 167, 32);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnlogin = new JButton("Login");
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					String query= "select * from test where id = ? and ch_name=?";
					PreparedStatement pst= connection.prepareStatement(query);
					pst.setString(1, textField.getText());
					pst.setString(2, passwordField.getText());
					ResultSet rs = pst.executeQuery();
					int count =0;
					while(rs.next())
					{
						count++;
					}
					if(count ==1)
					{
						JOptionPane.showMessageDialog(null, "Correct");
					}
					else if(count>1)
					{
						JOptionPane.showMessageDialog(null, "Duplicated info.");
					}
					else 
					{
						JOptionPane.showMessageDialog(null, "input error Try Again");
					}
					rs.close();
					pst.close();
				}
				
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "Connection Success");
				}

				
			}
		});
		btnlogin.setBounds(217, 185, 93, 23);
		frame.getContentPane().add(btnlogin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(160, 109, 167, 32);
		frame.getContentPane().add(passwordField);
	}
}
