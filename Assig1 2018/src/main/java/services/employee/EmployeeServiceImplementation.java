package services.employee;

import java.util.List;

import model.Employee;
import repository.employee.EmployeeRepository;

public class EmployeeServiceImplementation implements EmployeeService{

	private final EmployeeRepository employeeRepository;

    public EmployeeServiceImplementation(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

	@Override
	public void fireEmployee(String username) {
		employeeRepository.removeRegEmployee(username);
	}

	@Override
	public Employee findByUsername(String username) {
		return employeeRepository.findByUsername(username).getResult();
	}

	@Override
	public List<Employee> showAll() {
		return employeeRepository.findAll();
	}
    

}
