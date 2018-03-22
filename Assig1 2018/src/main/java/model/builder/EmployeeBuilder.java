package model.builder;

import java.util.List;

import model.Employee;
import model.Role;


public class EmployeeBuilder {

	Employee employee;
	
	public EmployeeBuilder(){
		this.employee = new Employee();
	}
	
	 public EmployeeBuilder setUsername(String username) {
	        employee.setUsername(username);
	        return this;
	    }

	    public EmployeeBuilder setPassword(String password) {
	        employee.setPassword(password);
	        return this;
	    }

	    public EmployeeBuilder setRoles(List<Role> roles) {
	       employee.setRoles(roles);
	        return this;
	    }

	    public Employee build() {
	        return employee;
	    }
}
