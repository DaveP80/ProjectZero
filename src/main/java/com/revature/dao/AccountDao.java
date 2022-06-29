package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.branch.Account;

import util.ConnectionUtil;

import javax.xml.transform.Result;

public class AccountDao implements IAccountDao {

	public int insert(Account newAccount) {
		Connection conn = ConnectionUtil.getConnection();
		String sql = "INSERT INTO accounts (balance, acc_owner, active) values (?, ?, ?) RETURNING accounts.id";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, newAccount.getBalance());
			stmt.setInt(2, newAccount.getAccOwner());
			stmt.setBoolean(3, newAccount.isActive());
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				int newAccountId = rs.getInt("id");
				System.out.println("Account successfully created with id " + newAccountId + " for user " + newAccount.getAccOwner());
				return newAccountId;
			}

		} catch (SQLException s) {}

		return -1;
	}

	public List<Account> findAll() {
		ArrayList<Account> accList = new ArrayList<>();
		Connection conn = ConnectionUtil.getConnection();
		if (conn != null) {
			try {
				String sql = "select * from accounts";
				Statement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					int id = rs.getInt("id");
					double balance = rs.getDouble("balance");
					int accOwnerId = rs.getInt("acc_owner");
					boolean isActive = rs.getBoolean("active");
					Account a = new Account(id, balance, accOwnerId, isActive);
					accList.add(a);
				}

				return accList;

			} catch (SQLException e) {}

			return accList;
		}

		return null;
	}

	public List<Account> findByOwner(int accOwnerId) {
		List<Account> ownedAccounts = new ArrayList<>();
		String sql = "SELECT * FROM accounts WHERE acc_owner = ?";
		// TODO Auto-generated method stub
		Connection conn = ConnectionUtil.getConnection();

		try {
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

	public double deposit(double deposit, int id, double currentBalance) {
		double newBalance = currentBalance + deposit;
		String sql = "update accounts set balance = ? where id = ? returning accounts.balance";
		// TODO Auto-generated method stub
		Connection conn = ConnectionUtil.getConnection();

		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setDouble(1, newBalance);
			statement.setInt(2, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Unable to retrieve accounts due to SQL Exception");
			e.printStackTrace();
			// TODO: handle exception
		}
		return -1;
	}

	public int withdraw(int userId, double withdrawAmount, int accountId, double currentBalance) {
		double newBalance = currentBalance - withdrawAmount;
		String sql = "update accounts set balance = ? where id = ? returning accounts.balance";
		// TODO Auto-generated method stub
		Connection conn = ConnectionUtil.getConnection();

		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setDouble(1, newBalance);
			statement.setInt(2, accountId);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Unable to retrieve accounts due to SQL Exception");
			e.printStackTrace();
			// TODO: handle exception
		}
		return -1;
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

	public boolean transfer(int accountIdFrom, int accountIdTo, double transferAmount, double accountIdFromBalance, double accountIdToBalance) {
		double newFromBalance = accountIdFromBalance - transferAmount;
		double newToBalance = accountIdToBalance + transferAmount;
		String balanceUpdate = "update accounts set balance = ? where id = ? returning accounts.id";
		Connection conn = ConnectionUtil.getConnection();
		try {
			PreparedStatement stmt1 = conn.prepareStatement(balanceUpdate);
			stmt1.setDouble(1, newFromBalance);
			stmt1.setInt(2, accountIdFrom);
			ResultSet rs = stmt1.executeQuery();
			if (rs.next()) {

				stmt1 = conn.prepareStatement(balanceUpdate);
				stmt1.setDouble(1, newToBalance);
				stmt1.setInt(2, accountIdTo);
				rs = stmt1.executeQuery();

				if (rs.next()) {
					return true;
				}
			}

			return false;

		} catch (SQLException s) {
			s.printStackTrace();
			return false;
		}
	}
}
