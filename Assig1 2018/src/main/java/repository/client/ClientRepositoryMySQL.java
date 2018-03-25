package repository.client;

import static database.Constants.Tables.CLIENT;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Client;
import model.Employee;
import model.Role;
import model.builder.ClientBuilder;
import model.builder.EmployeeBuilder;
import repository.security.RightsRolesRepository;

public class ClientRepositoryMySQL implements ClientRepository{

	private final Connection connection;
	
	 public ClientRepositoryMySQL(Connection connection) {
	        this.connection = connection;
	    }
	@Override
	public List<Client> findAll() {
			List<Client> clients = new ArrayList<Client>();
			 try {
		            Statement statement = connection.createStatement();
		            String sqlQuery = "Select * from "+ CLIENT;
		            ResultSet rs = statement.executeQuery(sqlQuery);

		            while (rs.next()) {
		                clients.add(getClientFromResultSet(rs));
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
			return clients;
			
	}

	@Override
	public Client findClientByCNP(String CNP) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client findClientById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client addClient(Client account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client updateClient(Client account) {
		// TODO Auto-generated method stub
		return null;
	}
	
	 private Client getClientFromResultSet(ResultSet rs) throws SQLException {
		
	        return new ClientBuilder()
	                .setId(rs.getLong("id"))
	                .setClientName(rs.getString("name"))
	                .setClientName(rs.getString("address"))
	                .setClientCNP(rs.getString("cnp"))
	                .setClientCardIdNumber(rs.getLong("cardIdNumber"))
	                .build();
	    }

}
