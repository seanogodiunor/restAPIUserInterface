package ui;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class admin_frame extends JFrame {

	public JTable table;	
	public JLabel ResultLabel;
	public JLabel lblUserName;
	public JLabel lblTime;
	
	public JLabel lblTimer;
	
	public static DefaultTableModel tableModel;

	static admin_frame frame = new admin_frame();
	static manager_add_frame managerAddFrame = new manager_add_frame();
	static login_frame loginFrame = new login_frame();
	static admin_edit_frame adminEditFrame = new admin_edit_frame();
	confirmPassword_frame confirmPasswordFrame = new confirmPassword_frame();

	static http_api.getAPI getAPI = new http_api.getAPI();

	
	
	static String jsonResult;
	
	

	public admin_frame() {
		
		setTitle("Admin Section");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

        // Initialize the table model with column names
        String[] columnNames = {"id", "First Name", "Last Name", "E-Mail", "Street", "House Nr.", "PLZ"};
        tableModel = new DefaultTableModel(columnNames, 0);
        getContentPane().setLayout(null);
        
        JPanel framePanel = new JPanel();
        framePanel.setBounds(6, 22, 1000, 573);
        getContentPane().add(framePanel);
        framePanel.setLayout(null);
        
        JPanel tablePanel = new JPanel();
        tablePanel.setBounds(6, 6, 988, 458);
        framePanel.add(tablePanel);
        tablePanel.setLayout(null);
        
        
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Table cell NOT editable
        table.setDefaultEditor(Object.class, null);
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModel.addListSelectionListener(new RowListener(this));
        
        
        
        
        // Add the JTable to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(6, 5, 976, 447);
        tablePanel.add(scrollPane);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(576, 515, 418, 52);
        framePanel.add(buttonPanel);
        buttonPanel.setLayout(null);
        
        JButton btnEdit = new JButton("EDIT");
        btnEdit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		
        		if (table.getSelectionModel().isSelectionEmpty()){
        			
        		      JOptionPane.showMessageDialog(frame, "No Employee selected",
      	            		"Select Employee", JOptionPane.CLOSED_OPTION);
        			
				}else {
        			// show frame if something in the table in selected
        			adminEditFrame.setVisible(true);

            		RowListener result = new RowListener();
            		String[] arrOfStr = result.re.split(":");

            		adminEditFrame.firstNameTextField.setText(arrOfStr[1]);
            		adminEditFrame.lastNameTextField.setText(arrOfStr[2]);
            		adminEditFrame.emailTextField.setText(arrOfStr[3]);
            		
            		adminEditFrame.streetTextField.setText(arrOfStr[4]);
            		adminEditFrame.houseNRtextField.setText(arrOfStr[5]);
            		adminEditFrame.plzTextField.setText(arrOfStr[6]);
            		
            		
            		
				}
        	}
        });
        btnEdit.setFont(new Font(".AppleSystemUIFont", Font.BOLD, 11));
        btnEdit.setBounds(169, 6, 117, 39);
        buttonPanel.add(btnEdit);
        
        JButton btnNewButton = new JButton("ADD");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		//	center thr childs frame in perents frame
        		 int parentX = frame.getX();
                 int parentY = frame.getY();
                 int parentWidth = frame.getWidth();
                 int parentHeight = frame.getHeight();

                 int childWidth = managerAddFrame.getWidth();
                 int childHeight = managerAddFrame.getHeight();

                 int childX = parentX + (parentWidth - childWidth) / 2;
                 int childY = parentY + (parentHeight - childHeight) / 2;
         		
                 managerAddFrame.setLocation(childX, childY);
                 managerAddFrame.setVisible(true);

                 
        	}
        });
        btnNewButton.setFont(new Font(".AppleSystemUIFont", Font.BOLD, 11));
        btnNewButton.setBounds(298, 6, 117, 39);
        buttonPanel.add(btnNewButton);
        
        JButton btnDelete = new JButton("DELETE");
        btnDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if (table.getSelectionModel().isSelectionEmpty()){
        			
        			 JOptionPane.showMessageDialog(frame, "No Employee selected",
       	            		"Select Employee", JOptionPane.CLOSED_OPTION);
        			
				}else {
					
        			// show frame if something in the table in selected
	        		confirmPassword_frame.frame.setVisible(true);
				}
        		
        	}
        });
        btnDelete.setFont(new Font(".AppleSystemUIFont", Font.BOLD, 11));
        btnDelete.setBounds(36, 6, 117, 39);
        buttonPanel.add(btnDelete);
        
        ResultLabel = new JLabel("");
        ResultLabel.setBounds(16, 470, 889, 33);
        framePanel.add(ResultLabel);
        
        JButton btnNewButton_1 = new JButton("reload");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		//If you want to notify your JTable about changes of your data, use
        		tableModel.fireTableDataChanged();
        		
				String postUrl = "http://localhost:8080/api/employees";
				String userName = loginFrame.usernametextField.getText();
                String userPassword = new String(loginFrame.passwordField.getPassword());
		        
				tableModel.setRowCount(0);	// Clear existing data
				
				getAPI.loadDataFromApi(postUrl, userName, userPassword);
				
				//Notify the table model of the update
		        tableModel.fireTableDataChanged();
				
        	}
        });
        btnNewButton_1.setBounds(922, 463, 72, 40);
        framePanel.add(btnNewButton_1);
        
        JLabel lbluser = new JLabel("User: ");
        lbluser.setFont(new Font(".AppleSystemUIFont", Font.BOLD, 11));
        lbluser.setBounds(18, 6, 44, 16);
        getContentPane().add(lbluser);
        
        lblUserName = new JLabel("");
        lblUserName.setFont(new Font(".AppleSystemUIFont", Font.BOLD, 11));
        lblUserName.setBounds(74, 6, 222, 16);
        getContentPane().add(lblUserName);
        
        lblTime = new JLabel("Time: ");
        lblTime.setFont(new Font(".AppleSystemUIFont", Font.BOLD, 11));
        lblTime.setBounds(814, 6, 167, 16);
        getContentPane().add(lblTime);
        
        lblTimer = new JLabel("00:00");
        lblTimer.setFont(new Font(".AppleSystemUIFont", Font.BOLD, 11));
        lblTimer.setBounds(887, 6, 94, 16);
        getContentPane().add(lblTimer);

        setSize(1012, 629);

	}
}



class RowListener implements ListSelectionListener {
	
	admin_frame readRow;
    JTable table;
    static String re;
  
    
    public RowListener() {
    }
    
    
    public RowListener(admin_frame rar) {
        readRow = rar;
        table = readRow.table;
        
    }
  
    public void valueChanged(ListSelectionEvent e) {
    	
        if(!e.getValueIsAdjusting()) {
            ListSelectionModel model = table.getSelectionModel();
            int lead = model.getLeadSelectionIndex();
            
            re = displayRowValues(lead);
            readRow.ResultLabel.setText(re);
            
        }
    }
  
    private String displayRowValues(int rowIndex) {
    	
        int columns = table.getColumnCount();
        
        System.out.println("columns sount: " + columns);
        String s = "";
       
        for(int col = 0; col < columns; col++) {
        	
            System.out.println("Col count: " + col);

        	
            Object o = table.getValueAt(rowIndex, col);
            
            System.out.println("Object count: " + o);

            
            
            s += o.toString();
            if(col < columns - 1) {
            	s += " : ";
            	
                System.out.println("Object count: " + o);

            }
        }
		return s;
    }
}
