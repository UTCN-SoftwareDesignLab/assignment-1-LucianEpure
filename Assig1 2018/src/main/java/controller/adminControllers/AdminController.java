package controller.adminControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;


import model.Employee;
import services.employee.AuthenticationService;
import services.employee.EmployeeService;
import validators.Notification;
import view.AdministratorMenu;

public class AdminController {
	private final AdministratorMenu administratorMenu;
	private final EmployeeService employeeService;
	private final AuthenticationService authenticationService;
	
	public AdminController(AdministratorMenu administratorMenu, EmployeeService employeeService,AuthenticationService authenticationService){
		this.administratorMenu = administratorMenu;
		this.employeeService = employeeService;
		this.authenticationService = authenticationService;
		administratorMenu.setUpdateEmployeeButtonListener(new UpdateRegEmployeeButtonListener());
		administratorMenu.setShowEmployeeButtonListener(new ShowRegEmployeeButtonListener());
		administratorMenu.setShowAllButtonListener(new ShowAllButtonListener());
		administratorMenu.setFireEmployeeButtonListener(new FireEmployeeButtonListener());
		administratorMenu.setRegisterRegEmployeeButtonListener(new RegisterRegEmployeeButtonListener());
	}
	
	class UpdateRegEmployeeButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String oldUsername = administratorMenu.getUpdateUsernameTf().getText();
			String newUsername = administratorMenu.getUpdatedUsernameTf().getText();
			String newPassword = administratorMenu.getUpdatedPasswordTf().getText();
			Employee employee = employeeService.findByUsername(oldUsername);
			
			
			Notification<Boolean> updateNotification = employeeService.updateEmployee(employee,newUsername,newPassword);
            if (updateNotification.hasErrors()) {
                JOptionPane.showMessageDialog(administratorMenu.getContentPane(), updateNotification.getFormattedErrors());
            } else {
                JOptionPane.showMessageDialog(administratorMenu.getContentPane(), "Update successful!");
            }
			 administratorMenu.setUpdateUsernameTf("");
			 administratorMenu.setUpdatedUsernameTf("");
			 administratorMenu.setUpdatedPasswordTf("");
			 administratorMenu.getEmployeesModel().setRowCount(0);
		}
	}
	
	class ShowRegEmployeeButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			administratorMenu.getEmployeesModel().setRowCount(0);
			String username = administratorMenu.getShowEmployeeTf().getText();
			Employee employee = employeeService.findByUsername(username);
			administratorMenu.getEmployeesModel().addRow(new Object[] {employee.getRole().getRoleTitle(), employee.getUsername()});
			administratorMenu.setShowEmployeeTf("");
		}
	}
	
	class ShowAllButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			administratorMenu.getEmployeesModel().setRowCount(0);
			List<Employee> employees = employeeService.showAll();
			for(Employee employee:employees)
				administratorMenu.getEmployeesModel().addRow(new Object[] {employee.getRole().getRoleTitle(), employee.getUsername()});
			
		}
	}
	
	class FireEmployeeButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String username = administratorMenu.getFireEmployee();			
			employeeService.fireEmployee(username);
			administratorMenu.setFireEmployee("");
			administratorMenu.getEmployeesModel().setRowCount(0);
		}
		
	}
	
	
	class RegisterRegEmployeeButtonListener implements ActionListener{

		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			 String username = administratorMenu.getUsername();
	         String password = administratorMenu.getPassword();
			
	         Notification<Boolean> registerNotification = authenticationService.registerEmployee(username, password);
	            if (registerNotification.hasErrors()) {
	                JOptionPane.showMessageDialog(administratorMenu.getContentPane(), registerNotification.getFormattedErrors());
	            } else {
	                JOptionPane.showMessageDialog(administratorMenu.getContentPane(), "Register successful!");
	                administratorMenu.setUsernameTf("");
	                administratorMenu.setPasswordTf("");
	            }
		}
		
	}
}
