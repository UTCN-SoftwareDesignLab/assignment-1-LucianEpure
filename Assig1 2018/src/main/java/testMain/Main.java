package testMain;




import java.sql.Connection;

import database.DBConnectionFactory;
import jdk.nashorn.internal.runtime.linker.Bootstrap;
import model.Account;
import model.builder.AccountBuilder;

public class Main {
	
	public static void main(String[] argv){
		
		
		Account acc = new AccountBuilder()
				.setSum(25)
				.setType("saving")
				.build();
		
	}

}
