//This is for user login


package javaGui;

import java.sql.*;
import javax.swing.*;

import java.util.*;
import java.awt.Container;
import java.sql.*;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;    
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
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 16));
		lblNewLabel.setBounds(53, 60, 93, 24);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 16));
		lblNewLabel_1.setBounds(53, 116, 79, 15);
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
					String query= "select * from user where name = ? and pass =?";
					PreparedStatement pst= connection.prepareStatement(query);
					pst.setString(1, textField.getText());
					pst.setString(2, passwordField.getText());
					ResultSet rs = pst.executeQuery();
					
				    query= "select * from nuser where name = ? and pass =?";
				    pst= connection.prepareStatement(query);
					pst.setString(1, textField.getText());
					pst.setString(2, passwordField.getText());
					ResultSet rt = pst.executeQuery();	
					
					if(rs.next())
					{
						JOptionPane.showMessageDialog(null, "You are super user");
						System.out.println("You are super user");
						frame.dispose();
						 menu  abc= new menu();
						abc.setVisible(true);
					}
					else if(rt.next())
					{
						JOptionPane.showMessageDialog(null, "You are normal user");
						System.out.println("You are not super user");
						frame.dispose();
						 order_page  bcd= new  order_page();
						 bcd.setVisible(true);				
			
					}
					else 
					{
						JOptionPane.showMessageDialog(null, "Username or Password wrong");
					}
					rs.close();
					pst.close();
				}
				
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "Connection Error");
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
