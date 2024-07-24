package ui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class admin_edit_frame extends JFrame {

	private JPanel contentPane;
	public static JTextField firstNameTextField;
	public static JTextField lastNameTextField;
	public static JTextField emailTextField;
	public static JTextField streetTextField;
	public static JTextField houseNRtextField;
	public static JTextField plzTextField;
	
	
	JButton btnSave;
	
	
	static admin_edit_frame frame = new admin_edit_frame();
	static admin_frame adminFrame = new admin_frame();
	static login_frame loginFrame = new login_frame();
	
	static http_api.getAPI getAPI = new http_api.getAPI();
	static http_api.putAPI putAPI = new http_api.putAPI();

	static RowListener rowListener = new RowListener();


	public admin_edit_frame() {
		
		setResizable(false);
		setTitle("Admin Edit");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 486, 324);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 474, 201);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 12));
		lblNewLabel.setBounds(6, 14, 125, 16);
		panel.add(lblNewLabel);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 12));
		lblLastName.setBounds(250, 14, 125, 16);
		panel.add(lblLastName);
		
		firstNameTextField = new JTextField();
		firstNameTextField.setBounds(6, 29, 223, 31);
		panel.add(firstNameTextField);
		firstNameTextField.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		firstNameTextField.setColumns(10);
		
		lastNameTextField = new JTextField();
		lastNameTextField.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		lastNameTextField.setColumns(10);
		lastNameTextField.setBounds(245, 29, 223, 31);
		panel.add(lastNameTextField);
		
		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 12));
		lblEmail.setBounds(6, 72, 125, 16);
		panel.add(lblEmail);
		
		emailTextField = new JTextField();
		emailTextField.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		emailTextField.setColumns(10);
		emailTextField.setBounds(6, 87, 223, 31);
		panel.add(emailTextField);
		
		streetTextField = new JTextField();
		streetTextField.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		streetTextField.setColumns(10);
		streetTextField.setBounds(245, 87, 223, 31);
		panel.add(streetTextField);
		
		JLabel lblStreetName = new JLabel("Street name");
		lblStreetName.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 12));
		lblStreetName.setBounds(250, 72, 125, 16);
		panel.add(lblStreetName);
		
		JLabel lblHouseNR = new JLabel("House NR.");
		lblHouseNR.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 12));
		lblHouseNR.setBounds(6, 134, 125, 16);
		panel.add(lblHouseNR);
		
		houseNRtextField = new JTextField();
		houseNRtextField.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		houseNRtextField.setColumns(10);
		houseNRtextField.setBounds(6, 149, 223, 31);
		panel.add(houseNRtextField);
		
		plzTextField = new JTextField();
		plzTextField.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		plzTextField.setColumns(10);
		plzTextField.setBounds(245, 149, 223, 31);
		panel.add(plzTextField);
		
		JLabel lblPlz = new JLabel("PLZ");
		lblPlz.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 12));
		lblPlz.setBounds(250, 134, 125, 16);
		panel.add(lblPlz);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(6, 179, 462, 16);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(6, 219, 474, 67);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		//JOptionPane.showConfirmDialog(this, "This change can not be reversed", "Comfirmation", JOptionPane.CANCEL_OPTION);
		
		btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String rowlistener = rowListener.re;
				String[] id = rowlistener.split(":");
				
                int idString = Integer.valueOf(id[0].replace(" ", "")) ;  
                String employeeID = String.valueOf(idString);
                
                
                System.err.println("employeeID: " + employeeID);
                
                		
				String putUrl = "http://localhost:8080/api/employees";
				
				String userName = loginFrame.usernametextField.getText();
                String userPassword = new String(loginFrame.passwordField.getPassword());
                
                
                String firstname = firstNameTextField.getText().trim();
                String lastname = lastNameTextField.getText().trim();
                String email = emailTextField.getText().trim();
                
                String street = streetTextField.getText().trim();
		    	String housNR = houseNRtextField.getText().trim();
		    	String plz = plzTextField.getText().trim();
                
                
                System.err.println("employeeID: " + employeeID);
                System.err.println("firstname: " + firstname);
                System.err.println("lastname: " + lastname);
                System.err.println("emain: " + email);
				
				
				// Post API
                
                
                int state = putAPI.put(putUrl, userName, userPassword, employeeID, firstname, lastname, email, street, housNR, plz);
				
				System.out.println("saved pressed: " + state);
				
				if (state == 200) {
					
					
					JOptionPane.showMessageDialog(adminFrame, "Employee successfully updated",
	                		"confirmation", JOptionPane.CLOSED_OPTION);
					
					
					firstNameTextField.setText("");
					lastNameTextField.setText("");
					emailTextField.setText("");
					
					admin_edit_frame.adminFrame.adminEditFrame.dispose();
					
					
					int result = JOptionPane.showConfirmDialog(null, "Reload to see the change",
		            		"confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					
		            if(result == JOptionPane.YES_OPTION){
		            	//If you want to notify your JTable about changes of your data, use
		            	adminFrame.tableModel.fireTableDataChanged();
		        		
						String postUrl = "http://localhost:8080/api/employees";
				        
		                adminFrame.tableModel.setRowCount(0);	// Clear existing data
						
		                getAPI.loadDataFromApi(postUrl, userName, userPassword);
						
						//Notify the table model of the update
						adminFrame.tableModel.fireTableDataChanged();
		             }
		            
				}else if (state == 403) {
					
					admin_edit_frame.adminFrame.adminEditFrame.dispose();
					
					JOptionPane.showMessageDialog(frame, "You are not permited to edit an Employee",
		            		"confirmation", JOptionPane.CLOSED_OPTION);
					
				}
			}
		});
		btnSave.setFont(new Font(".AppleSystemUIFont", Font.BOLD, 11));
		btnSave.setBounds(222, 17, 117, 39);
		panel_1.add(btnSave);
		
		JButton btnCance = new JButton("CANCEL");
		btnCance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				admin_edit_frame.adminFrame.adminEditFrame.dispose();
			}
		});
		btnCance.setFont(new Font(".AppleSystemUIFont", Font.BOLD, 11));
		btnCance.setBounds(351, 17, 117, 39);
		panel_1.add(btnCance);
	}
}
