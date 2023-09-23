package net.kigawa.onigoplugin.game

import net.kigawa.kutil.unitapi.component.container.UnitContainer
import net.kigawa.onigoplugin.OnigoPlugin
import net.kigawa.onigoplugin.player.OnigoPlayer
import net.kigawa.onigoplugin.role.OnigoRole
import net.kigawa.onigoplugin.util.plugin.all.player.PlayerGetter
import net.kigawa.onigoplugin.util.plugin.all.recorder.Recorder
import net.kigawa.onigoplugin.util.plugin.all.timer.Counter
import net.kigawa.onigoplugin.util.plugin.game.onigo.Game
import net.kigawa.onigoplugin.util.plugin.game.onigo.GameData
import net.kigawa.onigoplugin.util.plugin.game.onigo.GameManager
import net.kigawa.onigoplugin.util.plugin.game.onigo.runnable.GameCounter
import net.kigawa.onigoplugin.util.plugin.game.onigo.runnable.GameLimiter
import net.kigawa.onigoplugin.util.plugin.game.stage.StageManager
import net.kigawa.onigoplugin.util.plugin.game.stage.runnable.Limiter
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.scheduler.BukkitRunnable
import java.util.*

class OnigoGame(
  onigoPlugin: OnigoPlugin?,
  gameData: GameData?,
  manager: GameManager?,
  recorder: Recorder?,
  stageManager: StageManager?,
  playerGetter: PlayerGetter?,
  container: UnitContainer?
) : Game<OnigoRole, OnigoGame>(
  onigoPlugin!!, gameData!!, manager!!, recorder!!, stageManager!!, playerGetter!!, container!!
) {
  private var helmet = ItemStack(Material.GOLDEN_HELMET)
  private var caughtPlayer: MutableList<Player> = ArrayList()
  override val becomeOni: (player: OnigoPlayer<OnigoRole, OnigoGame>) -> Unit
    get() = {
      TODO("Not yet implemented")
    }
  override val becomeRunner: (player: OnigoPlayer<OnigoRole, OnigoGame>) -> Unit
    get() = {
      TODO("Not yet implemented")
    }
  override val bordName: String
    get() = "鬼ごっこ"

  override fun changeOni(oniPlayer: Player, runnerPlayer: Player): Boolean {
    val oni = playerManager.get(this, oniPlayer.uniqueId) ?: return false
    val runner = playerManager.get(this, runnerPlayer.uniqueId) ?: return false
    val joinPlayer = playerManager.getAll(this)
    if (oni.role == OnigoRole.ONI && runner.role == OnigoRole.RUNNER) {

      //check game type
      when (gameType) {
        "change" -> {
          //change runner list
          runner.role = OnigoRole.ONI
          //change oni list
          oni.role = OnigoRole.RUNNER
          //oni to runner
          oni.sendTitle(ChatColor.GREEN.toString() + "鬼を交代しました", "", 5, 15, 5)
          oni.useInventory {
            helmet = null
          }
          //runner to oni
          runnerPlayer.sendTitle(ChatColor.RED.toString() + "鬼になりました", "", 5, 15, 5)
          runnerPlayer.addPotionEffect(PotionEffect(PotionEffectType.BLINDNESS, 100, 1))
          runnerPlayer.addPotionEffect(PotionEffect(PotionEffectType.SLOW, 100, 1))
          //send all player
          plugin.messenger!!.sendMessage<OnigoRole, OnigoGame>(
            joinPlayer, ChatColor.GREEN.toString() + "鬼が変わりました"
          )
          plugin.messenger!!.sendMessage<OnigoRole, OnigoGame>(
            joinPlayer,
            ChatColor.BLUE.toString() + oniPlayer.name + ChatColor.WHITE + "→" + ChatColor.BLUE + runnerPlayer.name
          )
        }

        "increase" -> {
          //changed runner list
          runner.role = OnigoRole.ONI
          //send title
          runnerPlayer.sendTitle(ChatColor.RED.toString() + "鬼になりました", "", 5, 15, 5)
          oniPlayer.sendTitle(ChatColor.GREEN.toString() + "逃走者を捕まえました", "", 5, 15, 5)
          //send message
          Objects.requireNonNull(plugin.messenger)!!.sendMessage<OnigoRole, OnigoGame>(
            joinPlayer, ChatColor.GREEN.toString() + "鬼が増えました"
          )
          plugin.messenger!!.sendMessage<OnigoRole, OnigoGame>(
            joinPlayer,
            ChatColor.BLUE.toString() + oniPlayer.name + ChatColor.WHITE + "→" + ChatColor.BLUE + runnerPlayer.name
          )
        }

      }
      //return
      return true
    }
    return false
  }

  override fun sendEndMessage() {
    val joinPlayer = playerManager.getAll(this)
    val oniPlayer = playerManager.getAll(this, role = OnigoRole.ONI)
    val runPlayer = playerManager.getAll(this, role = OnigoRole.RUNNER)

    //new list
    val players: List<OnigoPlayer<OnigoRole, OnigoGame>> = LinkedList<OnigoPlayer<OnigoRole, OnigoGame>>(joinPlayer)
    when (gameType) {
      "change" -> {
        //send oni name
        plugin.messenger!!.sendMessage<OnigoRole, OnigoGame>(
          joinPlayer, ChatColor.GREEN.toString() + "最後に鬼だったプレーヤー"
        )
        for (player in oniPlayer) {
          plugin.messenger!!.sendMessage<OnigoRole, OnigoGame>(joinPlayer, ":" + ChatColor.BLUE + player.name)
        }
        //send title
        object : BukkitRunnable() {
          override fun run() {
            //send lose
            plugin.messenger!!.sendTitle<OnigoRole, OnigoGame>(
              players, ChatColor.RED.toString() + "敗北", ""
            )
            //send win
            plugin.messenger!!.sendTitle<OnigoRole, OnigoGame>(players, ChatColor.RED.toString() + "勝利", "")
          }
        }.runTaskLater(plugin, 40)
      }

      "ice" -> {
        for (player in caughtPlayer) {
          player.walkSpeed = 0.2f
          player.flySpeed = 0.2f
        }


        //runner win
        if (!runPlayer.isEmpty()) {
          plugin.messenger!!.sendTitleLater<OnigoRole, OnigoGame>(
            players, ChatColor.GREEN.toString() + "逃走者の勝利", 40L
          )
        }
        //oni win
        if (runPlayer.isEmpty()) {
          plugin.messenger!!.sendTitleLater<OnigoRole, OnigoGame>(
            players, ChatColor.RED.toString() + "鬼の勝利", 40L
          )
        }
      }

      "increase" -> {
        if (runPlayer.isNotEmpty()) {
          plugin.messenger!!.sendTitleLater<OnigoRole, OnigoGame>(
            players, ChatColor.GREEN.toString() + "逃走者の勝利", 40L
          )
        }
        if (runPlayer.isEmpty()) {
          plugin.messenger!!.sendTitleLater<OnigoRole, OnigoGame>(
            players, ChatColor.RED.toString() + "鬼の勝利", 40L
          )
        }
      }

      else -> throw IllegalStateException("Unexpected value: $gameType")
    }
  }

  override fun runnable() {
    val oniPlayer = playerManager.getAll(this, role = OnigoRole.ONI)
    val runPlayer = playerManager.getAll(this, role = OnigoRole.RUNNER)

    for (player in oniPlayer) {
      player.useInventory {
        helmet = this@OnigoGame.helmet
      }
    }
    when (gameType) {
      "increase", "ice" -> {
        //check runner
        if (runPlayer.isEmpty()) {
          end()
        }
      }
    }
  }

  override fun onStart() {}
  override fun start(sender: CommandSender, stage: String?) {

    if (d.waitRoomWorld != null) {
      //sort player
      playerGetter.room(
        d.waitRoomWorld!!, d.waitRoom[0], d.waitRoom[1], d.waitRoom[2],
        d.waitRoom[3], d.waitRoom[4], d.waitRoom[5]
      ).map(Player::getUniqueId)
        .let { playerManager.registerAll(it, this, OnigoRole.RUNNER) }
      playerGetter.room(
        d.oniWaitWorld!!,
        d.oniWait[0],
        d.oniWait[1],
        d.oniWait[2],
        d.oniWait[3],
        d.oniWait[4],
        d.oniWait[5]
      ).map(Player::getUniqueId)
        .let { playerManager.registerAll(it, this, OnigoRole.ONI) }

      val random = Random()
      var randomNumber: Int
      plugin.logger("join player" + playerManager.getAll(this).size)
      if (playerManager.getAll(this).size > d.oniCount) {
        while (d.oniCount != playerManager.getAll(this, role = OnigoRole.ONI).size) {
          if (d.oniCount > playerManager.getAll(this, role = OnigoRole.ONI).size) {
            randomNumber = random.nextInt(playerManager.getAll(this, role = OnigoRole.RUNNER).size)
            playerManager.getAll(this, role = OnigoRole.RUNNER)[randomNumber].role = OnigoRole.ONI
          }
          if (d.oniCount < playerManager.getAll(this, role = OnigoRole.ONI).size) {
            randomNumber = random.nextInt(playerManager.getAll(this, role = OnigoRole.ONI).size)
            playerManager.getAll(this, role = OnigoRole.ONI)[randomNumber].role = OnigoRole.RUNNER
          }
        }
        val joinPlayer = playerManager.getAll(this)
        val oniPlayer = playerManager.getAll(this, role = OnigoRole.ONI)
        val runPlayer = playerManager.getAll(this, role = OnigoRole.RUNNER)

        //clear inventory
        for (player in joinPlayer) {
          player.useInventory {
            clear()
            addItem(ItemStack(Material.BREAD, 64))
          }
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
                player.useInventory {
                  helmet = ItemStack(Material.GOLDEN_HELMET)
                }
              }
              //cancel limiter
              limiter!!.cancel()
              //limiter
              limiter1 = GameLimiter(plugin, stageData, this@OnigoGame, playerManager)
              //counter
              counter!!.cancel()
              gameCounter = GameCounter(bordName, name, this@OnigoGame, playerManager)
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

  override fun end() {
    val joinPlayer = playerManager.getAll(this)
    val oniPlayer = playerManager.getAll(this, role = OnigoRole.ONI)
    val runPlayer = playerManager.getAll(this, role = OnigoRole.RUNNER)

    //clear inventory
    for (player in joinPlayer) {
      player.useInventory {
        clear()
      }
    }
    sendEndMessage()
    //teleport players
    joinPlayer.forEach {
      it.teleport(
        Location(
          plugin.server.getWorld(d.endWorld!!),
          d.endLoc[0] + 0.5,
          d.endLoc[1] + 0.5,
          d.endLoc[2] + 0.5
        )
      )
    }
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
    playerManager.removeAll(this)
    stageData = null
    limiter = null
    limiter1 = null
    counter = null
    gameCounter = null
    runnable = null
    runnable1 = null
  }

}