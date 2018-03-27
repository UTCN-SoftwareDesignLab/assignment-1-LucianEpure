package testMain;

import services.account.AccountService;
import services.account.AccountServiceImplementation;
import services.client.ClientService;
import services.client.ClientServiceImplementation;
import services.employee.EmployeeService;
import services.employee.EmployeeServiceImplementation;

public class RegEmployeeComponentFactory {

	 private final ClientService clientService;
	 private final AccountService accountService;
	
	private static RegEmployeeComponentFactory instance;
	public static RegEmployeeComponentFactory instance() {
        if (instance == null) {
            instance = new RegEmployeeComponentFactory();
        }
        return instance;
    }
	
	private RegEmployeeComponentFactory() {  
        this.clientService = new ClientServiceImplementation(ComponentFactory.instance().getClientRepository());
        this.accountService = new AccountServiceImplementation(ComponentFactory.instance().getAccountRepository(),ComponentFactory.instance().getClientRepository());
    }
	
	public ClientService getClientService() {
		return clientService;
	}
	public AccountService getAccountService() {
		return accountService;
	}
}
