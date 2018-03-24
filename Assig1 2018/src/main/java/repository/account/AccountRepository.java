package repository.account;

import java.util.List;

import model.Account;
import model.Client;
public interface AccountRepository {
	
	public List<Account> findAll();
	
	public Account findAccountById(Long id);
	
	public Account addAccount(Account account);
	
	public Account updateAccount(Account account);
	
	public boolean removeAccount(Account account);
	
	public Client findClientId(Account account);
	

}
