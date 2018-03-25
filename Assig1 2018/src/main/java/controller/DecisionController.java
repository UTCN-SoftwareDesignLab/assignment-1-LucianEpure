package controller;

import static database.Constants.Roles.ADMINISTRATOR;
import static database.Constants.Roles.REGEMPLOYEE;

import controller.adminControllers.FireEmployeeController;
import controller.adminControllers.RegisterRegEmployeeController;
import controller.adminControllers.ShowAllController;
import controller.adminControllers.ShowEmployeeController;
import model.Employee;
import services.employee.AuthenticationService;
import services.employee.EmployeeService;
import testMain.AdminComponentFactory;
import testMain.ComponentFactory;
import view.AdministratorMenu;
import view.RegEmployeeMenu;

public class DecisionController {

	 
	
	
	 public static void decideWhichView(Employee employee,AuthenticationService authenticationService){
		if(employee.getRole().getRoleTitle().equalsIgnoreCase(ADMINISTRATOR)){
			AdminComponentFactory adminComponentFactory = AdminComponentFactory.instance();
			AdministratorMenu administratorMenu = new AdministratorMenu();
            administratorMenu.setVisible(true);
			new RegisterRegEmployeeController(administratorMenu,authenticationService);
			new FireEmployeeController(administratorMenu,adminComponentFactory.getEmployeeService());
			new ShowEmployeeController(administratorMenu,adminComponentFactory.getEmployeeService());
			new ShowAllController(administratorMenu, adminComponentFactory.getEmployeeService());
		}
		else if(employee.getRole().getRoleTitle().equalsIgnoreCase(REGEMPLOYEE)){
			RegEmployeeMenu regEmployeeMenu = new RegEmployeeMenu();
            regEmployeeMenu.setVisible(true);
			//new RegisterRegEmployeeController(regEmployeeMenu,authenticationService);
		}
	}
}
