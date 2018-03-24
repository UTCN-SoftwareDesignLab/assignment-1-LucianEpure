package repository.client;

import java.util.List;

import model.Client;;

public interface ClientRepository {

    public List<Client> findAll();
	
    public Client findClientByCNP(String CNP);
    
	public Client findClientById(Long id);
	
	public Client addClient(Client account);
	
	public Client updateClient(Client account);
	
	
}
