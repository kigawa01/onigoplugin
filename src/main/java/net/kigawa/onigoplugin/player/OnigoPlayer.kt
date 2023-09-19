package net.kigawa.onigoplugin.player

import net.kigawa.onigoplugin.role.Role
import net.kigawa.onigoplugin.util.plugin.game.onigo.Game
import java.util.*

class OnigoPlayer<ROLE : Role>(
  val uuid: UUID,
  val game: Game<ROLE>,
  var role: ROLE,
)