package local.homemade.auth.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import local.homemade.auth.data.HomeMadeAuthUserAttachments;
import local.homemade.auth.services.HomeMadeAuthRegisterService;

public class HomeMadeAuthRegisterCommandExecutor implements CommandExecutor {
	private final HomeMadeAuthRegisterService registerService;
	private final HomeMadeAuthUserAttachments attachment;
	
	public HomeMadeAuthRegisterCommandExecutor(HomeMadeAuthRegisterService registerService, HomeMadeAuthUserAttachments attachment) {
		this.registerService = registerService;
		this.attachment = attachment;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player && args.length == 2 && args[0].equals(args[1])) {
			Player player = (Player) sender;
			String playerName = sender.getName().toLowerCase();
			
			this.registerService.makeRegister(playerName, args[0]);
			
			player.sendMessage("Register success!");
			
			return true;
		}
		
		sender.sendMessage("Error");
		
		return false;
	}

}
