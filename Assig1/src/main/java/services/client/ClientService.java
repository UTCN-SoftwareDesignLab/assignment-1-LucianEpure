package services.client;

import java.util.List;

import model.Client;
import repository.EntityNotFoundException;
import validators.Notification;

public interface ClientService {

	public List<Client> showAll();
	
	public Notification<Boolean> addClient(String name, String address, String CNP, String cardIdNumber);
	
	public Client findClientById(Long id) throws EntityNotFoundException;
	
	public Client findClientByCnp(String CNP) throws EntityNotFoundException;
	
	public Long generateCardIdNumber();
	
	public Notification<Boolean> updateClient(Client client,String name, String address,  String cardId);
}
