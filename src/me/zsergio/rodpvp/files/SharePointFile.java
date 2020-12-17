package me.zsergio.rodpvp.files;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class SharePointFile {
	
	private File file;
	private FileConfiguration cfile;
	
	public void setup(String directory, String filename) {
		File dir = new File(directory);
		File file = new File(directory, filename);
		
		this.file = file;
		
		if(!dir.exists()) {
			dir.mkdir();
		}
		
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				return;
			}
		}
		
		cfile = YamlConfiguration.loadConfiguration(file);
		
	}
	
	public void reload(String directory, String filename) {
		File file = new File(directory, filename);
		
		this.file = file;
	}
	
	public FileConfiguration getConfig() {
		return cfile;
	}
	
	public void saveConfig() {

		try {
			cfile.save(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
	}
	
	public void reloadConfig() {
		cfile = YamlConfiguration.loadConfiguration(file);
	}
	
}
