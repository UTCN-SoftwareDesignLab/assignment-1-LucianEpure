package repository.client;

import java.util.List;

import model.Client;
import repository.EntityNotFoundException;;

public interface ClientRepository {

    public List<Client> findAll();
	
    public Client findClientByCNP(String CNP) throws EntityNotFoundException;
    
	public Client findClientById(Long id) throws EntityNotFoundException;
	
	public Client findClientByCardId(Long cardId) throws EntityNotFoundException;
	
	public boolean addClient(Client client);
	
	public Client updateClient(Client account);
	
	
}
