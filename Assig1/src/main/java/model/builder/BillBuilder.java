package model.builder;

import model.Account;
import model.Bill;

public class BillBuilder {

	Bill bill;
	
	public BillBuilder() {
		this.bill = new Bill();
	}
	
	public BillBuilder setNumber(String number){
		bill.setBillNumber(number);
		return this;
	}
	
	public BillBuilder setAccount(Account account){
		bill.setAccount(account);
		return this;
	}
	
	public BillBuilder setSum(double sum){
		bill.setSum(sum);
		return this;
	}
	
	public Bill build(){
		return bill;
	}
	
}
