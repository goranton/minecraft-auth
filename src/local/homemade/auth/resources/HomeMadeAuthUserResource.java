package local.homemade.auth.resources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import local.homemade.auth.HomeMadeAuthDbConnectionResolver;
import local.homemade.auth.models.HomeMadeAuthUserModel;

public class HomeMadeAuthUserResource 
{
	private HomeMadeAuthDbConnectionResolver db;
	
	public HomeMadeAuthUserResource(HomeMadeAuthDbConnectionResolver db) 
	{
		this.db = db;
	}

	public boolean isRegistered(String username) {
		try {
			Connection connection = this.db.configureAndResolve();
			
			PreparedStatement userExistStmt = connection.prepareStatement("SELECT COUNT(id) FROM main.users WHERE username = ?");
            
			userExistStmt.setString(1, username);
            
            return userExistStmt.executeQuery().getInt(1) != 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean storeUser(String username, String password) {
		try {
			Connection connection = this.db.configureAndResolve();
			
			PreparedStatement userAddStmt = connection.prepareStatement("INSERT INTO main.users ('username', 'password') VALUES (?, ?)");
			
			userAddStmt.setString(1, username.toLowerCase());
			userAddStmt.setString(2, password);
			
			userAddStmt.execute();
			
			System.out.println("Store user: " + username);
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public HomeMadeAuthUserModel findUserByUsernameAndPassword(String username, String password) {
		try {
			Connection connection = this.db.configureAndResolve();
			
			PreparedStatement userExistStmt = connection.prepareStatement("SELECT id, username, password FROM main.users WHERE username = ? AND password = ?");
            
			userExistStmt.setString(1, username.toLowerCase());
            userExistStmt.setString(2, password);
            
            ResultSet results = userExistStmt.executeQuery();
            
            if (results.next()) {
            	HomeMadeAuthUserModel user = new HomeMadeAuthUserModel(
        			results.getInt("id"),
        			results.getString("username"),
        			results.getString("password")
    			);
            	
            	return user;
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return null;
	}
}
