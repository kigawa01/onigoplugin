package net.kigawa.onigoplugin.player

import net.kigawa.onigoplugin.role.Role
import net.kigawa.onigoplugin.util.plugin.game.onigo.Game
import java.util.*

class OnigoPlayer<
    ROLE : Role<ROLE, GAME>,
    GAME : Game<ROLE, GAME>
    >(
  val uuid: UUID,
  val game: Game<ROLE, GAME>,
  var role: ROLE,
) {
}
