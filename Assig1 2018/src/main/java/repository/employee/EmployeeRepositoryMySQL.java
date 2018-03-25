package repository.employee;

import static database.Constants.Tables.EMPLOYEE;
import static database.Constants.Roles.ADMINISTRATOR;
import static database.Constants.Roles.REGEMPLOYEE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import model.Employee;
import model.Role;
import model.builder.EmployeeBuilder;
import repository.security.RightsRolesRepository;
import validators.Notification;

public class EmployeeRepositoryMySQL implements EmployeeRepository {

	 private final Connection connection;
	 private final RightsRolesRepository rightsRolesRepository;
	 
	   public EmployeeRepositoryMySQL(Connection connection, RightsRolesRepository rightsRolesRepository) {
	        this.connection = connection;
	        this.rightsRolesRepository = rightsRolesRepository;
	    }

	
	@Override
	public List<Employee> findAll() {
		List<Employee> regEmployees = new ArrayList<Employee>();
		 try {
	            Statement statement = connection.createStatement();
	            String sqlQuery = "Select * from "+ EMPLOYEE;
	            ResultSet rs = statement.executeQuery(sqlQuery);

	            while (rs.next()) {
	                regEmployees.add(getEmployeeFromResultSet(rs));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return regEmployees;
		
	}

	@Override
	public Notification<Employee> findByUsernameAndPassword(String username, String password) {
        Notification<Employee> findByUsernameAndPasswordNotification = new Notification<>();

        try {
            Statement statement = connection.createStatement();
            System.out.println(username+" "+password);
            String fetchEmployeeQuery = "Select * from `" + EMPLOYEE + "` where `username`=\'" + username + "\' and `password`=\'" + password + "\'";
            ResultSet employeeResultSet = statement.executeQuery(fetchEmployeeQuery);
            employeeResultSet.next();

            Employee employee =  getEmployeeFromResultSet(employeeResultSet);
            findByUsernameAndPasswordNotification.setResult(employee);
            return findByUsernameAndPasswordNotification;
        } catch (SQLException e) {
            e.printStackTrace();
            findByUsernameAndPasswordNotification.addError("Invalid email or password!");
            return findByUsernameAndPasswordNotification;
        }
    }
	
	@Override
	public Notification<Employee> findByUsername(String username) {
		Notification<Employee> findByUsernameNotification = new Notification<>();
		 try {
			Statement statement = connection.createStatement();
			String getEmployeeQuery = "Select * from `" + EMPLOYEE + "` where `username`=\'" + username + "\'";
			ResultSet employeeResultSet = statement.executeQuery(getEmployeeQuery);
			employeeResultSet.next();
			
			Employee employee =  getEmployeeFromResultSet(employeeResultSet);
			if(employee.getRole().toString().equalsIgnoreCase(ADMINISTRATOR))
				findByUsernameNotification.addError("Not a regular employee!");
			else
			findByUsernameNotification.setResult(employee);
			return findByUsernameNotification;
		} catch (SQLException e) {
			e.printStackTrace();
			 findByUsernameNotification.addError("Invalid username!");
	         return findByUsernameNotification;
		}
	}

	
	@Override
	public boolean saveAdmin(Employee admin) {
		// TODO Auto-generated method stub
		Role role = rightsRolesRepository.findRoleByTitle(ADMINISTRATOR);
		Long idRole = role.getId();
		try {
			
			
			 PreparedStatement insertStatement = connection
	                    .prepareStatement("INSERT INTO "+ EMPLOYEE +" values (null, ?, ?, ?)");
	            insertStatement.setString(1, admin.getUsername());
	            insertStatement.setString(2, admin.getPassword());
	            insertStatement.setLong(3, idRole);
	            insertStatement.executeUpdate();
	            
	            ResultSet rs = insertStatement.getGeneratedKeys();
	            rs.next();
	            long adminId = rs.getLong(1);
	            admin.setId(adminId);
	            return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}



	@Override
	public boolean saveEmployee(Employee employee) {
		Role role = rightsRolesRepository.findRoleByTitle(REGEMPLOYEE);
		Long idRole = role.getId();
		try {		
			 PreparedStatement insertStatement = connection
	                    .prepareStatement("INSERT INTO `"+ EMPLOYEE +"` values (null, ?, ?, ?)");
	            insertStatement.setString(1, employee.getUsername());
	            insertStatement.setString(2, employee.getPassword());
	            insertStatement.setLong(3, idRole);
	            insertStatement.executeUpdate();
	            
	            ResultSet rs = insertStatement.getGeneratedKeys();
	            rs.next();
	            long employeeId = rs.getLong(1);
	            employee.setId(employeeId);
	            return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public void removeRegEmployee(String username) {
		try {
			PreparedStatement deleteQuery = connection.prepareStatement("DELETE FROM `"+ EMPLOYEE + "` WHERE username = ?");
			deleteQuery.setString(1,username);
			deleteQuery.executeUpdate(); 
	} catch (SQLException e) {
	    e.printStackTrace();
    }
	}

	@Override
	public void removeAll() {
		try {
				Statement statement = connection.createStatement();
            	String sql = "DELETE from employee where id >= 0";
            	statement.executeUpdate(sql);
		} catch (SQLException e) {
		    e.printStackTrace();
        }
	}

		
		 private Employee getEmployeeFromResultSet(ResultSet rs) throws SQLException {
			 Role role = rightsRolesRepository.findRoleById(rs.getLong("id_role"));
		        return new EmployeeBuilder()
		                .setId(rs.getLong("id"))
		                .setUsername(rs.getString("username"))
		                .setPassword(rs.getString("password"))
		                .setRole(role)
		                .build();
		    }


}
