package net.kigawa.util.plugin.event.onigo;

import net.kigawa.onigoplugin.OnigoPlugin;
import net.kigawa.util.plugin.event.Event;
import net.kigawa.util.plugin.game.onigo.EqualsOniChange;
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
                plugin.getGameManagers().contains(new EqualsOniChange((Player) event.getDamager(),(Player) event.getEntity()));
            }
        }
    }
}
