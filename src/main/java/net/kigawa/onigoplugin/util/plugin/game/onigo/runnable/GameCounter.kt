package net.kigawa.onigoplugin.util.plugin.game.onigo.runnable

import net.kigawa.onigoplugin.game.OnigoGame
import net.kigawa.onigoplugin.player.OnigoPlayerManager
import net.kigawa.onigoplugin.util.plugin.all.timer.Counter
import org.bukkit.ChatColor

class GameCounter(
  bordName: String?,
  bordID: String?,
  private val game: OnigoGame,
  private val playerManager: OnigoPlayerManager
) : Counter(bordName, bordID, game.plugin, game) {
  init {
    //replace variable

    //start count
    startMin(
      0L, game.d.gameTime, 3, playerManager.getAll(game, null, null), ChatColor.RED.toString() + "END", ChatColor.GREEN
    )
  }

  override fun sendLastMessage() {
    //send end
    game.plugin.messenger!!.sendTitle(
      playerManager.getAll(game, null, null),
      ChatColor.RED.toString() + "END",
      ""
    )
  }
}
