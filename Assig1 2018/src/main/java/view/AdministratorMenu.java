package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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
	private JTextField deleteUsernameTf;
	private JTextField deletePasswordTf;
	private JButton registerEmployeeBtn;
	private JButton fireEmployeeBtn;
	private JButton showEmployeeBtn;
	private JButton showAllBtn;
	private JTable employees;
	private final DefaultTableModel employeesModel;
	private JScrollPane employeesH;
	



	public AdministratorMenu() {
		setTitle("Administrator menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 644, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usernameTf = new JTextField();
		usernameTf.setBounds(93, 10, 86, 20);
		contentPane.add(usernameTf);
		usernameTf.setColumns(10);
		
		passwordTf = new JTextField();
		passwordTf.setBounds(93, 41, 86, 20);
		contentPane.add(passwordTf);
		passwordTf.setColumns(10);
		
		registerEmployeeBtn = new JButton("Register \r\nEmployee");
		registerEmployeeBtn.setBounds(202, 10, 121, 52);
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
		showEmployeeTf.setBounds(93, 88, 86, 20);
		contentPane.add(showEmployeeTf);
		showEmployeeTf.setColumns(10);
		
		showEmployeeBtn = new JButton("Show");
		showEmployeeBtn.setBounds(202, 87, 121, 23);
		contentPane.add(showEmployeeBtn);
		
		JLabel lblUsername_2 = new JLabel("Username");
		lblUsername_2.setBounds(25, 141, 70, 14);
		contentPane.add(lblUsername_2);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(25, 122, 298, 8);
		contentPane.add(separator_1);
		
		fireEmployeeTf = new JTextField();
		fireEmployeeTf.setBounds(93, 138, 86, 20);
		contentPane.add(fireEmployeeTf);
		fireEmployeeTf.setColumns(10);
		
		fireEmployeeBtn = new JButton("Fire employee");
		fireEmployeeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		fireEmployeeBtn.setBounds(202, 137, 121, 23);
		contentPane.add(fireEmployeeBtn);
		
		JLabel lblUsername_3 = new JLabel("Username");
		lblUsername_3.setBounds(25, 192, 70, 14);
		contentPane.add(lblUsername_3);
		
		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setBounds(25, 221, 70, 14);
		contentPane.add(lblPassword_1);
		
		deleteUsernameTf = new JTextField();
		deleteUsernameTf.setColumns(10);
		deleteUsernameTf.setBounds(93, 189, 86, 20);
		contentPane.add(deleteUsernameTf);
		
		deletePasswordTf = new JTextField();
		deletePasswordTf.setColumns(10);
		deletePasswordTf.setBounds(93, 218, 86, 20);
		contentPane.add(deletePasswordTf);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(25, 179, 294, 2);
		contentPane.add(separator_2);
		
		JButton updateBtn = new JButton("Update");
		updateBtn.setBounds(202, 188, 121, 47);
		contentPane.add(updateBtn);
		
		Object[] employeeColumn = {  "Type","Username"};
		
		employeesModel = new DefaultTableModel(employeeColumn, 0);
		employees = new JTable(employeesModel);
		employees.setBounds(231, 15, 388, 246);
		
		employeesH = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		employeesH.setViewportView(employees);
		employeesH.setBounds(337, 6, 281, 229);
		contentPane.add(employeesH);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(25, 246, 298, 2);
		contentPane.add(separator_3);
		
		showAllBtn = new JButton("Show all");
		showAllBtn.setBounds(25, 260, 89, 23);
		contentPane.add(showAllBtn);
		
	}

	
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

	
	  public void setRegisterRegEmployeeButtonListener(ActionListener RegisterRegEmployeeButtonListener) {
	        registerEmployeeBtn.addActionListener(RegisterRegEmployeeButtonListener);
	    }
	  public void setFireEmployeeButtonListener(ActionListener FireEmployeeButtonListener) {
	       fireEmployeeBtn.addActionListener(FireEmployeeButtonListener);
	    }
	
	  public void setShowEmployeeButtonListener(ActionListener ShowEmployeeButtonListener){
		  showEmployeeBtn.addActionListener(ShowEmployeeButtonListener);
	  }
	  
	  public void setShowAllButtonListener(ActionListener ShowAllButtonListener){
		  showAllBtn.addActionListener(ShowAllButtonListener);
	  }
}
