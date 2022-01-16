package local.homemade.auth.events.listener;

import java.util.logging.Level;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.permissions.PermissionAttachment;

import local.homemade.auth.HomeMadeAuthPlugin;
import local.homemade.auth.services.HomeMadeAuthLoginService;

public class HomeMadeAuthPlayerEventListner implements Listener 
{
	private HomeMadeAuthPlugin plugin;
	private HomeMadeAuthLoginService loginService;

	public HomeMadeAuthPlayerEventListner(HomeMadeAuthPlugin plugin, HomeMadeAuthLoginService loginService) 
	{
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
		this.plugin = plugin;
		this.loginService = loginService;
	}
	
	@EventHandler
	public void onLogin(PlayerLoginEvent event) 
	{	
		Player player = event.getPlayer();
		
		String username = player.getName().toLowerCase();
		
		PermissionAttachment attachment = player.addAttachment(plugin);
		
		if (!this.loginService.test(username)) {
			attachment.setPermission("homeMadeAuth.unregisterUser", true);
		} else {
			attachment.unsetPermission("homeMadeAuth.unregisterUser");
		}
		
		
		plugin.getLogger().log(Level.INFO, "Player " + username + "is login!");
	}
}
