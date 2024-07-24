package ui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import http_api.deleteAPI;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class confirmPassword_frame extends JFrame {

	private JPanel contentPane;
	JPasswordField passwordField;
	JLabel confirmPasswordWrongLabel;
	
	
	static login_frame loginFrame = new login_frame();
	static RowListener rowListener = new RowListener();
	static admin_frame adminFrame = new admin_frame();
	static confirmPassword_frame frame = new confirmPassword_frame();


	static http_api.deleteAPI deletetAPI = new deleteAPI();
	static http_api.getAPI getAPI = new http_api.getAPI();
	

	public confirmPassword_frame() {
		
		setTitle("Confirm Password");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 454, 196);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 442, 154);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnConfirm = new JButton("CONFIRM");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 String rowlistener = rowListener.re;
	                
	             String[] id = rowlistener.split(":");
	             int employeeID = Integer.valueOf(id[0].replace(" ", "")) ;               
					
	             String deleteUrl = "http://localhost:8080/api/employees/"+ employeeID;
					
	             String confirmPassword =  new String(passwordField.getPassword());
	             String userName = loginFrame.usernametextField.getText();
	             String userPassword = new String(loginFrame.passwordField.getPassword());
	                
	                if (confirmPassword.equals(userPassword) ) {
	                	
						try {
							
							int state = deletetAPI.delete(deleteUrl, userName, userPassword);
						
							if (state == 200) {
								
								 passwordField.setText("");
	
								 JOptionPane.showMessageDialog(frame, "Employee successfully delected",
						            		"confirmation", JOptionPane.CLOSED_OPTION);
								 
								 frame.dispose();
								 
								 int result = JOptionPane.showConfirmDialog(null, "Reload to see the change",
				    	            		"confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
								 
								 if(result == JOptionPane.YES_OPTION){
						            	
						                //("You selected: Yes");
						            	//If you want to notify your JTable about changes of your data, use
						            	adminFrame.tableModel.fireTableDataChanged();
						        		
										String postUrl = "http://localhost:8080/api/employees";
								        
						                adminFrame.tableModel.setRowCount(0);	// Clear existing data
										
										getAPI.loadDataFromApi(postUrl, userName, userPassword);
										
										//Notify the table model of the update
										adminFrame.tableModel.fireTableDataChanged();
						             }
								 
							}else if (state == 403) {
								
								frame.setVisible(false);
								
								JOptionPane.showMessageDialog(frame, "Employee not delected, user not permitted",
					            		"confirmation", JOptionPane.CLOSED_OPTION);
								
							}
						
						} catch (IOException e1) {
							e1.printStackTrace();
						}	
						
					}else {
						confirmPasswordWrongLabel.setText("Wrong password, try again");
					}

			}
		});
		btnConfirm.setFont(new Font(".AppleSystemUIFont", Font.BOLD, 11));
		btnConfirm.setBounds(319, 109, 117, 39);
		panel.add(btnConfirm);
		
		// Enter to start
		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String rowlistener = rowListener.re;
                
	             String[] id = rowlistener.split(":");
	             int employeeID = Integer.valueOf(id[0].replace(" ", "")) ;               
					
	             String deleteUrl = "http://localhost:8080/api/employees/"+ employeeID;
					
	             String confirmPassword =  new String(passwordField.getPassword());
	             String userName = loginFrame.usernametextField.getText();
	             String userPassword = new String(loginFrame.passwordField.getPassword());
	                
	                if (confirmPassword.equals(userPassword) ) {
	                	
						try {
							
							int state = deletetAPI.delete(deleteUrl, userName, userPassword);
						
							if (state == 200) {
								
								 passwordField.setText("");
	
								 JOptionPane.showMessageDialog(frame, "Employee successfully delected",
						            		"confirmation", JOptionPane.CLOSED_OPTION);
								 
								 frame.dispose();
								 
								 int result = JOptionPane.showConfirmDialog(null, "Reload to see the change",
				    	            		"confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
								 
								 if(result == JOptionPane.YES_OPTION){
						            	
						                //("You selected: Yes");
						            	//If you want to notify your JTable about changes of your data, use
						            	adminFrame.tableModel.fireTableDataChanged();
						        		
										String postUrl = "http://localhost:8080/api/employees";
								        
						                adminFrame.tableModel.setRowCount(0);	// Clear existing data
										
										getAPI.loadDataFromApi(postUrl, userName, userPassword);
										
										//Notify the table model of the update
										adminFrame.tableModel.fireTableDataChanged();
						             }
								 
							}else if (state == 403) {
								
								frame.setVisible(false);
								
								JOptionPane.showMessageDialog(frame, "Employee not delected, user not permitted",
					            		"confirmation", JOptionPane.CLOSED_OPTION);
								
							}
						
						} catch (IOException e1) {
							e1.printStackTrace();
						}	
						
					}else {
						confirmPasswordWrongLabel.setText("Wrong password, try again");
					}

				
				
			}
		});
		passwordField.setBounds(90, 34, 253, 39);
		panel.add(passwordField);
		
		JLabel lblNewLabel = new JLabel("Confirm your password, if delected CAN NOT be reversed");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 6, 430, 26);
		panel.add(lblNewLabel);
		
		confirmPasswordWrongLabel = new JLabel("");
		confirmPasswordWrongLabel.setHorizontalAlignment(SwingConstants.CENTER);
		confirmPasswordWrongLabel.setBounds(6, 99, 430, 26);
		panel.add(confirmPasswordWrongLabel);
		
		JCheckBox showCheckBox = new JCheckBox("show password");
		showCheckBox.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		showCheckBox.setBounds(90, 71, 139, 23);
		showCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				if (showCheckBox.isSelected()) {
					// Show password
                    passwordField.setEchoChar((char) 0);  
				}else {
					// Hide password
                    passwordField.setEchoChar('●');  
				}
			}
		});
		panel.add(showCheckBox);
	}
}
