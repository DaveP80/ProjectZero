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

