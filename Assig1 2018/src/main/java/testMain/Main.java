package testMain;





import model.Account;
import model.builder.AccountBuilder;
import services.employee.AuthenticationServiceMySQL;

public class Main {
	
	public static void main(String[] argv){
		
		
		Account acc = new AccountBuilder()
				.setSum(25)
				.setType("saving")
				.build();
		
	
		AuthenticationServiceMySQL service  = new AuthenticationServiceMySQL(null,null);
		String encode1= service.encodePassword("3anca#");
		System.out.println(encode1);
		String encode2= service.encodePassword("3anca#");
		System.out.println(encode2);
		
		
		
	}

}
