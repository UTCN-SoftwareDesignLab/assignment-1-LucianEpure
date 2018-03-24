package testMain;

import services.employee.EmployeeService;
import services.employee.EmployeeServiceImplementation;

public class AdminComponentFactory {

	 private final EmployeeService employeeService;
	 private static AdminComponentFactory instance;
	
	 
	public static AdminComponentFactory instance() {
        if (instance == null) {
            instance = new AdminComponentFactory();
        }
        return instance;
    }
	
	private AdminComponentFactory() {  
        this.employeeService = new EmployeeServiceImplementation(ComponentFactory.instance().getUserRepository());
    }
	public EmployeeService getEmployeeService() {
		return employeeService;
	}
}
