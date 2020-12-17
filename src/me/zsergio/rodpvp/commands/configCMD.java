package me.zsergio.rodpvp.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.zsergio.rodpvp.RodPvP;
import me.zsergio.rodpvp.manage.ArenaManager;

public class configCMD implements CommandExecutor {

	private static RodPvP plugin = RodPvP.getInstance();
	private static ArenaManager arenamanager = plugin.getArenamanager();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.hasPermission("rodpvp.admin")) {
			
			if(args[0].equalsIgnoreCase("setspawn")) {
				Player player = (Player) sender;
				Location ploc = player.getLocation();
				arenamanager.setSpawn(ploc);
				sender.sendMessage("§7Spawn establecido correctamente.");
			}
			
			if(args[0].equalsIgnoreCase("setname")) {
				arenamanager.setName(args[1]);
				sender.sendMessage("§7Nombre cambiado a §e"+args[1]+"§7.");
			}
								
				
			} if(args[0].equalsIgnoreCase("setenabled")) {
				if(args[1].equalsIgnoreCase("true")) {
					arenamanager.setEnabled(true);
					sender.sendMessage("§7Arena:§a Habilitada");
					return true;
				} if(args[1].equalsIgnoreCase("false")) {
					arenamanager.setEnabled(false);
					sender.sendMessage("§7Arena:§c Deshabilitada");
					return true;
				} else {
					sender.sendMessage("§cSolo puedes poner true o false.");
				}
			}
		
		return true;
	}

}
