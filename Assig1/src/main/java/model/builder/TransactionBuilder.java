package model.builder;

import model.Account;
import model.Transaction;

public class TransactionBuilder {

	Transaction transaction;
	
	public TransactionBuilder(){
		this.transaction = new Transaction();
	}
	
	public TransactionBuilder setAccountFrom(Account accountFrom){
		transaction.setAccountFrom(accountFrom);
		return this;
	}
	
	public TransactionBuilder setAccountTo(Account accountTo){
		transaction.setAccountTo(accountTo);
		return this;
	}
	
	public TransactionBuilder setSum(double sum){
		transaction.setSum(sum);
		return this;
	}
	
	public Transaction build(){
		return transaction;
	}
}
