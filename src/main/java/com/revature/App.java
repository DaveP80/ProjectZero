package com.revature;

import java.util.Scanner;

import com.revature.branch.Role;
import com.revature.branch.User;
import com.revature.service.UserService;

public class App {
static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		public static void printMainMenu() {
			
			System.out.println("1. Login");
			System.out.println("2. Register");
		}
		
		public static void handleLogin() {
			System.out.println("Please enter a username");
			String username = scan.next();
			System.out.println("please enter a password");
			String password = scan.next();
			System.out.println(username + "" + password);
			String password = scan.next();
			
			User userLoggedIn =
		}
	public static void handleRegister(UserService userService) {
		System.out.println("please enter a username");
		String username = scan.next();
		User existingUser = userService.findByUsername(username);
		
		while (existingUser != null) {
			
		}
		public static void run() {
			System.out.println("welcome to our dp bank!");
			UserService userService = new UserService();
			
		while (true) {
			User currentUser = userService.getLoggedInUser()
			if (currentUser != null) {
				if (currentUser.getRole().equals(Role.Customer)) {
					
					printCustomerMenu();
					int input
					
				} else if (currentUser.getRole().equals(Role.Employee)) {
					printEmployeeMenu();
					
				} else if (currentUser.getRole().equals(Role.Admin)) {
					printAdminMenu();
					
				}
				
			} else {
				}
			
		
		System.out.println("Please enter a password");
		String password = scan.next();
		User userRegistered = UserService.register(username, password);
		System.out.println(userRegistered);
		
	public static void handleApplyForAccount() {
	}
	
	public static void run() {
		System.out.println("Welcome to our DP Bank");
		UserService userService = new UserService();
		
		while (true) {
			
			User currentUser = userService.getLoggedinUser();
			if(currentUser != null) {
				if (currentUser.getRole().equals(Role.Customer)) {
					
					printCustomerMenu();
					
					int input;
					
				}  else if (currentUser.getRole().equals(Role.employee)) {
					
					printEmployeeMenu();
					
				} else if (currentUser.getRole().equals(Role.Admin)) {
					printAdminMenu();
				}
		} else {
		}}
		}
		
		
//		
//		public static void run() {
//			
//			UserService us = new UserService();
//			AccountService as = new AccountService();
//			System.out.println("welcome to the dp bank");
//			
//		printMenu();
//		int input = scan.nextInt();
//		if input == input
				
		
		
		//Let's just fetch our accounts to test
		
//		AccountService as = new AccountService();
//		
//		
		run();
//		ConnectionUtil.getConnection();
//		
//		ConnectionUtil.getConnection();
			
	}
	
//	public static void run() {
//		
//		UserService us = new UserService();
//		AccountService as = new AccountService();
//		
//		System.out.println("Welcome to our DP Bank!");
//		
//		System.out.println("Press 1 if you're an existing member trying to log in. \nPress 2 if you'd like to register.");
//		
//		int input = scan.nextInt();
//		
//		if (input == 1) {
//			
//			System.out.println("Please enter your username");
//			
//			String username = scan.next();
//			
//			System.out.println("Please enter your password");
//			
//			String password = scan.next();
//			
//			User loggedInUser = us.login(username, password);
//			
//			System.out.println("Welcome: your balance and ID");
//			
//			as.printAccountsbyOwner(loggedInUser.getId()); //AcountService.java //and userdao
//			System.out.println("^^^^^^^");
//			
//			System.out.println("Press 1 to deposit money Press 2 to withdraw.");
//			int input1 = scan.nextInt();
//			
//			System.out.println("You pressed 1, enter your id number");
//			int input2 = scan.nextInt();
//			System.out.println("Enter the dollar amount you wish to deposit $$.cc");
//			double deposit = scan.nextDouble();
//			//depositMoney(d).get
//			
//			
//			
//			// Maybe some data validation
//			
//			// Maybe creating a basic menu for your accounts
//			
//			// Giving option for accounts etc.
////			
////			
//		} else if (input == 2) {//Creating a new user entry in the Bank0 schema
//			
//			System.out.println("Please enter a username");//Comes from your user DAO
//			String username = scan.next();
//			
//			System.out.println("Please enter a secure password");
//			String password = scan.next();
//			
//			User u = new User(username, password, Role.Customer, null);
//			
////			
////			
//			us.register(u);
//		}
//		
//	}

}