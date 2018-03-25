package controller.adminControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controller.adminControllers.ShowEmployeeController.ShowRegEmployeeButtonListener;
import model.Employee;
import services.employee.EmployeeService;
import validators.Notification;
import view.AdministratorMenu;

public class UpdateRegEmployeeController {

	private final AdministratorMenu administratorMenu;
	private final EmployeeService employeeService;
	
	public UpdateRegEmployeeController(AdministratorMenu administratorMenu, EmployeeService employeeService){
		this.administratorMenu = administratorMenu;
		this.employeeService = employeeService;
		administratorMenu.setUpdateEmployeeButtonListener(new UpdateRegEmployeeButtonListener());
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
}
