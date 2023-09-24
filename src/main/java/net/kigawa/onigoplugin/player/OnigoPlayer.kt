package net.kigawa.onigoplugin.player

import net.kigawa.onigoplugin.role.Role
import net.kigawa.onigoplugin.util.plugin.game.onigo.Game
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.inventory.PlayerInventory
import org.bukkit.scoreboard.Scoreboard
import java.util.*

interface OnigoPlayer<ROLE : Role<ROLE, GAME>, GAME : Game<ROLE, GAME>> {
  val uuid: UUID
  var role: ROLE
  var player: Player?
  val game: GAME
  fun usePlayer(task: Player.() -> Unit)
  fun sendMessage(message: String) = usePlayer { sendMessage(message) }
  fun teleport(location: Location) = usePlayer { teleport(location) }
  fun setScoreboard(scoreboard: Scoreboard) = usePlayer { setScoreboard(scoreboard) }
  fun setGameMode(gameMode: GameMode) = usePlayer { this.gameMode = gameMode }
  fun sendTitle(title: String, subtitle: String, fadeIn: Int, stay: Int, fadeOut: Int) =
    usePlayer { sendTitle(title, subtitle, fadeIn, stay, fadeOut) }

  fun useInventory(func: PlayerInventory.() -> Unit) {
    return usePlayer {
      func(inventory)
    }
  }

  fun useScoreboard(func: Scoreboard.() -> Unit) {
    return usePlayer {
      func(scoreboard)
    }
  }
}