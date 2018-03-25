package controller.adminControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import services.employee.EmployeeService;
import view.AdministratorMenu;

public class FireEmployeeController {

	private final AdministratorMenu administratorMenu;
	private final EmployeeService employeeService;
	public FireEmployeeController(AdministratorMenu administratorMenu, EmployeeService employeeService){
		this.administratorMenu = administratorMenu;
		this.employeeService = employeeService;
		administratorMenu.setFireEmployeeButtonListener(new FireEmployeeButtonListener());
	}
	
	private class FireEmployeeButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String username = administratorMenu.getFireEmployee();			
			employeeService.fireEmployee(username);
			administratorMenu.setFireEmployee("");
			administratorMenu.getEmployeesModel().setRowCount(0);
		}
		
	}
}
