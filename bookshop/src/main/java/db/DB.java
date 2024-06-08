package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DB {
	
	//singleton patten qui  viene applicato.
	
	private static Connection connection;
	
	public static Connection getDb() throws Exception {
		if (connection == null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/bookshop",
					"root",
					""
				);
		}
			
			
		return connection; 
	}
	
	public static Statement getStmt() throws Exception {
		return getDb().createStatement();
	}
	
	public static PreparedStatement getPreparedStmt(String query) throws Exception {
		return getDb().prepareStatement(query);
	}
	
	public static PreparedStatement getPreparedStmt(String query, int generatedKey) throws Exception {
		return getDb().prepareStatement(query, generatedKey);
	}
}
	
	
