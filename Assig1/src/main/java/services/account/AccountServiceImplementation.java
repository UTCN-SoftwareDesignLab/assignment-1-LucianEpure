package services.account;

import java.time.LocalDate;
import java.util.List;

import model.Account;
import model.builder.AccountBuilder;
import repository.EntityNotFoundException;
import repository.account.AccountRepository;
import repository.client.ClientRepository;
import validators.AccountValidator;
import validators.Notification;

public class AccountServiceImplementation implements AccountService{

	private final AccountRepository accountRepository;
	private final ClientRepository clientRepository;

	 public AccountServiceImplementation(AccountRepository accountRepository, ClientRepository clientRepository) {
	        this.accountRepository = accountRepository;
	        this.clientRepository = clientRepository;
	    }
	 
	 
	 
	@Override
	public Notification<Boolean> addAccount(String type, Double sum, LocalDate accountDate,Long clientId) {

		
        Account account = new AccountBuilder()
               .setType(type)
               .setSum(sum)
               .setDate(accountDate)
               .build();

        AccountValidator accountValidator = new AccountValidator(account);
        boolean accountValid = accountValidator.validate();
        Notification<Boolean> accountAddNotification = new Notification<>();
    

        if (!accountValid) {
            accountValidator.getErrors().forEach(accountAddNotification::addError);
            accountAddNotification.setResult(Boolean.FALSE);
            return accountAddNotification;
        } else {
            accountAddNotification.setResult(accountRepository.addAccount(account,clientId));
            return  accountAddNotification;
        }
	}

	@Override
	public List<Account> showAllAccountsOfClient(String client) {
		try {
			return accountRepository.findAccountsOfClient(clientRepository.findClientByCNP(client).getId());
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Account> showAllAccounts() {
		
		return accountRepository.findAll();
	}
	
	
	@Override
	public boolean removeAccount(Long id) {
		return accountRepository.removeAccount(id);
	}
	@Override
	
	
	public Notification<Boolean> updateAccount(Account account, String type, Double sum, LocalDate date) {
		Account newAccount = new AccountBuilder()
			   .setId(account.getId())
			   .setType(type)
               .setSum(sum)
               .setDate(date)
                .build();
	
		AccountValidator accountValidator = new AccountValidator(newAccount);
        boolean accountValid = accountValidator.validate();
        Notification<Boolean> accountUpdateNotification = new Notification<>();

        if (!accountValid) {
            accountValidator.getErrors().forEach(accountUpdateNotification::addError);
            accountUpdateNotification.setResult(Boolean.FALSE);
            return accountUpdateNotification;
        } else {
           accountUpdateNotification.setResult(accountRepository.updateAccount(newAccount,accountRepository.findClientId(newAccount.getId())));
            System.out.println(accountUpdateNotification.getResult());
            return  accountUpdateNotification;
        }
	}
	@Override
	public Account findById(Long id) throws EntityNotFoundException {
		return accountRepository.findAccountById(id);
	}
	@Override
	public Long findClientId(Long id) {
		return accountRepository.findClientId(id);
	}




	
}
