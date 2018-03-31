package validators;

import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.Bill;

public class BillValidator implements IValidator{

	private final List<String> errors;
	private final Bill bill;
	
	public BillValidator(Bill bill){
		this.bill = bill;
		errors = new ArrayList<String>();
	}
	private void validateSum (String sum){
		  if(Double.parseDouble(sum) < 0)
			 errors.add("No negative sum!");
	}
	private boolean validateAccount(Account account){
		if(account == null){
			errors.add("Account not found!");
			return false;
		}
		return true;
	}
	private void checkIfEnoughMoney(Account account, double sum){
		if(account.getSum()< sum)
			
			errors.add("Not enough money!");
	}
	
	public boolean validate() {
		if(validateAccount(bill.getAccount())){
			validateSum(String.valueOf(bill.getSum()));
			checkIfEnoughMoney(bill.getAccount(), bill.getSum());
		}
		return errors.isEmpty();
	}
	

	 public List<String> getErrors() {
	        return errors;
	    }

}
