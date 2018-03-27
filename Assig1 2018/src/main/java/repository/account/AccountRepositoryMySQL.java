package repository.account;

import static database.Constants.Tables.ACCOUNT;
import static database.Constants.Tables.EMPLOYEE;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.Client;
import model.Role;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;

public class AccountRepositoryMySQL implements AccountRepository{

	private final Connection connection;
	
	 public AccountRepositoryMySQL(Connection connection) {
	        this.connection = connection;
	    }
	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account findAccountById(Long id) {
		// TODO Auto-generated method stub
		return null;
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
	public Account updateAccount(Account account) {
		// TODO Auto-generated method stub
		return null;
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
	public Client findClientId(Account account) {
		// TODO Auto-generated method stub
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
                .setSum(rs.getInt("sum"))
                .setDate(rs.getDate("date").toLocalDate())
                .build();
       
    }

}
