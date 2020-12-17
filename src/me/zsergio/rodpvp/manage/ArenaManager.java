package me.zsergio.rodpvp.manage;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.zsergio.rodpvp.RodPvP;

public class ArenaManager {
	
	private static RodPvP plugin = RodPvP.getInstance();
	private static FileConfiguration config = plugin.getConfig();
	
	private ServerManager servermanager = plugin.getServermanager();
	
	private String name;
	private boolean enabled;
	
	private Location spawn;
	
	private int task = 0;
	private int countdown = 30;
	private int backupcontdown = countdown;
	private int maxplayers;
	private int minplayers;
	
	private List<String> players = new ArrayList<>();
	
	public ArenaManager() {
		getAll();
	}
	
	public int getMinplayers() {
		return minplayers;
	}
	
	public int getMaxplayers() {
		return maxplayers;
	}
	
	@SuppressWarnings("deprecation")
	public void startCountdown() {
		task = Bukkit.getScheduler().scheduleAsyncRepeatingTask(plugin, new Runnable() {
			
			@Override
			public void run() {
				if(countdown <= 30) {
					for(String str : players) {
						Player all = Bukkit.getPlayer(str);
						all.sendMessage("§7La partida empieza en §e"+countdown+" Segundos§7!");
						checkPlayers();
					}
					countdown--;
				}
				
			}
		}, 0L, 20L);
	}
	
	public void startGame() {
		
	}
	
	public void checkPlayers() {
		if(servermanager.getOnline() >= minplayers) {
			if(task == 0) {
				startCountdown();
			}
		} else {
			Bukkit.getScheduler().cancelTask(task);
			return;
		}
	}
	
	public void setSpawn(Location loc) {
		this.spawn = loc;
		config.set("spawn.world", loc.getWorld().getName());
		config.set("spawn.x", loc.getX());
		config.set("spawn.y", loc.getY());
		config.set("spawn.z", loc.getZ());
		config.set("spawn.pitch", loc.getPitch());
		config.set("spawn.yaw", loc.getYaw());
		plugin.saveConfig();
	}
	
	public void setName(String name) {
		this.name = name;
		config.set("name", name);
		plugin.saveConfig();
	}
	
	public String getName() {
		return name;
	}
	
	public Location getSpawn() {
		return spawn;
	}
	
	public List<String> getPlayers() {
		return players;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		config.set("enabled", enabled);
		plugin.saveConfig();
	}
	
	private void getAll() {
		
		if(config.contains("name")) {
			name = config.getString("name");
		}
		
		if(config.contains("enabled")) {
			enabled = config.getBoolean("enabled");
		} else {
			enabled = false;
		}
		
		if(config.contains("spawn.world") && config.contains("spawn.yaw")) {
			World world = Bukkit.getWorld(config.getString("spawn.world"));
			double x = config.getDouble("spawn.x");
			double y = config.getDouble("spawn.y");
			double z = config.getDouble("spawn.z");
			float pitch = config.getInt("spawn.pitch");
			float yaw = config.getInt("spawn.pitch");
			spawn = new Location(world, x, y, z, pitch, yaw);
		}
		
		if(config.contains("maxplayers")) {
			maxplayers = config.getInt("maxplayers");
		} if(config.contains("minplayers")) {
			maxplayers = config.getInt("minplayers");
		}
	}

}
