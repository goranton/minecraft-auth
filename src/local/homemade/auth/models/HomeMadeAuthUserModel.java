package local.homemade.auth.models;

public class HomeMadeAuthUserModel 
{
	public int id;
	
	public String username;
	
	public String password;
	
	public HomeMadeAuthUserModel(int id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
}
