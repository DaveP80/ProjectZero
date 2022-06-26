package com.revature.dao;


import java.nio.channels.SelectableChannel;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.revature.branch.Account;

import util.ConnectionUtil;

public class AccountDao implements IAccountDao{
	
	public int insert(Account a) {
		
		return 0; }
	
	public List<Account> findAll() {
	//instantiate a linkedlist to store all of the objects that we retrieved
	List<Account> accList = new LinkedList<Account>();
	
	try (Connection conn = ConnectionUtil.getConnection()){
      
		Statement stmt = conn.createStatement();
      //let's create our SQL query
      String sql = "SELECT * FROM accounts";
      
      /// "SELECT balance FROM accounts WHERE id='u';" = String sql
      
      ResultSet rs = stmt.executeQuery(sql);
      
      while (rs.next()) {
    	  
    	  int id = rs.getInt("id");
    	  double balance = rs.getDouble("balance");
    	  int accOwnerId = rs.getInt("acc_owner");
    	  boolean isActive = rs.getBoolean("active");
    	  
    	  Account a = new Account(id, balance, accOwnerId, isActive);
    	  
    	  accList.add(a);
      }
}
	catch (SQLException e) {
		System.out.println("Unable to retrieve accounts due to SQL Exception");
		e.printStackTrace();
	}
	return accList;
	
	}
	public List<Account> findByOwner(int accOwnerId) {//method for findbyowner
		
		List<Account> ownedAccounts = new LinkedList<>();
		String sql = "SELECT * FROM accounts WHERE acc_owner = ?";
		// TODO Auto-generated method stub
		
	try (Connection conn = ConnectionUtil.getConnection()) {
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, accOwnerId);// ?question mark and primarykey
		ResultSet rs = statement.executeQuery();//holds information back from sql
		
		while (rs.next()) { //we are going thru the column values of the next line from line 67 command
				int id = rs.getInt("id");
				double balance = rs.getDouble("balance");
				int ownerID = rs.getInt("acc_owner");
				boolean active = rs.getBoolean("active");
				
				Account a = new Account(id, balance, ownerID, active);
				ownedAccounts.add(a);///adding these column values into a fin
			
		}
		
	} catch (SQLException e) {
		System.out.println("Unable to retrieve accounts due to SQL Exception");
		e.printStackTrace();
		// TODO: handle exception
	}
		return ownedAccounts;
	}
	
	public boolean delete(Account a) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean update(Account a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Account findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
