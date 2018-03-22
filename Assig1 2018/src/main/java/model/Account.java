package model;

import java.util.Date;

public class Account {
	
	private Long id;
	private String type;
	private double sum;
	private Date date;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", type=" + type + ", sum=" + sum + ", date=" + date + "]";
	}
	
	
	
	
}
