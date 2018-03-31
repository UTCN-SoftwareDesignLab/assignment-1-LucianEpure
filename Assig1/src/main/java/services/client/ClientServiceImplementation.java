package services.client;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Account;
import model.Client;
import model.builder.ClientBuilder;
import repository.EntityNotFoundException;
import repository.account.AccountRepository;
import repository.client.ClientRepository;
import validators.ClientValidator;
import validators.Notification;


public class ClientServiceImplementation implements ClientService{

	private final ClientRepository clientRepository;
	private final AccountRepository accountRepository;
	
	
	  public ClientServiceImplementation(ClientRepository clientRepository, AccountRepository accountRepository) {
	        this.clientRepository = clientRepository;
	        this.accountRepository = accountRepository;
	    }

	@Override
	public Notification<Boolean> addClient(String name, String address, String CNP, String cardIdNumber) {
		

        Client client = new ClientBuilder()
               .setClientName(name)
               .setClientAddress(address)
               .setClientCNP(CNP)
               .setClientCardIdNumber(Long.parseLong(cardIdNumber))
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
		Client client = clientRepository.findClientById(id);
				client.setAccounts(accountRepository.findAccountsOfClient(id));
		return client;
	}
	@Override
	public Client findClientByCnp(String CNP) throws EntityNotFoundException {
		Client client = clientRepository.findClientByCNP(CNP);
			     client.setAccounts(accountRepository.findAccountsOfClient(client.getId()));
		return client;
	}
	@Override
	public List<Client> showAll() {
		return  clientRepository.findAll();	
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
		List<Client> allClients = clientRepository.findAll();
		Long generatedCardId;
		do{
			generatedCardId = (long) (100000 + rand.nextInt(900000));
		}
		while(checkIfCardExist(allClients,generatedCardId));
			return generatedCardId;
	}
	
	public boolean checkIfCardExist(List<Client> clients, Long cardId){
		for(Client client:clients){
			if(client.getCardIdNumber()==cardId){
				return true;
			}
		}
		return false;
	}

}
