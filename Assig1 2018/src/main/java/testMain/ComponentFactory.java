package testMain;
import database.DBConnectionFactory;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySQL;
import repository.employee.EmployeeRepository;
import repository.employee.EmployeeRepositoryMySQL;
import services.employee.AuthenticationService;
import services.employee.AuthenticationServiceMySQL;


import java.sql.Connection;

/**
 * Created by Alex on 18/03/2017.
 */
public class ComponentFactory {

    private final AuthenticationService authenticationService;

    private final EmployeeRepository employeeRepository;
    private final ClientRepository clientRepository;
    private final RightsRolesRepository rightsRolesRepository;

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
        this.employeeRepository = new EmployeeRepositoryMySQL(connection, rightsRolesRepository);
        this.authenticationService = new AuthenticationServiceMySQL(this.employeeRepository, this.rightsRolesRepository);
        this.clientRepository = new ClientRepositoryMySQL(connection);
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
    
    public ClientRepository getClientRepository(){
    	return clientRepository;
    }

}
