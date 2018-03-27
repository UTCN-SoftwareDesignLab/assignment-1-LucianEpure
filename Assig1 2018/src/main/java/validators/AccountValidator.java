package validators;

import java.util.ArrayList;
import java.util.List;


import model.Account;




public class AccountValidator {
	private final List<String> errors;
	private final Account account;
	
	public AccountValidator(Account account) {
		this.account = account;
		errors = new ArrayList<String>();
	}
	
	public boolean validate() {
		return errors.isEmpty();
	}
	

	 public List<String> getErrors() {
	        return errors;
	    }


}
