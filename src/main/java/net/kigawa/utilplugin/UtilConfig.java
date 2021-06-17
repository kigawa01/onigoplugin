package net.kigawa.utilplugin;

import net.kigawa.utilplugin.config.ConfigData;
import org.bukkit.configuration.file.FileConfiguration;

public class UtilConfig extends ConfigData {
    UtilPlugin plugin;
    FileConfiguration config;
    public UtilConfig(UtilPlugin plugin){
        super(plugin);
        this.plugin=plugin;
    }
    public FileConfiguration getConfig(){
        return config;
    }
    public String getHost(){
        return config.getString("host");
    }
    public int getPort(){
        return config.getInt("port");
    }
    public String getDatabase(){
        return config.getString("database name");
    }
    public String getUser(){
        return config.getString("user name");
    }
    public String getPassword(){
        return config.getString("password");
    }
    public String getOption(){
        return config.getString("option");
    }
}
