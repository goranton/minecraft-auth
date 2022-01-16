package local.homemade.auth;

import java.sql.Connection;

import local.homemade.auth.database.connections.HomeMadeAuthDatabaseSqlLiteConnection;

public class HomeMadeAuthDbConnectionResolver {
	private String driverName;
	
	private String databaseHost;
	
	public HomeMadeAuthDbConnectionResolver(String driverName, String databaseHost) 
	{
		this.driverName = driverName;
		this.databaseHost = databaseHost;
	}
	
	public Connection configureAndResolve() throws Exception {
		switch(this.driverName) {
			case "sqlite":
				return HomeMadeAuthDatabaseSqlLiteConnection.getConnection(this.databaseHost);
		}
	
		throw new Exception("Invalid connection type");
	}
}
