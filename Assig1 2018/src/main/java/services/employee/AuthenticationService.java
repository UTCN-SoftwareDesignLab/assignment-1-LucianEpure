package services.employee;

import model.Employee;
import validators.Notification;

public interface AuthenticationService {

	 Notification<Employee> login(String username, String password);
	 
	 Notification<Boolean> registerEmployee(String username, String password);
	 
	 Notification<Boolean> registerAdministrator(String username, String password);

	 boolean logout(Employee employee);
	 
	 Long obtainId(String username);
}
