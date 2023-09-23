package net.kigawa.onigoplugin.util.plugin.game.onigo.evnt

import net.kigawa.kutil.unitapi.annotation.Kunit
import net.kigawa.onigoplugin.OnigoPlugin
import net.kigawa.onigoplugin.player.OnigoPlayerManager
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.inventory.InventoryClickEvent

@Kunit
class OnigoListener(
  private val plugin: OnigoPlugin,
  private val playerManager: OnigoPlayerManager,
) : Listener {
  @EventHandler
  fun onClickEntity(event: EntityDamageByEntityEvent) {
    if (event.entity is Player) {
      if (event.damager is Player) {
        plugin.getGameManagers().forEach {
          it.changeOni(event.damager as Player, event.entity as Player)
        }
      }
    }
  }

  @EventHandler
  fun onInventoryClick(event: InventoryClickEvent) {
    if (playerManager.contains(event.whoClicked.uniqueId, null)) {
      event.isCancelled = true
    }
  }
}
