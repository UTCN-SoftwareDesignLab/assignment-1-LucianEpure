package view;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTable;

public class AdministratorMenu extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTf;
	private JTextField passwordTf;
	
	private JTextField showEmployeeTf;
	
	private JTextField fireEmployeeTf;

	private JTextField updateUsernameTf;
	private JTextField updatedUsernameTf;
	private JTextField updatedPasswordTf;
	
	private JButton registerEmployeeBtn;
	private JButton fireEmployeeBtn;
	private JButton showEmployeeBtn;
	private JButton showAllBtn;
	private JButton updateEmployeeBtn;
	private JTable employees;
	private JTable records;

	private final DefaultTableModel employeesModel;
	private final DefaultTableModel recordsModel;

	private JScrollPane employeesH;
	private JScrollPane recordsH;
	private JTextField idTf;
	private JLabel lblStartDate;
	private JLabel lblEndDate;
	private JTextField startDateTf;

	private JTextField endDateTf;
	private JButton generateRecordsBtn;

	
	public AdministratorMenu() {
		setTitle("Administrator menu");
		setBounds(100, 100, 765, 571);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usernameTf = new JTextField();
		usernameTf.setBounds(93, 10, 208, 20);
		contentPane.add(usernameTf);
		usernameTf.setColumns(10);
		
		passwordTf = new JTextField();
		passwordTf.setBounds(93, 41, 208, 20);
		contentPane.add(passwordTf);
		passwordTf.setColumns(10);
		
		registerEmployeeBtn = new JButton("Register \r\nEmployee");
		registerEmployeeBtn.setBounds(311, 9, 121, 52);
		contentPane.add(registerEmployeeBtn);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(25, 13, 70, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(25, 42, 70, 14);
		contentPane.add(lblPassword);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(25, 72, 298, 14);
		contentPane.add(separator);
		
		JLabel lblUsername_1 = new JLabel("Username");
		lblUsername_1.setBounds(25, 91, 70, 14);
		contentPane.add(lblUsername_1);
		
		showEmployeeTf = new JTextField();
		showEmployeeTf.setBounds(93, 88, 208, 20);
		contentPane.add(showEmployeeTf);
		showEmployeeTf.setColumns(10);
		
		showEmployeeBtn = new JButton("Show");
		showEmployeeBtn.setBounds(311, 87, 121, 23);
		contentPane.add(showEmployeeBtn);
		
		JLabel lblUsername_2 = new JLabel("Username");
		lblUsername_2.setBounds(25, 141, 70, 14);
		contentPane.add(lblUsername_2);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(25, 122, 298, 8);
		contentPane.add(separator_1);
		
		fireEmployeeTf = new JTextField();
		fireEmployeeTf.setBounds(93, 138, 112, 20);
		contentPane.add(fireEmployeeTf);
		fireEmployeeTf.setColumns(10);
		
		fireEmployeeBtn = new JButton("Fire employee");
		fireEmployeeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		fireEmployeeBtn.setBounds(311, 137, 121, 23);
		contentPane.add(fireEmployeeBtn);
		
		JLabel lblUsername_3 = new JLabel("Old User");
		lblUsername_3.setBounds(25, 192, 70, 14);
		contentPane.add(lblUsername_3);
		
		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setBounds(25, 247, 70, 14);
		contentPane.add(lblPassword_1);
		
		updateUsernameTf = new JTextField();
		updateUsernameTf.setColumns(10);
		updateUsernameTf.setBounds(93, 189, 208, 20);
		contentPane.add(updateUsernameTf);
		
		updatedPasswordTf = new JTextField();
		updatedPasswordTf.setColumns(10);
		updatedPasswordTf.setBounds(93, 244, 208, 20);
		contentPane.add(updatedPasswordTf);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(25, 179, 294, 2);
		contentPane.add(separator_2);
		
		updateEmployeeBtn = new JButton("Update");
		updateEmployeeBtn.setBounds(311, 189, 121, 73);
		contentPane.add(updateEmployeeBtn);
		
		Object[] employeeColumn = { "id", "Type","Username"};
			
		employeesModel = new DefaultTableModel(employeeColumn, 0);
		employees = new JTable(employeesModel);
		employees.setBounds(231, 15, 388, 246);
		
		employeesH = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		employeesH.setViewportView(employees);
		employeesH.setBounds(442, 6, 281, 268);
		contentPane.add(employeesH);
		
      
		
		Object[] recordsColumn = { "Employee", "Client","Name","Date"};
		
		recordsModel = new DefaultTableModel(recordsColumn, 0);
		records = new JTable(recordsModel);
		records.setBounds(231, 15, 388, 246);
		
		recordsH = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		recordsH.setViewportView(records);
		recordsH.setBounds(25, 346, 698, 175);
		contentPane.add(recordsH);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(25, 272, 407, 2);
		contentPane.add(separator_3);
		
		showAllBtn = new JButton("Show all");
		showAllBtn.setBounds(311, 285, 121, 23);
		contentPane.add(showAllBtn);
		
		JLabel lbl_Username4 = new JLabel("New User");
		lbl_Username4.setBounds(25, 217, 70, 14);
		contentPane.add(lbl_Username4);
		
		updatedUsernameTf = new JTextField();
		updatedUsernameTf.setBounds(93, 216, 208, 20);
		contentPane.add(updatedUsernameTf);
		updatedUsernameTf.setColumns(10);
		
		idTf = new JTextField();
		idTf.setBounds(255, 138, 46, 20);
		contentPane.add(idTf);
		idTf.setColumns(10);
		
		JLabel lblId = new JLabel("id");
		lblId.setBounds(215, 141, 30, 14);
		contentPane.add(lblId);
		
		lblStartDate = new JLabel("Start Date");
		lblStartDate.setBounds(25, 289, 70, 14);
		contentPane.add(lblStartDate);
		
		lblEndDate = new JLabel("End Date");
		lblEndDate.setBounds(25, 314, 70, 14);
		contentPane.add(lblEndDate);
		
		startDateTf = new JTextField();
		startDateTf.setBounds(93, 288, 86, 20);
		contentPane.add(startDateTf);
		startDateTf.setColumns(10);
		
		endDateTf = new JTextField();
		endDateTf.setBounds(93, 315, 86, 20);
		contentPane.add(endDateTf);
		endDateTf.setColumns(10);
		
		generateRecordsBtn = new JButton("Reports");
		generateRecordsBtn.setBounds(201, 285, 89, 23);
		contentPane.add(generateRecordsBtn);
		
	}

	//GEtters and Setters .................................................................................................
	public String getUsername(){
		return usernameTf.getText();
	}
	public String getPassword(){
		return passwordTf.getText();
	}
	public String getFireEmployee(){
		return fireEmployeeTf.getText();
	}
	public void setFireEmployee(String text){
		fireEmployeeTf.setText(text);
	}
	public JTextField getUsernameTf() {
		return usernameTf;
	}
	public void setUsernameTf(String text){
		usernameTf.setText(text);
	}

	public JTextField getPasswordTf() {
		return passwordTf;
	}
	public void setPasswordTf(String text){
		passwordTf.setText(text);
	}
	
	public JTextField getShowEmployeeTf() {
		return showEmployeeTf;
	}


	public void setShowEmployeeTf(String text) {
		showEmployeeTf.setText(text);
	}


	public DefaultTableModel getEmployeesModel() {
		return employeesModel;
	}
	
	
	public JTextField getUpdateUsernameTf() {
		return updateUsernameTf;
	}


	public void setUpdateUsernameTf(String text) {
		updateUsernameTf.setText(text);
	}


	public JTextField getUpdatedPasswordTf() {
		return updatedPasswordTf;
	}


	public void setUpdatedPasswordTf(String text) {
		updatedPasswordTf.setText(text);
	}
	
	public JTextField getUpdatedUsernameTf() {
		return updatedUsernameTf;
	}

	public void setUpdatedUsernameTf(String text) {
		updatedUsernameTf.setText(text);
	}

	public JTable getEmployees() {
		return employees;
	}
	public JTable getRecords() {
		return records;
	}
	
	public DefaultTableModel getRecordsModel() {
		return recordsModel;
	}


	public void setEmployees(JTable employees) {
		this.employees = employees;
	}
	
	public JTextField getIdTf() {
		return idTf;
	}

	public void setIdTf(String text) {
		idTf.setText(text);
	}
	
	public JTextField getStartDateTf() {
		return startDateTf;
	}

	public JTextField getEndDateTf() {
		return endDateTf;
	}
	public void setStartDateTf(String text) {
		this.startDateTf.setText(text);
	}

	public void setEndDateTf(String text) {
		this.endDateTf.setText(text);
	}

	

	//GEtters and Setters Done.................................................................................................
	//Controllers Setters......................................................................................................
	  public void setRegisterRegEmployeeButtonListener(ActionListener registerRegEmployeeButtonListener) {
	        registerEmployeeBtn.addActionListener(registerRegEmployeeButtonListener);
	    }
	  public void setFireEmployeeButtonListener(ActionListener fireEmployeeButtonListener) {
	       fireEmployeeBtn.addActionListener(fireEmployeeButtonListener);
	    }
	
	  public void setShowEmployeeButtonListener(ActionListener showEmployeeButtonListener){
		  showEmployeeBtn.addActionListener(showEmployeeButtonListener);
	  }
	  
	  public void setShowAllButtonListener(ActionListener showAllButtonListener){
		  showAllBtn.addActionListener(showAllButtonListener);
	  }
	  public void setUpdateEmployeeButtonListener(ActionListener updateRegEmployeeListener){
		  updateEmployeeBtn.addActionListener(updateRegEmployeeListener);
	  }
	  public void setTableListener(MouseListener tableListener){
		  employees.addMouseListener(tableListener);
	  }
	  
	  public void setGenerateRecordsButtonListener(ActionListener generateRecordsListener){
		  generateRecordsBtn.addActionListener(generateRecordsListener);
	  }
}
