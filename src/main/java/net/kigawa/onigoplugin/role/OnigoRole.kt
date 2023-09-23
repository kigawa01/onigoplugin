package net.kigawa.onigoplugin.role

import net.kigawa.onigoplugin.game.OnigoGame
import net.kigawa.onigoplugin.player.OnigoPlayerImpl

enum class OnigoRole(val label: String) : Role<OnigoRole, OnigoGame> {
  RUNNER("逃走者") {
    override fun become(player: OnigoPlayerImpl<OnigoRole, OnigoGame>, game: OnigoGame): Unit =
      game.becomeRunner(player)
  },
  ONI("鬼") {
    override fun become(player: OnigoPlayerImpl<OnigoRole, OnigoGame>, game: OnigoGame): Unit =
      game.becomeOni(player)
  },
}