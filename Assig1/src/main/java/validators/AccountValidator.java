package validators;

import java.util.ArrayList;
import java.util.List;
import model.Account;

public class AccountValidator implements  IValidator{
	private final List<String> errors;
	private final Account account;
	
	public AccountValidator(Account account) {
		this.account = account;
		errors = new ArrayList<String>();
	}
	
	private void validateSum (String sum){
		
		  if(Double.parseDouble(sum) < 0)
			 errors.add("No negative sum!");
	}
	
	public boolean validate() {
		validateSum(String.valueOf(account.getSum()));
		return errors.isEmpty();
	}
	

	 public List<String> getErrors() {
	        return errors;
	    }

	
}
