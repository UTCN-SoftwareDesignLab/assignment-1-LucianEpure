package testMain;

import model.Account;
import model.builder.AccountBuilder;

public class Main {
	
	public static void main(String[] argv){
		
		
		Account acc = new AccountBuilder()
				.setSum(25)
				.setType("saving")
				.build();
		System.out.println(acc.toString());
	}

}
