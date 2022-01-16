package local.homemade.auth.services;

import local.homemade.auth.models.HomeMadeAuthUserModel;
import local.homemade.auth.resources.HomeMadeAuthUserResource;

public class HomeMadeAuthRegisterService {
	private HomeMadeAuthUserResource resource;
	
	public HomeMadeAuthRegisterService(HomeMadeAuthUserResource resource) {
		this.resource = resource;
	}
	
	public boolean makeRegister(String username, String password) {
		HomeMadeAuthUserModel user = this.resource.findUserByUsernameAndPassword(username, password);
		
		if (user == null) {
			System.out.println("User store");
			
			this.resource.storeUser(username, password);
		} else {
			System.out.println("User already exists");
		}
		
		return true;
	}
}
