package repository.record;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import model.Record;
import model.builder.EmployeeBuilder;
import model.builder.RecordBuilder;

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

	@Override
	public List<Record> getRecords(Long employeeId, LocalDate date1, LocalDate date2) {
		List<Record> records = new ArrayList<Record>();
		
		try {
			Statement statement = connection.createStatement();
			String fetchRecordSQL = "Select * from record where employee_id= " + employeeId.toString() + " and date >= '" +date1.toString() +"' and date <= '"+date2.toString()+"'";
			ResultSet rs= statement.executeQuery(fetchRecordSQL);
			while(rs.next()) {
				Record record = getRecordFromResultSet(rs);
            	records.add(record);
			}
			return records;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return records;
	}
	
	
	 private Record getRecordFromResultSet(ResultSet rs) throws SQLException {
			
	        return new RecordBuilder()
	                .setId(rs.getLong("id"))
	                .setEmployeeId(rs.getLong("employee_id"))
	                .setDate(rs.getDate("date").toLocalDate())
	                .setClientId(rs.getLong("client_id"))
	                .setName(rs.getString("name"))
	                .build();
	    }
	 

	
}
