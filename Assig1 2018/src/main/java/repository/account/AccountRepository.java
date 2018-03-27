package repository.account;

import java.util.List;

import model.Account;
import model.Client;
public interface AccountRepository {
	
	public List<Account> findAll();
	
	public List<Account> findAccountsOfClient(Long clientId);
	
	public Account findAccountById(Long id);
	
	public boolean addAccount(Account account, Long clientId);
	
	public Account updateAccount(Account account);
	
	public boolean removeAccount(Long accountId);
	
	public Client findClientId(Account account);
	

}
