package net.kigawa.onigoplugin.util.plugin.all.event;

import net.kigawa.onigoplugin.OnigoPlugin;
import org.bukkit.event.Listener;

public class Event implements Listener {
    OnigoPlugin plugin;
    public Event(OnigoPlugin OnigoPlugin){
        plugin=OnigoPlugin;
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }
}
