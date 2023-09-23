package net.kigawa.onigoplugin.util.plugin.all.message

import net.kigawa.onigoplugin.OnigoPlugin
import net.kigawa.onigoplugin.player.OnigoPlayer
import net.kigawa.onigoplugin.role.Role
import net.kigawa.onigoplugin.util.plugin.game.onigo.Game
import org.bukkit.scheduler.BukkitRunnable

class Messenger(var plugin: OnigoPlugin) {
  fun <
      ROLE : Role<ROLE, GAME>,
      GAME : Game<ROLE, GAME>
      > sendTitle(players: List<OnigoPlayer<ROLE, GAME>>, title: String?, subTitle: String?) {
    for (player in players) {
      player.sendTitle(title!!, subTitle!!, 10, 20, 10)
    }
  }

  fun <
      ROLE : Role<ROLE, GAME>,
      GAME : Game<ROLE, GAME>
      > sendTitle(players: List<OnigoPlayer<ROLE, GAME>>, title: String?) {
    for (player in players) {
      player.sendTitle(title!!, "", 10, 20, 10)
    }
  }

  fun <
      ROLE : Role<ROLE, GAME>,
      GAME : Game<ROLE, GAME>
      > sendMessage(players: List<OnigoPlayer<ROLE, GAME>>, message: String?) {
    for (player in players) {
      player.sendMessage(message!!)
    }
  }

  fun <
      ROLE : Role<ROLE, GAME>,
      GAME : Game<ROLE, GAME>
      > sendTitleLater(players: List<OnigoPlayer<ROLE, GAME>>, title: String?, delay: Long?) {
    object : BukkitRunnable() {
      override fun run() {
        sendTitle(players, title)
      }
    }.runTaskLater(plugin, delay!!)
  }
}
