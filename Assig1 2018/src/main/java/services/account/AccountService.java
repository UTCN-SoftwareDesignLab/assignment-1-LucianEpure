package services.account;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import model.Account;
import validators.Notification;

public interface AccountService {
	
	public List<Account> showAll(String client);
	
	public Notification<Boolean> addAccount(String type, Double sum, LocalDate accDate, String client);
	
	public boolean removeAccount(Long id);
}
