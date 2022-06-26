package util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

///were going to make a singleton connection
public class ConnectionUtil {
	
	private static Connection conn = null;
	
	///private static instance
	//private constructor
	//public static getInstance method
private ConnectionUtil() {
	
}

public static Connection getConnection() {
	
	//check to see if there is a connection instance
	/// if there is then return it

	try {
		if (conn != null && !conn.isClosed()) {

			System.out.println("Using a previously made connection");
			return conn;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
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
	
	String url = System.getenv("DB_URL");
	String username = System.getenv("DB_USERNAME");
	String password = System.getenv("DB_PASSWORD");
	//
		try {
			conn = DriverManager.getConnection(url, username, password);
			
			conn.setSchema("bank0");
			System.out.println("vvvvvvv");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot make Connection");
			e.printStackTrace();//
		}
	
		
	
	return conn;

}

public static PreparedStatement prepareStatement(String sql) {
	// TODO Auto-generated method stub
	return null;
}
}

