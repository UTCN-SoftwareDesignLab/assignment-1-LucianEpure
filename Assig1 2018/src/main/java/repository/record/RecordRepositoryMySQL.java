package repository.record;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Record;

public class RecordRepositoryMySQL implements RecordRepository{
	 private final Connection connection;
	 
	 public RecordRepositoryMySQL(Connection connection){
		 this.connection = connection;
	 }

	@Override
	public boolean addRecord(Record record) {
		 try {
	            PreparedStatement insertQuery = connection
	                    .prepareStatement("INSERT INTO record values (null, ?, ?, ?, ?)");
	            insertQuery.setLong(1, record.getEmployeeId());
	            insertQuery.setLong(2, record.getClientId());
	            insertQuery.setString(3, record.getOperationName());
	            insertQuery.setDate(4, java.sql.Date.valueOf(record.getDate()));
	            insertQuery.executeUpdate();
	            return true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	}
}
