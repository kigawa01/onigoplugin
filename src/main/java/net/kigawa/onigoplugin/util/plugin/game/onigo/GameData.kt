package net.kigawa.onigoplugin.util.plugin.game.onigo

import net.kigawa.onigoplugin.util.plugin.all.recorder.RecorderData

open class GameData(name: String = "") : RecorderData(name) {

  var endLoc = IntArray(3)

  var endWorld: String? = null

  var gameTime = 0

  var waitTime = 0

  var waitRoomWorld: String? = null

  var oniCount = 0

  var waitRoom = IntArray(6)

  var gameType: String? = null

  var oniWait = IntArray(6)

  var oniWaitWorld: String? = null
}
