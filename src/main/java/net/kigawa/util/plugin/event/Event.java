package net.kigawa.util.plugin.event;

import net.kigawa.util.plugin.KigawaPlugin;
import org.bukkit.event.Listener;

public class Event implements Listener {
    KigawaPlugin plugin;
    public Event(KigawaPlugin kigawaPlugin){
        plugin=kigawaPlugin;
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }
}
