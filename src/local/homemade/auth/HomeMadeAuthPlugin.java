package local.homemade.auth;

import org.bukkit.plugin.java.JavaPlugin;

import local.homemade.auth.commands.HomeMadeAuthRegisterCommandExecutor;
import local.homemade.auth.data.HomeMadeAuthUserAttachments;
import local.homemade.auth.data.HomeMadeAuthUsersAuthCache;
import local.homemade.auth.events.listener.HomeMadeAuthPlayerEventListner;
import local.homemade.auth.resources.HomeMadeAuthUserResource;
import local.homemade.auth.services.HomeMadeAuthLoginService;
import local.homemade.auth.services.HomeMadeAuthRegisterService;

public class HomeMadeAuthPlugin extends JavaPlugin {

	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		
		String databaseDriver = this.getConfig().getString("DATABASE_DRIVER");
		String databaseHost = this.getConfig().getString("DATABASE_HOST");
		
		HomeMadeAuthUsersAuthCache userAuthCache = new HomeMadeAuthUsersAuthCache();
		HomeMadeAuthUserAttachments userAttachmentsCache = new HomeMadeAuthUserAttachments();
		
		HomeMadeAuthDbConnectionResolver databaseResolver = new HomeMadeAuthDbConnectionResolver(databaseDriver, databaseHost);
		
		HomeMadeAuthUserResource userResource = new HomeMadeAuthUserResource(databaseResolver);
		HomeMadeAuthLoginService loginService = new HomeMadeAuthLoginService(userResource, userAuthCache);
		HomeMadeAuthRegisterService registerService = new HomeMadeAuthRegisterService(userResource);
		
		new HomeMadeAuthPlayerEventListner(this, loginService);
		
		HomeMadeAuthRegisterCommandExecutor registerCommandExecutor = new HomeMadeAuthRegisterCommandExecutor(registerService, userAttachmentsCache);
		
		getCommand("register").setExecutor(registerCommandExecutor);
	}
}
