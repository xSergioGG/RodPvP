package me.zsergio.rodpvp.manage;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import me.zsergio.rodpvp.RodPvP;

public class ServerManager {
	
	private RodPvP plugin = RodPvP.getInstance();
	private ArenaManager arenamanager = plugin.getArenamanager();
	
	private HashMap<UUID, PlayerManager> players = new HashMap<>();
	
	private int online = 0;
	
	public void addPlayer(Player player) {
		if(!players.containsKey(player.getUniqueId())) {
			players.put(player.getUniqueId(), new PlayerManager(player.getUniqueId()));
			online++;
			arenamanager.checkPlayers();
		}
	}
	
	public void removePlayer(Player player) {
		if(players.containsKey(player.getUniqueId())) {
			players.remove(player.getUniqueId());
			online--;
			arenamanager.checkPlayers();
		}
	}
	
	public int getOnline() {
		return online;
	}
}
