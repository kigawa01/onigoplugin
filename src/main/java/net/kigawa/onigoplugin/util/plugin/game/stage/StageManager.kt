package net.kigawa.onigoplugin.util.plugin.game.stage

import net.kigawa.kutil.kutil.list.contains
import net.kigawa.kutil.unitapi.annotation.Kunit
import net.kigawa.onigoplugin.OnigoPlugin
import net.kigawa.onigoplugin.util.plugin.all.recorder.Recorder
import org.bukkit.command.CommandSender
import java.io.File
import java.util.*

@Kunit
class StageManager(var plugin: OnigoPlugin, private val recorder: Recorder) {
  val allStage: MutableList<StageData> = ArrayList()
  private var canUse: MutableList<StageData> = ArrayList()
  private var notUse: MutableList<StageData> = ArrayList()

  init {
    val folder = File(plugin.dataFolder, "stage")
    folder.mkdir()
    folder.list()?.forEach {
      plugin.logger(it)
      val data = recorder.load(StageData::class.java, "stage", it.substring(0, it.length - 4)) as StageData
      canUse.add(data)
      allStage.add(data)
    }
  }

  fun setStartLoc(name: String, x: Int, y: Int, z: Int, sender: CommandSender) {
    val stageData = getStage(name, sender)
    if (stageData != null) {
      val i = stageData.getStartLoc()
      i[0] = x
      i[1] = y
      i[2] = z
      //logger
      plugin.logger("stage manager startLoc " + stageData.getStartLoc()[0])
      plugin.logger("length " + stageData.getStartLoc().size)
      recorder.save(stageData, "stage")
    }
  }

  fun setStage2(name: String, world: String?, x: Int, y: Int, z: Int, sender: CommandSender) {
    val stageData = getStage(name, sender)
    if (stageData != null) {
      stageData.setStageWorld(world)
      val i = stageData.getStageLoc()
      i[3] = x
      i[4] = y
      i[5] = z
      recorder.save(stageData, "stage")
    }
  }

  fun setStage1(name: String, world: String?, x: Int, y: Int, z: Int, sender: CommandSender) {
    val stageData = getStage(name, sender)
    if (stageData != null) {
      stageData.setStageWorld(world)
      val i = stageData.getStageLoc()
      i[0] = x
      i[1] = y
      i[2] = z
      recorder.save(stageData, "stage")
    }
  }

  fun getStage(name: String, sender: CommandSender): StageData? {
    val stageData: StageData? = allStage.firstOrNull { it.name == name }
    stageData ?: sender.sendMessage("$name is not exit")
    return stageData
  }

  fun setStage(name: String, sender: CommandSender) {
    //check can use this name
    if (getStage(name) == null) {
      //create Stage
      val stageData = StageData()
      stageData.name = name
      //put in list
      canUse.add(stageData)
      allStage.add(stageData)
      //save
      recorder.save(stageData, "stage")
      //send message
      sender.sendMessage("create $name")
    } else {
      sender.sendMessage("this name can't use")
    }
  }

  fun getStage(name: String?): StageData? {
    var stageData: StageData? = null
    if (name != null) {
      stageData = allStage.firstOrNull { it.name == name }
    }
    if (name == null) {
      stageData = randomStage
    }
    return stageData
  }

  val randomStage: StageData?
    get() {
      var stageData: StageData? = null
      if (canUse.size > 0) {
        val random = Random()
        val randomNumber = random.nextInt(canUse.size)
        stageData = canUse[randomNumber]
        notUse.add(stageData)
        canUse.removeAt(randomNumber)
      }
      return stageData
    }

  fun returnStage(stageData: StageData) {
    if (notUse.contains { it.name == stageData.name }) {
      canUse.add(stageData)
      notUse.removeIf { it.name == stageData.name }
    }
  }
}
