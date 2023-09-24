package net.kigawa.onigoplugin.player

import net.kigawa.onigoplugin.role.Role
import net.kigawa.onigoplugin.util.plugin.game.onigo.Game
import org.bukkit.entity.Player
import java.util.*

class OnigoPlayerImpl<
    ROLE : Role<ROLE, GAME>,
    GAME : Game<ROLE, GAME>
    >(
  override val uuid: UUID,
  override val game: GAME,
  role: ROLE
) : OnigoPlayer<ROLE, GAME> {
  private val offlineTasks = mutableListOf<(Player) -> Unit>()

  override var role: ROLE = role
    set(value) {
      field = value
      value.become(this, game)
    }

  override var player: Player? = null
    set(value) {
      field = value
      if (value == null) {
        return
      }
      while (true) {
        offlineTasks
          .removeFirstOrNull()
          ?.also { it.invoke(value) }
          ?: break
      }
    }

  init {
    this.role.become(this, game)
  }

  override fun usePlayer(task: (Player) -> Unit) {
    player?.let(task) ?: offlineTasks.add(task)
  }
}
