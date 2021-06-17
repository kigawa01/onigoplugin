package net.kigawa.utilplugin.config;

import jdk.tools.jlink.plugin.Plugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class ConfigData {
    JavaPlugin plugin;
    FileConfiguration config;
    public ConfigData(JavaPlugin plugin){
        this.plugin=plugin;
        config=plugin.getConfig();

    }
    public FileConfiguration getConfig(){
        return config;
    }
}
