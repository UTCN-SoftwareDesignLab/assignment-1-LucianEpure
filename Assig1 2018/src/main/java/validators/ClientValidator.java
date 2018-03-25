package validators;

import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.StringUtils;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import model.Client;
import model.Employee;

public class ClientValidator {

	
	private final List<String> errors;
	private final Client client;
	
	public ClientValidator(Client client) {
		this.client = client;
		errors = new ArrayList<String>();
	}

	public boolean validate() {
		validateCNP(client.getCNP());
		validateCardId(client.getCardIdNumber());
		return errors.isEmpty();
	}
	
	public void validateCNP(String CNP){
		if(CNP.length()<13){
			errors.add("Too short CNP");
		}
		if(CNP.length()>13){
			errors.add("Too long CNP");
		}
		if(!isInteger(CNP)){
			errors.add("No character allowed");
		}		
	}
	
	public void validateCardId(Long cardId){
		if(cardId<100000){
			errors.add("Too short cardId");
		}
		if(cardId>900000){
			errors.add("Too long cardId");
		}
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
