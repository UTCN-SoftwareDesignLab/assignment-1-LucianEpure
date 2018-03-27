package services.employee;

import static database.Constants.Roles.REGEMPLOYEE;
import static database.Constants.Roles.ADMINISTRATOR;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import model.Role;
import model.builder.EmployeeBuilder;
import validators.EmployeeValidator;
import repository.security.RightsRolesRepository;
import repository.employee.EmployeeRepository;
import validators.Notification;

public class AuthenticationServiceMySQL implements AuthenticationService {
	
	    private final EmployeeRepository employeeRepository;
	    private final RightsRolesRepository rightsRolesRepository;

	    public AuthenticationServiceMySQL(EmployeeRepository employeeRepository, RightsRolesRepository rightsRolesRepository) {
	        this.employeeRepository = employeeRepository;
	        this.rightsRolesRepository = rightsRolesRepository;
	    }

	@Override
	public Notification<Employee> login(String username, String password) {
		return employeeRepository.findByUsernameAndPassword(username, encodePassword(password));
	}

	@Override
	public boolean logout(Employee employee) {
		return false;
	}
	

	 @Override
	    public Notification<Boolean> registerEmployee(String username, String password) {
		 	List<Role> employeeRoles = new ArrayList<Role> ();
	        Role employeeRole = rightsRolesRepository.findRoleByTitle(REGEMPLOYEE);
	        employeeRoles.add(employeeRole);
	        Employee employee = new EmployeeBuilder()
	                .setUsername(username)
	                .setPassword(password)
	                .setRoles(employeeRoles)
	                .build();

	        EmployeeValidator employeeValidator = new EmployeeValidator(employee);
	        boolean employeeValid = employeeValidator.validate();
	        Notification<Boolean> employeeRegisterNotification = new Notification<>();

	        if (!employeeValid) {
	            employeeValidator.getErrors().forEach(employeeRegisterNotification::addError);
	           employeeRegisterNotification.setResult(Boolean.FALSE);
	            return employeeRegisterNotification;
	        } else {
	            employee.setPassword(encodePassword(password));
	            employeeRegisterNotification.setResult(employeeRepository.saveEmployee(employee));
	            return  employeeRegisterNotification;
	        }
	    }
	 
	 @Override
	    public Notification<Boolean> registerAdministrator(String username, String password) {
	        Role adminRole = rightsRolesRepository.findRoleByTitle(ADMINISTRATOR);
	        List<Role> adminRoles = new ArrayList<Role>();
	        adminRoles.add(adminRole);
	        Employee administrator = new EmployeeBuilder()
	                .setUsername(username)
	                .setPassword(password)
	                .setRoles(adminRoles)
	                .build();

	        EmployeeValidator employeeValidator = new EmployeeValidator(administrator);
	        boolean adminValid = employeeValidator.validate();
	        Notification<Boolean> adminRegisterNotification = new Notification<>();

	        if (!adminValid) {
	            employeeValidator.getErrors().forEach(adminRegisterNotification::addError);
	           adminRegisterNotification.setResult(Boolean.FALSE);
	            return adminRegisterNotification;
	        } else {
	            administrator.setPassword(encodePassword(password));
	            adminRegisterNotification.setResult(employeeRepository.saveAdmin(administrator));
	            return  adminRegisterNotification;
	        }
	    }
	 
	 public static String encodePassword(String password) {
	        try {
	            MessageDigest digest = MessageDigest.getInstance("SHA-256");
	            byte[] hash = digest.digest(password.getBytes("UTF-8"));
	            StringBuilder hexString = new StringBuilder();

	            for (int i = 0; i < hash.length; i++) {
	                String hex = Integer.toHexString(0xff & hash[i]);
	                if (hex.length() == 1) hexString.append('0');
	                hexString.append(hex);
	            }

	            return hexString.toString();
	        } catch (Exception ex) {
	            throw new RuntimeException(ex);
	        }
		}

}
