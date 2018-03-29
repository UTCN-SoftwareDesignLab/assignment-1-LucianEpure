package model.builder;

import java.time.LocalDate;
import model.Account;

public class AccountBuilder {

	private Account account;
	
	public AccountBuilder(){
		this.account = new Account();
	}
	public AccountBuilder setId(Long id){
		account.setId(id);
		return this;
	}
	
	public AccountBuilder setType(String type){
		account.setType(type);
		return this;
	}
	
	public AccountBuilder setSum(double sum){
		account.setSum(sum);
		return this;
	}
	
	public AccountBuilder setDate(LocalDate accountDate){
		account.setDate(accountDate);
		return this;
	}
	
	public Account build(){
		return this.account;
	}
	
	
}
