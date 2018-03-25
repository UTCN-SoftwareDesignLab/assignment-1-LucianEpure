package services.client;

import java.util.List;

import model.Client;
import validators.Notification;

public interface ClientService {

	public List<Client> showAll();
	
	public Notification<Boolean> addClient(String name, String address, String CNP, String cardIdNumber);
	
	public Long generateCardIdNumber();
}
