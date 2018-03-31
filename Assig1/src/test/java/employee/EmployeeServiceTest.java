package employee;

import java.sql.Connection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import database.DBConnectionFactory;
import model.Employee;
import repository.employee.EmployeeRepository;
import repository.employee.EmployeeRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import services.employee.AuthenticationService;
import services.employee.AuthenticationServiceMySQL;
import services.employee.EmployeeService;
import services.employee.EmployeeServiceImplementation;

public class EmployeeServiceTest {
	 public static final String TEST_USERNAME = "test@gmail.com";
	 public static final String TEST_PASSWORD = "TestPassword1!";
	 public static final String TEST_USERNAME_UPDATED = "testUdated@gmail.com";
	 public static final String TEST_PASSWORD_UPDATED = "TestPasswordUpdated1!";

	 private static AuthenticationService authenticationService;
	 private static EmployeeRepository employeeRepository;
	 private static EmployeeService employeeService;
	 
	 @BeforeClass
	    public static void setUp() {
	        Connection connection = new DBConnectionFactory().getConnectionWrapper(true).getConnection();
	        RightsRolesRepository rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
	        employeeRepository = new EmployeeRepositoryMySQL(connection, rightsRolesRepository);
	        employeeService = new EmployeeServiceImplementation(employeeRepository, rightsRolesRepository);
	        authenticationService = new AuthenticationServiceMySQL(employeeRepository,rightsRolesRepository);        
	}
	 
	 	@Before
	    public void cleanUp() {
	        employeeRepository.removeAll();
	    }


	    @Test
	    public void register() throws Exception {
	        Assert.assertTrue(
	                authenticationService.registerEmployee(TEST_USERNAME, TEST_PASSWORD).getResult()
	        );
	    }
	    @Test
	    public void findAll() throws Exception{
	    	List<Employee> employees = employeeService.showAll();
	    	Assert.assertEquals(employees.size(),0);
	    }

	    @Test
	    public void login() throws Exception {
	        authenticationService.registerEmployee(TEST_USERNAME, TEST_PASSWORD).getResult();
	        Employee employee = authenticationService.login(TEST_USERNAME, TEST_PASSWORD).getResult();
	        Assert.assertNotNull(employee);
	    }
	    
	    @Test
	    public void findByUsername() throws Exception{
	    	authenticationService.registerEmployee(TEST_USERNAME, TEST_PASSWORD).getResult();
	    	Employee employee = employeeService.findByUsername(TEST_USERNAME);
	    	Assert.assertNotNull(employee);    	
	    }
	    
	    @Test
	    public void findById() throws Exception{
	    	authenticationService.registerEmployee(TEST_USERNAME, TEST_PASSWORD).getResult();
	    	Employee employee = authenticationService.login(TEST_USERNAME, TEST_PASSWORD).getResult();
	    	Employee foundEmployee = employeeService.findById(employee.getId()).getResult();
	    	Assert.assertNotNull( foundEmployee);    	
	    }
	    
	    @Test
	    public void update() throws Exception {
	    	authenticationService.registerEmployee(TEST_USERNAME, TEST_PASSWORD);
	    	Employee employee = employeeService.findByUsername(TEST_USERNAME);
	    	employeeService.updateEmployee(employee, TEST_USERNAME_UPDATED, TEST_PASSWORD_UPDATED);
	    	Employee updatedEmployee = employeeService.findByUsername(TEST_USERNAME_UPDATED);
	    	Assert.assertEquals(employee.getId(), updatedEmployee.getId());
	    }
	    
	    @Test
	    public void remove() throws Exception {
	    	authenticationService.registerEmployee(TEST_USERNAME, TEST_PASSWORD);
	    	Employee employee = employeeService.findByUsername(TEST_USERNAME);
	    	List<Employee> employeesBefore = employeeService.showAll();
	    	employeeService.fireEmployeeById(employee.getId());
	    	List<Employee> employeesAfter = employeeService.showAll();
	    	Assert.assertEquals(employeesBefore.size(),employeesAfter.size()+1);
	    }
}
