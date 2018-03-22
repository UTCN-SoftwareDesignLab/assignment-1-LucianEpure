package model.builder;

import java.util.Date;

import model.Account;

public class AccountBuilder {

	private Account account;
	
	public AccountBuilder(){
		this.account = new Account();
	}
	
	public AccountBuilder setType(String type){
		account.setType(type);
		return this;
	}
	
	public AccountBuilder setSum(double sum){
		account.setSum(sum);
		return this;
	}
	
	public AccountBuilder setDate(Date date){
		account.setDate(date);
		return this;
	}
	
	public Account build(){
		return this.account;
	}
	
}
