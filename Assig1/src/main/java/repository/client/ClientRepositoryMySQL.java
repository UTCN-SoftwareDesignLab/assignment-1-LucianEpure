package repository.client;

import static database.Constants.Tables.CLIENT;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Client;
import model.builder.ClientBuilder;
import repository.EntityNotFoundException;

public class ClientRepositoryMySQL implements ClientRepository{

	private final Connection connection;
	
	 public ClientRepositoryMySQL(Connection connection) {
	        this.connection = connection;
	    }
	 
	 @Override
		public void removeAll() {
		 try {
				Statement statement = connection.createStatement();
         	String sql = "DELETE from "+CLIENT+" where id >= 0";
         	statement.executeUpdate(sql);
         	String sqlResetIncrement = "ALTER TABLE "+CLIENT+" AUTO_INCREMENT = 1";
        	statement.executeUpdate(sqlResetIncrement);
		} catch (SQLException e) {
		    e.printStackTrace();
     }
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
	public boolean addClient(Client client) {
		 try {
	            PreparedStatement insertQuery = connection
	                    .prepareStatement("INSERT INTO "+CLIENT+" values (null, ?, ?, ?, ?)");
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
