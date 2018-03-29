package services.record;

import java.time.LocalDate;

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
	public Long getEmployeeId() {
		return employeeId;
	}
    @Override
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
}
