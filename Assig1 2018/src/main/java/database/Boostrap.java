package database;

import static database.Constants.getRolesRights;
import static database.Constants.Rights.RIGHTS;
import static database.Constants.Roles.ROLES;
import static database.Constants.Schemas.SCHEMAS;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;


public class Boostrap {
	 private static RightsRolesRepository rightsRolesRepository;

	    public static void main(String[] args) throws SQLException {
	    	
	    	dropAll();

	    	bootstrapTables();

	        bootstrapUserData();
	    }

	    private static void dropAll() throws SQLException {
	        for (String schema : SCHEMAS) {
	            System.out.println("Dropping all tables in schema: " + schema);

	            Connection connection = new JDBConnectionWrapper(schema).getConnection();
	            Statement statement = connection.createStatement();

	            String[] dropSQL = {
	            		 "TRUNCATE `record`; ",
		                 " DROP TABLE `record`;",
	            		 "TRUNCATE `role_right`; ",
	                     " DROP TABLE `role_right`;",
	                            "TRUNCATE `rights`; ",
	                            "DROP TABLE `rights`;",
	                            "TRUNCATE `employee_role`;",
	                            "DROP TABLE `employee_role`;",
	                            "TRUNCATE `employee`;",
	                            "DROP TABLE `employee`;",
	                            "TRUNCATE `role`;",
	                            "DROP TABLE `role`;",
	                            "TRUNCATE `account`;",
	                            "DROP TABLE  `account`;",
	                            "TRUNCATE `client`;",
	                            "DROP TABLE  `client`;"};
	            for(String query:dropSQL){
		            statement.execute(query);
	            }
	        }

	        System.out.println("Done table bootstrap");
	    }

	    private static void bootstrapTables() throws SQLException {
	        SQLTableCreationFactory sqlTableCreationFactory = new SQLTableCreationFactory();

	        for (String schema : SCHEMAS) {
	            System.out.println("Bootstrapping " + schema + " schema");


	            JDBConnectionWrapper connectionWrapper = new JDBConnectionWrapper(schema);
	            Connection connection = connectionWrapper.getConnection();

	            Statement statement = connection.createStatement();

	            for (String table : Constants.Tables.ORDERED_TABLES_FOR_CREATION) {
	                String createTableSQL = sqlTableCreationFactory.getCreateSQLForTable(table);
	                statement.execute(createTableSQL);
	            }
	        }

	        System.out.println("Done table bootstrap");
	    }

	    private static void bootstrapUserData() throws SQLException {
	        for (String schema : SCHEMAS) {
	            System.out.println("Bootstrapping user data for " + schema);

	            JDBConnectionWrapper connectionWrapper = new JDBConnectionWrapper(schema);
	            rightsRolesRepository = new RightsRolesRepositoryMySQL(connectionWrapper.getConnection());

	            bootstrapRoles();
	            bootstrapRights();
	            bootstrapRoleRight();
	        }
	    }

	    private static void bootstrapRoles() throws SQLException {
	        for (String role : ROLES) {
	            rightsRolesRepository.addRole(role);
	        }
	    }

	    private static void bootstrapRights() throws SQLException {
	        for (String right : RIGHTS) {
	            rightsRolesRepository.addRight(right);
	        }
	    }

	    private static void bootstrapRoleRight() throws SQLException {
	        Map<String, List<String>> rolesRights = getRolesRights();

	        for (String role : rolesRights.keySet()) {
	            Long roleId = rightsRolesRepository.findRoleByTitle(role).getId();

	            for (String right : rolesRights.get(role)) {
	                Long rightId = rightsRolesRepository.findRightByTitle(right).getId();

	                rightsRolesRepository.addRoleRight(roleId, rightId);
	            }
	        }
	    }

}
