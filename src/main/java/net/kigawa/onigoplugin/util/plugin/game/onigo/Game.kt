package net.kigawa.onigoplugin.util.plugin.game.onigo

import net.kigawa.kutil.unitapi.component.container.UnitContainer
import net.kigawa.onigoplugin.OnigoPlugin
import net.kigawa.onigoplugin.player.OnigoPlayer
import net.kigawa.onigoplugin.player.OnigoPlayerManager
import net.kigawa.onigoplugin.role.Role
import net.kigawa.onigoplugin.util.plugin.all.player.PlayerGetter
import net.kigawa.onigoplugin.util.plugin.all.recorder.Recorder
import net.kigawa.onigoplugin.util.plugin.all.timer.Counter
import net.kigawa.onigoplugin.util.plugin.game.stage.StageData
import net.kigawa.onigoplugin.util.plugin.game.stage.StageManager
import net.kigawa.onigoplugin.util.plugin.game.stage.runnable.Limiter
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitTask
import java.util.*

abstract class Game<ROLE_TYPE : Role<ROLE_TYPE, GAME>, GAME : Game<ROLE_TYPE, GAME>>(
  var plugin: OnigoPlugin,
  var d: GameData,
  private var manager: GameManager,
  protected var recorder: Recorder,
  stageManager: StageManager,
  protected val playerGetter: PlayerGetter,
  container: UnitContainer
) : Onigo {
  protected val stageManager: StageManager
  protected val playerManager = container.getUnit(OnigoPlayerManager::class.java)
  var stageData: StageData? = null
  var limiter: Limiter? = null
  var limiter1: Limiter? = null
  var counter: Counter? = null
  var gameCounter: Counter? = null
  protected var runnable: BukkitTask? = null
  var runnable1: BukkitTask? = null
  abstract val becomeOni: (player: OnigoPlayer<ROLE_TYPE, GAME>) -> Unit
  abstract val becomeRunner: (player: OnigoPlayer<ROLE_TYPE, GAME>) -> Unit

  init {
    this.stageManager = Objects.requireNonNull(stageManager)
  }

  abstract val bordName: String?
  abstract override fun changeOni(oniPlayer: Player, runnerPlayer: Player): Boolean
  abstract fun sendEndMessage()
  abstract fun runnable()

  abstract fun start(sender: CommandSender, stage: String?)
  abstract fun end()
  fun setEndLoc(world: String?, x: Int, y: Int, z: Int) {
    val loc = d.endLoc
    loc[0] = x
    loc[1] = y
    loc[2] = z
    d.endWorld = world
    recorder.save(d, manager.name)
  }

  fun setGameTime(gameTime: Int) {
    d.gameTime = gameTime
    recorder.save(d, manager.name)
  }

  fun setWaitTime(waitTime: Int) {
    d.waitTime = waitTime
    recorder.save(d, manager.name)
  }

  fun setOniCount(oniCount: Int) {
    d.oniCount = oniCount
    recorder.save(d, manager.name)
  }

  fun setOniWait1(world: String?, x: Int, y: Int, z: Int) {
    val loc = d.oniWait
    loc[0] = x
    loc[1] = y
    loc[2] = z
    d.oniWaitWorld = world
    recorder.save(d, manager.name)
  }

  fun setOniWait2(x: Int, y: Int, z: Int) {
    val loc = d.oniWait
    loc[3] = x
    loc[4] = y
    loc[5] = z
    recorder.save(d, manager.name)
  }

  fun setWaitingRoom1(world: String?, x: Int, y: Int, z: Int) {
    val loc = d.waitRoom
    loc[0] = x
    loc[1] = y
    loc[2] = z
    d.waitRoomWorld = world
    recorder.save(d, manager.name)
  }

  fun setWaitingRoom2(x: Int, y: Int, z: Int) {
    val loc = d.waitRoom
    loc[3] = x
    loc[4] = y
    loc[5] = z
    recorder.save(d, manager.name)
  }

  val name: String
    get() = d.name

  var gameType: String?
    get() = d.gameType
    set(gameType) {
      d.gameType = gameType
      recorder.save(d, manager.name)
    }
}
