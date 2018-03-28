package services.account;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import model.Account;
import model.Client;
import repository.EntityNotFoundException;
import validators.Notification;

public interface AccountService {
	
	public List<Account> showAllAccountsOfClient(String client);
	
	public List<Account> showAllAccounts();
	
	public Account findById(Long id) throws EntityNotFoundException;
	
	public Notification<Boolean> addAccount(String type, Double sum, LocalDate accDate, String client);
	
	public Notification<Boolean> updateAccount(Account account,String type, Double sum,  LocalDate date);
	
	public Long findClientId(Account account);
	
	public boolean removeAccount(Long id);
	
	public Notification<Boolean> performTransaction(Long id1, Long id2, double sum) throws EntityNotFoundException;
}
