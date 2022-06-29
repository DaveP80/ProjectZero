package com.revature.service;

import com.revature.branch.Role;
import com.revature.dao.IUserDao;
import com.revature.dao.UserDao;
import com.revature.exceptions.RegisterUserFailedException;
import com.revature.branch.User;
import util.ConnectionUtil;

import java.sql.Connection;

public class UserService {//Business Logic

	public UserDao getUdao() {
		return udao;
	}

	public void setUdao(UserDao udao) {
		this.udao = udao;
	}

	// Dependency Injection
	private UserDao udao = new UserDao();
	private User loggedInUser;//this instance variable allows us to simulate a user session
	private AccountService accountService = new AccountService();

	public int getIdByUsername(String username) {
		User u = udao.findByUsername(username);
		return u.getId();
	}

	public void setLoggedInUser(User u) {
		this.loggedInUser = u;
	}

	public User getLoggedInUser() {
		return loggedInUser;
	}

	public User register(String username, String password) {
		User user = new User(username, password, Role.Customer, null);
		udao.insert(user);
		return user;
	}
	public User register(User user) {

		udao.insert(user);
		return user;
	}
	public void logout() {//setting the instance variable back to null on logout selection
		setLoggedInUser(null);
	}

	public User findByUsername(String username) {
		return udao.findByUsername(username);
	}
	
	public User deposit(User u) {
		double generateBalance = udao.insert(u);
		return u;
	}
	
	public User login(String username, String password) {
		
		// We now need to call upon our userDAO to get us some information about the user with this specific username
		
		User returnedUser = udao.findByUsername(username);//username in the app gets run thru the findbyUsername
		// Check to see if returned password matches the entered password
		if (returnedUser != null) {
			String dbPassword = returnedUser.getPassword();
			if (dbPassword.equals(password)) {
				// user has succcessfully logged in, now we have to fetch all accounts by user id
				// then store the list of accounts on our user object
				System.out.println("User " + username + " has logged in successfully");
				returnedUser.setAccounts(accountService.getAccounts(returnedUser.getId()));
				return returnedUser;
			}
		}

		return null;
	}

}