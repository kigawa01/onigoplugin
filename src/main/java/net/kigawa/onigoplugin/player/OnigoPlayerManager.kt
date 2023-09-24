package net.kigawa.onigoplugin.player

import net.kigawa.kutil.kutil.list.contains
import net.kigawa.onigoplugin.role.Role
import net.kigawa.onigoplugin.util.plugin.game.onigo.Game
import org.bukkit.Bukkit
import java.util.*

class OnigoPlayerManager {
  private val onigoPlayers = mutableListOf<OnigoPlayer<*, *>>()

  fun contains(uuid: UUID, role: Role<*, *>? = null): Boolean {
    return onigoPlayers.filter {
      it.uuid == uuid
    }.contains {
      it.role == role
    }
  }

  fun <
      ROLE : Role<ROLE, GAME>,
      GAME : Game<ROLE, GAME>
      > registerAll(uuids: List<UUID>, game: GAME, role: ROLE): List<OnigoPlayer<ROLE, GAME>> {
    return uuids.map { register(it, game, role) }
  }

  fun <
      ROLE : Role<ROLE, GAME>,
      GAME : Game<ROLE, GAME>
      > register(uuid: UUID, game: GAME, role: ROLE): OnigoPlayer<ROLE, GAME> {
    val onigoPlayer = get(game, uuid) ?: OnigoPlayerImpl(uuid, game, role)
    onigoPlayers.add(onigoPlayer)

    val player = Bukkit.getPlayer(uuid)
    if (player != null) {
      onigoPlayer.player = player
    }
    return onigoPlayer
  }

  fun <
      ROLE : Role<ROLE, GAME>,
      GAME : Game<ROLE, GAME>
      > get(game: GAME, uuid: UUID): OnigoPlayer<ROLE, GAME>? {
    return getAll(game, uuid).firstOrNull()
  }

  fun <
      ROLE : Role<ROLE, GAME>,
      GAME : Game<ROLE, GAME>
      > getAll(
    game: GAME, uuid: UUID? = null, role: ROLE? = null
  ): List<OnigoPlayer<ROLE, GAME>> {

    return onigoPlayers
      .filterIsInstance<OnigoPlayer<ROLE, GAME>>()
      .filter { filter(it, game, uuid, role) }
  }

  fun <
      ROLE : Role<ROLE, GAME>,
      GAME : Game<ROLE, GAME>
      > removeAll(game: GAME, uuid: UUID? = null, role: ROLE? = null) {
    onigoPlayers
      .filterIsInstance<OnigoPlayer<ROLE, GAME>>()
      .filter { filter(it, game, uuid, role) }
      .let { onigoPlayers.removeAll(it) }
  }

  private fun <
      ROLE : Role<ROLE, GAME>,
      GAME : Game<ROLE, GAME>
      > filter(player: OnigoPlayer<ROLE, GAME>, game: GAME, uuid: UUID?, role: ROLE?): Boolean {
    if (player.game != game) return false
    if (uuid != null && player.uuid != uuid) return false
    if (role != null && player.role != role) return false
    return true
  }
}