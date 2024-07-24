package ui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import http_api.deleteAPI;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class manager_add_frame extends JFrame {

	
	private JPanel contentPane;
	
	public JTextField firatNameField;
	public JTextField lastNameField;
	public JTextField emailField;
	public JTextField streetField;
	public JTextField houseNrField;
	public JTextField plzField;
	
	static admin_frame adminFrame = new admin_frame();
	//static get_api api = new get_api();
	static login_frame loginFrame = new login_frame();
	//static manager_add_frame frame = new manager_add_frame();
	
	
	static http_api.postAPI postAPI = new http_api.postAPI();
	static http_api.deleteAPI deletetAPI = new deleteAPI();
	static http_api.getAPI getAPI = new http_api.getAPI();
	static http_api.putAPI putAPI = new http_api.putAPI();
	

	
	public manager_add_frame() {
		
		setTitle("Manager add frame");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 328, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 316, 483);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(7, 425, 304, 51);
		panel.add(buttonPanel);
		buttonPanel.setLayout(null);
		
		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				firatNameField.setText("");
				lastNameField.setText("");
				emailField.setText("");
				
				streetField.setText("");
				houseNrField.setText("");
				plzField.setText("");
				
			}
		});
		btnReset.setFont(new Font(".AppleSystemUIFont", Font.BOLD, 11));
		btnReset.setBounds(181, 6, 117, 39);
		buttonPanel.add(btnReset);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.setBounds(52, 6, 117, 39);
		buttonPanel.add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String firstName = firatNameField.getText().trim();
		    	String lastName = lastNameField.getText().trim();
		    	String email = emailField.getText().trim();
		    	
		    	String street = streetField.getText().trim();
		    	String housNR = houseNrField.getText().trim();
		    	String plz = plzField.getText().trim();
		    	
		    	
				String urlString = "http://localhost:8080/api/employees";
				String userName = loginFrame.usernametextField.getText();
                String userPassword = new String(loginFrame.passwordField.getPassword());

				int state = postAPI.post(urlString, userName, userPassword, firstName, lastName, email, street, housNR, plz);
				
				if (state == 200) {
					
					firatNameField.setText("");
			    	lastNameField.setText("");
			    	emailField.setText("");
			    	
			    	streetField.setText("");
			    	houseNrField.setText("");
			    	plzField.setText("");
			    	
					manager_add_frame.adminFrame.managerAddFrame.dispose();
					
					JOptionPane.showMessageDialog(adminFrame, "Employee successfully added",
				      		"confirmation", JOptionPane.CLOSED_OPTION);

					
					int result = JOptionPane.showConfirmDialog(null, "Reload to see the change",
    	            		"confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					
		            if(result == JOptionPane.YES_OPTION){
		            	adminFrame.tableModel.fireTableDataChanged();
		        		
						String postUrl = "http://localhost:8080/api/employees";
				        
		                adminFrame.tableModel.setRowCount(0);	// Clear existing data
						
						adminFrame.tableModel.fireTableDataChanged();
						
						getAPI.loadDataFromApi(postUrl, userName, userPassword);
		             }
		            
				}else if (state == 403) {
					
					
					
					
					
					JOptionPane.showMessageDialog(manager_add_frame.adminFrame, "You are not permited to edit an Employee",
		            		"confirmation", JOptionPane.CLOSED_OPTION);
					
				}else{
					
					manager_add_frame.adminFrame.managerAddFrame.dispose();;
					
					
				}
                	
			}
		});
		
		JPanel personalPanel = new JPanel();
		personalPanel.setBounds(6, 37, 305, 214);
		panel.add(personalPanel);
		personalPanel.setLayout(null);
		
		firatNameField = new JTextField();
		firatNameField.setBounds(18, 33, 275, 38);
		personalPanel.add(firatNameField);
		firatNameField.setColumns(10);
		
		lastNameField = new JTextField();
		lastNameField.setColumns(10);
		lastNameField.setBounds(18, 100, 275, 38);
		personalPanel.add(lastNameField);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(18, 166, 275, 38);
		personalPanel.add(emailField);
		
		JLabel lblFirstname = new JLabel("First Name");
		lblFirstname.setBounds(17, 16, 276, 16);
		personalPanel.add(lblFirstname);
		
		JLabel lblLastname = new JLabel("Last Name");
		lblLastname.setBounds(18, 83, 275, 16);
		personalPanel.add(lblLastname);
		
		JLabel lblMail = new JLabel("E-Mail");
		lblMail.setBounds(18, 150, 275, 16);
		personalPanel.add(lblMail);
		
		JPanel addressPanel = new JPanel();
		addressPanel.setLayout(null);
		addressPanel.setBounds(6, 274, 305, 145);
		panel.add(addressPanel);
		
		streetField = new JTextField();
		streetField.setColumns(10);
		streetField.setBounds(23, 34, 275, 38);
		addressPanel.add(streetField);
		
		houseNrField = new JTextField();
		houseNrField.setColumns(10);
		houseNrField.setBounds(23, 101, 105, 38);
		addressPanel.add(houseNrField);
		
		plzField = new JTextField();
		plzField.setColumns(10);
		plzField.setBounds(196, 101, 102, 38);
		addressPanel.add(plzField);
		
		JLabel lblStreetName = new JLabel("Street Name");
		lblStreetName.setBounds(22, 17, 276, 16);
		addressPanel.add(lblStreetName);
		
		JLabel lblHouseNr = new JLabel("House Nr");
		lblHouseNr.setBounds(23, 84, 95, 16);
		addressPanel.add(lblHouseNr);
		
		JLabel lblPostalCode = new JLabel("Postal-Code");
		lblPostalCode.setBounds(196, 84, 95, 16);
		addressPanel.add(lblPostalCode);
		
		JLabel lblPersonalInformation = new JLabel("Personal Information");
		lblPersonalInformation.setBounds(16, 17, 294, 16);
		panel.add(lblPersonalInformation);
		
		JLabel lblAdress = new JLabel("Adress");
		lblAdress.setBounds(16, 256, 294, 16);
		panel.add(lblAdress);
		
		
	}
}
