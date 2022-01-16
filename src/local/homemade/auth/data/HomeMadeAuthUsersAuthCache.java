package local.homemade.auth.data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HomeMadeAuthUsersAuthCache {
	private final Map<String, Boolean> cache = new ConcurrentHashMap<>();
	
	public void updateUserStatus(String username, boolean status) {
		cache.put(username.toLowerCase(), status);
	}
	
	public void removeUser(String username) {
		cache.remove(username);
	}
	
	public boolean isAuth(String username) {
		return cache.containsKey(username.toLowerCase());
	}
}
