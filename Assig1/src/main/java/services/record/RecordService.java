package services.record;

import java.time.LocalDate;
import java.util.List;

import model.Record;

public interface RecordService {

	public void addRecord(Long clientId,  String name, LocalDate date);
	
	public Long getEmployeeId() ;
	
	public void setEmployeeId(Long employeeId);
	
	public List<Record> generateRecords(Long employeeId, LocalDate date1, LocalDate date2);
	
}
