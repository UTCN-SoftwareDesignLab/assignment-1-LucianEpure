package services.employee;

import java.util.List;

import model.Employee;
import validators.Notification;

public interface EmployeeService {

	
	public Employee findByUsername(String username);
	
	public List<Employee> showAll();
	
	public Notification<Boolean> updateEmployee(Employee employee,String newUsername, String newPassword);

	public void fireEmployeeById(Long id);
}
