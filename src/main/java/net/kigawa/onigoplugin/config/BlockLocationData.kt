package net.kigawa.onigoplugin.config

import org.bukkit.Bukkit
import org.bukkit.Location

data class BlockLocationData(
  val world: String = "",
  val x: Int = 0,
  val y: Int = 0,
  val z: Int = 0,
) {
  fun toLocation(): Location? {
    val world = Bukkit.getWorld(world) ?: return null
    return Location(world, x.toDouble(), y.toDouble(), z.toDouble())
  }
}