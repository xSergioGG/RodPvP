package me.zsergio.rodpvp.listener;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.zsergio.rodpvp.RodPvP;
import me.zsergio.rodpvp.manage.ArenaManager;
import me.zsergio.rodpvp.manage.ServerManager;

public class onQuit implements Listener {
	
	private static RodPvP plugin = RodPvP.getInstance();
	
	private static FileConfiguration config = plugin.getConfig();
	
	private ArenaManager arenamanager = plugin.getArenamanager();
	private ServerManager servermanager = plugin.getServermanager();
	
	@EventHandler
	public void onJoinEvent(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		
		if(arenamanager.isEnabled() == false) {
			return;
		} else
		
		for(Player all : Bukkit.getOnlinePlayers()) {
			if(all.getWorld() == player.getWorld()) {
				all.sendMessage(config.getString("messages.quit").replace("&", "§").replace("%player%", player.getName())
						.replace("%current%", servermanager.getOnline()+"")
						.replace("%maxplayers%", config.getInt("maxplayers")+""));
			} else {
				event.setQuitMessage("");
			}
		}
		
		System.out.println("(-) Disconnected: "+player.getName());
		
		
	}

}