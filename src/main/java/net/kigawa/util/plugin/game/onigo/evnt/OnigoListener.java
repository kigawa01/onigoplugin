package net.kigawa.util.plugin.game.onigo.evnt;

import net.kigawa.util.plugin.all.KigawaPlugin;
import net.kigawa.util.plugin.all.event.Event;
import net.kigawa.util.plugin.game.onigo.list.EqualsOnigoManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

public class OnigoListener extends Event {
    KigawaPlugin plugin;

    public OnigoListener(KigawaPlugin onigoPlugin) {
        super(onigoPlugin);
        plugin = onigoPlugin;
    }

    @EventHandler
    public void onClickEntity(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            if (event.getDamager() instanceof Player) {
                plugin.getGameManagers().contains(new EqualsOnigoManager((Player) event.getDamager(), (Player) event.getEntity()));
            }
        }
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if (plugin.getGameManagers().contains(new EqualsOnigoManager(event.getWhoClicked()))){
            event.setCancelled(true);
        }
    }
}
