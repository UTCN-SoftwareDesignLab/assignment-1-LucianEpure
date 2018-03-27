package services.account;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import model.Account;
import model.Client;
import model.builder.AccountBuilder;
import repository.EntityNotFoundException;
import repository.account.AccountRepository;
import repository.client.ClientRepository;
import validators.AccountValidator;
import validators.Notification;

public class AccountServiceImplementation implements AccountService{

	private final AccountRepository accountRepository;
	private final ClientRepository clientRepository;
	Client clientC;
	 public AccountServiceImplementation(AccountRepository accountRepository, ClientRepository clientRepository) {
	        this.accountRepository = accountRepository;
	        this.clientRepository = clientRepository;
	        clientC = new Client();
	    }
	@Override
	public Notification<Boolean> addAccount(String type, Double sum, LocalDate accountDate, String client) {

		
		try {
			clientC = clientRepository.findClientByCNP(client);
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Account account = new AccountBuilder()
               .setType(type)
               .setSum(sum)
               .setDate(accountDate)
               .build();

        AccountValidator accountValidator = new AccountValidator(account);
        boolean accountValid = accountValidator.validate();
        Notification<Boolean> accountAddNotification = new Notification<>();
      //  accountAddNotification.setResult(accountRepository.addAccount(account,clientC.getId()));
       

        if (!accountValid) {
            accountValidator.getErrors().forEach(accountAddNotification::addError);
            accountAddNotification.setResult(Boolean.FALSE);
            return accountAddNotification;
        } else {
            accountAddNotification.setResult(accountRepository.addAccount(account,clientC.getId()));
            return  accountAddNotification;
        }
	}

	@Override
	public List<Account> showAll(String client) {
		try {
			clientC = clientRepository.findClientByCNP(client);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		return accountRepository.findAccountsOfClient(clientC.getId());
	}
	@Override
	public boolean removeAccount(Long id) {
		return accountRepository.removeAccount(id);
	}

	
}
