package repository.account;

import java.util.List;

import model.Account;
import repository.EntityNotFoundException;
public interface AccountRepository {
	
	public List<Account> findAll();
	
	public List<Account> findAccountsOfClient(Long clientId);
	
	public Account findAccountById(Long id) throws EntityNotFoundException;
	
	public boolean addAccount(Account account, Long clientId);
	
	public boolean updateAccount(Account account, Long clientId);
	
	public boolean removeAccount(Long accountId);
	
	public Long findClientId(Long accountId);
	

}
