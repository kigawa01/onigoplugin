package net.kigawa.onigoplugin.game

import net.kigawa.kutil.kutil.list.contains
import net.kigawa.kutil.unitapi.annotation.Kunit
import net.kigawa.onigoplugin.OnigoPlugin
import net.kigawa.onigoplugin.util.plugin.all.player.PlayerGetter
import net.kigawa.onigoplugin.util.plugin.all.recorder.Recorder
import net.kigawa.onigoplugin.util.plugin.game.onigo.Game
import net.kigawa.onigoplugin.util.plugin.game.onigo.GameData
import net.kigawa.onigoplugin.util.plugin.game.onigo.GameManager
import net.kigawa.onigoplugin.util.plugin.game.stage.StageManager
import org.bukkit.command.CommandSender

@Kunit
class ChangeManager(
  OnigoPlugin: OnigoPlugin?,
  recorder: Recorder,
  private val stageManager: StageManager,
  private val playerGetter: PlayerGetter
) : GameManager(OnigoPlugin!!, "change", recorder) {
  init {
    games = initializeGame(recorder.loadAll(GameData::class.java, name))
  }

  fun initializeGame(data: List<GameData?>): MutableList<Game> {
    val games: MutableList<Game> = ArrayList()

    //take out data list
    for (gameData in data) {
      games.add(OnigoGame(plugin, gameData, this, recorder, stageManager, playerGetter))
    }
    return games
  }

  override fun createGame(sender: CommandSender?, name: String?) {
    if (!games.contains { it.name == name }) {
      val data = OnigoData(name!!)
      games.add(OnigoGame(plugin, data, this, recorder, stageManager, playerGetter))
      recorder.save(data, name)
    } else {
      sender!!.sendMessage("this name can't use")
    }
  }
}
