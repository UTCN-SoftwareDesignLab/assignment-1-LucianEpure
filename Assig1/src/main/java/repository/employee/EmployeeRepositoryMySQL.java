package repository.employee;

import static database.Constants.Tables.EMPLOYEE;
import static database.Constants.Roles.ADMINISTRATOR;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import model.Employee;
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
		public void removeAll() {
			try {
					Statement statement = connection.createStatement();
	            	String sql = "DELETE from employee where id >= 0";
	            	statement.executeUpdate(sql);
	            	String sqlResetIncrement = "ALTER TABLE "+EMPLOYEE+ " AUTO_INCREMENT = 1";
	            	statement.executeUpdate(sqlResetIncrement);
			} catch (SQLException e) {
			    e.printStackTrace();
	        }
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
			if(employee.getRoles().get(0).toString().equalsIgnoreCase(ADMINISTRATOR))
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

		try {		
			 PreparedStatement insertStatement = connection
	                    .prepareStatement("INSERT INTO "+ EMPLOYEE +" values (null, ?, ?)", Statement.RETURN_GENERATED_KEYS);
	            insertStatement.setString(1, admin.getUsername());
	            insertStatement.setString(2, admin.getPassword());
	            insertStatement.executeUpdate();
	           
	            
	            ResultSet rs = insertStatement.getGeneratedKeys();
	            rs.next();
	            long adminId = rs.getLong(1);
	            admin.setId(adminId);
	            
	          
	            rightsRolesRepository.addRolesToEmployee(admin, admin.getRoles());
	            return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	//MODIFIED
	@Override
	public boolean saveEmployee(Employee employee) {
	
		try {		
			 PreparedStatement insertStatement = connection
	                    .prepareStatement("INSERT INTO `"+ EMPLOYEE +"` values (null, ?, ?)",Statement.RETURN_GENERATED_KEYS);
	            insertStatement.setString(1, employee.getUsername());
	            insertStatement.setString(2, employee.getPassword());     
	            insertStatement.executeUpdate();
	            
	            
	            
	            ResultSet rs = insertStatement.getGeneratedKeys();
	            rs.next();
	            long employeeId = rs.getLong(1);
	            employee.setId(employeeId);
	            
	           
	            rightsRolesRepository.addRolesToEmployee(employee, employee.getRoles());
	            return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}



	

	

		@Override
		public boolean updateEmployee(Employee employee) {
			PreparedStatement updateEmployeeQuery;
			try {
				updateEmployeeQuery = connection    
				        .prepareStatement("UPDATE " + EMPLOYEE + " SET username=?, password=? WHERE id=?;");
				updateEmployeeQuery.setString(1, employee.getUsername());
	            updateEmployeeQuery.setString(2, employee.getPassword());
	            updateEmployeeQuery.setLong(3, employee.getId());
	            updateEmployeeQuery.executeUpdate();

	            return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		
		@Override
		public Notification<Employee> findRegEmployeeById(Long id) {
			Notification<Employee> findByIdNotification = new Notification<>();
			try {
				Statement statement = connection.createStatement();
				String findQuery = "Select * from `" + EMPLOYEE + "` where `id`=\'" + id + "\'";
				ResultSet employeeResultSet = statement.executeQuery(findQuery);
				employeeResultSet.next();
				
				Employee employee =  getEmployeeFromResultSet(employeeResultSet);
				if(employee.getRoles().get(0).toString().equalsIgnoreCase(ADMINISTRATOR))
					findByIdNotification.addError("Not a regular employee!");
				else
				findByIdNotification.setResult(employee);
				return findByIdNotification;
		} catch (SQLException e) {
			e.printStackTrace();
			 findByIdNotification.addError("Invalid id!");
	         return findByIdNotification;
	    }
		}

		@Override
		public void removeRegEmployeeById(Long id) {
			try {
				PreparedStatement deleteQuery = connection.prepareStatement("DELETE FROM `"+ EMPLOYEE + "` WHERE id = ?");
				deleteQuery.setLong(1,id);
				deleteQuery.executeUpdate(); 
		} catch (SQLException e) {
		    e.printStackTrace();
	    }
		}

		





		 private Employee getEmployeeFromResultSet(ResultSet rs) throws SQLException {
				
		        return new EmployeeBuilder()
		                .setId(rs.getLong("id"))
		                .setUsername(rs.getString("username"))
		                .setPassword(rs.getString("password"))
		                .setRoles(rightsRolesRepository.findRolesForUser(rs.getLong("id")))
		                .build();
		    }



}
