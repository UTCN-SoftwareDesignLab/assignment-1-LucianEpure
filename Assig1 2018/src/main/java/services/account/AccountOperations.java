package services.account;

import repository.EntityNotFoundException;
import validators.Notification;

public interface AccountOperations {

	
	public Notification<Boolean> performTransaction(Long id1, Long id2, double sum) throws EntityNotFoundException;
	
	public Notification<Boolean> processBill(String numberId, Long clientId, Long accountId, double sum);
	
	public Long getAccountsClientId(Long accountId) throws EntityNotFoundException;
}
