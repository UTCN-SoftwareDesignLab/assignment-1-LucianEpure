package controller;

import static database.Constants.Roles.ADMINISTRATOR;
import static database.Constants.Roles.REGEMPLOYEE;

import controller.adminControllers.AdminController;
import controller.regEmployeeControllers.RegEmployeeController;
import controller.regEmployeeControllers.TransitionControllers.TransitionController;
import model.Employee;
import services.employee.AuthenticationService;
import services.employee.EmployeeService;
import testMain.AdminComponentFactory;
import testMain.ComponentFactory;
import testMain.RegEmployeeComponentFactory;
import view.AdministratorMenu;
import view.RegEmployeeMenu;

public class DecisionController {

	 
	
	
	 public static void decideWhichView(Employee employee,AuthenticationService authenticationService){
		if(employee.getRole().getRoleTitle().equalsIgnoreCase(ADMINISTRATOR)){
			AdminComponentFactory adminComponentFactory = AdminComponentFactory.instance();
			AdministratorMenu administratorMenu = new AdministratorMenu();
            administratorMenu.setVisible(true);
			new AdminController(administratorMenu,adminComponentFactory.getEmployeeService(),authenticationService);
		}
		else if(employee.getRole().getRoleTitle().equalsIgnoreCase(REGEMPLOYEE)){
			RegEmployeeComponentFactory regEmployeeComponentFactory = RegEmployeeComponentFactory.instance();
			RegEmployeeMenu regEmployeeMenu = new RegEmployeeMenu();
            regEmployeeMenu.setVisible(true);
            new TransitionController(regEmployeeMenu, regEmployeeComponentFactory);
            new RegEmployeeController(regEmployeeMenu, regEmployeeComponentFactory.getClientService());
			//new RegisterRegEmployeeController(regEmployeeMenu,authenticationService);
		}
	}
}
