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



}
