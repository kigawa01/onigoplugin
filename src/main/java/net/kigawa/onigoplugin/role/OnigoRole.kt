package net.kigawa.onigoplugin.role

import net.kigawa.onigoplugin.game.OnigoGame
import net.kigawa.onigoplugin.player.OnigoPlayer

enum class OnigoRole(val label: String) : Role<OnigoRole, OnigoGame> {
  RUNNER("逃走者") {
    override fun OnigoPlayer<OnigoRole, OnigoGame>.become(game: OnigoGame): Unit = game.becomeRunner(this)
  },
  ONI("鬼") {
    override fun OnigoPlayer<OnigoRole, OnigoGame>.become(game: OnigoGame): Unit = game.becomeOni(this)
  },
}