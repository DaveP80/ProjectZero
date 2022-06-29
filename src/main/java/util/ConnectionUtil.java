package util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

///were going to make a singleton connection
public class ConnectionUtil {

	private static Connection conn = null;
	private static String dbUrl = "jdbc:postgresql://localhost:5432/postgres?currentSchema=bank0";
	private static String dbUsername = "postgres";
	private static String dbPassword = "4alfonso4";

	private ConnectionUtil() {}

	public static Connection getConnection() {
		// check to see if there is a connection instance
		// if there is then return it
		try {
			if (conn != null && !conn.isClosed()) {
				System.out.println("Using a previously made connection");
				return conn;
			}
		} catch (SQLException e) {
			System.out.println("Error fetching existing connection");
			// do nothing
		}

		try {
			System.out.println("Creating new DB connection");
			conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			return conn;
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			System.out.println("Database connection can not be created at this time. Please try again later.");
			System.exit(1);
			return null;
		}
	}

	public static PreparedStatement prepareStatement(String sql) {
		// TODO Auto-generated method stub
		return null;
	}
}

// if not well set one up

// this is the not secure method
// this will change when we talk about cloud services
//String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=bank0";
//String username = "postgres";
//String password = "4alfonso4";
////
//	try {
//		conn = DriverManager.getConnection(url, username, password);
//		System.out.println("Established connection with the database.");
//	} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		System.out.println("Cannot make Connection");
//		e.printStackTrace();//
//	}
//	String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=BANK";
//	String username = "";
//	String password = "";
//
//	Properties prop = new Properties ();
//
//	try {
//		prop.load(new FileReader("C:\\Users\\david\\Desktop\\BSQLZERO\\src\\main\\resources\\application.properties"));
//		url = prop.getProperty("url");
//	    username = prop.getProperty("username");
//	    password = prop.getProperty("password");
//	    conn = DriverManager.getConnection(url, username, password);
//		System.out.println("Established connection with the database.");
//
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			System.out.println("Cannot make Connection");
//			e.printStackTrace();//
//		} catch (FileNotFoundException e)  {
//			System.out.println("Cannot find properties file");
//			e.printStackTrace();
//		} catch (IOException e) {
//			System.out.println("Something's wrong with the properties file");
//			e.printStackTrace();
//	}
//an even more secure method


