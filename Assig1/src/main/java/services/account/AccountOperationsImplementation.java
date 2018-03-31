package services.account;

import java.util.List;

import model.Account;
import model.Bill;
import model.Transaction;
import model.builder.BillBuilder;
import model.builder.TransactionBuilder;
import repository.EntityNotFoundException;
import repository.account.AccountRepository;
import repository.bill.BillRepository;
import validators.BillValidator;
import validators.Notification;
import validators.TransactionValidator;

public class AccountOperationsImplementation implements AccountOperations{
	
	
	private final AccountRepository accountRepository;
	private final BillRepository billRepository;
	public AccountOperationsImplementation(AccountRepository accountRepository, BillRepository billRepository){
		 this.accountRepository = accountRepository;
		 this.billRepository = billRepository;
	}
	
	@Override
	public Notification<Boolean> performTransaction(Long id1, Long id2, double sum) throws EntityNotFoundException {

		double accountFromNewBalance;
		double accountToNewBalance;
		Account accountFrom = accountRepository.findAccountById(id1);
		Account accountTo = accountRepository.findAccountById(id2);
		Long clientIdFrom = accountRepository.findClientId(id1);
		Long clientIdTo = accountRepository.findClientId(id2);
		
		Transaction transaction = new TransactionBuilder()
				.setAccountFrom(accountFrom)
				.setAccountTo(accountTo)
				.setSum(sum)
				.build();
			
		TransactionValidator transactionValidator = new TransactionValidator(transaction);
        boolean transactionValid = transactionValidator.validate();
        Notification<Boolean> transactionNotification = new Notification<>();
    	
        if (!transactionValid) {
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

	@Override
	public Notification<Boolean> processBill(String numberId, Long clientId, Long accountId, double sum) {
		
		
		List<Account> accounts = accountRepository.findAccountsOfClient(clientId);
		Account billAccount = null;
		Double newBalance;
		for(Account account: accounts){
			if(accountId == account.getId())
				billAccount = account;	
		}
		
		Bill bill = new BillBuilder()
					.setNumber(numberId)
					.setAccount(billAccount)
					.setSum(sum)
					.build();
		BillValidator billValidator = new BillValidator(bill);
        boolean billValid = billValidator.validate();
        Notification<Boolean> billNotification = new Notification<>();
      
        if(billRepository.findBillById(bill.getBillNumber()))
        {
        	billNotification.addError("Already paied!");
        	billValid = false;
        }
        
        if (!billValid) {
            billValidator.getErrors().forEach(billNotification::addError);
            billNotification.setResult(Boolean.FALSE);
            return billNotification;
        } else {
        		
        		newBalance = bill.getAccount().getSum() - sum;
        		bill.getAccount().setSum(newBalance);
        		billRepository.addBill(bill);
        		accountRepository.updateAccount(bill.getAccount(), clientId);
        		billNotification.setResult(true);       
                 return  billNotification;
        }
		
	}

	@Override
	public Long getAccountsClientId(Long accountId) throws EntityNotFoundException {
		return accountRepository.findClientId(accountId);
	}
	
	
}
