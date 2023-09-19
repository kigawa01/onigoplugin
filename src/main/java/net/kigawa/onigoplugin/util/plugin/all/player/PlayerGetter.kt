package net.kigawa.onigoplugin.util.plugin.all.player

import net.kigawa.kutil.unitapi.annotation.Kunit
import net.kigawa.onigoplugin.OnigoPlugin
import org.bukkit.Location
import org.bukkit.entity.Player

@Kunit
class PlayerGetter(var plugin: OnigoPlugin) {
  fun room(world: String, x: Int, y: Int, z: Int, x1: Int, y1: Int, z1: Int): MutableList<Player> {
    var sx: Int
    var bx: Int
    var sy: Int
    var by: Int
    var sz: Int
    var bz: Int
    if (x > x1) {
      sx = x1
      bx = x
    } else {
      sx = x
      bx = x1
    }
    if (y > y1) {
      sy = y1
      by = y
    } else {
      sy = y
      by = y1
    }
    if (z > z1) {
      sz = z1
      bz = z
    } else {
      sz = z
      bz = z1
    }
    if (sx < 0) sx--
    if (sy < 0) sy--
    if (sz < 0) sz--
    if (bx > 0) bx++
    if (by > 0) by++
    if (bz > 0) bz++
    val world1 = plugin.server.getWorld(world)
    var location: Location
    val allPlayers = world1!!.players
    plugin.logger("all player " + allPlayers.size)
    plugin.logger("sx $sx")
    plugin.logger("bx $bx")
    val inRoom: MutableList<Player> = ArrayList()
    var player: Player
    var isIn: Boolean
    for (i in allPlayers.indices) {
      isIn = false
      player = allPlayers[i]
      plugin.logger(player.name)
      location = player.location
      plugin.logger("x" + location.x)
      plugin.logger((location.x >= sx).toString() + (location.y >= sy) + (location.z >= sz))
      if (location.x >= sx && location.y >= sy && location.z >= sz) {
        plugin.logger("check big")
        isIn = location.x <= bx && location.y <= by && location.z <= bz
      }
      if (isIn) {
        inRoom.add(player)
      }
    }
    plugin.logger("in room " + inRoom.size)
    return inRoom
  }
}
