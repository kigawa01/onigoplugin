package net.kigawa.onigoplugin.command

import dev.jorel.commandapi.arguments.Argument
import dev.jorel.commandapi.arguments.LiteralArgument
import dev.jorel.commandapi.arguments.LocationArgument
import dev.jorel.commandapi.arguments.LocationType
import dev.jorel.commandapi.executors.CommandArguments
import dev.jorel.commandapi.executors.CommandExecutor
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import net.kigawa.onigoplugin.OnigoPlugin
import net.kigawa.onigoplugin.util.command.ArgumentBase
import net.kigawa.onigoplugin.util.command.CustomArgs
import net.kigawa.onigoplugin.util.command.SubCommand
import net.kigawa.onigoplugin.util.plugin.all.recorder.Recorder
import net.kigawa.onigoplugin.util.plugin.game.stage.StageData
import org.bukkit.Location
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class OnigoStage : ArgumentBase("stage", CustomArgs.stage("stage")) {
  private val recorder = OnigoPlugin.container.getUnit(Recorder::class.java)

  @SubCommand
  fun stageLoc(): Argument<*> =
    LiteralArgument("stage-room").then(CustomArgs.choice("loc point", "start-loc", "end-loc").then(LocationArgument("location", LocationType.BLOCK_POSITION).executesPlayer(PlayerCommandExecutor { player: Player, commandArguments: CommandArguments ->
      val stage = commandArguments.get("stage") as StageData
      val location = commandArguments.get("location") as Location
      val choice = commandArguments.get("loc point") as String

      if (choice == "start-loc") {
        stage.stageWorld = player.world.name
        stage.setStageStartLoc(location)
        recorder.save(stage, "stage")

        player.sendMessage("start point of stage room is set")
        return@PlayerCommandExecutor
      }
      if (choice == "end-loc") {
        stage.setStageEndLoc(location)
        recorder.save(stage, "stage")

        player.sendMessage("end point of stage room is set")
        return@PlayerCommandExecutor
      }
      player.sendMessage("$choice is not allowed")
    })))

  @SubCommand
  fun startLoc(): Argument<*> =
    LiteralArgument("start-loc").then(LocationArgument("location", LocationType.BLOCK_POSITION).executes(CommandExecutor { sender: CommandSender, args: CommandArguments ->
      val stage = args.get("stage") as StageData
      val location = args.get("location") as Location

      stage.setStartLoc(location)
      recorder.save(stage, "stage")

      sender.sendMessage("stage loc is set")
    }))
}