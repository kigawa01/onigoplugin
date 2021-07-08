package net.kigawa.onigoplugin.event.change;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.plugin.event.Event;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class OnigoListener extends Event {
    OnigoPlugin plugin;
    public OnigoListener(OnigoPlugin onigoPlugin) {
        super(onigoPlugin);
        plugin=onigoPlugin;
    }
    @EventHandler
    public void onClickEntity(EntityDamageByEntityEvent event){
        if (event.getEntity() instanceof Player) {
            if (event.getDamager() instanceof Player){
                plugin.getChangeGame().changeOni((Player) event.getDamager(),(Player) event.getEntity());
            }
        }
    }
}
