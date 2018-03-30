package model.builder;

import java.time.LocalDate;

import model.Record;

public class RecordBuilder {

	public Record record;
	
	public RecordBuilder(){
		this.record = new Record();
	}
	
	public RecordBuilder setId(Long id){
		record.setId(id);
		return this;
	}
	
	public RecordBuilder setClientId(Long id){
		record.setClientId(id);
		return this;
	}
	
	public RecordBuilder setEmployeeId(Long id){
		record.setEmployeeId(id);
		return this;
	}
	public RecordBuilder setDate(LocalDate date){
		record.setDate(date);
		return this;
	}
	public RecordBuilder setName(String name){
		record.setOperationName(name);
		return this;
	}
	
	public Record build(){
		return this.record;
	}
}
