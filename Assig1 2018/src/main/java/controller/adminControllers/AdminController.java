package controller.adminControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
	private int selectedRow = 0;
	private int selectedCol = 3;

	public AdminController(AdministratorMenu administratorMenu, EmployeeService employeeService,AuthenticationService authenticationService){
		this.administratorMenu = administratorMenu;
		this.employeeService = employeeService;
		this.authenticationService = authenticationService;
		administratorMenu.setUpdateEmployeeButtonListener(new UpdateRegEmployeeButtonListener());
		administratorMenu.setShowEmployeeButtonListener(new ShowRegEmployeeButtonListener());
		administratorMenu.setShowAllButtonListener(new ShowAllButtonListener());
		administratorMenu.setFireEmployeeButtonListener(new FireEmployeeButtonListener());
		administratorMenu.setRegisterRegEmployeeButtonListener(new RegisterRegEmployeeButtonListener());
		administratorMenu.setTableListener(new TableListenerEmployees());
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
			administratorMenu.getEmployeesModel().addRow(new Object[] {employee.getId(), employee.getRoles().get(0).getRoleTitle(), employee.getUsername()});
			administratorMenu.setShowEmployeeTf("");
		}
	}
	
	class ShowAllButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			administratorMenu.getEmployeesModel().setRowCount(0);
			List<Employee> employees = employeeService.showAll();
			for(Employee employee:employees)
				administratorMenu.getEmployeesModel().addRow(new Object[] {employee.getId(),employee.getRoles().get(0).getRoleTitle(), employee.getUsername()});
			
		}
	}
	
	class FireEmployeeButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String username = administratorMenu.getFireEmployee();	
			String id = administratorMenu.getIdTf().getText();
			employeeService.fireEmployeeById(Long.parseLong(id));
			administratorMenu.setIdTf("");
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
	class TableListenerEmployees implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
				setSelectedRow(administratorMenu.getEmployees().rowAtPoint(e.getPoint()));
				setSelectedCol(administratorMenu.getEmployees().columnAtPoint(e.getPoint()));
				administratorMenu.setFireEmployee(administratorMenu.getEmployees().getValueAt(selectedRow, 2).toString());
				administratorMenu.setIdTf(administratorMenu.getEmployees().getValueAt(selectedRow, 0).toString());
		}
		
		@Override
		public void mousePressed(MouseEvent e) {	
		}
		@Override
		public void mouseReleased(MouseEvent e) {
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
	
	
	
	public int getSelectedRow() {
		return selectedRow;
	}
	public void setSelectedRow(int selectedRow) {
		this.selectedRow = selectedRow;
	}
	public int getSelectedCol() {
		return selectedCol;
	}
	public void setSelectedCol(int selectedCol) {
		this.selectedCol = selectedCol;
	}
}
