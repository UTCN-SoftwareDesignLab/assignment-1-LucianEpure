package repository.security;

import model.Right;
import model.Role;

import java.sql.*;


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
}
