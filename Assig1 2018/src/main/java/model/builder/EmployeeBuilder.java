package model.builder;


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

	    public EmployeeBuilder setRole(Role role) {
	       employee.setRole(role);
	        return this;
	    }
	    
	    public EmployeeBuilder setId(Long id){
	    	employee.setId(id);
	    	return this;
	    }

	    public Employee build() {
	        return employee;
	    }
}
