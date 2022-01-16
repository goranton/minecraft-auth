package local.homemade.auth.database.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class for working with local sqlLite database
 * @author "Anton Gorbunov<goranton98@gmail.com>"
 */
public final class HomeMadeAuthDatabaseSqlLiteConnection 
{
	public static HomeMadeAuthDatabaseSqlLiteConnection instance = null;
	
	private Connection connection = null;
	
	public static synchronized Connection getConnection(String filePath) throws SQLException {
		if (instance == null) {
			instance = new HomeMadeAuthDatabaseSqlLiteConnection();
			
			System.out.println("create instance");
		}
		
		return instance.initConnection(filePath);
	}
		
	private Connection initConnection(String filePath) throws SQLException {
		if (connection == null) {
			this.connection = DriverManager.getConnection("jdbc:sqlite:" + filePath);
		}
		
		
		return this.connection;
	}
}
