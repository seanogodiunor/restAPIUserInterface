package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class login_frame extends JFrame {
	
	
	// SETTER
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
	// CONSTRUCTORS
	public login_frame(String userName, String password) throws HeadlessException {
		super();
		this.userName = userName;
		this.password = password;
	}
	
	
	// GETTER
	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	private JPanel contentPane;
	private JPanel panel;
	static JTextField usernametextField;
	static JPasswordField passwordField;
	private JButton loginButton;
	private JButton registerButton;
	private JCheckBox showCheckBox;
	private JLabel nameLabel;
	private JLabel passwordLabel;
	private JLabel errorLabel;
	

	static admin_frame adminFrame  = new admin_frame();
	static http_api.getAPI getAPI = new http_api.getAPI();
	static login_frame loginFrame;
	
	private String userName;
	private String password;
	
	
	
	static String jsonResult;
	LocalTime currentTime;
    DateTimeFormatter formatter;
	
	String urlString = "http://localhost:8080/api/employees";
	private JLabel wrongPasswordLabel;
	
	
	
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginFrame = new login_frame();
					loginFrame.setLocationRelativeTo(null);

					adminFrame.setLocationRelativeTo(null);
					loginFrame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public login_frame() {
		
		setTitle("Loggin");
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 552, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(6, 6, 540, 323);
		contentPane.add(panel);
		panel.setLayout(null);
		
		usernametextField = new JTextField();
		usernametextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				wrongPasswordLabel.setText("");
				
			}
		});
		usernametextField.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		usernametextField.setBounds(146, 50, 251, 39);
		panel.add(usernametextField);
		usernametextField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				wrongPasswordLabel.setText("");

				
			}
		});
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ENTER TO SAVE
				login_frame getUser = new login_frame(
						usernametextField.getText() , 
						new String(passwordField.getPassword()));
				
				
				adminFrame.lblUserName.setText(usernametextField.getText().toUpperCase());
				currentTime = LocalTime.now();
		        formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		        adminFrame.lblTimer.setText(currentTime.format(formatter));
				
		        
				int state = getAPI.loadDataFromApi(urlString, getUser.getUserName(), getUser.getPassword());

				if (state == 2) {
					wrongPasswordLabel.setText("Wrong information, try again");
					
				}else {
					
					adminFrame.setVisible(true);
					loginFrame.setVisible(false);
				}
			}
		});
		passwordField.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		passwordField.setBounds(146, 117, 251, 39);
		panel.add(passwordField);
		
		loginButton = new JButton("LOGIN");
		loginButton.setFont(new Font(".AppleSystemUIFont", Font.BOLD, 11));
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// CONSTRUCTOR
				login_frame getUser = new login_frame(
						usernametextField.getText() , 
						new String(passwordField.getPassword()));
				
				//Display User
				adminFrame.lblUserName.setText(usernametextField.getText().toUpperCase());
				
		        formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		        //adminFrame.lblTimer.setText(currentTime.format(formatter));
		       // System.out.println(currentTime.format(formatter));
				
				int state = getAPI.loadDataFromApi(urlString, getUser.getUserName(), getUser.getPassword());

				if (state == 2) {
					wrongPasswordLabel.setText("Wrong information, try again");
					
				}else {
					
					adminFrame.setVisible(true);
					loginFrame.setVisible(false);
				}
			}
		});
		loginButton.setBounds(130, 237, 117, 39);
		panel.add(loginButton);
		
		registerButton = new JButton("EXIT");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
				
			}
		});
		registerButton.setFont(new Font(".AppleSystemUIFont", Font.BOLD, 11));
		registerButton.setBounds(280, 237, 117, 39);
		panel.add(registerButton);
		
		showCheckBox = new JCheckBox("show password");
		showCheckBox.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
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
		showCheckBox.setBounds(146, 157, 139, 23);
		panel.add(showCheckBox);
		
		nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		nameLabel.setBounds(146, 35, 61, 16);
		panel.add(nameLabel);
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		passwordLabel.setBounds(146, 104, 104, 16);
		panel.add(passwordLabel);
		
		errorLabel = new JLabel("");
		errorLabel.setForeground(UIManager.getColor("Button.select"));
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setFont(new Font(".AppleSystemUIFont", Font.ITALIC, 11));
		errorLabel.setBounds(28, 288, 486, 16);
		panel.add(errorLabel);
		
		wrongPasswordLabel = new JLabel("");
		wrongPasswordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		wrongPasswordLabel.setBounds(6, 199, 528, 23);
		panel.add(wrongPasswordLabel);
		
		
		
		
		
	}
	

}
