package net.kigawa.onigoplugin.util.plugin.all.timer

import net.kigawa.onigoplugin.OnigoPlugin
import net.kigawa.onigoplugin.game.OnigoGame
import net.kigawa.onigoplugin.player.OnigoPlayer
import net.kigawa.onigoplugin.role.OnigoRole
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Objective
import org.bukkit.scoreboard.Score

open class Counter(
  bordName: String?,
  bordID: String?,
  var plugin: OnigoPlugin,
  private val game: OnigoGame
) : BukkitRunnable() {
  private var count: Int = 0
  private var objective: Objective
  private var score: Score? = null
  private var titleCount = 0
  private var players: List<OnigoPlayer<OnigoRole, OnigoGame>>? = null
  private var lastMessage: String? = null
  private var countdownColor: ChatColor? = null
  var unit: String? = null
  private var oneMinCounter: Counter? = null

  init {
    objective = bordID?.let { game.bord.getObjective(it) }
      ?: game.bord.registerNewObjective(bordID!!, "dummy", bordName!!)
  }

  fun startSec(
    delay: Long?, count: Int, titleCount: Int, players: List<OnigoPlayer<OnigoRole, OnigoGame>>?, lastMessage: String?,
    countdownColor: ChatColor?
  ) {
    this.count = count
    objective.displaySlot = DisplaySlot.SIDEBAR
    score = objective.getScore("時間(秒)")
    this.titleCount = titleCount
    this.players = players
    this.lastMessage = lastMessage
    this.countdownColor = countdownColor
    for (player in players!!) {
      player.setScoreboard(game.bord)
    }
    unit = "sec"
    runTaskTimer(plugin, delay!!, 20)
  }

  fun startMin(
    delay: Long?, count: Int, titleCount: Int, players: List<OnigoPlayer<OnigoRole, OnigoGame>>, lastMessage: String?,
    countdownColor: ChatColor?
  ) {
    this.count = count
    objective.displaySlot = DisplaySlot.SIDEBAR
    score = objective.getScore("時間(分)")
    this.titleCount = titleCount
    this.players = players
    this.lastMessage = lastMessage
    this.countdownColor = countdownColor
    for (player in players) {
      player.setScoreboard(game.bord)
    }
    unit = "min"
    runTaskTimer(plugin, delay!!, (20 * 60).toLong())
  }

  fun onRun() {}
  fun end() {
    //reset
    game.bord.resetScores("時間(分)")
    if (oneMinCounter != null) oneMinCounter!!.end()
    for (player in players!!) {
      player.setScoreboard(Bukkit.getScoreboardManager()!!.mainScoreboard)
    }
    cancel()
  }

  override fun run() {
    onRun()
    plugin.logger("run")
    score!!.score = count
    if (unit == "sec") {
      //count down
      if (count <= titleCount) {
        plugin.messenger!!.sendTitle(players!!, countdownColor.toString() + count.toString(), "")
      }
      //send message
      if (count == 0) {
        sendLastMessage()
        game.bord.resetScores("時間(秒)")
        end()
      }
    } else {
      if (unit == "min") {
        if (count == 1) {
          plugin.messenger!!.sendTitle(players!!, countdownColor.toString() + count.toString() + "分", "")
          game.bord.resetScores("時間(分)")
          //reset
          game.bord.resetScores("時間(分)")
          if (oneMinCounter != null) oneMinCounter!!.end()
          for (player in players!!) {
            player.usePlayer {
              scoreboard = scoreboard
            }
          }
          //create new counter
          oneMinCounter = Counter(objective.displayName, objective.name, plugin, game)
          oneMinCounter!!.startSec(0L, 60, titleCount, players, lastMessage, countdownColor)
          cancel()
        }
      }
    }
    count--
  }

  open fun sendLastMessage() {
    plugin.messenger!!.sendTitle(players!!, lastMessage, "")
  }
}
