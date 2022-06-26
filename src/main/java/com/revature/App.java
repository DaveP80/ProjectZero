package com.revature;

import java.util.Scanner;

import com.revature.branch.Role;
import com.revature.branch.User;
import com.revature.service.AccountService;
import com.revature.service.UserService;

public class App {
static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		
		
		//Let's just fetch our accounts to test
		
//		AccountService as = new AccountService();
//		
//		
		run();
//		ConnectionUtil.getConnection();
//		
//		ConnectionUtil.getConnection();
			
	}
	
	public static void run() {
		
		UserService us = new UserService();
		AccountService as = new AccountService();
		
		System.out.println("Welcome to our DP Bank!");
		
		System.out.println("Press 1 if you're an existing member trying to log in. \nPress 2 if you'd like to register.");
		
		int input = scan.nextInt();
		
		if (input == 1) {
			
			System.out.println("Please enter your username");
			
			String username = scan.next();
			
			System.out.println("Please enter your password");
			
			String password = scan.next();
			
			User loggedInUser = us.login(username, password);
			
			System.out.println("Welcome: your balance and ID");
			
			as.printAccountsbyOwner(loggedInUser.getId()); //AcountService.java //and userdao
			System.out.println("^^^^^^^");
			
			System.out.println("Press 1 to deposit money Press 2 to withdraw.");
			int input1 = scan.nextInt();
			
			System.out.println("You pressed 1, enter your id number");
			int input2 = scan.nextInt();
			System.out.println("Enter the dollar amount you wish to deposit $$.cc");
			double deposit = scan.nextDouble();
			//depositMoney(d).get
			
			
			
			// Maybe some data validation
			
			// Maybe creating a basic menu for your accounts
			
			// Giving option for accounts etc.
			
			
		} else if (input == 2) {//Creating a new user entry in the Bank0 schema
			
			System.out.println("Please enter a username");//Comes from your user DAO
			String username = scan.next();
			
			System.out.println("Please enter a secure password");
			String password = scan.next();
			
			User u = new User(username, password, Role.Customer, null);
			
			
			
			us.register(u);
		}
		
	}

}