package net.kigawa.onigoplugin.listener

import net.kigawa.kutil.kutil.list.contains
import net.kigawa.kutil.unitapi.annotation.Kunit
import net.kigawa.onigoplugin.util.plugin.game.onigo.GameManager
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityDamageEvent
import java.net.http.WebSocket.Listener

@Kunit
class OnigoListener(
  private val gameManager: GameManager
) : Listener {
  @EventHandler
  fun cancelDamage(event: EntityDamageEvent) {
    val player = event.entity
    if (player !is Player) return
    if (!gameManager.games.contains { it.contain(player) }) return
    event.isCancelled = true
  }
}