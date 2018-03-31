package testMain;
import database.DBConnectionFactory;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.account.AccountRepository;
import repository.account.AccountRepositoryMySQL;
import repository.bill.BillRepository;
import repository.bill.BillRepositoryMySQL;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySQL;
import repository.employee.EmployeeRepository;
import repository.employee.EmployeeRepositoryMySQL;
import repository.record.RecordRepository;
import repository.record.RecordRepositoryMySQL;
import services.account.AccountOperations;
import services.account.AccountOperationsImplementation;
import services.account.AccountService;
import services.account.AccountServiceImplementation;
import services.client.ClientService;
import services.client.ClientServiceImplementation;
import services.employee.AuthenticationService;
import services.employee.AuthenticationServiceMySQL;
import services.employee.EmployeeService;
import services.employee.EmployeeServiceImplementation;
import services.record.RecordService;
import services.record.RecordServiceImplementation;

import java.sql.Connection;

/**
 * Created by Alex on 18/03/2017.
 */
public class ComponentFactory {

    private final AuthenticationService authenticationService;
    private final BillRepository billRepository;
   private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;
    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;
    private final RightsRolesRepository rightsRolesRepository;
    private final RecordRepository recordRepository;
    private final ClientService clientService;
	 private final AccountService accountService;
	 private final AccountOperations accountOperations;
	 private final RecordService recordService;

    private static ComponentFactory instance;

    public static ComponentFactory instance() {
        if (instance == null) {
            instance = new ComponentFactory();
        }
        return instance;
    }

    private ComponentFactory() {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(false).getConnection();
       
        this.rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        this.accountRepository = new AccountRepositoryMySQL(connection);
        this.billRepository = new BillRepositoryMySQL(connection);
        this.employeeRepository = new EmployeeRepositoryMySQL(connection, rightsRolesRepository);
        this.authenticationService = new AuthenticationServiceMySQL(this.employeeRepository, this.rightsRolesRepository);
        this.clientRepository = new ClientRepositoryMySQL(connection);
        this.recordRepository = new RecordRepositoryMySQL(connection);
        this.employeeService = new EmployeeServiceImplementation(this.employeeRepository,this.rightsRolesRepository);
        this.recordService = new RecordServiceImplementation(this.recordRepository);
        this.clientService = new ClientServiceImplementation(this.clientRepository, this.accountRepository);
        this.accountService = new AccountServiceImplementation(this.accountRepository);
        this.accountOperations = new AccountOperationsImplementation(this.accountRepository,this.billRepository);
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public EmployeeRepository getUserRepository() {
        return employeeRepository;
    }

    public RightsRolesRepository getRightsRolesRepository() {
        return rightsRolesRepository;
    }
    
   
	public EmployeeService getEmployeeService() {
		return employeeService;
		
	}
	public ClientService getClientService() {
		return clientService;
	}
	public AccountService getAccountService() {
		return accountService;
	}
	public AccountOperations getAccountOperations() {
		return accountOperations;
	}
	public RecordService getRecordService(){
		return recordService;
	}
	 public BillRepository getBillRepository() {
			return billRepository;
		}
		

}
