package validators;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

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

	 public boolean isInteger( String input )
	 {
	    try{
	       Long.parseLong(input);
	       return true;
	    }
	    catch(ParseException e){
	       return false;
	    }
	 }
}
