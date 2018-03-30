package services.record;

import java.time.LocalDate;
import java.util.List;

import model.Record;
import model.builder.RecordBuilder;
import repository.record.RecordRepository;
import validators.Notification;

public class RecordServiceImplementation implements RecordService{

	private Long employeeId;

	private final RecordRepository recordRepository;
	public RecordServiceImplementation(RecordRepository recordRepository) {
		this.recordRepository = recordRepository;
	}
	@Override
	public void addRecord(Long clientId,  String name,LocalDate date) {
		Record record = new RecordBuilder()
				        .setEmployeeId(employeeId)
						.setClientId(clientId)
						.setName(name)
						.setDate(date)
						.build();
		recordRepository.addRecord(record);
		
	}
	

	@Override
	public List<Record> generateRecords(Long employeeId, LocalDate date1, LocalDate date2) {
		return recordRepository.getRecords(employeeId, date1, date2);
	}
    @Override
	public Long getEmployeeId() {
		return employeeId;
	}
    @Override
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
}
