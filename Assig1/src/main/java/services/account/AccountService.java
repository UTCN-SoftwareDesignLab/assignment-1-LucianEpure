package services.account;

import java.time.LocalDate;
import java.util.List;

import model.Account;
import repository.EntityNotFoundException;
import validators.Notification;

public interface AccountService {
	
	public List<Account> showAllAccountsOfClient(Long clientId);
	
	public List<Account> showAllAccounts();
	
	public Account findById(Long id) throws EntityNotFoundException;
	
	public Notification<Boolean> addAccount(String type, Double sum, LocalDate accDate, Long clientId);
	
	public Notification<Boolean> updateAccount(Account account,String type, Double sum,  LocalDate date);
	
	public Long findClientId(Long accountId);
	
	public boolean removeAccount(Long id);
	
	public void removeAll();
	
	
}
