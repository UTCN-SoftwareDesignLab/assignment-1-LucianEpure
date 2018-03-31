package client;

import java.sql.Connection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import database.DBConnectionFactory;
import model.Client;
import repository.account.AccountRepository;
import repository.account.AccountRepositoryMySQL;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySQL;

import services.client.ClientService;
import services.client.ClientServiceImplementation;


public class ClientServiceTest {

	 public static final String TEST_NAME = "Mihai";
	 public static final String TEST_ADDRESS = "Strada Mihai Viteazul";
	 public static final String TEST_CNP = "0123456789123";
	 public static final String TEST_CARDID = "111111";
	 public static final String TEST_NAME_2 = "Mihaitza";
	 public static final String TEST_ADDRESS_2 = "Strada Mihaitza Viteazul";
	 public static final String TEST_CNP_2 = "3219876543210";
	 public static final String TEST_CARDID_2 = "111112";

	 private static ClientRepository clientRepository;
	 private static AccountRepository accountRepository;
	 private static ClientService clientService;
	 @BeforeClass
	    public static void setUp() {
	        Connection connection = new DBConnectionFactory().getConnectionWrapper(true).getConnection();
	        clientRepository = new ClientRepositoryMySQL(connection);
	        accountRepository = new AccountRepositoryMySQL(connection);
	        clientService = new ClientServiceImplementation(clientRepository, accountRepository);      
	}
	 @Before
	    public void cleanUp() {
	        clientRepository.removeAll();
	    }
	 @Test
	 public void addClient() throws Exception {
	        Assert.assertTrue(
	                clientService.addClient(TEST_NAME, TEST_ADDRESS, TEST_CNP, TEST_CARDID).getResult()
	        );
	    }
	 @Test
	 public void findById() throws Exception{
		 clientService.addClient(TEST_NAME, TEST_ADDRESS, TEST_CNP, TEST_CARDID);
		 Client client = clientService.findClientById(1L);
		 Assert.assertNotNull(client);
	 }
	 
	 @Test
	 public void findByCnp() throws Exception{
		 clientService.addClient(TEST_NAME, TEST_ADDRESS, TEST_CNP, TEST_CARDID);
		 Client client = clientService.findClientByCnp(TEST_CNP);
		 Assert.assertNotNull(client);
	 }
	 @Test 
	 public void findAll() throws Exception{
		 clientService.addClient(TEST_NAME, TEST_ADDRESS, TEST_CNP, TEST_CARDID);
		 clientService.addClient(TEST_NAME_2, TEST_ADDRESS_2, TEST_CNP_2, TEST_CARDID_2);
		 List<Client>  clients = clientService.showAll();
		 Assert.assertEquals(clients.size(),2);
	 }
	 
	 @Test
	 public void generateCardId() throws Exception{
		 Long cardId = clientService.generateCardIdNumber();
		 Long min = 100000L;
		 Long max = 999999L;
		 Assert.assertTrue((min<= cardId)&&(cardId<=max));
	 }
	 
	 @Test
	 public void updateClient() throws Exception{
		 clientService.addClient(TEST_NAME, TEST_ADDRESS, TEST_CNP, TEST_CARDID);
		 Client client = clientService.findClientByCnp(TEST_CNP);
		 clientService.updateClient(client, TEST_NAME_2, TEST_ADDRESS_2, TEST_CARDID_2);
		 Client updatedClient = clientService.findClientByCnp(TEST_CNP);	 
	 	 Assert.assertNotEquals(client.getName(), updatedClient.getName());
	 }
}
