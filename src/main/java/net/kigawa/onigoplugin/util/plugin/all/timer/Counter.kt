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
import org.bukkit.scoreboard.Scoreboard

open class Counter(bordName: String?, bordID: String?, var plugin: OnigoPlugin) : BukkitRunnable() {
  var count = 0
  var bord: Scoreboard
  var objective: Objective
  var score: Score? = null
  var titleCount = 0
  var players: List<OnigoPlayer<OnigoRole, OnigoGame>>? = null
  var lastMessage: String? = null
  var countdownColor: ChatColor? = null
  var unit: String? = null
  var oneMinCounter: Counter? = null

  init {
    bord = Bukkit.getScoreboardManager()!!.newScoreboard
    objective = bord.registerNewObjective(bordID!!, "dummy", bordName!!)
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
      player.setScoreboard(bord)
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
      player.setScoreboard(bord)
    }
    unit = "min"
    runTaskTimer(plugin, delay!!, (20 * 60).toLong())
  }

  fun onRun() {}
  fun end() {
    //reset
    bord.resetScores("時間(分)")
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
        bord.resetScores("時間(秒)")
        end()
      }
    } else {
      if (unit == "min") {
        if (count == 1) {
          plugin.messenger!!.sendTitle(players!!, countdownColor.toString() + count.toString() + "分", "")
          bord.resetScores("時間(分)")
          //reset
          bord.resetScores("時間(分)")
          if (oneMinCounter != null) oneMinCounter!!.end()
          for (player in players!!) {
            player.usePlayer {
              scoreboard = scoreboard
            }
          }
          //create new counter
          oneMinCounter = Counter(objective.displayName, objective.name, plugin)
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
