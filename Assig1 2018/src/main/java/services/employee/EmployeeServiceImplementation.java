package services.employee;

import java.util.List;

import model.Employee;
import model.builder.EmployeeBuilder;
import repository.employee.EmployeeRepository;
import validators.EmployeeValidator;
import validators.Notification;

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

	@Override
	public Notification<Boolean> updateEmployee(Employee employee,String newUsername, String newPassword) {
		
		Employee newEmployee = new EmployeeBuilder()
                .setUsername(newUsername)
                .setPassword(newPassword)
                .setRoles(employee.getRoles())
                .setId(employee.getId())
                .build();
	
		EmployeeValidator employeeValidator = new EmployeeValidator(newEmployee);
        boolean employeeValid = employeeValidator.validate();
        Notification<Boolean> employeeUpdateNotification = new Notification<>();

        if (!employeeValid) {
            employeeValidator.getErrors().forEach(employeeUpdateNotification::addError);
           employeeUpdateNotification.setResult(Boolean.FALSE);
            return employeeUpdateNotification;
        } else {
            newEmployee.setPassword(AuthenticationServiceMySQL.encodePassword(newPassword));
            employeeUpdateNotification.setResult(employeeRepository.updateEmployee(newEmployee));
            System.out.println(employeeUpdateNotification.getResult());
            return  employeeUpdateNotification;
        }
	}

	
    

}
