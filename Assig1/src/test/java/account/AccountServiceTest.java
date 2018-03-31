package account;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

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

public class AccountServiceTest {

	 public static final String TEST_TYPE = "Saving";
	 public static final double TEST_SUM = 4500;
	 public static final String TEST_TYPE_2 = "Spending";
	 public static final double TEST_SUM_2 = 5500;
	 public static final String TEST_BILL_NUMBER = "1234";
	 public static final double TEST_SUM_3 = 200;
	 //public static final LocalDate TEST_DATE = new LocalDate(0, 0, 0);
	 
	
	private static AccountRepository accountRepository;
	private static AccountService accountService;
	private static ClientService clientService;
	private static ClientRepository clientRepository;
	private static AccountOperations accountOperations;
	private static BillRepository billRepository;
	
	@BeforeClass
	public static void setup(){
		  Connection connection = new DBConnectionFactory().getConnectionWrapper(true).getConnection();
		  accountRepository = new AccountRepositoryMySQL(connection);
		  accountService = new AccountServiceImplementation(accountRepository);
		  clientRepository = new ClientRepositoryMySQL(connection);
		  clientService = new ClientServiceImplementation(clientRepository, accountRepository);
		  billRepository = new BillRepositoryMySQL(connection);	 
		  accountOperations = new AccountOperationsImplementation(accountRepository, billRepository);
		 
	}
	
	 @Before
	    public void cleanUp() {
	        accountRepository.removeAll();
	        clientRepository.removeAll();
	    }
	 @Before 
	  public void createClients(){
		 	clientService.addClient("TestClient", "Livada", "1234567891234", "123456");
	    	clientService.addClient("TestClient2", "Alun", "4564564564560", "123654");
	 }
	 @Test
	 public void addAccount() throws Exception {
	        Assert.assertTrue(
	                accountService.addAccount(TEST_TYPE, TEST_SUM, LocalDate.now(), 1L).getResult()
	        );
	    }
	 @Test
	 public void findById() throws Exception{
		 accountService.addAccount(TEST_TYPE, TEST_SUM, LocalDate.now(), 1L);
		 Account account = accountService.findById(1L);
		 Assert.assertNotNull(account);
	 }
	 @Test
	 public void findAll() throws Exception{
		 accountService.addAccount(TEST_TYPE, TEST_SUM, LocalDate.now(), 1L);
		 accountService.addAccount(TEST_TYPE_2, TEST_SUM_2, LocalDate.now(), 1L);
		 List<Account> accounts = accountService.showAllAccounts();
		 Assert.assertEquals(accounts.size(), 2);
	 }
	 @Test
	 public void findClientId() throws Exception{
		 accountService.addAccount(TEST_TYPE, TEST_SUM, LocalDate.now(), 1L);
		 Long clientId = accountService.findClientId(1L);
		 Assert.assertEquals(clientId.longValue(), 1L);
	 }
	 
	 @Test 
	 public void showAccountsOfClient() throws Exception{
		 accountService.addAccount(TEST_TYPE, TEST_SUM, LocalDate.now(), 1L);
		 accountService.addAccount(TEST_TYPE_2, TEST_SUM_2, LocalDate.now(), 2L);
		 List<Account> accounts = accountService.showAllAccountsOfClient(1L);
		 Assert.assertEquals(accounts.size(), 1);
	 }
	 
	 @Test
	 public void updateAccount() throws Exception{
		 accountService.addAccount(TEST_TYPE, TEST_SUM, LocalDate.now(), 1L);
		 Account account = accountService.findById(1L);
		 accountService.updateAccount(account, TEST_TYPE_2, TEST_SUM_2, LocalDate.now());
		 Account updatedAccount = accountService.findById(1L);
		 Assert.assertNotEquals(account.getType(), updatedAccount.getType());
	 }
	 
	 @Test
	 public void removeAccount() throws Exception{
		 accountService.addAccount(TEST_TYPE, TEST_SUM, LocalDate.now(), 1L);
		 List<Account> accounts = accountService.showAllAccounts();
		 accountService.removeAccount(1L);
		 List<Account> accountsAfter = accountService.showAllAccounts();
		 Assert.assertEquals(accounts.size(),accountsAfter.size()+1);
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
