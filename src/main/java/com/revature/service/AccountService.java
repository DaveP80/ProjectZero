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
	private AccountDao adao = new AccountDao();
	
	// Lets make a logger here
	Logger logger = Logger.getLogger(AccountService.class);

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

    public int createAccount(double balance, int accountOwner, boolean isActive) {
		Account newAccount = new Account(balance, accountOwner, isActive);
		return adao.insert(newAccount);
    }

	public List<Account> getAccounts(int accountOwnerId) {
		return adao.findByOwner(accountOwnerId);
	}

	public double withdraw(int userId, double withdrawAmount, int accountId, double balance) {
		return adao.withdraw(userId, withdrawAmount, accountId, balance);
	}

	public double deposit(double deposit, int id, double currentBalance) {
		return adao.deposit(deposit, id, currentBalance);
	}

	public boolean transfer(int accountIdFrom, int accountIdTo, double transferAmount, double accountIdFromBalance, double accountIdToBalance) {
		return adao.transfer(accountIdFrom, accountIdTo, transferAmount, accountIdFromBalance, accountIdToBalance);
	}
}
