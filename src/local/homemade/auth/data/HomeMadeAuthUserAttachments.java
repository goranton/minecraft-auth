package local.homemade.auth.data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.permissions.PermissionAttachment;

public class HomeMadeAuthUserAttachments {
	private final Map<String, PermissionAttachment> cache = new ConcurrentHashMap<>();
	
	public void storeAttachment(String username, PermissionAttachment attachment) {
		this.cache.put(username, attachment);
	}
	
	public void removeAttachment(String username) {
		this.cache.remove(username);
	}
	
	public PermissionAttachment getAttachment(String username) {
		return this.cache.get(username);
	}
}
