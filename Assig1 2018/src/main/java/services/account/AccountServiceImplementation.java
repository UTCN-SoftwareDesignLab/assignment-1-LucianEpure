package services.account;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import model.Account;
import model.Client;
import model.Transaction;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;
import model.builder.TransactionBuilder;
import repository.EntityNotFoundException;
import repository.account.AccountRepository;
import repository.client.ClientRepository;
import validators.AccountValidator;
import validators.ClientValidator;
import validators.Notification;
import validators.TransactionValidator;

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
	public List<Account> showAllAccountsOfClient(String client) {
		try {
			clientC = clientRepository.findClientByCNP(client);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		return accountRepository.findAccountsOfClient(clientC.getId());
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
           accountUpdateNotification.setResult(accountRepository.updateAccount(newAccount,accountRepository.findClientId(newAccount)));
            System.out.println(accountUpdateNotification.getResult());
            return  accountUpdateNotification;
        }
	}
	@Override
	public Account findById(Long id) throws EntityNotFoundException {
		return accountRepository.findAccountById(id);
	}
	@Override
	public Long findClientId(Account account) {
		return accountRepository.findClientId(account);
	}



	@Override
	public Notification<Boolean> performTransaction(Long id1, Long id2, double sum) throws EntityNotFoundException {

			double accountFromNewBalance;
			double accountToNewBalance;
			Account accountFrom = accountRepository.findAccountById(id1);
			Account accountTo = accountRepository.findAccountById(id2);
			Long clientIdFrom = accountRepository.findClientId(accountFrom);
			Long clientIdTo = accountRepository.findClientId(accountTo);
			
			Transaction transaction = new TransactionBuilder()
					.setAccountFrom(accountFrom)
					.setAccountTo(accountTo)
					.setSum(sum)
					.build();
				
			TransactionValidator transactionValidator = new TransactionValidator(transaction);
	        boolean transactionValid = transactionValidator.validate();
	        Notification<Boolean> transactionNotification = new Notification<>();
	    	
	        if (!transactionValid) {
	            transactionValidator.getErrors().forEach(transactionNotification::addError);
	            System.out.println("hehe");
		    	System.out.println(accountFrom.getSum()+" "+ accountTo.getSum()+" "+sum);
	            transactionNotification.setResult(Boolean.FALSE);
	            return transactionNotification;
	        } else {
	        	
	  			accountFromNewBalance = accountFrom.getSum() - sum;
				accountToNewBalance = accountTo.getSum() + sum;
				accountFrom.setSum(accountFromNewBalance);
				accountTo.setSum(accountToNewBalance);
				accountRepository.updateAccount(accountFrom, clientIdFrom);
				accountRepository.updateAccount(accountTo, clientIdTo); 	
				 transactionNotification.setResult(true);       
	             return  transactionNotification;
	        }	
	}
	
	public boolean checkIfEnoughMoney(Account account, double sum){
		if(account.getSum()>= sum)
			return true;
		else
			return false;
	}


	
}
