package com.revature.dao;

import java.sql.Connection;
import java.util.List;

import com.revature.branch.Account;

import util.ConnectionUtil;

public interface IAccountDao {
//lets define our crud methods
	
	int insert(Account a);
	//a few methods for read
	
	List<Account> findAll();
	/////FIND balance by using id and selecting accounts table
	
	Account findById(int id);

	
     //returns account based
	
	//Update
	boolean update(Account a);
	// delete
	
	boolean delete(Account a);

	List<Account> findByOwner(int ownerId);


}
