package model.builder;
import java.util.List;

import model.Account;
import model.Client;

public class ClientBuilder {

	private Client client;
	
	public ClientBuilder(){
		this.client = new Client();
	}
	
	public ClientBuilder setId(Long id){
		System.out.println(id);
		client.setId(id);
		return this;
	}
	
	public ClientBuilder setClientCNP(String CNP){
		client.setCNP(CNP);
		return this;
	}
	public ClientBuilder setClientName(String name){
	
		client.setName(name);
		return this;
	}
	public ClientBuilder setClientAddress(String address){
		client.setAddress(address);
		return this;
	}
	public ClientBuilder setClientCardIdNumber(Long cardIdNumber){
		client.setCardIdNumber(cardIdNumber);
		return this;
	}
	public ClientBuilder setAccounts(List<Account> accounts){
		client.setAccounts(accounts);
		return this;
	}
	public Client build(){
		return client;
	}
}
