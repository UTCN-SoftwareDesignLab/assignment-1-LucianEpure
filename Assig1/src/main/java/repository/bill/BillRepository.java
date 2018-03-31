package repository.bill;

import model.Bill;

public interface BillRepository {

	public boolean addBill(Bill bill);
	
	public boolean findBillById(String billNumber);
	
}
