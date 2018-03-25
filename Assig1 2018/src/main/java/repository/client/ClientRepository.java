package repository.client;

import java.util.List;

import model.Client;;

public interface ClientRepository {

    public List<Client> findAll();
	
    public Client findClientByCNP(String CNP);
    
	public Client findClientById(Long id);
	
	public boolean addClient(Client client);
	
	public Client updateClient(Client account);
	
	
}
