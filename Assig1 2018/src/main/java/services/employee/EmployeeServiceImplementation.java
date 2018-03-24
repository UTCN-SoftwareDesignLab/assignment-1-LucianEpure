package services.employee;

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
    

}
