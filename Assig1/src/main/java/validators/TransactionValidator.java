package validators;

import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.Transaction;

public class TransactionValidator implements IValidator{
	private final List<String> errors;
	private final Transaction transaction;

	
	public TransactionValidator(Transaction transaction){
		this.transaction = transaction;
		errors = new ArrayList<String>();
	}
	public boolean validate() {
		validateSum(String.valueOf(transaction.getSum()));
		checkIfEnoughMoney(transaction.getAccountFrom(),transaction.getSum());
		return errors.isEmpty();
	}
	
	public List<String> getErrors() {
        return errors;
    }
	
	private void validateSum (String sum){
		
		  if (Double.parseDouble(sum) < 0)
			  errors.add("No negative ammount!");
	}
	
	private void checkIfEnoughMoney(Account accountFrom, double sum){
			if(sum > accountFrom.getSum())
				errors.add("Not enough money!");
	}
	
	
}
