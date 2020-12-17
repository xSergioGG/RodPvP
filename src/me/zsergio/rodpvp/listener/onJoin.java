package me.zsergio.rodpvp.listener;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.zsergio.rodpvp.RodPvP;
import me.zsergio.rodpvp.manage.ArenaManager;
import me.zsergio.rodpvp.manage.ServerManager;

public class onJoin implements Listener {
	
	private static RodPvP plugin = RodPvP.getInstance();
	
	private static FileConfiguration config = plugin.getConfig();
	
	private ServerManager servermanager = plugin.getServermanager();
	private ArenaManager arenamanager = plugin.getArenamanager();
	
	@EventHandler
	public void onJoinEvent(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		event.setJoinMessage("");
		
		if(!player.hasPermission("rodpvp.admin")) {
			if(arenamanager.isEnabled() == false) {
				player.kickPlayer(config.getString("messages.not-enabled").replace("&", "§"));
				return;
			} else
				servermanager.addPlayer(player);
				for(Player all : Bukkit.getOnlinePlayers()) {
					if(all.getWorld() == player.getWorld()) {
						all.sendMessage(config.getString("messages.join").replace("&", "§").replace("%player%", player.getName()
								.replace("%current%", servermanager.getOnline()+"")
								.replace("%max%", config.getInt("maxplayers")+"")));
					}
				}
				
				System.out.println("(+) Connected: "+player.getName());
		
				servermanager.addPlayer(player);
		} else {
			servermanager.addPlayer(player);
			for(Player all : Bukkit.getOnlinePlayers()) {
				if(all.getWorld() == player.getWorld()) {
					all.sendMessage(config.getString("messages.join").replace("&", "§").replace("%player%", player.getName()
							.replace("%current%", servermanager.getOnline()+"")
							.replace("%max%", config.getInt("maxplayers")+"")));
				}
			}
			
			System.out.println("(+) OP Connected: "+player.getName());
	
		}
		
	}

}
