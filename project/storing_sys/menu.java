package javaGui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menu frame = new menu();
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
	public menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 376, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("EDIT Customer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				 ad_custom  abc= new ad_custom();
				 abc.setVisible(true);
				
			}

		});
		btnNewButton.setBounds(30, 54, 121, 37);
		contentPane.add(btnNewButton);
		
		JButton btnEditProduction = new JButton("EDIT Production");
		btnEditProduction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 admin_page  abc= new admin_page();
				 abc.setVisible(true);
				
			}
		});
		btnEditProduction.setBounds(185, 54, 140, 37);
		contentPane.add(btnEditProduction);
		
		JButton btnOrdering = new JButton("ORDERING");
		btnOrdering.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 custom_page  abc= new custom_page();
				 abc.setVisible(true);
	
			}
		});
		btnOrdering.setBounds(38, 337, 113, 37);
		contentPane.add(btnOrdering);
		
		JLabel lblNewLabel = new JLabel("\u7F16\u8F91\u5BA2\u6237\u660E\u7EC6");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel.setBounds(30, 22, 113, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u7F16\u8F91\u8D27\u54C1\u660E\u7EC6");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(205, 22, 134, 31);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("\u5BA2\u6237\u4E0B\u5355");
		lblNewLabel_1_1.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(59, 309, 71, 31);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("\u6253\u5370\u5BF9\u8D26\u5355");
		lblNewLabel_1_1_1.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(210, 112, 101, 31);
		contentPane.add(lblNewLabel_1_1_1);
		
		JButton btnStatement = new JButton("Print Statement");
		btnStatement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 pt_stat  abc= new pt_stat();
				 abc.setVisible(true);	
				
			}
		});
		btnStatement.setBounds(185, 141, 140, 37);
		contentPane.add(btnStatement);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("\u8D27\u54C1\u5165\u5E93");
		lblNewLabel_1_1_1_1.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1.setBounds(59, 112, 71, 31);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		JButton btnInput = new JButton("GOODS INPUT");
		btnInput.setBounds(38, 141, 113, 37);
		contentPane.add(btnInput);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("\u4FEE\u6539\u5BF9\u8D26\u5355");
		lblNewLabel_1_1_1_2.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_1_1_1_2.setBounds(201, 309, 110, 31);
		contentPane.add(lblNewLabel_1_1_1_2);
		
		JButton btnStatics = new JButton("Edit Statement");
		btnStatics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ad_invoice  abc= new ad_invoice();
				 abc.setVisible(true);				
				
			}
		});
		btnStatics.setBounds(185, 337, 140, 37);
		contentPane.add(btnStatics);
	}
}
