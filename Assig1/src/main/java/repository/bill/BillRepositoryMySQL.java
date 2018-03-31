package repository.bill;

import static database.Constants.Tables.BILL;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Bill;


public class BillRepositoryMySQL implements BillRepository{

	private final Connection connection;
	
	 public BillRepositoryMySQL(Connection connection) {
	        this.connection = connection;
	    }
	@Override
	public boolean addBill(Bill bill) {
		try {
			PreparedStatement insertQuery = connection.prepareStatement("INSERT INTO "+BILL+" values (null, ?, ?, ?)");
			 	insertQuery.setString(1, bill.getBillNumber());
	            insertQuery.setDouble(2, bill.getSum());
	            insertQuery.setLong(3, bill.getAccount().getId());
	            insertQuery.executeUpdate();
	            return true;
		} catch (SQLException e) {
			return false;
		}
		
	}
	@Override
	public boolean findBillById(String billNumber) {
		
		Statement statement;
		try {
			statement = connection.createStatement();
			 String sql = "Select * from "+ BILL +" where code =" + billNumber;
	         ResultSet rs = statement.executeQuery(sql);
	         if (rs.next()) {
	        	 return true;
	         }
	         else{
	        	 return false;
	         }
	        	
		} catch (SQLException e) {
			return false;
		}
		
	}

}
