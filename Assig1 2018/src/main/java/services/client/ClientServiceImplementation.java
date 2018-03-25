package services.client;

import static database.Constants.Roles.REGEMPLOYEE;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Account;
import model.Client;
import model.Employee;
import model.Role;
import model.builder.ClientBuilder;
import model.builder.EmployeeBuilder;
import repository.client.ClientRepository;
import repository.employee.EmployeeRepository;
import validators.ClientValidator;
import validators.EmployeeValidator;
import validators.Notification;


public class ClientServiceImplementation implements ClientService{

	private final ClientRepository clientRepository;
	
	  public ClientServiceImplementation(ClientRepository clientRepository) {
	        this.clientRepository = clientRepository;
	    }
	@Override
	public List<Client> showAll() {
		return  clientRepository.findAll();	
	}
	@Override
	public Notification<Boolean> addClient(String name, String address, String CNP, String cardIdNumber) {
		
		Long cardId = Long.parseLong(cardIdNumber); 
		System.out.println(name);
        Client client = new ClientBuilder()
               .setClientName(name)
               .setClientAddress(address)
               .setClientCNP(CNP)
               .setClientCardIdNumber(cardId)
               .build();

        ClientValidator clientValidator = new ClientValidator(client);
        boolean clientValid = clientValidator.validate();
        Notification<Boolean> clientAddNotification = new Notification<>();
        clientAddNotification.setResult(clientRepository.addClient(client));
       

        if (!clientValid) {
            clientValidator.getErrors().forEach(clientAddNotification::addError);
            clientAddNotification.setResult(Boolean.FALSE);
            return clientAddNotification;
        } else {
            clientAddNotification.setResult(clientRepository.addClient(client));
            return  clientAddNotification;
        }
	}
	@Override
	public Long generateCardIdNumber(){
		Random rand = new Random();
		Long generatedCardId;
		//do{
			generatedCardId = (long) (100000 + rand.nextInt(900000));
		//}
		//while()
			return generatedCardId;
	}

}
