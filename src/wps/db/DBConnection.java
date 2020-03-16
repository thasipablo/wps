package wps.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	static Connection conn = null;
	
	static {
        try {
            // db parameters
            String url = "jdbc:sqlite:wps";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
	}
	
	public static Connection getConnection() {
		return conn;
	}
}
