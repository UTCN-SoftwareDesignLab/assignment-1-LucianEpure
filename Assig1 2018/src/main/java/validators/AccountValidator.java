package validators;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import model.Account;




public class AccountValidator {
	private final List<String> errors;
	private final Account account;
	
	public AccountValidator(Account account) {
		this.account = account;
		errors = new ArrayList<String>();
	}
	
	public void validateSum (String sum){
		
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
