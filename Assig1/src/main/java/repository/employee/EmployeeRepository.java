package repository.employee;

import java.util.List;

import model.Employee;
import validators.Notification;

public interface EmployeeRepository {

	 	List<Employee> findAll();
	 	
	 	Notification<Employee> findByUsername(String username);

	    Notification<Employee> findByUsernameAndPassword(String username, String password);

	    boolean saveAdmin(Employee admin);
	    
	    boolean saveEmployee(Employee employee);
	   
	    boolean updateEmployee(Employee employee);
	    
	    
	    void removeAll();
	    
		void removeRegEmployeeById(Long id);

		Notification<Employee> findRegEmployeeById(Long id);
}
