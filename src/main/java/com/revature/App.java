package com.revature;

import java.util.List;
import java.util.Scanner;

import com.revature.branch.*;
import com.revature.dao.UserDao;
import com.revature.service.AccountService;
import com.revature.service.ApplicationService;
import com.revature.service.UserService;

public class App {

	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		run();
	}

	public static void printMainMenu() {
		System.out.println("1. Login");
		System.out.println("2. Register");
	}

	public static void printCustomerMenu() {
		System.out.println("1. Apply to open an account");
		System.out.println("2. View your accounts");
		System.out.println("3. Withdraw");
		System.out.println("4. Deposit");
		System.out.println("5. Transfer");
		System.out.println("6. Logout");
	}

	public static void handleWithdraw(User currentUser, AccountService acctService) {
		System.out.println("Enter withdraw amount");
		double withdrawAmount = scan.nextInt();
		System.out.println("Enter account id to withdraw");

		int accountId = scan.nextInt();
		List<Account> accts = currentUser.getAccounts();

		Account accountToWithdraw = null;

		for (int i = 0; i < accts.size(); i++) {
			Account currentAcct = accts.get(i);
			if (accountId == currentAcct.getId()) {
				accountToWithdraw = accts.get(i);
				if (accountToWithdraw.getBalance() >= withdrawAmount) {
					double currentBalance = acctService.withdraw(currentUser.getId(), withdrawAmount, accountToWithdraw.getId(), accountToWithdraw.getBalance());
					System.out.println("Current balance for account " + accountId + " is " + currentBalance);
					currentAcct.setBalance(currentBalance);
				}
			}
		}

		if (accountToWithdraw == null) {
			System.out.println("Account id not valid");
		}
	}

	public static void handleDeposit(User currentUser, AccountService accountService) {
		System.out.println("Enter deposit amount");
		double deposit = scan.nextDouble();//deposit, accept deposit amt

		// if the user ever enters a bad number, just repeat
		while (deposit <= 0) {
			System.out.println("Enter deposit amount");
			deposit = scan.nextDouble();
		}

		System.out.println("Enter account id");
		int accountId = scan.nextInt();

		List<Account> accts = currentUser.getAccounts();

		Account accountToDeposit = null;

		for (int i = 0; i < accts.size(); i++) {
			Account currentAcct = accts.get(i);
			if (accountId == currentAcct.getId()) {
				accountToDeposit = accts.get(i);
				double newBalance = accountService.deposit(deposit, accountToDeposit.getId(), accountToDeposit.getBalance());
				currentAcct.setBalance(newBalance);
				System.out.println("Current balance for account " + accountId + " is " + newBalance);
			}
		}

		if (accountToDeposit == null) {
			System.out.println("Account id not valid");
		}
	}

	public static void handleTransfer(User user, AccountService accountService) {
		System.out.println("Enter transfer amount");
		double transferAmount = scan.nextDouble();
		while (transferAmount <= 0) {
			System.out.println("Enter transfer amount");
			transferAmount = scan.nextDouble();
		}

		try {
			System.out.println("Enter account id from");
			int accountIdFrom = scan.nextInt();
			System.out.println("Enter account id to");
			int accountIdTo = scan.nextInt();

			Account accountFrom = user.getAccounts().stream().filter(account -> account.getId() == accountIdFrom).findFirst().get();
			double accountIdFromBalance = accountFrom.getBalance();

			Account accountTo = (Account) user.getAccounts().stream().filter(account -> account.getId() == accountIdTo).findFirst().get();
			double accountIdToBalance = accountTo.getBalance();

			boolean complete = accountService.transfer(accountIdFrom, accountIdTo, transferAmount, accountIdFromBalance, accountIdToBalance);

			if (complete) {
				System.out.println("Transfer Successful");
			} else {
				System.out.println("Unable to complete transfer. Please try again later");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to complete transfer at this time.");
		}
	}

	// Encapsulate functionality for customer options
	public static void handleCustomerMenu(UserService userService, AccountService accountService, ApplicationService applicationService) {

		printCustomerMenu();
		User currentUser = userService.getLoggedInUser();

		int input = scan.nextInt();
		if (input == 1) {

			// apply for an account
			handleApplyForAccount(currentUser, applicationService);

		} else if (input == 2) {

			// when a user is logged in we fetch all accounts, print them when requested;
			currentUser.getAccounts().forEach(acct -> {
				System.out.println(acct);
			});
		} else if (input == 3) {
			handleWithdraw(currentUser, accountService);
		} else if (input == 4) {
			handleDeposit(currentUser, accountService);
		} else if (input == 5) {
			handleTransfer(currentUser, accountService);
		} else if (input == 6) {
			System.out.println("You have successfully logged out. Please come back soon!");
			userService.logout();
		}
	}

	public static void printEmployeeMenu() {
		System.out.println("1. Account Information");
		System.out.println("2. Account Balances");
		System.out.println("3. Personal Information");
		System.out.println("4. View Open Applications");
		System.out.println("5. Approve Application");
		System.out.println("6. Decline Application");
		System.out.println("7. Logout");
	}

	public static void printAdminMenu() {

	}

	public static void handleLogin(UserService userService) {//every record of account owner id is remembered
		System.out.println("Please enter your username");
		String username = scan.next();
		System.out.println("Please enter your password");
		String password = scan.next();
		User userLoggedIn = userService.login(username, password);
		if (userLoggedIn != null) {
			// successful login confirmed
			// Keep track of our logged in user by setting it as instance variable in userService
			// This is simulating a user session
			userService.setLoggedInUser(userLoggedIn);
		}
	}

	public static void handleRegister(UserService userService) {
		System.out.println("Please enter a username");
		String username = scan.next();
		User existingUser = userService.findByUsername(username);

		while (existingUser != null) {
			System.out.println("Username " + username + " already exists. Please try again.");
			System.out.println("Please enter a username");
			username = scan.next();
			existingUser = userService.findByUsername(username);
		}

		// now that we have our user object that has a unique username, get the password then insert
		System.out.println("Please enter a password");
		String password = scan.next();
		User userRegistered = userService.register(username, password);
		System.out.println(userRegistered);
	}

	public static void handleApplyForAccount(User user, ApplicationService applicationService) {
		Application application = applicationService.createApplication(user.getUsername());
		if (application != null) {
			System.out.println("Your application has been submitted. Please check back on the status of your application in 24 - 48 hours.");
		}
	}

	public static void run() {
		System.out.println("Welcome to our DP Bank!");

		UserService userService = new UserService();
		AccountService accountService = new AccountService();
		ApplicationService applicationService = new ApplicationService();

		while (true) {
			User currentUser = userService.getLoggedInUser();
			if (currentUser != null) {

				// for customers
				if (currentUser.getRole().equals(Role.Customer)) {
					handleCustomerMenu(userService, accountService, applicationService);

				// for employees
				} else if (currentUser.getRole().equals(Role.Employee)) {
					printEmployeeMenu();
					int input = scan.nextInt();
					if (input == 1) {
						System.out.println("Please enter username");
						String username = scan.next();
						User user = userService.findByUsername(username);
					} else if (input == 4) {
						System.out.println("Please enter username");
						String username = scan.next();
						// show all applications that are in status of waiting
						List<Application> openApplications = applicationService.getOpenApplications(username);
						openApplications.forEach(e -> {
							System.out.println(e);
						});
					} else if (input == 5) {
						// approve an application
						System.out.println("Please enter the application id to be approved");
						int appId = scan.nextInt();
						int appIdAfterUpdate = applicationService.updateApplication(appId, ApplicationStatus.Approved);
						String username = applicationService.usernameById(appIdAfterUpdate);
						int accountOwnerId = userService.getIdByUsername(username);
						if (appIdAfterUpdate > -1) {
							System.out.println("Application id " + appIdAfterUpdate + " has been approved");
							accountService.createAccount(0, accountOwnerId, true);
							System.out.println("Successfully created account for user " + username);
						} else {
							System.out.println("Application id " + appId + " Does not exist. Please try again");
						}
					} else if (input == 6) {
						System.out.println("Please enter the application id to be declined");
						int appId = scan.nextInt();
						int appIdAfterUpdate = applicationService.updateApplication(appId, ApplicationStatus.Declined);
						if (appIdAfterUpdate > -1) {
							System.out.println("Application id " + appIdAfterUpdate + " has been declined");
						} else {
							System.out.println("Application id " + appId + " Does not exist. Please try again");
						}
					} else if (input == 7) {
						userService.logout();
					}

				// for admins
				} else if (currentUser.getRole().equals(Role.Admin)) {
					printAdminMenu();
				}
			} else {

				//no user is logged in, print the main menu
				printMainMenu();
				int input = scan.nextInt();
				if (input == 1) {
					handleLogin(userService);
				} else if (input == 2) {
					handleRegister(userService);
				} else {
					System.out.println("Invalid option. Please try again");
				}
			}
		}
	}
}
