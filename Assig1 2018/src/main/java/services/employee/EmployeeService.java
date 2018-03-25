package services.employee;

import java.util.List;

import model.Employee;

public interface EmployeeService {

	public void fireEmployee(String username);
	
	public Employee findByUsername(String username);
	
	public List<Employee> showAll();
}
