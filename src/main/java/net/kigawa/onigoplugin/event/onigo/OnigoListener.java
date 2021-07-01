package net.kigawa.onigoplugin.event.onigo;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.plugin.KigawaPlugin;
import net.kigawa.util.plugin.event.Event;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.*;

public class OnigoListener extends Event {
    OnigoPlugin plugin;
    public OnigoListener(OnigoPlugin onigoPlugin) {
        super(onigoPlugin);
        plugin=onigoPlugin;
    }
    @EventHandler
    public void onClickEntity(EntityDamageByEntityEvent event){
        event.getEntity().sendMessage(event.getEventName()+"a");
        if (event.getEntity() instanceof Player) {
            if (event.getDamager() instanceof Player){
                plugin.getOnigoManager().changeOni((Player) event.getDamager(),(Player) event.getEntity());
            }
        }
    }
}
