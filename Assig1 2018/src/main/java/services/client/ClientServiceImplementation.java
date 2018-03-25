package services.client;

import java.util.List;

import model.Client;
import repository.client.ClientRepository;
import repository.employee.EmployeeRepository;


public class ClientServiceImplementation implements ClientService{

	private final ClientRepository clientRepository;
	
	  public ClientServiceImplementation(ClientRepository clientRepository) {
	        this.clientRepository = clientRepository;
	    }
	@Override
	public List<Client> showAll() {
		return  clientRepository.findAll();	
	}

}
