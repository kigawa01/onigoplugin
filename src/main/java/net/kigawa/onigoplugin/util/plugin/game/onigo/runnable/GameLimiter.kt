package net.kigawa.onigoplugin.util.plugin.game.onigo.runnable

import net.kigawa.onigoplugin.OnigoPlugin
import net.kigawa.onigoplugin.game.OnigoGame
import net.kigawa.onigoplugin.player.OnigoPlayerManager
import net.kigawa.onigoplugin.util.plugin.game.stage.StageData
import net.kigawa.onigoplugin.util.plugin.game.stage.runnable.Limiter

class GameLimiter(
  onigoPlugin: OnigoPlugin?, stageData: StageData?,
  private val game: OnigoGame,
  playerManager: OnigoPlayerManager
) : Limiter(
  onigoPlugin, playerManager.getAll(
    game, null, null
  ), stageData
) {
  override fun onRun() {
    game.runnable()
  }
}
