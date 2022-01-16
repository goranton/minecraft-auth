package local.homemade.auth.services;

import local.homemade.auth.data.HomeMadeAuthUsersAuthCache;
import local.homemade.auth.models.HomeMadeAuthUserModel;
import local.homemade.auth.resources.HomeMadeAuthUserResource;

public class HomeMadeAuthLoginService 
{
	private HomeMadeAuthUserResource resource;
	private HomeMadeAuthUsersAuthCache cache;
	
	public HomeMadeAuthLoginService(HomeMadeAuthUserResource resource, HomeMadeAuthUsersAuthCache cache) 
	{
		this.resource = resource;
	}
	
	public boolean test(String username) {
		return this.resource.isRegistered(username);
	}
	
	public boolean login(String username, String password) {
		HomeMadeAuthUserModel user = resource.findUserByUsernameAndPassword(username, password);
		
		if (user == null || cache.isAuth(username)) {
			return false;
		}
		
		cache.updateUserStatus(user.username, true);
		
		return true;
	}
}
