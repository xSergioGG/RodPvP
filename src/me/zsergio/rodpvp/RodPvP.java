package me.zsergio.rodpvp;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.zsergio.rodpvp.commands.configCMD;
import me.zsergio.rodpvp.files.SharePointFile;
import me.zsergio.rodpvp.listener.onJoin;
import me.zsergio.rodpvp.listener.onQuit;
import me.zsergio.rodpvp.manage.ArenaManager;
import me.zsergio.rodpvp.manage.ServerManager;

public class RodPvP extends JavaPlugin {
	
	private static RodPvP instance;
	
	private SharePointFile playerdata;
	private ServerManager servermanager;
	private ArenaManager arenamanager;
	
	@Override
	public void onEnable() {
		instance = this;
		playerdata = new SharePointFile();
		servermanager = new ServerManager();
		arenamanager = new ArenaManager();
		
		playerdata.setup("..\\DATA", "playerdata.yml");
		
		regConfig();
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new onJoin(), this);
		pm.registerEvents(new onQuit(), this);
		
		getCommand("config").setExecutor(new configCMD());
		
	}
	
	@Override
	public void onDisable() {
		getPlayerdata().saveConfig();
	}
	
	private void regConfig() {
		File config = new File(this.getDataFolder(), "config.yml");
		
		if(!config.exists()) {
			this.getConfig().options().copyDefaults(true);
		}
		saveConfig();
	}
	
	public ArenaManager getArenamanager() {
		return arenamanager;
	}
	
	public static RodPvP getInstance() {
		return instance;
	}
	
	public SharePointFile getPlayerdata() {
		return playerdata;
	}

	public ServerManager getServermanager() {
		return servermanager;
	}
	
}
