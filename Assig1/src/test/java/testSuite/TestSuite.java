package testSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import account.AccountOperationsTest;
import account.AccountServiceTest;
import client.ClientServiceTest;
import employee.EmployeeServiceTest;
import record.RecordServiceTest;

@RunWith(Suite.class)
	
@Suite.SuiteClasses({
	EmployeeServiceTest.class,
	ClientServiceTest.class,
	AccountServiceTest.class,
	AccountOperationsTest.class,
	RecordServiceTest.class
	
})

public class TestSuite {

}
