package com.revature.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.dao.AccountDao;
import com.revature.dao.IAccountDao;

import util.ConnectionUtil;

import com.revature.branch.Account;

public class AccountService {//		String sql = "UPDATE accounts SET balance=? WHERE id=?";
	
	// Let's inject our DAO to the service
	private IAccountDao adao = new AccountDao();
	
	// Lets make a logger here
	Logger logger = Logger.getLogger(AccountService.class);
	
	private static Scanner scan = new Scanner(System.in);
	
	public void viewAllAccounts() {
		
//		System.out.println("Fetching accounts...");
		
		logger.info("Fetching Accounts...");
		
		// Lets call upon our DAO to get all of our accounts
		
		List<Account> accList = adao.findAll();
		
		for (Account a: accList) {
			System.out.println(a);
		}
	
	}
   public void printAccountsbyOwner(int OwnerId) {///call upon dao for result from command balance inquiry
	
	   List<Account> result = adao.findByOwner(OwnerId);
	   
	   for (Account a: result) {
		   
		   System.out.println(a);// printAccountsbyOwner
	   }
	   
	
}
	

}
