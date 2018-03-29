package services.record;

import java.time.LocalDate;

import validators.Notification;

public interface RecordService {

	public void addRecord(Long clientId,  String name, LocalDate date);
	
	public Long getEmployeeId() ;
	
	public void setEmployeeId(Long employeeId);
}
