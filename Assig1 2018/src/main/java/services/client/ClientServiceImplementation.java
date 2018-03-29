package services.client;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Account;
import model.Client;
import model.builder.ClientBuilder;
import repository.EntityNotFoundException;
import repository.client.ClientRepository;
import validators.ClientValidator;
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
               .setAccounts(new ArrayList<Account>())
               .build();

        ClientValidator clientValidator = new ClientValidator(client);
        boolean clientValid = clientValidator.validate();
        Notification<Boolean> clientAddNotification = new Notification<>();
 
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
	public Client findClientById(Long id) throws EntityNotFoundException {
		return clientRepository.findClientById(id);
	}
	@Override
	public Client findClientByCnp(String CNP) throws EntityNotFoundException {
		Client client = clientRepository.findClientByCNP(CNP);
		return client;
	}
	@Override
	public Client findClientByCardId(Long cardId) throws EntityNotFoundException {
		return clientRepository.findClientByCardId(cardId);
	}
	@Override
	public Notification<Boolean> updateClient(Client client, String name, String address,  String cardId) {
		Client newClient = new ClientBuilder()
				.setId(client.getId())
               .setClientName(name)
               .setClientAddress(address)
               .setClientCNP(client.getCNP())
               .setClientCardIdNumber(Long.parseLong(cardId))
               .setAccounts(client.getAccounts())
                .build();
	
		ClientValidator clientValidator = new ClientValidator(newClient);
        boolean clientValid = clientValidator.validate();
        Notification<Boolean> clientUpdateNotification = new Notification<>();

        if (!clientValid) {
            clientValidator.getErrors().forEach(clientUpdateNotification::addError);
            clientUpdateNotification.setResult(Boolean.FALSE);
            return clientUpdateNotification;
        } else {
           clientUpdateNotification.setResult(clientRepository.updateClient(newClient));
            return  clientUpdateNotification;
        }
	}
	
	@Override
	public Long generateCardIdNumber(){
		Random rand = new Random();
		Long generatedCardId;
		do{
			generatedCardId = (long) (100000 + rand.nextInt(900000));
		}
		while(checkIfCardExist(generatedCardId));
			return generatedCardId;
	}
	
	public boolean checkIfCardExist(Long cardId){
		List<Client> allClients = clientRepository.findAll();
		for(Client client:allClients){
			if(client.getCardIdNumber()==cardId){
				return true;
			}
		}
		return false;
	}

}
