package controller.adminControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import controller.adminControllers.ShowEmployeeController.ShowRegEmployeeButtonListener;
import model.Employee;
import services.employee.EmployeeService;
import view.AdministratorMenu;

public class ShowAllController {
	private final AdministratorMenu administratorMenu;
	private final EmployeeService employeeService;
	
	public ShowAllController(AdministratorMenu administratorMenu, EmployeeService employeeService){
		this.administratorMenu = administratorMenu;
		this.employeeService = employeeService;
		administratorMenu.setShowAllButtonListener(new ShowAllButtonListener());
	}
	
	class ShowAllButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			List<Employee> employees = employeeService.showAll();
			for(Employee employee:employees)
				administratorMenu.getEmployeesModel().addRow(new Object[] {employee.getRole().getRoleTitle(), employee.getUsername()});
		}
	}
}
