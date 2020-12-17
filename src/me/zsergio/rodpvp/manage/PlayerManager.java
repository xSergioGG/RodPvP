package me.zsergio.rodpvp.manage;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.zsergio.rodpvp.RodPvP;

public class PlayerManager {
	
	private RodPvP plugin = RodPvP.getInstance();
	private FileConfiguration playerdata = plugin.getPlayerdata().getConfig();
	
	private UUID uuid;
	
	private Player player;
	private String name;
	
	private int points = 0;
	
	public PlayerManager(UUID uuid) {
		this.uuid = uuid;
		player = Bukkit.getPlayer(uuid);
		name = player.getName();
		getAll();
	}
	
	public int getPoints() {
		return points;
	}
	
	public void addPoints(int toadd) {
		points = getPoints()+toadd;
	}
	
	public void getAll() {
		
		if(playerdata.contains("points."+name)) {
			plugin.getPlayerdata().reloadConfig();
			points = playerdata.getInt("points."+name);
		} else {
			playerdata.set("points."+name, points);
			plugin.getPlayerdata().saveConfig();
		}
		
	}

}
