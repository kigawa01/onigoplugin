package net.kigawa.onigoplugin.util.plugin.game.onigo

import net.kigawa.onigoplugin.OnigoPlugin
import net.kigawa.onigoplugin.role.Role
import net.kigawa.onigoplugin.util.plugin.all.player.PlayerGetter
import net.kigawa.onigoplugin.util.plugin.all.recorder.Recorder
import net.kigawa.onigoplugin.util.plugin.all.timer.Counter
import net.kigawa.onigoplugin.util.plugin.game.onigo.runnable.GameCounter
import net.kigawa.onigoplugin.util.plugin.game.onigo.runnable.GameLimiter
import net.kigawa.onigoplugin.util.plugin.game.stage.StageData
import net.kigawa.onigoplugin.util.plugin.game.stage.StageManager
import net.kigawa.onigoplugin.util.plugin.game.stage.runnable.Limiter
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.entity.HumanEntity
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask
import java.util.*

abstract class Game<ROLE_TYPE : Role?>(
  @JvmField var plugin: OnigoPlugin, @JvmField var d: GameData, var manager: GameManager,
  protected var recorder: Recorder,
  stageManager: StageManager, private val playerGetter: PlayerGetter
) : Onigo {
  private val stageManager: StageManager
  var game: Game<*> = this
  var joinPlayer: MutableList<Player> = mutableListOf()
  public var oniPlayer: MutableList<Player> = mutableListOf()
  var runPlayer: MutableList<Player> = mutableListOf()
  var stageData: StageData? = null
  var limiter: Limiter? = null
  var limiter1: Limiter? = null
  var counter: Counter? = null
  var gameCounter: Counter? = null
  var runnable: BukkitTask? = null
  var runnable1: BukkitTask? = null

  init {
    this.stageManager = Objects.requireNonNull(stageManager)
  }

  abstract val bordName: String?
  abstract override fun changeOni(oni: Player, runner: Player): Boolean
  abstract fun sendEndMessage()
  abstract fun runnable()
  abstract fun onStart()
  override fun contain(humanEntity: HumanEntity): Boolean {
    return if (humanEntity is Player) joinPlayer.contains(humanEntity) else false
  }

  fun start(sender: CommandSender, stage: String?) {
    if (d.waitRoomWorld != null) {
      //sort player
      joinPlayer = playerGetter.room(
        d.waitRoomWorld!!, d.waitRoom[0], d.waitRoom[1], d.waitRoom[2],
        d.waitRoom[3], d.waitRoom[4], d.waitRoom[5]
      )
      val random = Random()
      oniPlayer = ArrayList()
      runPlayer = ArrayList()
      runPlayer.addAll(joinPlayer)
      oniPlayer = playerGetter.room(
        d.oniWaitWorld!!,
        d.oniWait[0],
        d.oniWait[1],
        d.oniWait[2],
        d.oniWait[3],
        d.oniWait[4],
        d.oniWait[5]
      )
      joinPlayer.addAll(oniPlayer)
      var randomNumber: Int
      plugin.logger("join player" + joinPlayer.size)
      if (joinPlayer.size > d.oniCount) {
        while (d.oniCount != oniPlayer!!.size) {
          if (d.oniCount > oniPlayer!!.size) {
            randomNumber = random.nextInt(runPlayer!!.size)
            oniPlayer!!.add(runPlayer!!.get(randomNumber))
            runPlayer!!.removeAt(randomNumber)
          }
          if (d.oniCount < oniPlayer!!.size) {
            randomNumber = random.nextInt(oniPlayer!!.size)
            runPlayer!!.add(oniPlayer!![randomNumber])
            oniPlayer!!.removeAt(randomNumber)
          }
        }
        //clear inventory
        for (player in joinPlayer!!) {
          player.inventory.clear()
          //give food
          player.inventory.addItem(ItemStack(Material.BREAD, 64))
        }
        //get stage
        stageData = stageManager.getStage(stage)
        if (stageData != null) {
          for (player in runPlayer) {
            //teleport runner
            player.teleport(
              Location(
                plugin.server.getWorld(stageData!!.stageWorld!!),
                stageData!!.startLoc[0] + 0.5,
                stageData!!.startLoc[1] + 0.5,
                stageData!!.startLoc[2] + 0.5
              )
            )
          }
          //limiter
          limiter = Limiter(plugin, runPlayer, stageData)
          //counter
          counter = Counter(bordName, "onigo", plugin)
          counter!!.startSec(
            0L,
            d.waitTime,
            3,
            joinPlayer,
            ChatColor.GREEN.toString() + "START",
            ChatColor.GREEN
          )
          //count wait time
          runnable = object : BukkitRunnable() {
            override fun run() {
              //send message
              plugin.messenger!!.sendMessage(joinPlayer, ChatColor.GREEN.toString() + "最初に鬼のプレーヤー")
              //teleport oni
              for (player in oniPlayer) {
                player.teleport(
                  Location(
                    plugin.server.getWorld(stageData!!.stageWorld!!),
                    stageData!!.startLoc[0] + 0.5,
                    stageData!!.startLoc[1] + 0.5,
                    stageData!!.startLoc[2] + 0.5
                  )
                )
                plugin.messenger!!.sendMessage(joinPlayer, ChatColor.BLUE.toString() + player.name)
                //wear gold helmet
                player.inventory.helmet = ItemStack(Material.GOLDEN_HELMET)
              }
              //cancel limiter
              limiter!!.cancel()
              //limiter
              limiter1 = GameLimiter(plugin, stageData, game)
              //counter
              counter!!.cancel()
              gameCounter = GameCounter(bordName, name, game)
              //end
              runnable1 = object : BukkitRunnable() {
                override fun run() {
                  end()
                }
              }.runTaskLater(plugin, d.gameTime.toLong() * 20 * 60)
            }
          }.runTaskLater(plugin, d.waitTime * 20L)
          onStart()
        } else {
          sender.sendMessage("stage is not exit")
        }
      } else {
        sender.sendMessage("player is not enough")
      }
    } else {
      sender.sendMessage("need waiting room")
    }
  }

  fun end() {
    //clear inventory
    for (player in joinPlayer!!) {
      player.inventory.clear()
    }
    sendEndMessage()
    //teleport players
    plugin.teleport!!.teleportPlayers(
      joinPlayer, Location(
        plugin.server.getWorld(d.endWorld!!),
        d.endLoc[0] + 0.5,
        d.endLoc[1] + 0.5,
        d.endLoc[2] + 0.5
      )
    )
    //return stage
    stageData?.let { stageManager.returnStage(it) }
    //cancel counter
    counter!!.end()
    gameCounter!!.end()
    //cancel limiter
    limiter!!.cancel()
    limiter1!!.cancel()
    //cancel runnable
    runnable!!.cancel()
    runnable1!!.cancel()
    //remove in list
    while (joinPlayer.isNotEmpty()) {
      joinPlayer.removeAt(0)
    }
    while (oniPlayer.isNotEmpty()) {
      oniPlayer.removeAt(0)
    }
    while (runPlayer.isNotEmpty()) {
      runPlayer.removeAt(0)
    }
    joinPlayer.clear()
    oniPlayer.clear()
    runPlayer.clear()
    stageData = null
    limiter = null
    limiter1 = null
    counter = null
    gameCounter = null
    runnable = null
    runnable1 = null
  }

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
