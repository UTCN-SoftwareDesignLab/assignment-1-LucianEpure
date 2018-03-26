package repository.client;

import static database.Constants.Tables.CLIENT;
import static database.Constants.Tables.EMPLOYEE;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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
import repository.EntityNotFoundException;
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
	public Client findClientByCNP(String CNP)  throws EntityNotFoundException{
		 try {
	            Statement statement = connection.createStatement();
	            String sql = "Select * from "+ CLIENT + " where cnp =" + CNP;
	            ResultSet rs = statement.executeQuery(sql);

	            if (rs.next()) {
	                return getClientFromResultSet(rs);
	            } else {
	                throw new EntityNotFoundException(CNP, Client.class.getSimpleName());
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new EntityNotFoundException(CNP, Client.class.getSimpleName());
	        }
	}

	@Override
	public Client findClientById(Long id)  throws EntityNotFoundException {
		 try {
	            Statement statement = connection.createStatement();
	            String sql = "Select * from "+ CLIENT +" where id =" + id;
	            ResultSet rs = statement.executeQuery(sql);

	            if (rs.next()) {
	                return getClientFromResultSet(rs);
	            } else {
	                throw new EntityNotFoundException(id, Client.class.getSimpleName());
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new EntityNotFoundException(id, Client.class.getSimpleName());
	        }
	}
	
	@Override
	public Client findClientByCardId(Long cardId) throws EntityNotFoundException {
		 try {
	            Statement statement = connection.createStatement();
	            String sql = "Select * from "+ CLIENT + " where cardIdNumber =" + cardId;
	            ResultSet rs = statement.executeQuery(sql);

	            if (rs.next()) {
	                return getClientFromResultSet(rs);
	            } else {
	                throw new EntityNotFoundException(cardId, Client.class.getSimpleName());
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new EntityNotFoundException(cardId, Client.class.getSimpleName());
	        }
	}


	@Override
	public boolean addClient(Client client) {
		 try {
			 System.out.println(client.getCNP());
	            PreparedStatement insertQuery = connection
	                    .prepareStatement("INSERT INTO client values (null, ?, ?, ?, ?)");
	            insertQuery.setString(1, client.getName());
	            insertQuery.setString(2, client.getAddress());
	            insertQuery.setString(3, client.getCNP());
	            insertQuery.setLong(4, client.getCardIdNumber());
	            insertQuery.executeUpdate();
	            return true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	}
	
	
	@Override
	public boolean updateClient(Client client) {
			PreparedStatement updateClientQuery;
			try {
				System.out.println(client.getId());
				updateClientQuery = connection    
				        .prepareStatement("UPDATE " + CLIENT + " SET name=?, address=?, cardIdNumber=? WHERE id=?;");
				updateClientQuery.setString(1, client.getName());
	            updateClientQuery.setString(2, client.getAddress());
	            updateClientQuery.setLong(3, client.getCardIdNumber());
	            updateClientQuery.setLong(4, client.getId());
	            updateClientQuery.executeUpdate();

	            return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		
	}
	

	
	private Client getClientFromResultSet(ResultSet rs) throws SQLException {
        return new ClientBuilder()
                .setId(rs.getLong("id"))
                .setClientName(rs.getString("name"))
                .setClientAddress(rs.getString("address"))
                .setClientCNP(rs.getString("cnp"))
                .setClientCardIdNumber(rs.getLong("cardIdNumber"))
                .build();
       
    }

}
