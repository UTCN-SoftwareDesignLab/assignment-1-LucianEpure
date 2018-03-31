package repository.record;

import java.time.LocalDate;
import java.util.List;

import model.Record;

public interface RecordRepository {

	
	public boolean addRecord(Record record);
	
	public List<Record> getRecords(Long employeeId, LocalDate date1, LocalDate date2);
	
	public void removeAll();
}
