package record;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import database.DBConnectionFactory;
import model.Record;
import repository.account.AccountRepository;
import repository.account.AccountRepositoryMySQL;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySQL;
import repository.employee.EmployeeRepository;
import repository.employee.EmployeeRepositoryMySQL;
import repository.record.RecordRepository;
import repository.record.RecordRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import services.client.ClientService;
import services.client.ClientServiceImplementation;
import services.employee.AuthenticationService;
import services.employee.AuthenticationServiceMySQL;
import services.record.RecordService;
import services.record.RecordServiceImplementation;

public class RecordServiceTest {
	private static AccountRepository accountRepository;
	private static ClientService clientService;
	private static ClientRepository clientRepository;
	private static RecordService recordService;
	private static RecordRepository recordRepository;
	private static EmployeeRepository employeeRepository;
	 private static AuthenticationService authenticationService;
	 
	@BeforeClass
	public static void setup() throws Exception{
		  Connection connection = new DBConnectionFactory().getConnectionWrapper(true).getConnection();
		  accountRepository = new AccountRepositoryMySQL(connection);
		  clientRepository = new ClientRepositoryMySQL(connection);
		  clientService = new ClientServiceImplementation(clientRepository, accountRepository);
		  recordRepository = new RecordRepositoryMySQL(connection);
		  recordService = new RecordServiceImplementation(recordRepository);
		  RightsRolesRepository rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
	      employeeRepository = new EmployeeRepositoryMySQL(connection, rightsRolesRepository);
	      authenticationService = new AuthenticationServiceMySQL(employeeRepository,rightsRolesRepository);
	}
	
	 @Before
	    public void cleanUp() {
	      
	        clientRepository.removeAll();
	        employeeRepository.removeAll();
	        recordRepository.removeAll();
	    }
	 @Before 
	  public void prepareTest(){
		 	clientService.addClient("TestClient", "Livada", "1234567891234", "123456");
		 	clientService.addClient("TestClient2", "Alunas", "1234567761234", "156756");
		 	authenticationService.registerEmployee("lucian@yahoo.com", "aB123456!");
	 }
	 @Test
	 public void recordService(){
		 recordService.setEmployeeId(1L);
		 recordService.addRecord(1L , "Add Client", LocalDate.of(2018, 5, 10));
		 recordService.addRecord(1L , "Update Client", LocalDate.of(2018, 5, 14));
		 recordService.addRecord(2L , "Add Client", LocalDate.of(2018, 6, 4));
		 List<Record> records= recordService.generateRecords(1L, LocalDate.of(2018, 3, 20), LocalDate.of(2018, 5, 20));
		 Assert.assertEquals(records.size(),2);
	 }
}
