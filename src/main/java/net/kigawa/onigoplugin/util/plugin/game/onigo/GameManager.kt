package net.kigawa.onigoplugin.util.plugin.game.onigo

import net.kigawa.kutil.kutil.list.contains
import net.kigawa.onigoplugin.OnigoPlugin
import net.kigawa.onigoplugin.util.plugin.all.recorder.Recorder
import org.bukkit.command.CommandSender
import org.bukkit.entity.HumanEntity
import org.bukkit.entity.Player
import java.io.File

abstract class GameManager(@JvmField var plugin: OnigoPlugin, var name: String, @JvmField protected var recorder: Recorder) : Onigo {
  var games: MutableList<Game> = ArrayList()
    protected set

  init {
    val folder = File(plugin.dataFolder, name)
    folder.mkdir()
  }

  override fun contain(player: HumanEntity): Boolean {
    return games.contains { it.contain(player) }
  }

  override fun changeOni(oni: Player, runner: Player): Boolean {
    return games.contains { it.changeOni(oni, runner) }
  }

  fun endAll() {
    for (game in games) {
      game.end()
    }
  }

  fun getGame(gameName: String?): Game? {
    return games.firstOrNull { it.name == gameName }
  }

  abstract fun createGame(sender: CommandSender?, name: String?)
  fun containGame(name: String): Boolean {
    return games.stream().filter { game: Game -> game.name == name }.toList().isNotEmpty()
  }
}
