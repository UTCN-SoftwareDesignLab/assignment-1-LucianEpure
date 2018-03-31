package account;

import java.sql.Connection;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import database.DBConnectionFactory;
import model.Account;
import repository.account.AccountRepository;
import repository.account.AccountRepositoryMySQL;
import repository.bill.BillRepository;
import repository.bill.BillRepositoryMySQL;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySQL;
import services.account.AccountOperations;
import services.account.AccountOperationsImplementation;
import services.account.AccountService;
import services.account.AccountServiceImplementation;
import services.client.ClientService;
import services.client.ClientServiceImplementation;


public class AccountOperationsTest {
	

	private static AccountService accountService;
	private static ClientService clientService;
	private static AccountOperations accountOperations;

	 public static final String TEST_TYPE = "Saving";
	 public static final double TEST_SUM = 4500;
	 public static final String TEST_TYPE_2 = "Spending";
	 public static final double TEST_SUM_2 = 5500;
	 public static final String TEST_BILL_NUMBER = "1234";
	 public static final double TEST_SUM_3 = 200;
	@BeforeClass
	public static void setup(){
		  Connection connection = new DBConnectionFactory().getConnectionWrapper(true).getConnection();
		  AccountRepository accountRepository = new AccountRepositoryMySQL(connection);
		  accountService = new AccountServiceImplementation(accountRepository);
		  ClientRepository clientRepository = new ClientRepositoryMySQL(connection);
		  clientService = new ClientServiceImplementation(clientRepository, accountRepository);
		  BillRepository billRepository = new BillRepositoryMySQL(connection);	 
		  accountOperations = new AccountOperationsImplementation(accountRepository, billRepository);
		 
	}

	 @Before
	    public void cleanUp() {
	        accountService.removeAll();
	        clientService.removeAll();
	    }
	 @Before 
	  public void createClients(){
		 	clientService.addClient("TestClient", "Livada", "1234567891234", "123456");
	    	clientService.addClient("TestClient2", "Alun", "4564564564560", "123654");
	 }
	 
	 @Test
	 public void processBill() throws Exception{
		 accountService.addAccount(TEST_TYPE, TEST_SUM, LocalDate.now(), 1L);
		 Account account = accountService.findById(1L);
		 accountOperations.processBill(TEST_BILL_NUMBER, 1L, 1L, TEST_SUM_3);
		 Account accountPaied = accountService.findById(1L);
		 Assert.assertEquals(account.getSum(), accountPaied.getSum()+TEST_SUM_3, 0.001);
	 }
	 
	 @Test
	 public void performTransaction() throws Exception{
		 accountService.addAccount(TEST_TYPE, TEST_SUM, LocalDate.now(), 1L);
	     Account account = accountService.findById(1L);
		 accountService.addAccount(TEST_TYPE_2, TEST_SUM_2, LocalDate.now(), 2L);
		 Account secondAccount = accountService.findById(2L);
		 accountOperations.performTransaction(1L, 2L, TEST_SUM_3);
		 Account accountTransact = accountService.findById(1L);
		 Account secondAccountTransact = accountService.findById(2L);
		 Assert.assertEquals(account.getSum(),accountTransact.getSum()+TEST_SUM_3,0.001);
		 Assert.assertEquals(secondAccount.getSum(),secondAccountTransact.getSum()-TEST_SUM_3,0.001);
	 }
}
