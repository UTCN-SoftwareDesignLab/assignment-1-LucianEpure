package testMain;

import services.client.ClientService;
import services.client.ClientServiceImplementation;
import services.employee.EmployeeService;
import services.employee.EmployeeServiceImplementation;

public class RegEmployeeComponentFactory {

	 private final ClientService clientService;
	
	private static RegEmployeeComponentFactory instance;
	public static RegEmployeeComponentFactory instance() {
        if (instance == null) {
            instance = new RegEmployeeComponentFactory();
        }
        return instance;
    }
	
	private RegEmployeeComponentFactory() {  
        this.clientService = new ClientServiceImplementation(ComponentFactory.instance().getClientRepository());
    }
	
	public ClientService getClientService() {
		return clientService;
	}
}
