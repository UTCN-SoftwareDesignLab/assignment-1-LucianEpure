package controller.adminControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Employee;
import services.employee.EmployeeService;
import view.AdministratorMenu;



public class ShowEmployeeController {
	private final AdministratorMenu administratorMenu;
	private final EmployeeService employeeService;
	
	public ShowEmployeeController(AdministratorMenu administratorMenu, EmployeeService employeeService){
		this.administratorMenu = administratorMenu;
		this.employeeService = employeeService;
		administratorMenu.setShowEmployeeButtonListener(new ShowRegEmployeeButtonListener());
	}
	
	class ShowRegEmployeeButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String username = administratorMenu.getShowEmployeeTf().getText();
			Employee employee = employeeService.findByUsername(username);
			administratorMenu.getEmployeesModel().addRow(new Object[] {employee.getRole().getRoleTitle(), employee.getUsername()});
			 administratorMenu.setShowEmployeeTf("");
		}
	}
}
