package repository.security;

import model.Employee;
import model.Right;
import model.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables.*;


public class RightsRolesRepositoryMySQL implements RightsRolesRepository {

    private final Connection connection;

    public RightsRolesRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addRole(String role) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT IGNORE INTO " + ROLE + " values (null, ?)");
            insertStatement.setString(1, role);
            insertStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    @Override
    public void addRight(String right) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT IGNORE INTO `" + RIGHTS + "` values (null, ?)");
            insertStatement.setString(1, right);
            insertStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    @Override
    public Role findRoleByTitle(String role) {
        Statement statement;
        try {
            statement = connection.createStatement();
            String fetchRoleSql = "Select * from " + ROLE + " where `role`=\'" + role + "\'";
            ResultSet roleResultSet = statement.executeQuery(fetchRoleSql);
            roleResultSet.next();
            Long roleId = roleResultSet.getLong("id");
            String roleTitle = roleResultSet.getString("role");
            return new Role(roleId, roleTitle, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Role findRoleById(Long roleId) {
        Statement statement;
        try {
            statement = connection.createStatement();
            String fetchRoleSql = "Select * from " + ROLE + " where `id`=\'" + roleId + "\'";
            ResultSet roleResultSet = statement.executeQuery(fetchRoleSql);
            roleResultSet.next();
            String roleTitle = roleResultSet.getString("role");
            return new Role(roleId, roleTitle, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
}
    @Override
    public Right findRightByTitle(String right) {
        Statement statement;
        try {
            statement = connection.createStatement();
            String fetchRoleSql = "Select * from `" + RIGHTS + "` where `rights`=\'" + right + "\'";
            ResultSet rightResultSet = statement.executeQuery(fetchRoleSql);
            rightResultSet.next();
            Long rightId = rightResultSet.getLong("id");
            String rightTitle = rightResultSet.getString("rights");
            return new Right(rightId, rightTitle);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public void addRoleRight(Long roleId, Long rightId) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT IGNORE INTO " + ROLE_RIGHT + " values (null, ?, ?)");
            insertStatement.setLong(1, roleId);
            insertStatement.setLong(2, rightId);
            insertStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

	@Override
	public Role getRoleOfEmployee(Long employeeId) {
        try {
            Role role;
            Statement statement = connection.createStatement();
            String fetchRoleSql = "Select * from " + EMPLOYEE + " where `_id`=\'" + employeeId + "\'";
            ResultSet employeeRole = statement.executeQuery(fetchRoleSql);
                Long roleId = employeeRole.getLong("id_role");
                role = findRoleById(roleId);
            return role;
        } catch (SQLException e) {

        }
        return null;
    }
	 @Override
	    public void addRolesToEmployee(Employee employee, List<Role> roles) {
	        try {
	            for (Role role : roles) {
	                PreparedStatement insertEmployeeRoleStatement = connection
	                        .prepareStatement("INSERT INTO `employee_role` values (null, ?, ?)");
	                insertEmployeeRoleStatement.setLong(1, employee.getId());
	                insertEmployeeRoleStatement.setLong(2, role.getId());
	                insertEmployeeRoleStatement.executeUpdate();
	            }
	        } catch (SQLException e) {

	        }
	    }

	    @Override
	    public List<Role> findRolesForUser(Long employeeId) {
	        try {
	            List<Role> roles = new ArrayList<>();
	            Statement statement = connection.createStatement();
	            String fetchRoleSql = "Select * from " + EMPLOYEE_ROLE + " where `employee_id`=\'" + employeeId + "\'";
	            ResultSet employeeRoleResultSet = statement.executeQuery(fetchRoleSql);
	            while (employeeRoleResultSet.next()) {
	                long roleId = employeeRoleResultSet.getLong("role_id");
	                roles.add(findRoleById(roleId));
	            }
	            return roles;
	        } catch (SQLException e) {

	        }
	        return null;
	}
	    
		@Override
		public void removeEmployeeRole(Long id) {
			try {
				PreparedStatement deleteQuery = connection.prepareStatement("DELETE FROM `"+ EMPLOYEE_ROLE + "` WHERE employee_id = ?");
				deleteQuery.setLong(1,id);
				deleteQuery.executeUpdate(); 
		} catch (SQLException e) {
		    e.printStackTrace();
	    }
		}
	    
	    
}
