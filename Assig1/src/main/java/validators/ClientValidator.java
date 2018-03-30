package validators;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;



import model.Client;

public class ClientValidator {

	
	private final List<String> errors;
	private final Client client;
	private final String CNP_VALIDATION_REGEX = "[0-9]+";
	
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
		   if (!Pattern.compile(CNP_VALIDATION_REGEX).matcher(CNP).matches()) {
	            errors.add("No letters in CNP!");
	        }
	}
	
	public void validateCardId(Long cardId){
		if(cardId<100000){
			errors.add("Too short cardId");
		}
		if(cardId>999999){
			errors.add("Too long cardId");
		}
	}

	 public List<String> getErrors() {
	        return errors;
	    }

	 public boolean isInteger( String input )
	 {
	    Long.parseLong(input);
	       return true;
	 }
}
