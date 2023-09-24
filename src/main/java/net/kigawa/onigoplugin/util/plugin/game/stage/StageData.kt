package net.kigawa.onigoplugin.util.plugin.game.stage

import net.kigawa.onigoplugin.util.plugin.all.recorder.RecorderData
import org.bukkit.Location

class StageData(name: String = "") : RecorderData(name) {
  var stageWorld: String? = null
  var startLoc = IntArray(3)
  var stageLoc = IntArray(6)
  fun setStageStartLoc(location: Location) {
    stageLoc[0] = location.blockX
    stageLoc[1] = location.blockY
    stageLoc[2] = location.blockZ
  }

  fun setStageEndLoc(location: Location) {
    stageLoc[3] = location.blockX
    stageLoc[4] = location.blockY
    stageLoc[5] = location.blockZ
  }

  fun setStartLoc(location: Location) {
    startLoc[0] = location.blockX
    startLoc[1] = location.blockY
    startLoc[2] = location.blockZ
  }
}
