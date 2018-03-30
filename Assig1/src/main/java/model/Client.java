package model;

import java.util.List;

public class Client {
	private Long id;
	
	private String name;
	private String CNP;
	private String address;
	private Long cardIdNumber;
	private List<Account> accounts;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCNP() {
		return CNP;
	}
	public void setCNP(String cNP) {
		CNP = cNP;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getCardIdNumber() {
		return cardIdNumber;
	}
	public void setCardIdNumber(Long cardIdNumber) {
		this.cardIdNumber = cardIdNumber;
	}
	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
}
