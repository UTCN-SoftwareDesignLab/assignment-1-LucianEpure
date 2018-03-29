package repository.account;

import static database.Constants.Tables.ACCOUNT;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.Client;
import model.builder.AccountBuilder;
import repository.EntityNotFoundException;

public class AccountRepositoryMySQL implements AccountRepository{

	private final Connection connection;
	
	 public AccountRepositoryMySQL(Connection connection) {
	        this.connection = connection;
	    }
	@Override
	public List<Account> findAll() {
		List<Account> accounts = new ArrayList<>();
        Statement statement;
		try {
			statement = connection.createStatement();
			 String fetchAccountsSql = "Select * from " + ACCOUNT ;
		        ResultSet accountsResultSet = statement.executeQuery(fetchAccountsSql);
		        while (accountsResultSet.next()) {
		            accounts.add(getAccountFromResultSet(accountsResultSet));
		        }
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return accounts;
       
	}

	@Override
	public Account findAccountById(Long id)  throws EntityNotFoundException{
		 try {
	            Statement statement = connection.createStatement();
	            String sql = "Select * from "+ ACCOUNT + " where id =" + id;
	            ResultSet rs = statement.executeQuery(sql);

	            if (rs.next()) {
	                return getAccountFromResultSet(rs);
	            } else {
	                throw new EntityNotFoundException(id, Account.class.getSimpleName());
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new EntityNotFoundException(id, Client.class.getSimpleName());
	        }
	}

	@Override
	public boolean addAccount(Account account,Long clientId) {
		try {
            PreparedStatement insertQuery = connection
                    .prepareStatement("INSERT INTO account values (null, ?, ?, ?, ?)");
            insertQuery.setString(1, account.getType());
            insertQuery.setDouble(2, account.getSum());
            insertQuery.setDate(3, java.sql.Date.valueOf(account.getDate()));
            insertQuery.setLong(4,clientId);
            insertQuery.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}

	@Override
	public boolean updateAccount(Account account, Long clientId) {
		PreparedStatement updateAccountQuery;
		try {
			
			updateAccountQuery = connection    
			        .prepareStatement("UPDATE " + ACCOUNT + " SET type=?, sum=?, date=?, client_id=? WHERE id=?;");
			updateAccountQuery.setString(1, account.getType());
            updateAccountQuery.setDouble(2, account.getSum());
            updateAccountQuery.setDate(3, java.sql.Date.valueOf(account.getDate()));
            updateAccountQuery.setLong(4, clientId);
            updateAccountQuery.setLong(5,account.getId());
            updateAccountQuery.executeUpdate();

            return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean removeAccount(Long accountId) {
		
			try {
				PreparedStatement deleteQuery = connection.prepareStatement("DELETE FROM `"+ ACCOUNT + "` WHERE id = ?");
				deleteQuery.setLong(1,accountId);
				deleteQuery.executeUpdate(); 
				return true;
		} catch (SQLException e) {
			  e.printStackTrace();
			  return false;  
	    }
		

	}

	@Override
	public Long findClientId(Long accountId) {
		 try {
			Statement statement = connection.createStatement();
			 String fetchAccountsSql = "Select * from " + ACCOUNT + " where `id`=\'" + accountId + "\'";
			 ResultSet rs = statement.executeQuery(fetchAccountsSql);
			  if (rs.next()){
				  return rs.getLong("client_id");
			  }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
		
	}
	@Override
	public List<Account> findAccountsOfClient(Long clientId) {
		 try {
	            List<Account> accounts = new ArrayList<>();
	            Statement statement = connection.createStatement();
	            String fetchAccountsSql = "Select * from " + ACCOUNT + " where `client_id`=\'" + clientId + "\'";
	            ResultSet accountsResultSet = statement.executeQuery(fetchAccountsSql);
	            while (accountsResultSet.next()) {
	                accounts.add(getAccountFromResultSet(accountsResultSet));
	            }
	            return accounts;
	        } catch (SQLException e) {

	        }
		return null;
	}
	
	private Account getAccountFromResultSet(ResultSet rs) throws SQLException {
        return new AccountBuilder()
                .setId(rs.getLong("id"))
                .setType(rs.getString("type"))
                .setSum(rs.getDouble("sum"))
                .setDate(rs.getDate("date").toLocalDate())
                .build();
       
    }

}
